package com.weiwei.practice.gesture

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.core.widget.NestedScrollView

/**
 * @author weicools
 * @date 2020.06.01
 */
class GestureScrollView @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : NestedScrollView(context, attrs, defStyleAttr) {
  override fun onTouchEvent(ev: MotionEvent): Boolean {
    return super.onTouchEvent(ev)
  }
}