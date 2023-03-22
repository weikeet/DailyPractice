package com.weiwei.core.recyclerview

import android.content.Context
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

/**
 * @author weiwei
 * @date 2021-01-02
 * 对无法居中的 item 调用 smoothScrollToPosition 时，会导致 接下来的第 1 次点击事件无效
 */
class LinearCenterLayoutManager(context: Context) : LinearLayoutManager(context) {
  inner class CenterSmoothScroller(context: Context) : LinearSmoothScroller(context) {
    override fun calculateDtToFit(viewStart: Int, viewEnd: Int, boxStart: Int, boxEnd: Int, snapPreference: Int): Int {
      return boxStart + (boxEnd - boxStart) / 2 - (viewStart + (viewEnd - viewStart) / 2)
    }

    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
      val newDuration: Float = 400f / abs(targetPosition - lastPosition)
      return newDuration / displayMetrics.densityDpi
    }
  }

  private var lastPosition = 0
  private var targetPosition = 0

  override fun smoothScrollToPosition(recyclerView: RecyclerView, state: RecyclerView.State?, position: Int) {
    val smoothScroller = CenterSmoothScroller(recyclerView.context)
    smoothScroller.targetPosition = position
    startSmoothScroll(smoothScroller)
  }

  fun smoothScrollToPosition(recyclerView: RecyclerView, state: RecyclerView.State?, lastPosition: Int, position: Int) {
    this.lastPosition = lastPosition
    this.targetPosition = position
    smoothScrollToPosition(recyclerView, state, position)
  }
}