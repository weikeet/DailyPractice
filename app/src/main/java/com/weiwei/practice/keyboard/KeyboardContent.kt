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

package com.weiwei.practice.keyboard

import android.content.Intent
import com.weiwei.practice.ui.main.data.ModuleContent
import com.weiwei.practice.ui.main.data.ModuleFunction

/**
 * @author weiwei
 * @date 2022.08.14
 */
class KeyboardContent : ModuleContent() {
  override fun setupFunction(function: ModuleFunction) {
    function.apply {
      title = "Lifecycle"
      description = "测试 keyboard"
      clickAction = {
        it.startActivity(Intent(it, KeyboardActivity::class.java))
      }
    }
  }
}