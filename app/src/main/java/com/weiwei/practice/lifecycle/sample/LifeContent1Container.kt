package com.weiwei.practice.lifecycle.sample

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.weiwei.fluent.widget.extensions.dp
import com.weiwei.fluent.widget.params.matchParent
import com.weiwei.practice.R
import com.weiwei.practice.common.adapter.FragmentAdapter
import com.weiwei.practice.common.adapter.FragmentData
import com.weiwei.practice.lifecycle.core.logger
import com.weiwei.practice.lifecycle.core.widget.LifeFrameLayout

/**
 * @author weicools
 * @date 2021.07.07
 */
class LifeContent1Container : LifeFrameLayout {
  constructor(context: Context) : super(context)
  constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

  private val viewPager = ViewPager(context).apply {
    id = R.id.view_pager_c1
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

  private fun createFragment(index: Int): Fragment {
    return when (index) {
      0 -> ViewPager1Fragment()
      1 -> ViewPager2Fragment()
      2 -> ViewPager3Fragment()
      3 -> ViewPager4Fragment()
      else -> throw IllegalStateException("index out of range")
    }
  }

  fun initContent(supportFragmentManager: FragmentManager) {
    val fragmentDataList: ArrayList<FragmentData> = ArrayList()
    for (i in 0..3) {
      val title = "P${i + 1}"
      val tag = "android:switcher:" + viewPager.id + ":" + i
      val fragment = supportFragmentManager.findFragmentByTag(tag)
      if (fragment == null) {
        fragmentDataList.add(FragmentData(title, createFragment(i)))
      } else {
        fragmentDataList.add(FragmentData(title, fragment))
      }
    }

    viewPager.adapter = FragmentAdapter(supportFragmentManager, fragmentDataList)
    viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
      override fun onPageSelected(position: Int) {
        logger(viewTag, "content1Container.viewPager onPageSelected: position=$position")
      }
    })
    tabLayout.setupWithViewPager(viewPager)
  }
}