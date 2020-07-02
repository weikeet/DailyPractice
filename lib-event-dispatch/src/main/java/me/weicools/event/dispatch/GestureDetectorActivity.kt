package me.weicools.event.dispatch

import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_gesture_detector.*
import kotlin.math.abs

/**
 * @author weicools
 * @date 2020.05.29
 */
class GestureDetectorActivity : AppCompatActivity() {
  private val MAX_MOVEMENT_STEP_TO_GET_DIRECTION = 3

  private var xMoveDistance = 0f
  private var yMoveDistance = 0f
  private var moveStepCount = 0
  private var lateralMovement = false

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_gesture_detector)

    val gestureListener = object : GestureDetector.SimpleOnGestureListener {
      override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        xMoveDistance -= distanceX
        yMoveDistance -= distanceY
        if (moveStepCount == MAX_MOVEMENT_STEP_TO_GET_DIRECTION && abs(xMoveDistance) < abs(yMoveDistance)) {
          lateralMovement = true
        }
        moveStepCount++

        return super.onScroll(e1, e2, distanceX, distanceY)
      }
    }

    rootView.setOnTouchListener(object : View.OnTouchListener {
      override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (event==null) {
          return false;
        }
        if (scrollView.translationY <= 0) {
          return false;
        }
      }
    })
  }
}