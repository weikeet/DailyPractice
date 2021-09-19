package io.weicools.daily.practice.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import io.weicools.daily.practice.R
import io.weicools.daily.practice.TimeRecorder.recordStopTime

/**
 * @author weicools
 */
class MainActivity : AppCompatActivity() {
  companion object {
    private const val TAG = "MainActivity_"

    private const val TAG_MAIN = "TAG_MAIN"
  }

  override fun onWindowFocusChanged(hasFocus: Boolean) {
    super.onWindowFocusChanged(hasFocus)
    if (hasFocus) {
      recordStopTime()
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val mainLayout = MainLayout(this)
    setContentView(mainLayout)

    setSupportActionBar(mainLayout.toolbar)

    supportFragmentManager.beginTransaction()
      .replace(mainLayout.containerView.id, MainFragment.newInstance(), TAG_MAIN)
      .commitNow()
  }

}