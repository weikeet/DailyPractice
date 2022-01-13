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

import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.Window
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat

/**
 * @author weiwei
 * @date 2022.01.11
 */
class WindowInsetsManager {

  fun getSystemBarInsets(view: View): Insets? {
    // 获取 WindowInsets
    val windowInsetsCompat = ViewCompat.getRootWindowInsets(view)

    // 获取 SystemBar Insets
    val systemBarInsets = windowInsetsCompat?.getInsets(WindowInsetsCompat.Type.systemBars())
    return systemBarInsets
  }

  fun getSystemBarInfo(view: View) {
    // 获取 WindowInsets
    val windowInsetsCompat = ViewCompat.getRootWindowInsets(view)

    // 获取 StatusBar Insets
    val statusBarInsets = windowInsetsCompat?.statusBarInsets
    val statusBarTop = statusBarInsets?.top
    val statusBarBottom = statusBarInsets?.bottom

    // Emulator Pixel 5 statusBarTop=66 statusBarBottom=0 ok
    Log.d("WeiOwO", "statusBarTop=$statusBarTop, statusBarBottom=$statusBarBottom")

    // 获取 NavigationBar Insets
    val navigationBarInsets = windowInsetsCompat?.navigationBarInsets
    val navigationBarTop = navigationBarInsets?.top
    val navigationBarBottom = navigationBarInsets?.bottom

    // Emulator Pixel 5 statusBarTop=0 statusBarBottom=132 ok
    Log.d("WeiOwO", "navigationBarTop=$navigationBarTop, navigationBarBottom=$navigationBarBottom")

    // 当 System bar 隐藏时 getInsets() 获取的高度为 0
    // 如果想在隐藏状态时也能获取高度，可以使用 `getInsetsIgnoringVisibility()` 方法
    val statusBarInsetsIgnore = windowInsetsCompat?.statusBarInsetsIgnore
    val navigationBarInsetsIgnore = windowInsetsCompat?.navigationBarInsetsIgnore
    Log.d("WeiOwO", "IgnoringVisibility statusBarTop=${statusBarInsetsIgnore?.top}, statusBarBottom=${statusBarInsetsIgnore?.bottom}")
    Log.d("WeiOwO", "IgnoringVisibility navigationBarTop=${navigationBarInsetsIgnore?.top}, navigationBarBottom=${navigationBarInsetsIgnore?.bottom}")
  }

  fun isStatusBarVisible(view: View): Boolean {
    val windowInsetsCompat = ViewCompat.getRootWindowInsets(view)
    val visible = windowInsetsCompat?.isVisible(WindowInsetsCompat.Type.statusBars()) ?: true

    Log.d("WeiOwO", "isStatusBarVisible=$visible")

    return visible
  }

  fun showStatusBar(view: View) {
    ViewCompat.getWindowInsetsController(view)?.show(WindowInsetsCompat.Type.statusBars())
  }

  fun hideStatusBar(view: View) {
    ViewCompat.getWindowInsetsController(view)?.hide(WindowInsetsCompat.Type.statusBars())
  }

  fun setLightStatusBars(view: View, isLight: Boolean) {
    ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = isLight
  }

  fun isNavigationBarVisible(view: View): Boolean {
    val windowInsetsCompat = ViewCompat.getRootWindowInsets(view)
    val visible = windowInsetsCompat?.isVisible(WindowInsetsCompat.Type.navigationBars()) ?: true

    Log.d("WeiOwO", "isNavigationBarVisible=$visible")

    return visible
  }

  fun showNavigationBar(view: View) {
    ViewCompat.getWindowInsetsController(view)?.show(WindowInsetsCompat.Type.navigationBars())
  }

  fun hideNavigationBar(view: View) {
    ViewCompat.getWindowInsetsController(view)?.hide(WindowInsetsCompat.Type.navigationBars())
  }

  fun setLightNavigationBars(view: View, isLight: Boolean) {
    ViewCompat.getWindowInsetsController(view)?.isAppearanceLightNavigationBars = isLight
  }

  fun isImeVisible(view: View): Boolean {
    val windowInsetsCompat = ViewCompat.getRootWindowInsets(view)
    val visible = windowInsetsCompat?.isVisible(WindowInsetsCompat.Type.ime()) ?: true

    Log.d("WeiOwO", "isImeVisible=$visible")

    return visible
  }

  fun showIme(view: View) {
    ViewCompat.getWindowInsetsController(view)?.show(WindowInsetsCompat.Type.ime())
  }

  fun hideIme(view: View) {
    ViewCompat.getWindowInsetsController(view)?.hide(WindowInsetsCompat.Type.ime())
  }

  fun fitEdgeToEdge(window: Window, view: View) {
    // 1. 使内容区域全屏
    WindowCompat.setDecorFitsSystemWindows(window, false)

    // 2. 设置 System bar 透明
    window.statusBarColor = Color.TRANSPARENT
    window.navigationBarColor = Color.TRANSPARENT

    // 3. 可能出现视觉冲突的 view 处理 insets
    // ViewCompat.setOnApplyWindowInsetsListener(view) { view, windowInsets ->
    //   val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
    //   // 此处更改的 margin，也可设置 padding，视情况而定
    //   view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
    //     topMargin = insets.top
    //     leftMargin = insets.left
    //     bottomMargin = insets.bottom
    //     rightMargin = insets.right
    //   }
    //   WindowInsetsCompat.CONSUMED
    // }
  }

}