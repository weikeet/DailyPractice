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

package com.weiwei.main.app

import android.util.Log
import timber.log.Timber

/**
 * @author weiwei
 * @date 2023.03.26
 */
class CrashReportingTree: Timber.Tree() {
  override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
    if (priority == Log.VERBOSE || priority == Log.DEBUG) {
      return;
    }

    FakeCrashLibrary.log(priority, tag, message);

    if (t != null) {
      if (priority == Log.ERROR) {
        FakeCrashLibrary.logError(t);
      } else if (priority == Log.WARN) {
        FakeCrashLibrary.logWarning(t);
      }
    }
  }
}

private object FakeCrashLibrary {
  fun log(priority: Int, tag: String?, message: String) {
    // TODO add log entry to circular buffer.
  }

  fun logWarning(t: Throwable) {
    // TODO report non-fatal warning.
  }

  fun logError(t: Throwable) {
    // TODO report non-fatal error.
  }
}