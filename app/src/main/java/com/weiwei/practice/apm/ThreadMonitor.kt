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

package com.weiwei.practice.apm

import timber.log.Timber

/**
 * @author weiwei
 * @date 2023.03.26
 */
object ThreadMonitor {

  fun trackThreadCount() {
    // val threadGroup = Thread.currentThread().threadGroup ?: return
    // val threadCount = threadGroup.activeCount()
    // val threads = arrayOfNulls<Thread>(threadCount)
    // threadGroup.enumerate(threads)
    // for (thread in threads) {
    //   if (thread != null) {
    //     println("Thread: ${thread.name}")
    //   }
    // }

    val t = Thread.getAllStackTraces()

    Timber.d("Thread size: ${t.size}")
    for (thread in t.keys) {
      Timber.d("Thread name: ${thread.name}")
    }
  }
}