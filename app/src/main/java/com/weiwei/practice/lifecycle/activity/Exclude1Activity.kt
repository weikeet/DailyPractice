package com.weiwei.practice.lifecycle.activity

import android.os.Bundle
import android.widget.FrameLayout
import com.weiwei.practice.lifecycle.LifeActivity

/**
 * @author weicools
 * @date 2021.07.05
 */
class Exclude1Activity : LifeActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(FrameLayout(this))
  }
}