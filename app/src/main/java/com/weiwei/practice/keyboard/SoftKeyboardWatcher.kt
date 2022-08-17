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
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Gravity
import android.view.ViewTreeObserver
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.PopupWindow
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 * @author weiwei
 * @date 2022.08.13
 */
class SoftKeyboardWatcher(
  activity: Activity,
  lifecycleOwner: LifecycleOwner,
  private val listener: (imeVisible: Boolean, imeHeight: Int, navigationBarsHeight: Int) -> Unit
) : PopupWindow(activity), ViewTreeObserver.OnGlobalLayoutListener {

  private val popupRect = Rect()

  private val popupView = FrameLayout(activity).apply {
    layoutParams = FrameLayout.LayoutParams(
      FrameLayout.LayoutParams.MATCH_PARENT,
      FrameLayout.LayoutParams.MATCH_PARENT
    )
  }

  private val decorView = activity.window.decorView

  private var keyboardVisible = false
  private var keyboardHeight = 0

  init {
    contentView = popupView
    popupView.viewTreeObserver.addOnGlobalLayoutListener(this)

    // 软键盘弹出时，PopupWindow 要调整大小
    @Suppress("DEPRECATION")
    softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE or WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE
    inputMethodMode = INPUT_METHOD_NEEDED

    width = 0 // 宽度设为0，避免遮挡界面
    height = WindowManager.LayoutParams.MATCH_PARENT
    setBackgroundDrawable(ColorDrawable(0))

    // androidx.appcompat.widget.ContentFrameLayout{...android:id/content}
    // val rootView: View = activity.findViewById(android.R.id.content)
    decorView.post {
      if (!isShowing && decorView.windowToken != null) {
        showAtLocation(decorView, Gravity.NO_GRAVITY, 0, 0)
      }
    }

    // Activity 销毁时或者 Fragment onDestroyView 时必须关闭 popupWindow ，避免内存泄漏
    lifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
      override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        dismiss()
      }
    })
  }

  override fun onGlobalLayout() {

    popupView.getWindowVisibleDisplayFrame(popupRect)

    SoftKeyboardPrinter.print("SoftKeyboardWatcher", decorView, popupView, popupRect)

    val rootInsets = ViewCompat.getRootWindowInsets(decorView)

    var imeBottom = 0
    var statusBarTop = 0
    var navigationBarBottom = 0
    if (rootInsets != null) {
      imeBottom = rootInsets.getInsets(WindowInsetsCompat.Type.ime()).bottom
      statusBarTop = rootInsets.getInsets(WindowInsetsCompat.Type.statusBars()).top
      navigationBarBottom = rootInsets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom
    }

    // TODO: check visibleHeight not change

    // 粗略计算高度的变化值，后面会根据状态栏和导航栏修正
    val heightDiff = decorView.height - popupRect.height()
    // 如果获取到的高度大于 1/5，即认为软键盘弹出
    keyboardVisible = heightDiff > SoftKeyboardPrinter.screenRealHeight / 5
    // 全屏时 statusBarTop = 0, 不需要考虑全屏和刘海屏的问题
    keyboardHeight = if (keyboardVisible) heightDiff - statusBarTop else 0

    if ((keyboardHeight > 0 && imeBottom == 0) || (keyboardHeight == 0 && imeBottom > 0)) {
      decorView.post {
        SoftKeyboardPrinter.print("SoftKeyboardWatcherPost", decorView)
      }
    }

    Log.i("SoftKeyboardWatcher", "keyboardVisible = $keyboardVisible, keyboardHeight = $keyboardHeight")

    listener.invoke(keyboardVisible, keyboardHeight, navigationBarBottom)
  }
}