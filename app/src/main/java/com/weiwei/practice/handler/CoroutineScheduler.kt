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

package com.weiwei.practice.handler

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author weiwei
 * @date 2023.03.23
 */
class CoroutineScheduler {
  companion object {
    const val REFRESH_INTERVAL = 1500L
  }

  private val externalScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

  private var scheduledRefresh = false

  private var job: Job? = null

  fun startScheduleRefresh() {
    if (!scheduledRefresh) {
      scheduledRefresh = true
      job = externalScope.launch {
        // while (scheduledRefresh) {
        //     refreshData()
        //     delay(REFRESH_INTERVAL)
        // }
        while (true) {
          refreshData() // 调用了 cancel 还是会再执行一次
          delay(REFRESH_INTERVAL)
        }
      }
    }
  }

  private fun stopScheduleRefresh() {
    scheduledRefresh = false
    // externalScope.cancel()
    job?.cancel()
  }

  private var count = 0
  private fun refreshData() {
    count++
    Log.d("CoroutineScheduler", "refreshData: $count")

    if (count > 6) {
      stopScheduleRefresh()
    }
  }
}