package com.ryg.chapter_3

import android.content.Context
import android.view.MotionEvent
import android.view.View

/**
 * @author Weicools
 *
 * @date 2021.10.24
 */
class ObtainMotionEventView(private val context: Context) {
  class XView(context: Context) : View(context) {
    override fun onTouchEvent(motionEvent: MotionEvent?): Boolean {
      val event = motionEvent ?: return super.onTouchEvent(motionEvent)

      when (event.action) {
        MotionEvent.ACTION_DOWN -> {
          // 手指刚接触屏幕
        }
        MotionEvent.ACTION_MOVE -> {
          // 手指在屏幕上滑动
        }
        MotionEvent.ACTION_UP -> {
          // 手指从屏幕松开一瞬间
        }
        MotionEvent.ACTION_CANCEL -> {
          // 手指滑动到边缘触发取消
        }
      }

      // 点击屏幕后离开松开，事件序列为 DOWN -> UP
      // 点击屏幕滑动一会再松开，事件序列为 DOWN -> MOVE -> … > MOVE -> UP

      // 相对于当前 View 左上角的 x 和 y 坐标
      val x = event.x
      val y = event.y

      // 相对于手机屏幕左上角的x和y坐标
      val rx = event.rawX
      val ry = event.rawY

      return super.onTouchEvent(event)
    }
  }

}