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

import android.util.Log;

/**
 * @author weiwei
 * @date 2023.03.07
 */
public final class ActivityThreadHooker {
  private static final String TAG = "ActivityThreadHooker";

  private volatile static boolean hooked;

  /**
   * @param ignorePackages comma-separated list
   */
  public static void hook(final String ignorePackages) {
    if (hooked) {
      return;
    }

    try {
      final String pkgs = null == ignorePackages ? "" : ignorePackages.trim();
      final ActivityThreadCallback callback = new ActivityThreadCallback(pkgs.split("\\s*,\\s*"));
      hooked = callback.hook();
      if (!hooked) {
        Log.i(TAG, "Hook ActivityThread.mH.mCallback failure");
      } else {
        Log.i(TAG, "Hook ActivityThread.mH.mCallback success!");
      }
    } catch (final Throwable t) {
      Log.w(TAG, "Hook ActivityThread.mH.mCallback failure", t);
    }
  }
}
