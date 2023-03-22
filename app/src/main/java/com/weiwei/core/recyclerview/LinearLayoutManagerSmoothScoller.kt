package com.weiwei.core.recyclerview

import android.util.DisplayMetrics
import android.view.View
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.ceil
import kotlin.math.sqrt

/**
 * @author weiwei
 * @date 2021-01-02
 *
 * RecyclerView SmoothScrollToPosition知多少？
 * https://juejin.cn/post/7012422855115833357
 */
fun RecyclerView.smoothLinearScrollToPosition(position: Int, totalPixel: Float = -1f) {
  val linearSmoothScroller = object : LinearSmoothScroller(context) {
    override fun onTargetFound(targetView: View, state: RecyclerView.State, action: Action) {
      val dx = calculateDxToMakeVisible(targetView, horizontalSnapPreference)
      val dy = calculateDyToMakeVisible(targetView, verticalSnapPreference)
      val distance = sqrt((dx * dx + dy * dy).toDouble()).toInt()
      val time = calculateTimeForDeceleration(distance)
      if (time > 0) {
        action.update(-dx, -dy, time, mLinearInterpolator)
      }
    }

    override fun calculateTimeForDeceleration(dx: Int): Int {
      return ceil(calculateTimeForScrolling(dx).toDouble()).toInt()
    }

    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
      if (totalPixel < 0f) {
        return super.calculateSpeedPerPixel(displayMetrics)
      }
      return totalPixel / displayMetrics.densityDpi
    }
  }
  linearSmoothScroller.targetPosition = position
  layoutManager?.startSmoothScroll(linearSmoothScroller)
}

fun RecyclerView.smoothScrollToPositionWithOffset(position: Int, offset: Int, totalPixel: Float = -1f) {
  val linearSmoothScroller = object : LinearSmoothScroller(context) {
    override fun onTargetFound(targetView: View, state: RecyclerView.State, action: Action) {
      super.onTargetFound(targetView, state, action)
      val dx = calculateDxToMakeVisible(targetView, horizontalSnapPreference)
      val dy = calculateDyToMakeVisible(targetView, SNAP_TO_START)

      val distance = sqrt((dx * dx + dy * dy).toDouble()).toInt()
      val time = calculateTimeForDeceleration(distance)
      if (time > 0) {
        action.update(-dx, -dy - offset, time, mDecelerateInterpolator)
      }
    }

    /**
     * return 滑过 1px 时经历的时间(ms)
     */
    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
      if (totalPixel < 0f) {
        return super.calculateSpeedPerPixel(displayMetrics)
      }
      return totalPixel / displayMetrics.densityDpi
    }
  }
  linearSmoothScroller.targetPosition = position
  layoutManager?.startSmoothScroll(linearSmoothScroller)
}
