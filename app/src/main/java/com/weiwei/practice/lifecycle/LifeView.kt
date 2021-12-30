package com.weiwei.practice.lifecycle

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.weiwei.practice.common.extensions.visibilityText
import com.weiwei.practice.R

/**
 * @author weicools
 * @date 2020.05.14
 */
open class LifeView : View {
  constructor(context: Context) : super(context)
  constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

  private val TAG = javaClass.simpleName

  private val viewLog by lazy(LazyThreadSafetyMode.NONE) {
    LifeViewLog().also { setupViewLog(it) }
  }

  init {
    @Suppress("LeakingThis")
    setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary16))
    // if (context is AppCompatActivity) {
    //   val observer = LifeViewObserver(TAG)
    //   context.lifecycle.addObserver(observer)
    //   post { observer.enableActivityLife = viewLog.enableActivityLife }
    // }
  }

  open fun setupViewLog(viewLog: LifeViewLog) {
  }

  private fun loggerInner(tag: String, msg: String) {
    if (viewLog.enable) {
      logger(tag, msg)
    }
  }

  /**
   * View 加载 XML 完成时
   */
  override fun onFinishInflate() {
    super.onFinishInflate()
    loggerInner(TAG, "onFinishInflate: ")
  }

  //region Attach-Detach
  /**
   * View 被关联到窗口时
   */
  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    if (viewLog.enableAttachDetach) {
      loggerInner(TAG, "onAttachedToWindow: ")
    }
  }

  /**
   * View 从窗口分离时
   */
  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    if (viewLog.enableAttachDetach) {
      loggerInner(TAG, "onDetachedFromWindow: ")
    }
  }
  //endregion

  //region FocusChanged
  /**
   * View 获得焦点 或者 失去焦点时
   */
  override fun onFocusChanged(gainFocus: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
    super.onFocusChanged(gainFocus, direction, previouslyFocusedRect)
    if (viewLog.enableFocusChanged) {
      loggerInner(TAG, "onFocusChanged: gainFocus=$gainFocus")
    }
  }

  /**
   * View 所在窗口 获得焦点 或者 失去焦点时
   */
  override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
    super.onWindowFocusChanged(hasWindowFocus)
    if (viewLog.enableWindowFocusChanged) {
      loggerInner(TAG, "onWindowFocusChanged: hasWindowFocus=$hasWindowFocus")
    }
  }
  //endregion

  //region VisibilityChanged
  /**
   * View 可见性发生变化时
   */
  override fun onVisibilityChanged(changedView: View, visibility: Int) {
    super.onVisibilityChanged(changedView, visibility)
    if (viewLog.enableVisibilityChanged) {
      loggerInner(TAG, "onVisibilityChanged: visibility=${visibility.visibilityText}")
    }
  }

  /**
   * View 所在窗口的可见性发生变化时
   */
  override fun onWindowVisibilityChanged(visibility: Int) {
    super.onWindowVisibilityChanged(visibility)
    if (viewLog.enableWindowVisibilityChanged) {
      loggerInner(TAG, "onWindowVisibilityChanged: visibility=${visibility.visibilityText}")
    }
  }
  //endregion

  //region Measure-Layout-Draw
  /**
   * 测量 View 及其子 View 大小时
   */
  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    if (viewLog.enableMeasure) {
      loggerInner(TAG, "onMeasure: widthMeasureSpec=$widthMeasureSpec, heightMeasureSpec=$heightMeasureSpec")
    }
  }

  /**
   * 布局 View 及其子 View 位置时
   */
  override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
    super.onLayout(changed, left, top, right, bottom)
    if (viewLog.enableLayout) {
      loggerInner(TAG, "onLayout: changed=$changed, left=$left, top=$top, right=$right, bottom=$bottom")
    }
  }

  /**
   * View 大小发生改变时
   */
  override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
    super.onSizeChanged(w, h, oldw, oldh)
    if (viewLog.enableSizeChanged) {
      loggerInner(TAG, "onSizeChanged: w=$w, h=$h, oldW=$oldw, oldH=$oldh")
    }
  }

  /**
   * 绘制 View 及其子 View 时
   */
  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)
    if (viewLog.enableDraw) {
      loggerInner(TAG, "onDraw: ")
    }
  }
  //endregion

}