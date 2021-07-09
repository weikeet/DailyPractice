package io.weicools.daily.practice.lifecycle.sample

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import io.weicools.daily.practice.R

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
  }
}