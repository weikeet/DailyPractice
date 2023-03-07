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

package com.weiwei.core.androidx.finalizer;

import android.os.Looper;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author weiwei
 * @date 2023.03.07
 */
public final class FinalizerWatchdogDaemonKiller {
  private static final String TAG = "FinalizerWatchdogDaemonKiller";

  // 最大重试次数
  private static final int MAX_RETRY_TIMES = 10;

  // 线程休眠时间: ms
  private static final long THREAD_SLEEP_TIME = 5000;

  public static void kill() {
    new Thread(() -> {
      doKillOnWork();
    }, "FinalizerWatchdogDaemonKiller").start();
  }

  private static void doKillOnWork() {
    for (int retry = 0; retry < MAX_RETRY_TIMES && isFinalizerWatchdogDaemonExists(); retry++) {
      try {
        final Class<?> clazz = Class.forName("java.lang.Daemons$FinalizerWatchdogDaemon");
        final Field field = clazz.getDeclaredField("INSTANCE");
        field.setAccessible(true);
        final Object watchdog = field.get(null);

        try {
          final Field thread = clazz.getSuperclass().getDeclaredField("thread");
          thread.setAccessible(true);
          thread.set(watchdog, null);
        } catch (Throwable t) {
          Log.e(TAG, "Clearing reference of thread `FinalizerWatchdogDaemon` failed", t);

          try {
            final Method method = clazz.getSuperclass().getDeclaredMethod("stop");
            method.setAccessible(true);
            method.invoke(watchdog);
          } catch (final Throwable e) {
            Log.e(TAG, "Interrupting thread `FinalizerWatchdogDaemon` failed", e);
            break;
          }
        }

        try {
          Thread.sleep(THREAD_SLEEP_TIME);
        } catch (final InterruptedException ignore) {
        }
      } catch (Throwable t) {
        Log.e(TAG, "Killing thread `FinalizerWatchdogDaemon` failed", t);
        break;
      }
    }

    if (isFinalizerWatchdogDaemonExists()) {
      Log.e(TAG, "Killing thread `FinalizerWatchdogDaemon` failed");
    } else {
      Log.i(TAG, "Thread `FinalizerWatchdogDaemon` does not exist");
    }
  }

  private static boolean isFinalizerWatchdogDaemonExists() {
    try {
      for (final Thread t : getAllThreads()) {
        if (null != t && "FinalizerWatchdogDaemon".equals(t.getName())) {
          return true;
        }
      }
    } catch (Throwable ignore) {
    }
    return false;
  }

  private static Collection<Thread> getAllThreads() {
    try {
      final ThreadGroup root = Looper.getMainLooper().getThread().getThreadGroup().getParent();
      final Thread[] threads = new Thread[root.activeCount()];
      final int count = root.enumerate(threads);

      if (threads.length != count) {
        final Thread[] target = new Thread[count];
        System.arraycopy(threads, 0, target, 0, count);
        return Arrays.asList(target);
      } else {
        return Arrays.asList(threads);
      }
    } catch (Throwable t) {
      return Thread.getAllStackTraces().keySet();
    }
  }

}
