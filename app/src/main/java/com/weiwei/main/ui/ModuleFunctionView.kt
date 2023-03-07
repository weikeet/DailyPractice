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

package com.weiwei.main.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.weiwei.fluentview.ui.textColorResource
import com.weiwei.fluentview.ui.unit.dp
import com.weiwei.fluentview.view.appcompat.imageView
import com.weiwei.fluentview.view.appcompat.textView
import com.weiwei.fluentview.view.constraint.centerVerticalOf
import com.weiwei.fluentview.view.constraint.chainPacked
import com.weiwei.fluentview.view.constraint.constraintParams
import com.weiwei.fluentview.view.constraint.parentId
import com.weiwei.fluentview.view.wrapContent
import com.weiwei.practice.R
import com.weiwei.main.ui.data.ModuleFunction

/**
 * @author Weicools
 *
 * @date 2021.07.10
 */
class ModuleFunctionView(context: Context) : ConstraintLayout(context) {
  private val iconId = generateViewId()
  private val arrowId = generateViewId()
  private val titleId = generateViewId()
  private val descriptionId = generateViewId()

  private val iconView = imageView(iconId) {
    layoutParams = constraintParams(40.dp, 40.dp) {
      leftToLeft = parentId
      centerVerticalOf = parentId
      leftMargin = 8.dp
    }
  }

  val arrowView = imageView(arrowId) {
    layoutParams = constraintParams(40.dp, 40.dp) {
      rightToRight = parentId
      centerVerticalOf = parentId
      rightMargin = 8.dp
    }
  }

  private val titleView = textView(titleId) {
    layoutParams = constraintParams(0, wrapContent) {
      leftToRight = iconId
      rightToLeft = arrowId
      topToTop = parentId
      bottomToTop = descriptionId
      verticalChainStyle = chainPacked
      leftMargin = 8.dp
      rightMargin = 8.dp
    }
    textSize = 16f
    textColorResource = R.color.md_grey900
  }

  private val descriptionView = textView(descriptionId) {
    layoutParams = constraintParams(0, wrapContent) {
      leftToRight = iconId
      rightToLeft = arrowId
      topToBottom = titleId
      bottomToBottom = parentId
      topMargin = 2.dp
      leftMargin = 8.dp
      rightMargin = 8.dp
    }
    textSize = 12f
    textColorResource = R.color.md_grey500
  }

  fun bindFunction(moduleFunction: ModuleFunction) {
    if (moduleFunction.iconResource > 0) {
      iconView.setImageResource(moduleFunction.iconResource)
    }
    titleView.text = moduleFunction.title
    descriptionView.text = moduleFunction.description
    setOnClickListener {
      moduleFunction.clickAction(context as AppCompatActivity)
    }
  }
}