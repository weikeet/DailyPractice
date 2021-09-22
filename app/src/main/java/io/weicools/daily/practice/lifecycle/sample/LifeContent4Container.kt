package io.weicools.daily.practice.lifecycle.sample

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import com.google.android.material.tabs.TabLayout
import com.weicools.fluent.widget.extensions.dp
import com.weicools.fluent.widget.params.matchParent
import io.weicools.daily.practice.R
import io.weicools.daily.practice.lifecycle.LifeLayout

/**
 * @author weicools
 * @date 2021.07.07
 */
class LifeContent4Container : LifeLayout {
  constructor(context: Context) : super(context)
  constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

  val container = FrameLayout(context).apply {
    id = generateViewId()
    id = R.id.content_container_c4
    layoutParams = LayoutParams(matchParent, matchParent).apply {
      bottomMargin = 48.dp
    }
  }.also { addView(it) }

  val tabLayout = TabLayout(context).apply {
    id = generateViewId()
    layoutParams = LayoutParams(matchParent, 48.dp).apply {
      gravity = Gravity.BOTTOM
    }
  }.also { addView(it) }
}