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

package com.weiwei.practice.window.delegate

import android.app.Activity
import android.graphics.Color
import android.view.View
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat

/**
 * @author weiwei
 * @date 2022.01.12
 *
 * 三大金刚 错
 * 手势 对
 *
 * https://github.com/shingkit/EdgeInsetsDelegate
 */
class EdgeInsetDelegate(private val activity: Activity) {

  private var everGivenInsetsToDecorView = false

  fun start() {
    everGivenInsetsToDecorView = false

    // 不让 decorView 给状态栏导航栏留白
    WindowCompat.setDecorFitsSystemWindows(activity.window, false)

    activity.window.statusBarColor = Color.TRANSPARENT
    activity.window.navigationBarColor = Color.TRANSPARENT

    // 防止 miui 系统上出现白色半透明导航栏
    ViewCompat.setOnApplyWindowInsetsListener(activity.window.decorView) { _: View?, windowInsets: WindowInsetsCompat ->
      val navigationBarInsets = windowInsets.getInsets(WindowInsetsCompat.Type.navigationBars())
      val isGestureNavigation = isGestureNavigation(navigationBarInsets)

      if (!isGestureNavigation) {
        // Let decorView draw the translucent navigationBarColor
        ViewCompat.onApplyWindowInsets(activity.window.decorView, windowInsets)
        everGivenInsetsToDecorView = true

      } else if (isGestureNavigation && everGivenInsetsToDecorView) {
        // Let DecorView remove navigationBarColor once it has bean drawn
        val newInsets = Insets.of(navigationBarInsets.left, navigationBarInsets.top, navigationBarInsets.right, 0)
        val noBottomInsets = WindowInsetsCompat.Builder()
          .setInsets(WindowInsetsCompat.Type.navigationBars(), newInsets)
          .build()
        ViewCompat.onApplyWindowInsets(activity.window.decorView, noBottomInsets)
      }

      windowInsets
    }
  }

  private fun isGestureNavigation(navigationBarsInsets: Insets): Boolean {
    val threshold = (20 * activity.resources.displayMetrics.density).toInt()
    // 44 is the fixed height of the iOS-like navigation bar (Gesture navigation), in pixels!
    return navigationBarsInsets.bottom <= threshold.coerceAtLeast(44) // 20dp or 44px
  }

}