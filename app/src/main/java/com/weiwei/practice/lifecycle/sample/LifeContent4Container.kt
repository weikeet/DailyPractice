package com.weiwei.practice.lifecycle.sample

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.tabs.TabLayout
import com.weiwei.fluent.widget.extensions.dp
import com.weiwei.fluent.widget.params.matchParent
import com.weiwei.practice.R
import com.weiwei.practice.lifecycle.core.widget.LifeFrameLayout

/**
 * @author weicools
 * @date 2021.07.07
 */
class LifeContent4Container : LifeFrameLayout {
  constructor(context: Context) : super(context)
  constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

  private val container = FrameLayout(context).apply {
    id = R.id.content_container_c4
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

  class TabData(
    val tag: String,
    val title: String
  )

  fun initContent(supportFragmentManager: FragmentManager, viewModel: SampleLifeViewModel, lifecycleOwner: LifecycleOwner) {
    val tabDataList = ArrayList<TabData>()
    tabDataList.add(TabData("TAG_HOME", "PX"))
    tabDataList.add(TabData("TAG_DASHBOARD", "PY"))
    tabDataList.add(TabData("TAG_NOTIFICATIONS", "PZ"))

    tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
      override fun onTabSelected(tab: TabLayout.Tab) {
        viewModel.setContent4TabIndex(tab.tag as Int)
      }

      override fun onTabUnselected(tab: TabLayout.Tab) {}
      override fun onTabReselected(tab: TabLayout.Tab) {}
    })

    for (i in tabDataList.indices) {
      tabLayout.addTab(
        tabLayout.newTab()
          .setTag(i)
          .setText(tabDataList[i].title)
      )
    }

    viewModel.content4TabIndexData.observe(lifecycleOwner) { index ->
      if (index < 0 || index >= tabDataList.size) {
        throw IllegalStateException("index out of range")
      }

      val transaction = supportFragmentManager.beginTransaction()

      val showFragment = supportFragmentManager.findFragmentByTag(tabDataList[index].tag)
      if (showFragment == null) {
        val fragment = when (index) {
          0 -> ViewPagerXFragment()
          1 -> ViewPagerYFragment()
          2 -> ViewPagerZFragment()
          else -> throw IllegalStateException("index out of range")
        }
        transaction.add(container.id, fragment, tabDataList[index].tag)
      } else {
        transaction.show(showFragment)
      }

      for (i in tabDataList.indices) {
        if (i != index) {
          val hideFragment = supportFragmentManager.findFragmentByTag(tabDataList[i].tag)
          if (hideFragment != null) {
            transaction.hide(hideFragment)
          }
        }
      }

      transaction.commit()
    }
  }
}