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

package com.weiwei.practice.flow

import androidx.navigation.NavController
import com.weiwei.practice.R
import com.weiwei.main.ui.data.ModuleContent
import com.weiwei.main.ui.data.ModuleFunction

// Kotlin 异步 | Flow 应用场景及原理 https://juejin.cn/post/6989032238079803429
// Kotlin 异步 | Flow 限流的应用场景及原理 https://juejin.cn/post/6989782281191686180

/**
 * @author weiwei
 * @date 2022.12.10
 */
class FlowSampleContent(private val navController: NavController) : ModuleContent() {
  override fun setupFunction(function: ModuleFunction) {
    function.apply {
      title = "Kotlin-flow"
      description = ""
      clickAction = {
        navController.navigate(R.id.action_MainFragment_to_FlowSampleFragment)
      }
    }
  }
}