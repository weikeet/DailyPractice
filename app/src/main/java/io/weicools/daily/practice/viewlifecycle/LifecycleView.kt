package io.weicools.daily.practice.viewlifecycle

import android.content.Context
import android.graphics.Canvas
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

  override fun onFinishInflate() {
    super.onFinishInflate()
    Log.d(TAG, "onFinishInflate: ")
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    Log.d(TAG, "onAttachedToWindow: ")
  }

  override fun onWindowVisibilityChanged(visibility: Int) {
    super.onWindowVisibilityChanged(visibility)
    Log.d(TAG, "onWindowVisibilityChanged: visibility=${visibility.convertVisibility()}")
  }

  override fun onVisibilityChanged(changedView: View, visibility: Int) {
    super.onVisibilityChanged(changedView, visibility)
    Log.d(TAG, "onVisibilityChanged: changedView=${changedView.javaClass.simpleName}, visibility=${visibility.convertVisibility()}")
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    Log.d(TAG, "onMeasure: ")
  }

  override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
    super.onLayout(changed, left, top, right, bottom)
    Log.d(TAG, "onLayout: changed=$changed, left=$left, top=$top, right=$right, bottom=$bottom")
  }

  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)
    Log.d(TAG, "onDraw: ")
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    Log.d(TAG, "onDetachedFromWindow: ")
  }
}