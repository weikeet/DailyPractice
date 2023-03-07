package com.weiwei.practice.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.weiwei.practice.R
import kotlin.math.roundToInt

class WorkoutTopProgressView @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

  private val Float.dp get() = (this * resources.displayMetrics.density)

  var size = 0
  var index = 0
  var exerciseTime = 0
  var countdownTime = 0

  private var progress = 0f

  private var eachWidth = 0f

  private var viewWidth = 0
  private var viewHeight = 0

  private val divider = 3f.dp
  private val roundRadius = 2.5f * resources.displayMetrics.density

  var progressColor = ContextCompat.getColor(context, R.color.black)
  var bgColor = ContextCompat.getColor(context, R.color.white)

  private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

  init {
    // val ta = context.obtainStyledAttributes(attrs, R.styleable.WorkoutTopProgressView)
    // progressColor = ta.getColor(R.styleable.WorkoutTopProgressView_progressColor, progressColor)
    // bgColor = ta.getColor(R.styleable.WorkoutTopProgressView_bgColor, bgColor)
    // ta.recycle()

    clipToOutline = true
    outlineProvider = RoundOutlineProvider(30f)
  }

  override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
    super.onSizeChanged(w, h, oldw, oldh)
    viewWidth = w
    viewHeight = h

    if (size > 0) {
      eachWidth = (viewWidth - divider * (size - 1)) / size.toFloat()
    }
  }

  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)

    if (size == 0) return

    for (i in 0 until size) {
      val rectLeft = i * (eachWidth + divider)
      val rectRight = rectLeft + eachWidth

      if (i < index) {
        drawFinished(canvas, rectLeft, rectRight)
      } else if (i == index) {
        drawExercise(canvas, rectLeft, rectRight)
      } else {
        drawNotStarted(canvas, rectLeft, rectRight)
      }
    }
  }

  private fun drawFinished(canvas: Canvas, left: Float, right: Float) {
  }

  private fun drawExercise(canvas: Canvas, left: Float, right: Float) {
    paint.color = bgColor
    canvas.drawRect(left.toFloat(), 0f, right.toFloat(), viewHeight.toFloat(), paint)

    paint.color = progressColor
    canvas.drawRect(left.toFloat(), 0f, left + (right - left) * progress, viewHeight.toFloat(), paint)
  }

  private fun drawNotStarted(canvas: Canvas, left: Float, right: Float) {
    paint.color = bgColor
    Log.d("WorkoutTopProgressView", "drawNotStarted: right=$right, width=$viewWidth")
    canvas.drawRect(left.toFloat(), 0f, right.toFloat(), viewHeight.toFloat(), paint)
  }

  fun setWorkoutProgress(size: Int, index: Int, exerciseTime: Int, countdownTime: Int) {
    if (size == 0 || exerciseTime == 0) {
      return
    }

    if (this.size != size) {
      this.size = size
      if (viewWidth > 0) {
        eachWidth = (viewWidth - divider * (size - 1)) / size.toFloat()
      }
    }

    if (this.index != index || this.exerciseTime != exerciseTime || this.countdownTime != countdownTime) {
      this.index = index
      this.exerciseTime = exerciseTime
      this.countdownTime = countdownTime
      this.progress = (exerciseTime - countdownTime) / exerciseTime.toFloat()
      invalidate()
    }
  }

}