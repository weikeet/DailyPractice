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

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.Gravity
import android.view.ViewTreeObserver
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.PopupWindow
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsAnimationCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.weiwei.practice.app.mainContext

/**
 * @author weiwei
 * @date 2022.08.13
 *
 * Must be set WindowCompat.setDecorFitsSystemWindows(activity.window, false)
 * It is recommended to set the activity windowSoftInputMode to adjustNothing
 */
class SoftKeyboardWatcher(
  activity: Activity,
  lifecycleOwner: LifecycleOwner,
  private val listener: (imeVisible: Boolean, imeHeight: Int, navigationBarsHeight: Int, animated: Boolean) -> Unit
) {

  private val decorView = activity.window.decorView

  init {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
      watchViaPopupWindow(activity, lifecycleOwner)
    } else {
      watchViaWindowInsetsAnimationCallback()
    }
  }

  @RequiresApi(Build.VERSION_CODES.R)
  private fun watchViaWindowInsetsAnimationCallback() {
    ViewCompat.setWindowInsetsAnimationCallback(decorView, object : WindowInsetsAnimationCompat.Callback(DISPATCH_MODE_CONTINUE_ON_SUBTREE) {
      private var imeVisibleOnPrepare = false
      private var navigationBarsHeight = 0

      override fun onPrepare(animation: WindowInsetsAnimationCompat) {
        super.onPrepare(animation)

        val rootInsets = ViewCompat.getRootWindowInsets(decorView)
        if (rootInsets != null) {
          imeVisibleOnPrepare = rootInsets.isVisible(WindowInsetsCompat.Type.ime())
          navigationBarsHeight = rootInsets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom
        }
      }

      override fun onProgress(insets: WindowInsetsCompat, runningAnimations: MutableList<WindowInsetsAnimationCompat>): WindowInsetsCompat {
        val imeVisible = insets.isVisible(WindowInsetsCompat.Type.ime())
        val imeHeight = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
        listener.invoke(imeVisible, imeHeight, navigationBarsHeight, true)
        return insets
      }
    })
  }

  private fun watchViaPopupWindow(activity: Activity, lifecycleOwner: LifecycleOwner) {

    val popupView = FrameLayout(activity).apply {
      layoutParams = FrameLayout.LayoutParams(
        FrameLayout.LayoutParams.MATCH_PARENT,
        FrameLayout.LayoutParams.MATCH_PARENT
      )
    }

    val popupRect = Rect()

    var keyboardHeight = 0

    val wm = mainContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val screenRealHeight: Int =
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val bounds = wm.currentWindowMetrics.bounds
        bounds.height()
      } else {
        Point().also {
          @Suppress("DEPRECATION")
          wm.defaultDisplay.getRealSize(it)
        }.y
      }

    val globalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
      popupView.getWindowVisibleDisplayFrame(popupRect)

      var statusBarTop = 0
      var navigationBarBottom = 0
      val rootInsets = ViewCompat.getRootWindowInsets(decorView)
      if (rootInsets != null) {
        statusBarTop = rootInsets.getInsets(WindowInsetsCompat.Type.statusBars()).top
        navigationBarBottom = rootInsets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom
      }

      val heightDiff = decorView.height - popupRect.height()
      // 如果高度差值大于 1/5，即认为软键盘弹出
      val imeVisible = heightDiff > screenRealHeight / 5
      // 全屏时 statusBarTop = 0, 不需要考虑全屏和刘海屏的问题
      val imeHeight = if (imeVisible) heightDiff - statusBarTop else 0

      if (keyboardHeight != imeHeight) {
        keyboardHeight = imeHeight
        listener.invoke(imeVisible, imeHeight, navigationBarBottom, false)
      }
    }

    val popupWindow = PopupWindow(activity).apply {
      contentView = popupView
      // 软键盘弹出时，PopupWindow 要调整大小
      @Suppress("DEPRECATION")
      softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE or WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE
      inputMethodMode = PopupWindow.INPUT_METHOD_NEEDED

      width = 0 // 宽度设为0，避免遮挡界面
      height = WindowManager.LayoutParams.MATCH_PARENT
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        /**
         * windowLayoutType default = [WindowManager.LayoutParams.TYPE_APPLICATION_PANEL]
         */
        windowLayoutType = WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG
      }
      setBackgroundDrawable(ColorDrawable(0))
    }

    lifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
      override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        popupView.viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
        decorView.post {
          if (!popupWindow.isShowing && decorView.windowToken != null) {
            popupWindow.showAtLocation(decorView, Gravity.NO_GRAVITY, 0, 0)
          }
        }
      }

      override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        popupView.viewTreeObserver.removeOnGlobalLayoutListener(globalLayoutListener)
        popupWindow.dismiss()
      }
    })
  }
}