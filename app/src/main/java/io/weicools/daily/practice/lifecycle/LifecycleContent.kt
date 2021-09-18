package io.weicools.daily.practice.lifecycle

import android.content.Intent
import io.weicools.daily.practice.ui.main.data.ModuleContent
import io.weicools.daily.practice.ui.main.data.ModuleFunction

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
        it.startActivity(Intent(it, LifeActivity::class.java))
      }
    }
  }
}