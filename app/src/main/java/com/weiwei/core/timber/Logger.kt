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

package com.weiwei.core.timber

import com.weiwei.practice.BuildConfig
import timber.log.Timber

/**
 * @author weiwei
 * @date 2023.03.29
 */
object Logger {

  val enableLog = BuildConfig.DEBUG

  inline fun i(tag: String? = null, t: Throwable? = null, message: () -> String) {
    if (enableLog) {
      if (tag == null) {
        Timber.i(t, message())
      } else {
        Timber.tag(tag).i(t, message())
      }
    }
  }

  inline fun v(tag: String? = null, t: Throwable? = null, message: () -> String) {
    if (enableLog) {
      if (tag == null) {
        Timber.v(t, message())
      } else {
        Timber.tag(tag).v(t, message())
      }
    }
  }

  inline fun w(tag: String? = null, t: Throwable? = null, message: () -> String) {
    if (enableLog) {
      if (tag == null) {
        Timber.w(t, message())
      } else {
        Timber.tag(tag).w(t, message())
      }
    }
  }

  inline fun d(tag: String? = null, t: Throwable? = null, message: () -> String) {
    if (enableLog) {
      if (tag == null) {
        Timber.d(t, message())
      } else {
        Timber.tag(tag).d(t, message())
      }
    }
  }

  inline fun e(tag: String? = null, t: Throwable? = null, message: () -> String) {
    if (enableLog) {
      if (tag == null) {
        Timber.e(t, message())
      } else {
        Timber.tag(tag).e(t, message())
      }
    }
  }

}