package com.weiwei.core.orientation

import android.content.Context
import android.view.OrientationEventListener

/**
 * @author weiwei
 * @date 2023.02.20
 * Desc: 时刻检测屏幕的旋转角度，并计算当前的横竖屏状态
 */
class ScreenOrientationDetector(context: Context?, rate: Int) : OrientationEventListener(context, rate) {
  interface OrientationChangeListener {
    fun onOrientationChanged(orientation: Int)
  }

  companion object {
    const val ORIENTATION_UNDEFINED = 0
    const val ORIENTATION_PORTRAIT = 1
    const val ORIENTATION_LANDSCAPE = 2
  }

  private var mCurrentOrientation = ORIENTATION_UNDEFINED
  private var currentOrientation = ORIENTATION_UNDEFINED

  private var listener: OrientationChangeListener? = null

  fun setOrientationChangeListener(l: OrientationChangeListener?) {
    listener = l
  }

  fun initOrientation() {
    currentOrientation = ORIENTATION_UNDEFINED
  }

  private val orientation: Int
    get() = if (mCurrentOrientation != 0 && mCurrentOrientation != 180) {
      if (mCurrentOrientation != 90 && mCurrentOrientation != 270) ORIENTATION_UNDEFINED else ORIENTATION_LANDSCAPE
    } else {
      ORIENTATION_PORTRAIT
    }

  override fun onOrientationChanged(orientation: Int) {
    if (orientation != ORIENTATION_UNKNOWN) {
      if (orientation in 45..315) {
        when (orientation) {
          in 46..134 -> {
            mCurrentOrientation = 90
          }
          in 136..224 -> {
            mCurrentOrientation = 180
          }
          in 226..314 -> {
            mCurrentOrientation = 270
          }
        }
      } else {
        mCurrentOrientation = 0
      }

      val newOrientation = this.orientation
      if (newOrientation != ORIENTATION_UNDEFINED && newOrientation != currentOrientation) {
        currentOrientation = newOrientation
        if (listener != null) {
          listener?.onOrientationChanged(newOrientation)
        }
      }
    }
  }

}