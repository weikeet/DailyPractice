package com.weiwei.practice.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.weiwei.practice.R
import com.weiwei.practice.TimeRecorder.recordStopTime
import com.weiwei.practice.window.delegate.EdgeInsetDelegate

/**
 * @author weicools
 */
class MainActivity : AppCompatActivity() {
  companion object {
    private const val TAG = "MainActivity_"

    private const val tag_main = "TAG_MAIN"
  }

  override fun onWindowFocusChanged(hasFocus: Boolean) {
    super.onWindowFocusChanged(hasFocus)
    if (hasFocus) {
      recordStopTime()
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_main)

    EdgeInsetDelegate(this)
      .setNavigationBarColor(0x20000000)
      .start()

    // // 不让 decorView 给状态栏导航栏留白
    // WindowCompat.setDecorFitsSystemWindows(this.window, false)
    //
    // this.window.statusBarColor = Color.TRANSPARENT
    // this.window.navigationBarColor = Color.TRANSPARENT
  }

}