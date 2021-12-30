package com.weiwei.practice.androidart.chapter_3

import android.content.Context
import android.view.View
import android.widget.Scroller

/**
 * @author Weicools
 *
 * @date 2021.10.24
 */
class ObtainScroller {
  class ScrollerView(context: Context) : View(context) {
    private val scroller = Scroller(context)

    private fun smoothScrollTo(destX: Int, destY: Int) {
      val delta = destX - scrollX

      // 1000ms内滑向destX，效果就是慢慢滑动
      scroller.startScroll(scrollX, 0, delta, 0, 1000)

      invalidate()
    }

    override fun computeScroll() {
      // super.computeScroll()

      if (scroller.computeScrollOffset()) {
        scrollTo(scroller.currX, scroller.currY)
      }
    }
  }
}