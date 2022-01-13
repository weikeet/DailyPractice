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

import android.content.Context
import android.util.Log

/**
 * @author weiwei
 * @date 2022.01.11
 */
object BarUtils {

  fun getStatusBarHeightViaResource(context: Context): Int {
    val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
    val statusBarHeight = context.resources.getDimensionPixelSize(resourceId)

    // Emulator Pixel 5 value=66 ok
    Log.d("WeiOwO", "getStatusBarHeightViaResource=$statusBarHeight")

    return statusBarHeight
  }

  fun getNavigationBarHeightViaResource(context: Context): Int {
    val resourceId = context.resources.getIdentifier("navigation_bar_height", "dimen", "android")
    val navigationBarHeight = context.resources.getDimensionPixelSize(resourceId)

    // Emulator Pixel 5 value=44 error
    Log.d("WeiOwO", "getNavigationBarHeightViaResource=$navigationBarHeight")

    return navigationBarHeight
  }

}