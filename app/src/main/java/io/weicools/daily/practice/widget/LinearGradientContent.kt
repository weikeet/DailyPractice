package io.weicools.daily.practice.widget

import android.content.Intent
import io.weicools.daily.practice.ui.main.data.ModuleContent
import io.weicools.daily.practice.ui.main.data.ModuleFunction

/**
 * @author Weicools
 *
 * @date 2021.08.08
 */
class LinearGradientContent: ModuleContent() {
  override fun setupFunction(function: ModuleFunction) {
    function.apply {
      title = "LinearGradient"
      description = "渐变背景渐变动画实现"
      clickAction = {
        it.startActivity(Intent(it, LinearGradientActivity::class.java))
      }
    }
  }
}