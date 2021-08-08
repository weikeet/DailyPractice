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
      clickAction = {
        it.startActivity(Intent(it, LinearGradientActivity::class.java))
      }
    }
  }
}