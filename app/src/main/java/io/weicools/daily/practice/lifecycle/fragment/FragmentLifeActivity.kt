package io.weicools.daily.practice.lifecycle.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import io.weicools.daily.practice.FragmentAdapter
import io.weicools.daily.practice.ktx.dp
import io.weicools.daily.practice.ktx.matchParent

/**
 * @author weicools
 * @date 2021.03.31
 */
class FragmentLifeActivity : AppCompatActivity() {
  companion object {
    private const val TAG = "FragmentLifeActivity"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Log.d(LifeConstants.TAG, "$TAG onCreate: ")

    val container = FrameLayout(this).apply { id = View.generateViewId() }
    setContentView(container)

    val viewPager = ViewPager(this).apply {
      id = View.generateViewId()
      layoutParams = FrameLayout.LayoutParams(matchParent, matchParent).apply {
        bottomMargin = 48.dp
      }
    }.also { container.addView(it) }

    val tabLayout = TabLayout(this).apply {
      layoutParams = FrameLayout.LayoutParams(matchParent, 48.dp).apply {
        gravity = Gravity.BOTTOM
      }
    }.also { container.addView(it) }

    container.doOnPreDraw {
      Log.d(LifeConstants.TAG, "$TAG doOnPreDraw: ")
    }

    val fragmentList = ArrayList<Fragment>().apply {
      add(ViewPagerFragment.newInstance(1))
      add(ViewPagerFragment.newInstance(2))
      add(ViewPagerFragment.newInstance(3))
      add(ViewPagerFragment.newInstance(4))
    }
    val titleList = ArrayList<String>().apply {
      add("Page1")
      add("Page2")
      add("Page3")
      add("Page4")
    }

    viewPager.adapter = FragmentAdapter(supportFragmentManager, fragmentList, titleList)
    viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
      override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
        Log.d(LifeConstants.TAG, "$TAG onPageSelected: position=$position")
      }
    })
    tabLayout.setupWithViewPager(viewPager)
  }

  override fun onNewIntent(intent: Intent?) {
    super.onNewIntent(intent)
    Log.d(LifeConstants.TAG, "$TAG onNewIntent: ")
  }

  override fun onRestart() {
    super.onRestart()
    Log.d(LifeConstants.TAG, "$TAG onRestart: ")
  }

  override fun onStart() {
    super.onStart()
    Log.d(LifeConstants.TAG, "$TAG onStart: ")
  }

  override fun onResume() {
    super.onResume()
    Log.d(LifeConstants.TAG, "$TAG onResume: ")
  }

  override fun onPause() {
    super.onPause()
    Log.d(LifeConstants.TAG, "$TAG onPause: ")
  }

  override fun onStop() {
    super.onStop()
    Log.d(LifeConstants.TAG, "$TAG onStop: ")
  }

  override fun onDestroy() {
    super.onDestroy()
    Log.d(LifeConstants.TAG, "$TAG onDestroy: ")
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    Log.d(LifeConstants.TAG, "$TAG onAttachedToWindow: ")
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    Log.d(LifeConstants.TAG, "$TAG onDetachedFromWindow: ")
  }

  override fun onWindowFocusChanged(hasFocus: Boolean) {
    super.onWindowFocusChanged(hasFocus)
    Log.d(LifeConstants.TAG, "$TAG onWindowFocusChanged: $hasFocus")
  }
}