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

package com.weiwei.practice.window

import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding

/**
 * @author weiwei
 * @date 2022.01.12
 */

// -------------------------------------------------------------------------------------
// getInsets from WindowInsetsCompat
// -------------------------------------------------------------------------------------

inline val WindowInsetsCompat.statusBarInsets: Insets
  get() = this.getInsets(WindowInsetsCompat.Type.statusBars())

inline val WindowInsetsCompat.statusBarInsetsIgnore: Insets
  get() = this.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.statusBars())

inline val WindowInsetsCompat.navigationBarInsets: Insets
  get() = this.getInsets(WindowInsetsCompat.Type.navigationBars())

inline val WindowInsetsCompat.navigationBarInsetsIgnore: Insets
  get() = this.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.navigationBars())

inline val WindowInsetsCompat.systemBarInsets: Insets
  get() = this.getInsets(WindowInsetsCompat.Type.systemBars())

inline val WindowInsetsCompat.systemBarIgnoreInsets: Insets
  get() = this.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.systemBars())

inline val WindowInsetsCompat.imeInsets: Insets
  get() = this.getInsets(WindowInsetsCompat.Type.ime())


// -------------------------------------------------------------------------------------
// getInsets top/bottom/left/right from WindowInsetsCompat
// -------------------------------------------------------------------------------------

inline val WindowInsetsCompat.statusBarTop: Int
  get() = this.getInsets(WindowInsetsCompat.Type.statusBars()).top

inline val WindowInsetsCompat.statusBarHeight: Int
  get() = this.getInsets(WindowInsetsCompat.Type.statusBars()).top

inline val WindowInsetsCompat.navigationBarBottom: Int
  get() = this.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom

inline val WindowInsetsCompat.navigationBarHeight: Int
  get() = this.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom

inline val WindowInsetsCompat.systemBarTop: Int
  get() = this.getInsets(WindowInsetsCompat.Type.systemBars()).top

inline val WindowInsetsCompat.systemBarBottom: Int
  get() = this.getInsets(WindowInsetsCompat.Type.systemBars()).bottom

inline val WindowInsetsCompat.systemBarLeft: Int
  get() = this.getInsets(WindowInsetsCompat.Type.systemBars()).left

inline val WindowInsetsCompat.systemBarRight: Int
  get() = this.getInsets(WindowInsetsCompat.Type.systemBars()).right

inline val WindowInsetsCompat.imeTop: Int
  get() = this.getInsets(WindowInsetsCompat.Type.ime()).top

inline val WindowInsetsCompat.imeBottom: Int
  get() = this.getInsets(WindowInsetsCompat.Type.ime()).bottom


// -------------------------------------------------------------------------------------
// view extensions for WindowInsets
// -------------------------------------------------------------------------------------

typealias InitialPadding = Rect
typealias InitialMargin = Rect

fun View.recordInitialPaddingForView(): InitialPadding =
  InitialPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)

fun View.recordInitialMarginForView(): InitialMargin = (layoutParams as? ViewGroup.MarginLayoutParams)
  ?.let {
    InitialMargin(it.leftMargin, it.topMargin, it.rightMargin, it.bottomMargin)
  }
  ?: InitialMargin(0, 0, 0, 0)

@Suppress("DEPRECATION")
fun View.applyBottomWindowInsetForScrollingView(scrollingView: ViewGroup) {
  scrollingView.clipToPadding = false
  doOnApplyWindowInsets { windowInsets, padding, _ ->
    scrollingView.updatePadding(bottom = padding.bottom + windowInsets.systemWindowInsetBottom)
  }
}

fun View.doOnApplyWindowInsets(
  block: (windowInsets: WindowInsetsCompat, padding: InitialPadding, margin: InitialMargin) -> Unit
) {
  val initialMargin = recordInitialMarginForView()
  val initialPadding = recordInitialPaddingForView()
  ViewCompat.setOnApplyWindowInsetsListener(this) { _, windowInsets ->
    block(windowInsets, initialPadding, initialMargin)
    windowInsets
  }
  requestApplyInsetsWhenAttached()
}

fun View.requestApplyInsetsWhenAttached() {
  if (isAttachedToWindow) {
    requestApplyInsets()
  } else {
    addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
      override fun onViewAttachedToWindow(v: View) {
        v.removeOnAttachStateChangeListener(this)
        v.requestApplyInsets()
      }

      override fun onViewDetachedFromWindow(v: View) = Unit
    })
  }
}
