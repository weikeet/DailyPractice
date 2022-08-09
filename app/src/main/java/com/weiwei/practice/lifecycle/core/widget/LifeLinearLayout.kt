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

package com.weiwei.practice.lifecycle.core.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.doOnPreDraw
import com.weiwei.practice.common.extensions.visibilityText
import com.weiwei.practice.lifecycle.core.LifeViewLog
import com.weiwei.practice.lifecycle.core.logger

/**
 * @author weicools
 * @date 2020.05.14
 */
open class LifeLinearLayout : LinearLayout {
  constructor(context: Context) : super(context)
  constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

  var viewTag = javaClass.simpleName

  private val viewLog by lazy(LazyThreadSafetyMode.NONE) {
    LifeViewLog().also { setupViewLog(it) }
  }

  init {
    doOnPreDraw {
      loggerInner(viewTag, "onPreDraw")
    }
  }

  open fun setupViewLog(viewLog: LifeViewLog) {
  }

  private fun loggerInner(tag: String, msg: String) {
    if (viewLog.enable) {
      logger(tag, msg)
    }
  }

  /**
   * View 加载 XML 完成时
   */
  override fun onFinishInflate() {
    super.onFinishInflate()
    loggerInner(viewTag, "onFinishInflate: ")
  }

  //region Attach-Detach
  /**
   * View 被关联到窗口时
   */
  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    if (viewLog.enableAttachDetach) {
      loggerInner(viewTag, "onAttachedToWindow: ")
    }
  }

  /**
   * View 从窗口分离时
   */
  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    if (viewLog.enableAttachDetach) {
      loggerInner(viewTag, "onDetachedFromWindow: ")
    }
  }
  //endregion

  //region FocusChanged
  /**
   * View 获得焦点 或者 失去焦点时
   */
  override fun onFocusChanged(gainFocus: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
    super.onFocusChanged(gainFocus, direction, previouslyFocusedRect)
    if (viewLog.enableFocusChanged) {
      loggerInner(viewTag, "onFocusChanged: gainFocus=$gainFocus")
    }
  }

  /**
   * View 所在窗口 获得焦点 或者 失去焦点时
   */
  override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
    super.onWindowFocusChanged(hasWindowFocus)
    if (viewLog.enableWindowFocusChanged) {
      loggerInner(viewTag, "onWindowFocusChanged: hasWindowFocus=$hasWindowFocus")
    }
  }
  //endregion

  //region VisibilityChanged
  /**
   * View 可见性发生变化时
   */
  override fun onVisibilityChanged(changedView: View, visibility: Int) {
    super.onVisibilityChanged(changedView, visibility)
    if (viewLog.enableVisibilityChanged) {
      loggerInner(viewTag, "onVisibilityChanged: visibility=${visibility.visibilityText}")
    }
  }

  /**
   * View 所在窗口的可见性发生变化时
   */
  override fun onWindowVisibilityChanged(visibility: Int) {
    super.onWindowVisibilityChanged(visibility)
    if (viewLog.enableWindowVisibilityChanged) {
      loggerInner(viewTag, "onWindowVisibilityChanged: visibility=${visibility.visibilityText}")
    }
  }
  //endregion

  //region Measure-Layout-Draw
  /**
   * 测量 View 及其子 View 大小时
   */
  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    if (viewLog.enableMeasure) {
      loggerInner(viewTag, "onMeasure: widthMeasureSpec=$widthMeasureSpec, heightMeasureSpec=$heightMeasureSpec")
    }
  }

  /**
   * 布局 View 及其子 View 位置时
   */
  override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
    super.onLayout(changed, left, top, right, bottom)
    if (viewLog.enableLayout) {
      loggerInner(viewTag, "onLayout: changed=$changed, left=$left, top=$top, right=$right, bottom=$bottom")
    }
  }

  /**
   * View 大小发生改变时
   */
  override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
    super.onSizeChanged(w, h, oldw, oldh)
    if (viewLog.enableSizeChanged) {
      loggerInner(viewTag, "onSizeChanged: w=$w, h=$h, oldW=$oldw, oldH=$oldh")
    }
  }

  /**
   * 绘制 View 及其子 View 时
   */
  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)
    if (viewLog.enableDraw) {
      loggerInner(viewTag, "onDraw: ")
    }
  }
  //endregion

}