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

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

/**
 * @author weiwei
 * @date 2022.01.12
 */

fun Context.findWindow(): Window? {
  var context = this
  while (context is ContextWrapper) {
    if (context is Activity) {
      return context.window
    }
    context = context.baseContext
  }
  return null
}

fun View.getWindowInsetsControllerCompat(): WindowInsetsControllerCompat? {
  val window = context.findWindow() ?: return null
  return WindowCompat.getInsetsController(window, this)
}

// -------------------------------------------------------------------------------------
// getInsets from WindowInsetsCompat
// -------------------------------------------------------------------------------------

inline val WindowInsetsCompat.statusBarsInsets: Insets
  get() = this.getInsets(WindowInsetsCompat.Type.statusBars())

inline val WindowInsetsCompat.statusBarsInsetsIgnore: Insets
  get() = this.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.statusBars())

inline val WindowInsetsCompat.navigationBarsInsets: Insets
  get() = this.getInsets(WindowInsetsCompat.Type.navigationBars())

inline val WindowInsetsCompat.navigationBarsInsetsIgnore: Insets
  get() = this.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.navigationBars())

inline val WindowInsetsCompat.systemBarsInsets: Insets
  get() = this.getInsets(WindowInsetsCompat.Type.systemBars())

inline val WindowInsetsCompat.systemBarsIgnoreInsets: Insets
  get() = this.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.systemBars())

inline val WindowInsetsCompat.imeInsets: Insets
  get() = this.getInsets(WindowInsetsCompat.Type.ime())


// -------------------------------------------------------------------------------------
// getInsets top/bottom/left/right from WindowInsetsCompat
// -------------------------------------------------------------------------------------

inline val WindowInsetsCompat.statusBarTop: Int
  get() = this.getInsets(WindowInsetsCompat.Type.statusBars()).top

inline val WindowInsetsCompat.navigationBarBottom: Int
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
// getInsets top/bottom/left/right from WindowInsetsCompat
// -------------------------------------------------------------------------------------
fun getRootStatusBarTop(activity: Activity, default: Int = 0): Int =
  ViewCompat.getRootWindowInsets(activity.window.decorView)?.getInsets(WindowInsetsCompat.Type.statusBars())?.top ?: default

fun getRootNavigationBar(activity: Activity, default: Int = 0): Int =
  ViewCompat.getRootWindowInsets(activity.window.decorView)?.getInsets(WindowInsetsCompat.Type.navigationBars())?.bottom ?: default

// -------------------------------------------------------------------------------------
// view extensions for WindowInsets
// -------------------------------------------------------------------------------------

typealias InitialPadding = Rect
typealias InitialMargin = Rect
typealias InitialParam = ViewGroup.LayoutParams

fun View.recordInitialParam(): InitialParam =
  InitialParam(layoutParams.width, layoutParams.height)

fun View.recordInitialPadding(): InitialPadding =
  InitialPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)

fun View.recordInitialMargin(): InitialMargin = (layoutParams as? ViewGroup.MarginLayoutParams)
  ?.let {
    InitialMargin(it.leftMargin, it.topMargin, it.rightMargin, it.bottomMargin)
  }
  ?: InitialMargin(0, 0, 0, 0)


/**
 * @param requestApplyInsets: see [requestApplyInsetsWhenAttached] notes
 * @param block: callback WindowInsetsCompat
 */
fun View.doOnApplyWindowInsets(
  requestApplyInsets: Boolean = false, block: (windowInsets: WindowInsetsCompat) -> Unit
) {
  ViewCompat.setOnApplyWindowInsetsListener(this) { _, windowInsets ->
    block(windowInsets)
    windowInsets
  }
  if (requestApplyInsets) {
    requestApplyInsetsWhenAttached()
  }
}

/**
 * @param requestApplyInsets: see [requestApplyInsetsWhenAttached] notes
 * @param block: callback WindowInsetsCompat, InitialPadding, InitialMargin
 */
fun View.doOnApplyWindowInsets(
  requestApplyInsets: Boolean = false, block: (windowInsets: WindowInsetsCompat, padding: InitialPadding, margin: InitialMargin) -> Unit
) {
  val initialMargin = recordInitialMargin()
  val initialPadding = recordInitialPadding()
  ViewCompat.setOnApplyWindowInsetsListener(this) { _, windowInsets ->
    block(windowInsets, initialPadding, initialMargin)
    windowInsets
  }
  if (requestApplyInsets) {
    requestApplyInsetsWhenAttached()
  }
}


/**
 * It is best always call this method when setting OnApplyWindowInsetsListener,
 * otherwise OnApplyWindowInsetsListener will not be called sometimes, such as
 * when we set OnApplyWindowInsetsListener in the constructor of a view and
 * this view will be added to the ViewGroup after delay.
 */
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
