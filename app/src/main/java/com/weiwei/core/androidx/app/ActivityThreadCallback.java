/*
 * Copyright (c) 2020 Weiwei
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

package com.weiwei.core.androidx.app;

import android.content.res.Resources;
import android.os.Build;
import android.os.DeadSystemException;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import com.weiwei.core.androidx.Intrinsics;
import com.weiwei.core.androidx.Reflection;
import com.weiwei.core.androidx.ThrowableHandler;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author weiwei
 * @date 2023.03.07
 */
final class ActivityThreadCallback implements Handler.Callback {
  private static final String TAG = "ActivityThreadCallback";

  private static final String LOADED_APK_GET_ASSETS = "android.app.LoadedApk.getAssets";

  private static final String ASSET_MANAGER_GET_RESOURCE_VALUE = "android.content.res.AssetManager.getResourceValue";

  private static final String[] SYSTEM_PACKAGE_PREFIXES = {
      "java.",
      "android.",
      "androidx.",
      "dalvik.",
      "com.android.",
  };

  private final Handler mHandler;

  private final Handler.Callback mDelegate;

  private final Set<String> mIgnorePackages;

  public ActivityThreadCallback(String[] ignorePackages) {
    final Set<String> packages = new HashSet<>(Arrays.asList(SYSTEM_PACKAGE_PREFIXES));
    for (final String pkg : ignorePackages) {
      if (null == pkg) {
        continue;
      }
      packages.add(pkg.endsWith(".") ? pkg : (pkg + "."));
    }
    packages.add(getClass().getPackage().getName() + ".");
    this.mIgnorePackages = Collections.unmodifiableSet(packages);
    this.mHandler = getHandler(getActivityThread());
    this.mDelegate = Reflection.getFieldValue(this.mHandler, "mCallback");
  }

  private static Object getActivityThread() {
    Object thread = null;

    try {
      thread = android.app.ActivityThread.currentActivityThread();
    } catch (Throwable t1) {
      Log.w(TAG, "ActivityThread.currentActivityThread() is inaccessible", t1);
      try {
        thread = Reflection.getStaticFieldValue(android.app.ActivityThread.class, "sCurrentActivityThread");
      } catch (final Throwable t2) {
        Log.w(TAG, "ActivityThread.sCurrentActivityThread is inaccessible", t2);
      }
    }

    if (null == thread) {
      Log.w(TAG, "ActivityThread instance is inaccessible");
    }

    return thread;
  }

  private static Handler getHandler(final Object thread) {
    Handler handler;

    if (null == thread) {
      return null;
    }

    if (null != (handler = Reflection.getFieldValue(thread, "mH"))) {
      return handler;
    }

    if (null != (handler = Reflection.invokeMethod(thread, "getHandler"))) {
      return handler;
    }

    try {
      if (null != (handler = Reflection.getFieldValue(thread, Class.forName("android.app.ActivityThread$H")))) {
        return handler;
      }
    } catch (final ClassNotFoundException e) {
      Log.w(TAG, "Main thread handler is inaccessible", e);
    }

    return null;
  }

  @Override
  public boolean handleMessage(@NonNull Message msg) {
    try {
      if (null != mDelegate) {
        return this.mDelegate.handleMessage(msg);
      }

      if (null != this.mHandler) {
        this.mHandler.handleMessage(msg);
      }
    } catch (NullPointerException e) {
      if (ThrowableHandler.hasStackTraceElement(e, ASSET_MANAGER_GET_RESOURCE_VALUE, LOADED_APK_GET_ASSETS)) {
        return abort(e);
      }
      rethrowIfCausedByUser(e);
    } catch (SecurityException
             | IllegalArgumentException
             | AndroidRuntimeException
             | Resources.NotFoundException
             | WindowManager.BadTokenException e) {
      rethrowIfCausedByUser(e);
    } catch (final RuntimeException e) {
      final Throwable cause = e.getCause();
      if (((Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) && ThrowableHandler.isCausedBy(cause, DeadSystemException.class))
          || (ThrowableHandler.isCausedBy(cause, NullPointerException.class) && ThrowableHandler.hasStackTraceElement(e, LOADED_APK_GET_ASSETS))) {
        // usually occurred after app upgrade installation, it seems like a system bug
        return abort(e);
      }
      rethrowIfCausedByUser(e);
    } catch (Error e) {
      rethrowIfCausedByUser(e);
      return abort(e);
    }
    return true;
  }

  private void rethrowIfCausedByUser(final RuntimeException e) {
    if (isCausedByUser(e)) {
      for (Throwable cause = e; null != cause; cause = cause.getCause()) {
        Intrinsics.sanitizeStackTrace(cause, getClass());
      }
      throw e;
    }
  }

  private void rethrowIfCausedByUser(final Error e) {
    if (isCausedByUser(e)) {
      for (Throwable cause = e; null != cause; cause = cause.getCause()) {
        Intrinsics.sanitizeStackTrace(cause, getClass());
      }
      throw e;
    }
  }

  private boolean isCausedByUser(final Throwable t) {
    if (null == t) {
      return false;
    }

    for (Throwable cause = t; null != cause; cause = cause.getCause()) {
      for (final StackTraceElement element : cause.getStackTrace()) {
        if (isUserStackTrace(element)) {
          return true;
        }
      }
    }

    return false;
  }

  private boolean isUserStackTrace(final StackTraceElement element) {
    final String name = element.getClassName();
    for (final String prefix : this.mIgnorePackages) {
      if (name.startsWith(prefix)) {
        return false;
      }
    }
    return true;
  }

  private static boolean abort(final Throwable t) {
    final int pid = Process.myPid();
    final String msg = "Process " + pid + " is going to be killed";

    if (null != t) {
      Log.w(TAG, msg, t);
    } else {
      Log.w(TAG, msg);
    }

    Process.killProcess(pid);
    System.exit(10);
    return true;
  }

  /**
   * Hook ActivityThread.mH.mCallback
   */
  public boolean hook() {
    if (null != this.mDelegate) {
      Log.w(TAG, "ActivityThread.mH.mCallback has already been hooked by " + this.mDelegate);
    }
    return Reflection.setFieldValue(this.mHandler, "mCallback", this);
  }

}
