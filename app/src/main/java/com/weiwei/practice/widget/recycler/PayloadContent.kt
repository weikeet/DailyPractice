/*
 * Copyright (c) 2020 Weicools
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

package com.weiwei.practice.widget.recycler

import androidx.navigation.NavController
import com.weiwei.practice.R
import com.weiwei.main.ui.data.ModuleContent
import com.weiwei.main.ui.data.ModuleFunction

class PayloadContent(val findNavController: NavController) : ModuleContent() {
  override fun setupFunction(function: ModuleFunction) {
    function.apply {
      title = "Recycler Payload"
      description = "WaveView test"
      clickAction = {
        findNavController.navigate(R.id.action_mainFragment_to_recyclerPayloadFragment)
      }
    }
  }
}