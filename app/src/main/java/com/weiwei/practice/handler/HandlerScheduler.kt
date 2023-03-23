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

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log

/**
 * @author weiwei
 * @date 2023.03.23
 */
class HandlerScheduler {
  companion object {
    const val REFRESH_INTERVAL = 1500L
  }

  private val refreshRunnable = object : Runnable {
    override fun run() {
      if (scheduledRefresh) {
        refreshData()
        handler.postDelayed(this, REFRESH_INTERVAL)
      }
    }
  }

  private val handler = object : Handler(Looper.getMainLooper()) {
    override fun handleMessage(msg: Message) {
      when (msg.what) {
        1 -> {
          if (scheduledRefresh) {
            refreshData()
            sendEmptyMessageDelayed(1, REFRESH_INTERVAL)
          }
        }
      }
    }
  }

  private var scheduledRefresh = false

  fun startScheduleRefresh() {
    if (!scheduledRefresh) {
      scheduledRefresh = true
      // handler.post(refreshRunnable)
      handler.sendEmptyMessage(1)
    }
  }

  private fun stopScheduleRefresh() {
    Log.d("WorkoutDownloader", "stopScheduleRefresh: $scheduledRefresh")
    if (scheduledRefresh) {
      scheduledRefresh = false
      // handler.removeCallbacks(refreshRunnable) // remove invalid?
      // handler.removeMessages(1) // remove invalid?
    }
  }

  private var count = 0

  private fun refreshData() {
    count++
    Log.d("WorkoutDownloader", "refreshData: $count")

    if (count > 5) {
      stopScheduleRefresh()
    }
  }

}