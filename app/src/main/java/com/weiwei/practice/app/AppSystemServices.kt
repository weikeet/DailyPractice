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

package com.weiwei.practice.app

import android.content.ClipboardManager
import android.content.pm.PackageManager
import android.os.PowerManager
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import java.util.concurrent.Executor

/**
 * @author weiwei
 * @date 2022.08.08
 */

val mainExecutor: Executor by lazy {
  ContextCompat.getMainExecutor(mainContext)
}

val packageManager: PackageManager by lazy {
  mainContext.packageManager
}

val clipboardManager: ClipboardManager by lazy {
  ContextCompat.getSystemService(mainContext, ClipboardManager::class.java)!!
}

val notificationManager: NotificationManagerCompat by lazy {
  NotificationManagerCompat.from(mainContext)
}

val powerManager: PowerManager by lazy {
  ContextCompat.getSystemService(mainContext, PowerManager::class.java)!!
}