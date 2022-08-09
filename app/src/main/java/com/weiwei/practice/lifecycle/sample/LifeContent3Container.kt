package com.weiwei.practice.lifecycle.sample

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.weiwei.fluent.widget.extensions.dp
import com.weiwei.fluent.widget.params.matchParent
import com.weiwei.practice.R
import com.weiwei.practice.lifecycle.core.LifeLayout

/**
 * @author weicools
 * @date 2021.07.07
 */
class LifeContent3Container : LifeLayout {
  constructor(context: Context) : super(context)
  constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

  val viewPager2 = ViewPager2(context).apply {
    id = R.id.view_pager2_c3
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