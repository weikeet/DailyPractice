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

package com.weiwei.core.app

import android.app.NotificationChannel
import android.content.Context
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes

/**
 * @author weiwei
 * @date 2022.08.08
 */
class NotificationChannelTemplate(
  val id: String,
  val importance: Int,
  @StringRes val nameRes: Int,
  @StringRes var descriptionRes: Int? = null,
  private var group: String? = null,
  private var showBadge: Boolean? = null,
  private var sound: Pair<Uri, AudioAttributes>? = null,
  private val lightEnabled: Boolean? = null,
  private val lightColor: Int? = null,
  private val vibrationEnabled: Boolean? = null,
  private val vibrationPattern: LongArray? = null,
  private val bypassDnd: Boolean? = null,
  private val lockscreenVisibility: Int? = null,
  private val allowBubbles: Boolean? = null
) {
  @RequiresApi(Build.VERSION_CODES.O)
  fun create(context: Context): NotificationChannel {
    val channel = NotificationChannel(id, context.getString(nameRes), importance)

    descriptionRes?.let { channel.description = context.getString(it) }
    group?.let { channel.group = it }
    showBadge?.let { channel.setShowBadge(it) }
    sound?.let { channel.setSound(it.first, it.second) }
    lightEnabled?.let { channel.enableLights(it) }
    lightColor?.let { channel.lightColor = it }
    vibrationEnabled?.let { channel.enableVibration(vibrationEnabled) }
    vibrationPattern?.let { channel.vibrationPattern = it }
    bypassDnd?.let { channel.setBypassDnd(it) }
    lockscreenVisibility?.let { channel.lockscreenVisibility = it }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
      allowBubbles?.let { channel.setAllowBubbles(it) }
    }
    return channel
  }
}

object AppNotificationChannel {
  const val PRACTICE_APP = "PracticeApp"
}