package com.weiwei.practice.lifecycle.sample

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.weiwei.fluentview.view.WindowInsetsEdgeDelegate
import com.weiwei.practice.R

/**
 * @author weicools
 * @date 2021.03.31
 */
class SampleLifeSecondActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val container = FrameLayout(this).apply {
      id = View.generateViewId()
      setBackgroundResource(R.color.colorAccent)
    }
    setContentView(container)

    WindowInsetsEdgeDelegate(this).start()
  }
}