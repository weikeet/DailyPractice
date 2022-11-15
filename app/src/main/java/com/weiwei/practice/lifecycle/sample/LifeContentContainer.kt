package com.weiwei.practice.lifecycle.sample

import android.content.Context
import android.util.AttributeSet
import com.weiwei.fluentview.view.matchParent
import com.weiwei.practice.R
import com.weiwei.practice.lifecycle.core.widget.LifeFrameLayout
import com.weiwei.practice.lifecycle.core.widget.LifeRelativeLayout
import com.weiwei.practice.lifecycle.core.widget.LifeTextView
import com.weiwei.practice.lifecycle.core.widget.LifeView

/**
 * @author weicools
 * @date 2021.07.07
 */
class LifeContentContainer : LifeRelativeLayout {
  constructor(context: Context) : super(context)
  constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

  private val centerId = generateViewId();

  private val centerView = LifeView(context).apply {
    id = centerId
    layoutParams = LayoutParams(24, 24).apply {
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
  }.also { addView(it) }

  val content2Container = LifeContent2Container(context).apply {
    id = generateViewId()
    layoutParams = LayoutParams(matchParent, matchParent).apply {
      addRule(ABOVE, centerId)
      addRule(RIGHT_OF, centerId)
    }
  }.also { addView(it) }

  val content3Container = LifeContent3Container(context).apply {
    id = generateViewId()
    layoutParams = LayoutParams(matchParent, matchParent).apply {
      addRule(BELOW, centerId)
      addRule(LEFT_OF, centerId)
    }
  }.also { addView(it) }

  val content4Container = LifeContent4Container(context).apply {
    id = generateViewId()
    layoutParams = LayoutParams(matchParent, matchParent).apply {
      addRule(BELOW, centerId)
      addRule(RIGHT_OF, centerId)
    }
  }.also { addView(it) }

  val testContainer1 = LifeFrameLayout(context).apply {
    viewTag = "testContainer1"
    layoutParams = LayoutParams(100, 100).apply {
      addRule(CENTER_IN_PARENT)
    }

    val testContainer2 = LifeFrameLayout(context).apply {
      viewTag = "testContainer2"
      layoutParams = LayoutParams(100, 100)

      val testTextView = LifeTextView(context).apply {
        viewTag = "testTextView"
        text = "test"
      }.also { addView(it) }

    }.also { addView(it) }

  }.also { addView(it) }

  val testContainer3 = LifeFrameLayout(context).apply {
    viewTag = "testContainer3"
    layoutParams = LayoutParams(100, 100).apply {
      addRule(CENTER_IN_PARENT)
    }
  }.also { addView(it) }

}