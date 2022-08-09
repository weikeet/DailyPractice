package com.weiwei.practice.lifecycle.sample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.weiwei.practice.common.extensions.visibilityText
import com.weiwei.fluent.widget.params.matchParent
import com.weiwei.practice.R
import com.weiwei.practice.lifecycle.core.LifeViewLog
import com.weiwei.practice.lifecycle.core.logger

/**
 * @author weicools
 * @date 2021.07.07
 */
class LifeContentContainer : RelativeLayout {
  constructor(context: Context) : super(context)
  constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

  private val centerId = generateViewId();

  private val centerView = View(context).apply {
    id = centerId
    layoutParams = LayoutParams(10, 10).apply {
      addRule(CENTER_IN_PARENT)
    }
    setBackgroundResource(R.color.colorAccent)
  }.also { addView(it) }

  val content1Container = LifeContent1Container(context).apply {
    id = generateViewId()
    layoutParams = LayoutParams(matchParent, matchParent).apply {
      addRule(ABOVE, centerId)
      addRule(LEFT_OF, centerId)
    }
    // setBackgroundResource(R.color.colorAccent)
  }.also { addView(it) }

  val content2Container = LifeContent2Container(context).apply {
    id = generateViewId()
    layoutParams = LayoutParams(matchParent, matchParent).apply {
      addRule(ABOVE, centerId)
      addRule(RIGHT_OF, centerId)
    }
    // setBackgroundResource(R.color.colorAccent)
  }.also { addView(it) }

  val content3Container = LifeContent3Container(context).apply {
    id = generateViewId()
    layoutParams = LayoutParams(matchParent, matchParent).apply {
      addRule(BELOW, centerId)
      addRule(LEFT_OF, centerId)
    }
    // setBackgroundResource(R.color.colorAccent)
  }.also { addView(it) }

  val content4Container = LifeContent4Container(context).apply {
    id = generateViewId()
    layoutParams = LayoutParams(matchParent, matchParent).apply {
      addRule(BELOW, centerId)
      addRule(RIGHT_OF, centerId)
    }
    // setBackgroundResource(R.color.colorAccent)
  }.also { addView(it) }

  private val TAG = javaClass.simpleName

  private val viewLog by lazy(LazyThreadSafetyMode.NONE) {
    LifeViewLog().also { setupViewLog(it) }
  }

  fun setupViewLog(viewLog: LifeViewLog) {
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