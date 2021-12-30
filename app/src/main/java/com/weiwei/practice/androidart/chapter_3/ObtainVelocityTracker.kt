package com.weiwei.practice.androidart.chapter_3

import android.content.Context
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View

/**
 * @author Weicools
 *
 * @date 2021.10.24
 */
class ObtainVelocityTracker(private val context: Context) {
  class VelocityView(context: Context) : View(context) {
    override fun onTouchEvent(motionEvent: MotionEvent?): Boolean {
      val event = motionEvent ?: return super.onTouchEvent(motionEvent)

      val velocityTracker = VelocityTracker.obtain();
      velocityTracker.addMovement(motionEvent)

      // 必须要调用computeCurrentVelocity方法
      // 比如将时间间隔设为1000ms时，在1s内，手指在水平方向从左向右滑过100像素，那么水平速度就是100
      velocityTracker.computeCurrentVelocity(1000)

      val velocityX = velocityTracker.xVelocity
      val velocityY = velocityTracker.yVelocity

      // 当手指从右往左滑动时，水平方向速度即为负值
      // 速度=（终点位置-起点位置）/ 时间段

      // 需要调用clear方法来重置并回收内存
      velocityTracker.clear()
      velocityTracker.recycle()

      return super.onTouchEvent(event)
    }
  }
}