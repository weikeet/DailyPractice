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

package com.weiwei.practice.keyboard

import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.graphics.Rect
import android.os.Build
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.weiwei.core.app.mainContext
import com.weiwei.core.app.mainHandler

/**
 * @author weiwei
 * @date 2022.08.15
 */
object SoftKeyboardPrinter {

  private val widthPixels: Int by lazy(LazyThreadSafetyMode.NONE) {
    Resources.getSystem().displayMetrics.widthPixels
  }

  // 1. heightPixels = screenRealHeight
  // 2. heightPixels = screenRealHeight - navigationBarHeight
  // 3. heightPixels = screenRealHeight - navigationBarHeight - statusBarHeight
  private val heightPixels: Int by lazy(LazyThreadSafetyMode.NONE) {
    Resources.getSystem().displayMetrics.heightPixels
  }

  // 屏幕真实高度
  val screenRealHeight: Int by lazy(LazyThreadSafetyMode.NONE) {
    val wm = mainContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
      val bounds = wm.currentWindowMetrics.bounds // API 30
      bounds.height()
    } else {
      val point = Point().also {
        @Suppress("DEPRECATION")
        wm.defaultDisplay.getRealSize(it)
      }
      point.y
    }
  }

  fun start(lifecycleOwner: LifecycleOwner, decorView: View) {
    val runnable = object : Runnable {
      override fun run() {
        print("SoftKeyboardPrinter", decorView)
        mainHandler.postDelayed(this, 3000L)
      }
    }
    mainHandler.postDelayed(runnable, 3000L)

    lifecycleOwner.lifecycle.addObserver(object : LifecycleEventObserver {
      override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
          mainHandler.removeCallbacks(runnable)
        }
      }
    })
  }

  fun print(tag: String, decorView: View, popupView: View? = null, popupRect: Rect? = null) {
    // 与 Resources.getSystem().displayMetrics 取值一致
    // val displayMetrics = decorView.resources.displayMetrics
    // Log.i(tag, "widthPixels=${displayMetrics.widthPixels}, heightPixels=${displayMetrics.heightPixels}")

    // 魅族 Flyme7 heightPixels=screenRealHeight
    // 原生 Android12/13 heightPixels=screenRealHeight-navigationBarBottom
    // 小米 MIUI11, LG heightPixels=screenRealHeight-navigationBarBottom-statusBarTop
    // widthPixels/heightPixels/screenRealHeight 取值与 setEdge 和 windowSoftInputMode 无关
    Log.i(tag, "widthPixels=$widthPixels, heightPixels=$heightPixels, screenRealHeight=$screenRealHeight")

    val rootInsets = ViewCompat.getRootWindowInsets(decorView) ?: return
    val imeBottom = rootInsets.getInsets(WindowInsetsCompat.Type.ime()).bottom
    val statusBarTop = rootInsets.getInsets(WindowInsetsCompat.Type.statusBars()).top
    val navigationBarBottom = rootInsets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom

    val imeVisible = rootInsets.isVisible(WindowInsetsCompat.Type.ime())
    val statusBarsVisible = rootInsets.isVisible(WindowInsetsCompat.Type.statusBars())
    val navigationBarsVisible = rootInsets.isVisible(WindowInsetsCompat.Type.navigationBars())

    when (heightPixels) {
      screenRealHeight -> {
        Log.w(tag, "heightPixels = screenRealHeight ")
      }
      screenRealHeight - navigationBarBottom -> {
        Log.w(tag, "heightPixels = screenRealHeight - navigationBarBottom")
      }
      screenRealHeight - navigationBarBottom - statusBarTop -> {
        Log.w(tag, "heightPixels = screenRealHeight - navigationBarBottom - statusBarTop")
      }
    }
    Log.i(tag, "imBottom=$imeBottom, statusBarTop=$statusBarTop, navigationBarBottom=$navigationBarBottom")
    Log.i(tag, "imeVisible=$imeVisible, statusBarsVisible=$statusBarsVisible, navigationBarsVisible=$navigationBarsVisible")

    val decorRect = Rect()
    decorView.getWindowVisibleDisplayFrame(decorRect)
    // diff viewHeight vs frameHeight, viewHeight vs heightPixels, viewHeight vs screenRealHeight
    // 弹出键盘 viewHeight 不会改变, adjustNothing 时 frameHeight 不会改变, adjustResize 时 frameHeight 会改变
    Log.w(tag, "decorViewWindowVisibleDisplayFrame top=${decorRect.top}, bottom=${decorRect.bottom}, height=${decorRect.height()}, viewHeight=${decorView.height}")

    if (popupView == null || popupRect == null) {
      return
    }
    Log.e(tag, "popupViewWindowVisibleDisplayFrame top=${popupRect.top}, bottom=${popupRect.bottom}, height=${popupRect.height()}, viewHeight=${popupView.height}")
  }

  fun printInset(insets: WindowInsetsCompat?, tag: String, msg: String) {
    if (insets == null) {
      Log.w(tag, "$msg: insets == null")
      return
    }
    val imeVisible = insets.isVisible(WindowInsetsCompat.Type.ime())
    val imeHeight = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
    val navigationBarsHeight = insets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom
    Log.d(tag, "$msg: imeVisible=$imeVisible, imeHeight=$imeHeight, navigationBarsHeight=$navigationBarsHeight")
  }
}