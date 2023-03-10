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

package com.weiwei.practice.differ

import androidx.navigation.NavController
import com.weiwei.main.ui.data.ModuleContent
import com.weiwei.main.ui.data.ModuleFunction
import com.weiwei.practice.R

// [更高效地刷新 RecyclerView | DiffUtil二次封装](https://juejin.cn/post/6882531923537707015)
// [wisdomtl/VarietyAdapter](https://github.com/wisdomtl/VarietyAdapter)

/**
 * @author weiwei
 * @date 2023.03.10
 */
class ListDifferContent(val navController: NavController) : ModuleContent() {
  override fun setupFunction(function: ModuleFunction) {
    function.apply {
      title = "List differ"
      clickAction = {
        navController.navigate(R.id.action_MainFragment_to_ListDifferFragment)
      }
    }
  }
}