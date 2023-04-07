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

package com.weiwei.core.track

import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.core.view.ViewCompat
import timber.log.Timber

/**
 * @author weiwei
 * @date 2023.03.30
 *
 * Page 曝光分析
 * View 可见性检测
 */

// 全局重绘监听器
private class GlobalLayoutListener(
  private val view: View,
  private val visibilityCode: Int,
  private val checkVisibility: () -> Unit,
  private val block: (view: View, isVisible: Boolean) -> Unit
) : ViewTreeObserver.OnGlobalLayoutListener {
  // 标记位用于区别是否是遮挡case
  var addedView: View? = null

  override fun onGlobalLayout() {
    // 遮挡 case
    if (addedView != null) {
      // 插入视图矩形区域
      val addedRect = Rect()
      addedView?.getGlobalVisibleRect(addedRect)

      // 当前视图矩形区域
      val rect = Rect()
      view.getGlobalVisibleRect(rect)

      // 如果插入视图矩形区域包含当前视图矩形区域，则视为当前控件不可见
      if (addedRect.contains(rect)) {
        block(view, false)
        view.setTag(visibilityCode, false)
      } else {
        block(view, true)
        view.setTag(visibilityCode, true)
      }
    } else {
      // 非遮挡 case
      checkVisibility()
    }
  }
}

val View.isInScreen: Boolean
  get() = ViewCompat.isAttachedToWindow(this) && visibility == View.VISIBLE && getLocalVisibleRect(Rect())

fun checkVisibilityChange(
  targetView: View,
  // 会被插入 Fragment 的容器集合
  viewGroups: List<ViewGroup>? = null,
  enableViewScrollDetect: Boolean = false,
  block: (view: View, isVisible: Boolean) -> Unit
) {
  val hasListenerCode = "kHasListener".hashCode()

  // 若当前控件已监听可见性，则返回
  if (targetView.getTag(hasListenerCode) == true) return

  val visibilityCode = "kVisibility".hashCode()

  // 检测可见性
  val checkVisibility = {
    // 获取上一次可见性
    val lastVisibility = targetView.getTag(visibilityCode) as? Boolean
    // 判断控件是否出现在屏幕中
    val isInScreen = targetView.isInScreen
    // 首次可见性变更
    if (lastVisibility == null) {
      if (isInScreen) {
        block(targetView, true)
        targetView.setTag(visibilityCode, true)
      }
    }
    // 非首次可见性变更
    else if (lastVisibility != isInScreen) {
      block(targetView, isInScreen)
      targetView.setTag(visibilityCode, isInScreen)
    }
  }

  val globalLayoutListener = GlobalLayoutListener(
    view = targetView,
    visibilityCode = visibilityCode,
    checkVisibility = checkVisibility,
    block = block
  )
  // 编辑容器监听其插入视图时机
  viewGroups?.forEach { viewGroup ->
    viewGroup.setOnHierarchyChangeListener(object : ViewGroup.OnHierarchyChangeListener {
      override fun onChildViewAdded(parent: View?, child: View?) {
        Timber.tag("checkVisibilityChange").d("onChildViewAdded: parent=$parent")
        // 当控件插入，则置标记位
        globalLayoutListener.addedView = child
      }

      override fun onChildViewRemoved(parent: View?, child: View?) {
        Timber.tag("checkVisibilityChange").d("onChildViewRemoved: parent=$parent")
        // 当控件移除，则置标记位
        globalLayoutListener.addedView = null
      }
    })
  }
  targetView.viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)

  /**
   * 全局滑动回调过于频繁 - check [isInScreen] 时需要创建对象，可能会引起性能问题
   * 建议关闭非必要时关闭，设置 [enableViewScrollDetect] 为 false
   */
  var scrollListener: ViewTreeObserver.OnScrollChangedListener? = null
  if (enableViewScrollDetect) {
    scrollListener = ViewTreeObserver.OnScrollChangedListener {
      Timber.tag("checkVisibilityChange").d("onScrollChanged: v=$targetView")
      checkVisibility()
    }
    targetView.viewTreeObserver.addOnScrollChangedListener(scrollListener)
  }

  // 全局焦点变化监听器
  val focusChangeListener = ViewTreeObserver.OnWindowFocusChangeListener { hasFocus ->
    Timber.tag("checkVisibilityChange").d("onWindowFocusChanged: hasFocus=$hasFocus, v=$targetView")
    val lastVisibility = targetView.getTag(visibilityCode) as? Boolean
    val isInScreen = targetView.isInScreen
    if (hasFocus) {
      if (lastVisibility != isInScreen) {
        block(targetView, isInScreen)
        targetView.setTag(visibilityCode, isInScreen)
      }
    } else {
      if (lastVisibility == true) {
        block(targetView, false)
        targetView.setTag(visibilityCode, false)
      }
    }
  }
  targetView.viewTreeObserver.addOnWindowFocusChangeListener(focusChangeListener)

  // 为避免内存泄漏，当视图被移出的同时反注册监听器
  targetView.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
    override fun onViewAttachedToWindow(v: View) {
    }

    override fun onViewDetachedFromWindow(v: View) {
      Timber.tag("checkVisibilityChange").d("onViewDetachedFromWindow: v=$targetView")
      // 有时候 View detach 后，还会执行全局重绘，为此退后反注册
      targetView.post {
        try {
          v.viewTreeObserver.removeOnGlobalLayoutListener(globalLayoutListener)
        } catch (_: Exception) {
          @Suppress("DEPRECATION")
          v.viewTreeObserver.removeGlobalOnLayoutListener(globalLayoutListener)
        }

        v.viewTreeObserver.removeOnWindowFocusChangeListener(focusChangeListener)

        if (scrollListener != null) {
          v.viewTreeObserver.removeOnScrollChangedListener(scrollListener)
        }

        viewGroups?.forEach { viewGroup ->
          viewGroup.setOnHierarchyChangeListener(null)
        }
      }
      targetView.removeOnAttachStateChangeListener(this)
    }
  })

  // 标记已设置监听器
  targetView.setTag(hasListenerCode, true)
}
