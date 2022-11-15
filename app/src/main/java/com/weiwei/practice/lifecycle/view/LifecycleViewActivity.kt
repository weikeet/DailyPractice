package com.weiwei.practice.lifecycle.view

import android.os.Bundle
import android.util.Log
import com.weiwei.fluentview.view.WindowInsetsEdgeDelegate
import com.weiwei.practice.lifecycle.core.LifeActivity

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

    WindowInsetsEdgeDelegate(this).start()

    Log.d(TAG, "onCreate")
    setContentView(LifecycleViewContainer(this))
    // val lifecycleView = LifecycleView1(this)
    // lifecycleView.visibility = View.GONE
  }
}