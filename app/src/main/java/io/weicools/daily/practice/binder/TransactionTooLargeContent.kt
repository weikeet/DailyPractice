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

package io.weicools.daily.practice.binder

import android.graphics.BitmapFactory
import android.util.Log
import io.weicools.daily.practice.R
import io.weicools.daily.practice.ui.main.data.ModuleContent
import io.weicools.daily.practice.ui.main.data.ModuleFunction

/**
 * @author weiwei
 * @date 2021.11.27
 */
class TransactionTooLargeContent : ModuleContent() {
  override fun setupFunction(function: ModuleFunction) {
    function.apply {
      title = "TransactionTooLarge"
      description = "传输超大图片 bitmap"
      clickAction = {
        val b = BitmapFactory.decodeResource(it.resources, R.drawable.px_94280767_p0)
        // val b = BitmapFactory.decodeResource(it.resources, R.drawable.xxxxx)
        Log.d("TAG", "setupFunction: b size=${b.byteCount}")
        TransactionTooLargeActivity.start(it, b)
      }
    }
  }
}