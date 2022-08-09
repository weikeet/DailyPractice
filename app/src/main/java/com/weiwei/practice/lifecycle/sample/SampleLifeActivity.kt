package com.weiwei.practice.lifecycle.sample

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.weiwei.practice.common.adapter.FragmentAdapter
import com.weiwei.practice.common.adapter.FragmentAdapter2
import com.weiwei.practice.lifecycle.core.LifeActivity
import com.weiwei.practice.lifecycle.core.logger

/**
 * @author weicools
 * @date 2021.03.31
 */
class SampleLifeActivity : LifeActivity() {

  private val TAG = javaClass.simpleName

  private lateinit var container: LifeContentContainer

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    container = LifeContentContainer(this)
    setContentView(container)

    initContent1()
    initContent2()
    initContent3()
    initContent4()
  }

  private fun instantiateFragment(viewPager: ViewPager, position: Int, defaultResult: () -> Fragment): Fragment {
    val tag = "android:switcher:" + viewPager.id + ":" + position
    val fragment = supportFragmentManager.findFragmentByTag(tag)
    return fragment ?: defaultResult()
  }

  private fun initContent1() {
    val viewPager = container.content1Container.viewPager
    val tabLayout = container.content1Container.tabLayout

    val fragmentList = ArrayList<Fragment>().apply {
      add(instantiateFragment(viewPager, 0) { ViewPager1Fragment() })
      add(instantiateFragment(viewPager, 1) { ViewPager2Fragment() })
      add(instantiateFragment(viewPager, 2) { ViewPager3Fragment() })
      add(instantiateFragment(viewPager, 3) { ViewPager4Fragment() })
    }
    val titleList = listOf("P1", "P2", "P3", "P4")

    viewPager.adapter = FragmentAdapter(supportFragmentManager, fragmentList, titleList)
    viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
      override fun onPageSelected(position: Int) {
        logger(TAG, "content1Container.viewPager onPageSelected: position=$position")
      }
    })
    tabLayout.setupWithViewPager(viewPager)
  }

  private fun initContent2() {
    val viewPager = container.content2Container.viewPager
    val tabLayout = container.content2Container.tabLayout

    val fragmentList = ArrayList<Fragment>().apply {
      add(instantiateFragment(viewPager, 0) { ViewPager5Fragment() })
      add(instantiateFragment(viewPager, 1) { ViewPager6Fragment() })
      add(instantiateFragment(viewPager, 2) { ViewPager7Fragment() })
      add(instantiateFragment(viewPager, 3) { ViewPager8Fragment() })
    }
    val titleList = listOf("P4", "P5", "P6", "P7")

    viewPager.adapter = FragmentAdapter(supportFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragmentList, titleList)
    viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
      override fun onPageSelected(position: Int) {
        logger(TAG, "content2Container.viewPager onPageSelected: position=$position")
      }
    })
    tabLayout.setupWithViewPager(viewPager)
  }

  private fun initContent3() {
    val viewPager2 = container.content3Container.viewPager2
    val tabLayout = container.content3Container.tabLayout

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
        logger(TAG, "content3Container.viewPager2 onPageSelected: position=$position")
      }
    })
    TabLayoutMediator(tabLayout, viewPager2) { tab, pos ->
      tab.text = titleList[pos]
    }.attach()
  }

  private fun initContent4() {
    val tabLayout = container.content4Container.tabLayout
    val fragmentContainer = container.content4Container.container

    val fm = LifeFragmentManager(fragmentContainer.id, supportFragmentManager)
    fm.setupTabLayout(tabLayout)
  }

}