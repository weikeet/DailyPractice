package com.weiwei.practice.androidart.chapter_3

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

/**
 * @author Weicools
 *
 * @date 2021.10.24
 */
class ObtainGestureDetector(private val context: Context) {
  class GestureDetectorView(context: Context) : View(context) {
    private val gestureListener = object : GestureDetector.SimpleOnGestureListener() {

      override fun onDown(e: MotionEvent?): Boolean {
        // 手指轻触屏幕一瞬间 由1个 ACTION_DOWN 触发
        return super.onDown(e)
      }

      override fun onShowPress(e: MotionEvent?) {
        // 手指轻触屏幕，尚未松开或拖动，由1个 ACTION_DOWN 触发
        // 区别于 onDown 这个强调没有松开和拖动
        super.onShowPress(e)
      }

      override fun onSingleTapUp(e: MotionEvent?): Boolean {
        // 手指轻触屏幕后松开，伴随着1个 ACTION_UP，是单击行为
        return super.onSingleTapUp(e)
      }

      override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        // 手指按下屏幕并拖动，由1个 ACTION_DOWN 和多个 ACTION_MOVE 触发
        return super.onScroll(e1, e2, distanceX, distanceY)
      }

      override fun onLongPress(e: MotionEvent?) {
        // 用户长久拖着屏幕不放
        super.onLongPress(e)
      }

      override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        // 按下屏幕快速滑动后松开，有 1个 ACTION_DOWN 多个 ACTION_MOVE 和 1个 ACTION_UP 触发，属于快速滑动
        return super.onFling(e1, e2, velocityX, velocityY)
      }

      override fun onDoubleTap(e: MotionEvent?): Boolean {
        // 双击，有连续两次单击组成，和 onSingleTapConfirmed 互斥
        return super.onDoubleTap(e)
      }

      override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        // 严格单击行为，区别于 onSingleTapUp，如果触发了 onSingleTapConfirmed 后面可能再跟另一个单击行为
        return super.onSingleTapConfirmed(e)
      }

      override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        // 表示发生了双击行为，在双击期间 ACTION_DOWN ACTION_MOVE ACTION_UP 都会回调
        return super.onDoubleTapEvent(e)
      }
    }

    private val gestureDetector = GestureDetector(context, gestureListener)

    init {
      // 解决长按屏幕后无法拖动的情况
      gestureDetector.setIsLongpressEnabled(false)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
      // return super.onTouchEvent(event)

      // 如果只是监听滑动相关的，建议自己在onTouchEvent中实现
      // 如果要监听双击这种行为的话，那么就使用GestureDetector
      return gestureDetector.onTouchEvent(event)
    }
  }
}