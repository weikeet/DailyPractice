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

package com.weiwei.core.view

import android.view.View

/**
 * @author weiwei
 * @date 2023.02.21
 */
class ViewClickDelegate(
  private val clickInterval: Long = 500L,
  private val clickListener: View.OnClickListener
) : View.OnClickListener {
  private var lastClick: Long = 0

  override fun onClick(v: View) {
    if (System.currentTimeMillis() - lastClick > clickInterval) {
      lastClick = System.currentTimeMillis()
      clickListener.onClick(v)
    }
  }
}