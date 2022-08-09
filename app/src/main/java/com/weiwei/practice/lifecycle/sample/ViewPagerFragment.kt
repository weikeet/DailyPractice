package com.weiwei.practice.lifecycle.sample

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.weiwei.practice.lifecycle.core.LifeFragment
import com.weiwei.practice.lifecycle.core.widget.LifeTextView

/**
 * @author weicools
 * @date 2021.03.31
 */

class ViewPager1Fragment : ViewPagerFragment()
class ViewPager2Fragment : ViewPagerFragment()
class ViewPager3Fragment : ViewPagerFragment()
class ViewPager4Fragment : ViewPagerFragment()

class ViewPager5Fragment : ViewPagerFragment()
class ViewPager6Fragment : ViewPagerFragment()
class ViewPager7Fragment : ViewPagerFragment()
class ViewPager8Fragment : ViewPagerFragment()

class ViewPagerAFragment : ViewPagerFragment()
class ViewPagerBFragment : ViewPagerFragment()
class ViewPagerCFragment : ViewPagerFragment()
class ViewPagerDFragment : ViewPagerFragment()

class ViewPagerXFragment : ViewPagerFragment()
class ViewPagerYFragment : ViewPagerFragment()
class ViewPagerZFragment : ViewPagerFragment()

open class ViewPagerFragment : LifeFragment() {

  override fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    return LifeTextView(requireContext()).apply {
      val simpleName = this@ViewPagerFragment.javaClass.simpleName

      viewTag = "${simpleName}#TextView"
      layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
      gravity = Gravity.CENTER
      textSize = 16f
      text = simpleName

      setOnClickListener {
        val activity = requireActivity()
        activity.startActivity(Intent(activity, SampleLifeSecondActivity::class.java))
      }
    }
  }

}