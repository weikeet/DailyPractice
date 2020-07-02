package io.weicools.daily.practice.viewlifecycle

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * @author weicools
 * @date 2020.05.14
 */
class ViewLifecycleActivity : AppCompatActivity() {
  companion object {
    const val TAG = "LifecycleViewActivity"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Log.d(TAG, "onCreate")
    val lifecycleView = LifecycleView(this)
    setContentView(lifecycleView)
    lifecycleView.visibility = View.GONE
  }

  override fun onStart() {
    super.onStart()
    Log.d(TAG, "onStart")
  }

  override fun onResume() {
    super.onResume()
    Log.d(TAG, "onResume")
  }

  override fun onWindowFocusChanged(hasFocus: Boolean) {
    super.onWindowFocusChanged(hasFocus)
    Log.d(TAG, "onWindowFocusChanged, hasFocus=$hasFocus")
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    Log.d(TAG, "onAttachedToWindow")
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    Log.d(TAG, "onDetachedFromWindow")
  }

  override fun onPause() {
    super.onPause()
    Log.d(TAG, "onPause")
  }

  override fun onStop() {
    super.onStop()
    Log.d(TAG, "onStop")
  }

  override fun onDestroy() {
    super.onDestroy()
    Log.d(TAG, "onDestroy")
  }
}