package com.weiwei.practice.androidart.chapter_3

import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration

/**
 * @author Weicools
 *
 * @date 2021.10.24
 */
class ObtainTouchSlopView(private val context: Context) {
  class SlopView(context: Context) : View(context) {
    override fun onTouchEvent(event: MotionEvent?): Boolean {
      // TouchSlop是系统所能识别出的被认为是滑动的最小距离
      // 可以利用这个常量来做一些过滤，比如当两次滑动事件的滑动距离小于这个值，我们就可以认为未达到滑动距离的临界值，因此就可以认为它们不是滑动
      val touchSlop = ViewConfiguration.get(context).scaledTouchSlop

      // 在源码中找到这个常量的定义，在frameworks/base/core/res/res/values/config.xml文件中
      // <dimen name="config_viewConfigurationTouchSlop">8dp</dimen>

      return super.onTouchEvent(event)
    }
  }
}