package com.weiwei.practice.lifecycle.sample

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.updatePadding
import com.weiwei.fluentview.view.WindowInsetsEdgeDelegate
import com.weiwei.practice.lifecycle.core.LifeActivity
import com.weiwei.practice.window.doOnApplyWindowInsets
import com.weiwei.practice.window.navigationBarBottom

/**
 * @author weicools
 * @date 2021.03.31
 */
class SampleLifeActivity : LifeActivity() {

  private lateinit var container: LifeContentContainer

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    WindowInsetsEdgeDelegate(this).start()

    container = LifeContentContainer(this)
    setContentView(container)

    container.doOnApplyWindowInsets { windowInsets, padding, margin ->
      container.updatePadding(bottom = padding.bottom + windowInsets.navigationBarBottom)
    }

    val viewModel: SampleLifeViewModel by viewModels()
    container.content1Container.initContent(supportFragmentManager)
    container.content2Container.initContent(supportFragmentManager)
    container.content3Container.initContent(supportFragmentManager, lifecycle)
    container.content4Container.initContent(supportFragmentManager, viewModel, this)
  }
}