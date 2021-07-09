package io.weicools.daily.practice.lifecycle.view

import android.os.Bundle
import android.util.Log
import io.weicools.daily.practice.lifecycle.LifeActivity

/**
 * @author weicools
 * @date 2020.05.14
 */
class LifecycleViewActivity : LifeActivity() {
  companion object {
    const val TAG = "LifecycleViewActivity"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Log.d(TAG, "onCreate")
    setContentView(LifecycleViewContainer(this))
    // val lifecycleView = LifecycleView1(this)
    // lifecycleView.visibility = View.GONE
  }
}