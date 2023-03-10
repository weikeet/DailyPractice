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

import android.content.Context
import android.graphics.Color
import android.widget.LinearLayout
import com.weiwei.fluentview.ui.gravityCenterVertical
import com.weiwei.fluentview.ui.unit.dp
import com.weiwei.fluentview.view.appcompat.textView
import com.weiwei.fluentview.view.linearParams
import com.weiwei.fluentview.view.matchParent
import com.weiwei.fluentview.view.wrapContent

/**
 * @author weiwei
 * @date 2023.03.10
 */
class ListDifferData1Layout(context: Context) : LinearLayout(context) {

  init {
    orientation = HORIZONTAL
    gravity = gravityCenterVertical
  }

  val tv = textView {
    layoutParams = linearParams(matchParent, wrapContent) {
      leftMargin = 16.dp
      rightMargin = 16.dp
    }
    setTextColor(Color.BLACK)
    textSize = 16f
  }
}