package com.weiwei.practice.lifecycle.sample

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.weiwei.practice.R
import com.weiwei.practice.lifecycle.logger
import java.util.*

/**
 * @author weicools
 * @date 2021.06.30
 */
class LifeFragmentManager(
  private val containerId: Int,
  private val supportFragmentManager: FragmentManager
) {

  private val tagList: MutableList<String> = ArrayList()

  fun setupTabLayout(tabLayout: TabLayout) {
    // if (true) {
    //   showHome()
    //   return
    // }
    tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
      override fun onTabSelected(tab: TabLayout.Tab) {
        if (tab.tag is String) {
          when (tab.tag as String) {
            TAG_HOME -> showHome()
            TAG_DASHBOARD -> showDashboard()
            TAG_NOTIFICATIONS -> showNotifications()
          }
        }
      }

      override fun onTabUnselected(tab: TabLayout.Tab) {}
      override fun onTabReselected(tab: TabLayout.Tab) {}
    })
    tabLayout.addTab(
      tabLayout.newTab()
        .setTag(TAG_HOME)
        .setText(R.string.title_home)
    )
    tabLayout.addTab(
      tabLayout.newTab()
        .setTag(TAG_DASHBOARD)
        .setText(R.string.title_dashboard)
    )
    tabLayout.addTab(
      tabLayout.newTab()
        .setTag(TAG_NOTIFICATIONS)
        .setText(R.string.title_notifications)
    )

    if (tagList.size > 0) {
      tagList.clear()
    }
    tagList.add(TAG_HOME)
    tagList.add(TAG_DASHBOARD)
    tagList.add(TAG_NOTIFICATIONS)
    showHome()
  }

  fun showHome() {
    logger("SampleLifeActivity", "showHome: ")
    if (!showFragment(TAG_HOME)) {
      addFragment(ViewPagerXFragment(), TAG_HOME)
    }
    hideOtherFragment(TAG_HOME)
  }

  fun showDashboard() {
    logger("SampleLifeActivity", "showDashboard: ")
    if (!showFragment(TAG_DASHBOARD)) {
      addFragment(ViewPagerYFragment(), TAG_DASHBOARD)
    }
    hideOtherFragment(TAG_DASHBOARD)
  }

  fun showNotifications() {
    logger("SampleLifeActivity", "showNotifications: ")
    if (!showFragment(TAG_NOTIFICATIONS)) {
      addFragment(ViewPagerZFragment(), TAG_NOTIFICATIONS)
    }
    hideOtherFragment(TAG_NOTIFICATIONS)
  }

  private fun hideOtherFragment(currentTag: String) {
    for (tag in tagList) {
      if (tag != currentTag) {
        hideFragment(tag)
      }
    }
  }

  private fun addFragment(fragment: Fragment, tag: String) {
    supportFragmentManager.beginTransaction()
      .add(containerId, fragment, tag)
      .commitNow()
  }

  private fun showFragment(tag: String): Boolean {
    val fragment = supportFragmentManager.findFragmentByTag(tag) ?: return false
    supportFragmentManager.beginTransaction()
      .show(fragment)
      .commitNow()
    return true
  }

  private fun hideFragment(tag: String): Boolean {
    val fragment = supportFragmentManager.findFragmentByTag(tag) ?: return false
    supportFragmentManager.beginTransaction()
      .hide(fragment)
      .commitNow()
    return true
  }

  companion object {
    const val TAG_HOME = "TAG_HOME"
    const val TAG_DASHBOARD = "TAG_DASHBOARD"
    const val TAG_NOTIFICATIONS = "TAG_NOTIFICATIONS"
  }
}