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

import android.content.Intent
import com.weiwei.core.app.mainContext
import com.weiwei.main.ui.data.ModuleContent
import com.weiwei.main.ui.data.ModuleFunction

/**
 * @author weiwei
 * @date 2022.08.08
 *
 * Notes: startForegroundService.md
 */
class ForegroundServiceContent : ModuleContent() {
  override fun setupFunction(function: ModuleFunction) {
    function.apply {
      title = "startForegroundService"
      description = "test startForegroundService"
      clickAction = {
        val serviceIntent = Intent(mainContext, TestForegroundService::class.java)
        // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        //   try {
        //     mainContext.startForegroundService(serviceIntent)
        //     // mainContext.stopService(serviceIntent) // Context.startForegroundService() did not then call Service.startForeground()
        //   } catch (e: Exception) {
        //   }
        // } else {
        //   mainContext.startService(serviceIntent)
        // }
        mainContext.startService(serviceIntent)
        // mainContext.stopService(serviceIntent)
      }
    }
  }
}