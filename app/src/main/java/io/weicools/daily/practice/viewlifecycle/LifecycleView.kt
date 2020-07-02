package io.weicools.daily.practice.viewlifecycle

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View
import io.weicools.daily.practice.ktx.convertVisibility

/**
 * @author weicools
 * @date 2020.05.14
 */
class LifecycleView : View {
  companion object {
    const val TAG = "LifecycleView"
  }

  constructor(context: Context?) : super(context) {
    init()
  }

  constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
    init()
  }

  constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    init()
  }

  private fun init() {
    Log.d(TAG, "init: ")
  }

  /**
   * View在xml文件里加载完成时调用
   */
  override fun onFinishInflate() {
    super.onFinishInflate()
    Log.d(TAG, "onFinishInflate: ")
  }

  /**
   * View被关联到窗口时调用
   */
  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    Log.d(TAG, "onAttachedToWindow: ")
  }

  /**
   * View从窗口分离时调用
   */
  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    Log.d(TAG, "onDetachedFromWindow: ")
  }

  /**
   * View获取焦点或者失去焦点时调用
   */
  override fun onFocusChanged(gainFocus: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
    super.onFocusChanged(gainFocus, direction, previouslyFocusedRect)
    Log.d(TAG, "onFocusChanged: gainFocus=$gainFocus")
  }

  /**
   * View所在窗口获取焦点或者失去焦点时调用
   */
  override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
    super.onWindowFocusChanged(hasWindowFocus)
    Log.d(TAG, "onWindowFocusChanged: hasWindowFocus=$hasWindowFocus")
  }

  /**
   * View的可见性发生变化时调用
   */
  override fun onVisibilityChanged(changedView: View, visibility: Int) {
    super.onVisibilityChanged(changedView, visibility)
    Log.d(TAG, "onVisibilityChanged: changedView=${changedView.javaClass.simpleName}, visibility=${visibility.convertVisibility()}")
  }

  /**
   * View所在窗口的可见性发生变化时调用
   */
  override fun onWindowVisibilityChanged(visibility: Int) {
    super.onWindowVisibilityChanged(visibility)
    Log.d(TAG, "onWindowVisibilityChanged: visibility=${visibility.convertVisibility()}")
  }

  /**
   * 测量View及其子View大小时调用
   */
  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    Log.d(TAG, "onMeasure: ")
  }

  /**
   * 布局View及其子View大小时调用
   */
  override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
    super.onLayout(changed, left, top, right, bottom)
    Log.d(TAG, "onLayout: changed=$changed, left=$left, top=$top, right=$right, bottom=$bottom")
  }

  /**
   * View大小发生改变时调用
   */
  override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
    super.onSizeChanged(w, h, oldw, oldh)
    Log.d(TAG, "onSizeChanged: w=$w, h=$h, oldW=$oldw, oldH=$oldh")
  }

  /**
   * 绘制View及其子View大小时调用
   */
  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)
    Log.d(TAG, "onDraw: ")
  }
}