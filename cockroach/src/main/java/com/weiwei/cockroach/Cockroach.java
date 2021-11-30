/*
 * Copyright (c) 2020 Weicools
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *    https://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.weiwei.cockroach;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import com.weiwei.cockroach.compat.ActivityKiller;
import com.weiwei.cockroach.compat.ActivityKillerV15V20;
import com.weiwei.cockroach.compat.ActivityKillerV21V23;
import com.weiwei.cockroach.compat.ActivityKillerV24V25;
import com.weiwei.cockroach.compat.ActivityKillerV26;
import com.weiwei.cockroach.compat.ActivityKillerV28;
import java.lang.reflect.Field;
import me.weishu.reflection.Reflection;

public final class Cockroach {
  private static ActivityKiller sActivityKiller = new ActivityKillerV15V20();
  private static ExceptionHandler sExceptionHandler;

  private static boolean sInstalled = false; // 标记位，避免重复安装卸载
  private static boolean sIsSafeMode;

  private Cockroach() {}

  public static void install(Context context, ExceptionHandler exceptionHandler) {
    if (sInstalled) {
      return;
    }

    try {
      //解除 android P 反射限制
      Reflection.unseal(context);
    } catch (Throwable t) {
      t.printStackTrace();
    }

    sInstalled = true;
    sExceptionHandler = exceptionHandler;

    initActivityKiller();

    Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
      @Override public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        if (sExceptionHandler != null) {
          sExceptionHandler.uncaughtExceptionHappened(t, e);
        }
        if (t == Looper.getMainLooper().getThread()) {
          isChoreographerException(e);
          safeMode();
        }
      }
    });
  }

  /**
   * 替换ActivityThread.mH.mCallback，实现拦截Activity生命周期，直接忽略生命周期的异常的话会导致黑屏，目前
   * 会调用ActivityManager的finishActivity结束掉生命周期抛出异常的Activity
   */
  private static void initActivityKiller() {
    // 各版本 android 的 ActivityManager 获取方式，finishActivity 的参数，token(binder对象) 的获取不一样
    if (Build.VERSION.SDK_INT >= 28) {
      sActivityKiller = new ActivityKillerV28();
    } else if (Build.VERSION.SDK_INT >= 26) {
      sActivityKiller = new ActivityKillerV26();
    } else if (Build.VERSION.SDK_INT == 24 || Build.VERSION.SDK_INT == 25) {
      sActivityKiller = new ActivityKillerV24V25();
    } else if (Build.VERSION.SDK_INT == 21 || Build.VERSION.SDK_INT == 22 || Build.VERSION.SDK_INT == 23) {
      sActivityKiller = new ActivityKillerV21V23();
    } else {
      sActivityKiller = new ActivityKillerV15V20();
    }

    try {
      hookmH();
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }

  private static final int LAUNCH_ACTIVITY = 100;
  private static final int PAUSE_ACTIVITY = 101;
  private static final int PAUSE_ACTIVITY_FINISHING = 102;
  private static final int STOP_ACTIVITY_HIDE = 104;
  private static final int RESUME_ACTIVITY = 107;
  private static final int DESTROY_ACTIVITY = 109;

  public static final int EXECUTE_TRANSACTION = 159;

  private static void hookmH() throws Exception {

    @SuppressLint("PrivateApi")
    Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");

    @SuppressLint("DiscouragedPrivateApi")
    Object activityThread = activityThreadClass.getDeclaredMethod("currentActivityThread").invoke(null);

    @SuppressLint("DiscouragedPrivateApi") final Field mhField = activityThreadClass.getDeclaredField("mH");
    mhField.setAccessible(true);

    final Handler mhHandler = (Handler) mhField.get(activityThread);

    @SuppressLint("DiscouragedPrivateApi")
    Field callbackField = Handler.class.getDeclaredField("mCallback");
    callbackField.setAccessible(true);

    callbackField.set(mhHandler, new Handler.Callback() {
      @Override public boolean handleMessage(@NonNull Message msg) {
        if (Build.VERSION.SDK_INT >= 28) {
          //android P 生命周期全部走这
          if (msg.what == EXECUTE_TRANSACTION) {
            try {
              mhHandler.handleMessage(msg);
            } catch (Throwable throwable) {
              sActivityKiller.finishLaunchActivity(msg);
              notifyException(throwable);
            }
            return true;
          }
          return false;
        }

        switch (msg.what) {
          case LAUNCH_ACTIVITY:
            // startActivity--> activity.attach  activity.onCreate  r.activity!=null  activity.onStart  activity.onResume
            try {
              mhHandler.handleMessage(msg);
            } catch (Throwable throwable) {
              sActivityKiller.finishLaunchActivity(msg);
              notifyException(throwable);
            }
            return true;

          case RESUME_ACTIVITY://回到activity onRestart onStart onResume
            try {
              mhHandler.handleMessage(msg);
            } catch (Throwable throwable) {
              sActivityKiller.finishResumeActivity(msg);
              notifyException(throwable);
            }
            return true;

          case PAUSE_ACTIVITY_FINISHING://按返回键 onPause
          case PAUSE_ACTIVITY://开启新页面时，旧页面执行 activity.onPause
            try {
              mhHandler.handleMessage(msg);
            } catch (Throwable throwable) {
              sActivityKiller.finishPauseActivity(msg);
              notifyException(throwable);
            }
            return true;

          case STOP_ACTIVITY_HIDE://开启新页面时，旧页面执行 activity.onStop
            try {
              mhHandler.handleMessage(msg);
            } catch (Throwable throwable) {
              sActivityKiller.finishStopActivity(msg);
              notifyException(throwable);
            }
            return true;

          case DESTROY_ACTIVITY:// 关闭activity onStop  onDestroy
            try {
              mhHandler.handleMessage(msg);
            } catch (Throwable throwable) {
              notifyException(throwable);
            }
            return true;

          default:
            break;
        }

        return false;
      }
    });
  }

  private static void notifyException(Throwable throwable) {
    if (sExceptionHandler == null) {
      return;
    }

    if (isSafeMode()) {
      sExceptionHandler.bandageExceptionHappened(throwable);
    } else {
      sExceptionHandler.uncaughtExceptionHappened(Looper.getMainLooper().getThread(), throwable);
      safeMode();
    }
  }

  public static boolean isSafeMode() {
    return sIsSafeMode;
  }

  private static void safeMode() {
    sIsSafeMode = true;
    if (sExceptionHandler != null) {
      sExceptionHandler.enterSafeMode();
    }

    while (true) {
      try {
        Looper.loop();
      } catch (Throwable e) {
        isChoreographerException(e);
        if (sExceptionHandler != null) {
          sExceptionHandler.bandageExceptionHappened(e);
        }
      }
    }
  }

  /**
   * view measure layout draw时抛出异常会导致Choreographer挂掉
   *
   * 建议直接杀死app。以后的版本会只关闭黑屏的Activity
   */
  private static void isChoreographerException(Throwable e) {
    if (e == null || sExceptionHandler == null) {
      return;
    }
    StackTraceElement[] elements = e.getStackTrace();

    for (int i = elements.length - 1; i > -1; i--) {
      if (elements.length - i > 20) {
        return;
      }

      StackTraceElement element = elements[i];
      if ("android.view.Choreographer".equals(element.getClassName())
          && "Choreographer.java".equals(element.getFileName())
          && "doFrame".equals(element.getMethodName())) {
        sExceptionHandler.mayBeBlackScreen(e);
        return;
      }
    }
  }
}


