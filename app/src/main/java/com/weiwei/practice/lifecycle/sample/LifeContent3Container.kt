package com.weiwei.practice.lifecycle.sample

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.weiwei.fluentview.ui.unit.dp
import com.weiwei.fluentview.view.matchParent
import com.weiwei.practice.R
import com.weiwei.practice.lifecycle.core.logger
import com.weiwei.practice.lifecycle.core.widget.LifeFrameLayout

/**
 * @author weicools
 * @date 2021.07.07
 */
class LifeContent3Container : LifeFrameLayout {
  constructor(context: Context) : super(context)
  constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

  private val viewPager2 = ViewPager2(context).apply {
    id = R.id.view_pager2_c3
    layoutParams = LayoutParams(matchParent, matchParent).apply {
      bottomMargin = 48.dp
    }
  }.also { addView(it) }

  private val tabLayout = TabLayout(context).apply {
    id = generateViewId()
    layoutParams = LayoutParams(matchParent, 48.dp).apply {
      gravity = Gravity.BOTTOM
    }
  }.also { addView(it) }

  fun initContent(supportFragmentManager: FragmentManager, lifecycle: Lifecycle) {
    val fragmentList = ArrayList<Fragment>().apply {
      add(ViewPagerAFragment())
      add(ViewPagerBFragment())
      add(ViewPagerCFragment())
      add(ViewPagerDFragment())
    }
    val titleList = listOf("PA", "PB", "PC", "PD")

    viewPager2.adapter = FragmentAdapter2(supportFragmentManager, lifecycle).apply { updateFragmentList(fragmentList) }
    viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
      override fun onPageSelected(position: Int) {
        logger(viewTag, "content3Container.viewPager2 onPageSelected: position=$position")
      }
    })
    TabLayoutMediator(tabLayout, viewPager2) { tab, pos ->
      tab.text = titleList[pos]
    }.attach()
  }
}