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

package com.weiwei.practice.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.updateLayoutParams
import com.google.android.material.appbar.MaterialToolbar
import com.weiwei.practice.window.doOnApplyWindowInsets
import com.weiwei.practice.window.systemBarTop

/**
 * @author weiwei
 * @date 2023.03.10
 */

fun Context.topAppToolbar(
  title: String,
  titleColor: Int? = null,

  backgroundColor: Int? = null,

  navigationIcon: Drawable? = null,
  navigationIconRes: Int? = null,
  navigationIconTint: Int? = null,
  navigationIconClick: (() -> Unit)? = null,

  init: LinearLayout.() -> Unit
): LinearLayout {
  val widget = LinearLayout(this)
  widget.orientation = LinearLayout.VERTICAL

  val statusView = View(this)
  widget.addView(statusView, LinearLayout.LayoutParams.MATCH_PARENT, 0)
  if (backgroundColor != null) {
    statusView.setBackgroundColor(backgroundColor)
  }

  val materialToolbar = MaterialToolbar(this)
  widget.addView(materialToolbar, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
  if (titleColor != null) {
    materialToolbar.setTitleTextColor(titleColor)
  }
  if (backgroundColor != null) {
    materialToolbar.setBackgroundColor(backgroundColor)
  }
  materialToolbar.title = title

  if (navigationIconRes != null) {
    materialToolbar.setNavigationIcon(navigationIconRes)
    if (navigationIconTint != null) {
      materialToolbar.setNavigationIconTint(navigationIconTint)
    }
    materialToolbar.setNavigationOnClickListener {
      navigationIconClick?.invoke()
    }
  } else if (navigationIcon != null) {
    materialToolbar.navigationIcon = navigationIcon
    if (navigationIconTint != null) {
      materialToolbar.setNavigationIconTint(navigationIconTint)
    }
    materialToolbar.setNavigationOnClickListener {
      navigationIconClick?.invoke()
    }
  }

  widget.apply(init)

  statusView.doOnApplyWindowInsets { windowInsets ->
    statusView.updateLayoutParams { height = windowInsets.systemBarTop }
  }

  return widget
}