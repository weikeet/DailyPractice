package com.weiwei.practice.lifecycle

import android.content.Intent
import com.weiwei.practice.lifecycle.sample.SampleLifeActivity
import com.weiwei.practice.lifecycle.view.LifecycleViewActivity
import com.weiwei.practice.ui.main.data.ModuleContent
import com.weiwei.practice.ui.main.data.ModuleFunction

/**
 * @author Weicools
 *
 * @date 2021.08.08
 */
class LifecycleContent: ModuleContent() {
  override fun setupFunction(function: ModuleFunction) {
    function.apply {
      title = "Lifecycle"
      description = "测试 Activity Fragment View 生命周期"
      clickAction = {
        it.startActivity(Intent(it, SampleLifeActivity::class.java))
      }
    }
  }
}