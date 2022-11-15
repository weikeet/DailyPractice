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

package com.weiwei.practice.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.weiwei.practice.R
import com.weiwei.core.app.AppNotificationChannel

/**
 * @author weiwei
 * @date 2022.08.08
 */
class TestForegroundService : Service() {

  override fun onCreate() {
    super.onCreate()
    Log.d("TestForegroundService", "onCreate: ")

    val notification = NotificationCompat.Builder(applicationContext, AppNotificationChannel.PRACTICE_APP)
      .setSmallIcon(R.drawable.ic_dashboard_black_24dp)
      .setContentText("Test foreground service")
      .build()
    startForeground(1, notification)
  }

  override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
    Log.d("TestForegroundService", "onStartCommand: startId=$startId")

    val notification = NotificationCompat.Builder(applicationContext, AppNotificationChannel.PRACTICE_APP)
      .setSmallIcon(R.drawable.ic_dashboard_black_24dp)
      .setContentText("Test foreground service")
      .build()
    startForeground(1, notification)

    return super.onStartCommand(intent, flags, startId)
  }

  override fun onBind(intent: Intent?): IBinder? {
    Log.d("TestForegroundService", "onBind: ")

    return null
  }
}