package io.weicools.daily.practice.gesture

import android.content.Intent
import io.weicools.daily.practice.ui.main.data.ModuleContent
import io.weicools.daily.practice.ui.main.data.ModuleFunction

/**
 * @author Weicools
 *
 * @date 2021.08.08
 */
class GestureContent: ModuleContent() {
  override fun setupFunction(function: ModuleFunction) {
    function.apply {
      title = "ActivityTask"
      clickAction = {
        it.startActivity(Intent(it, GestureDetectorActivity::class.java))
      }
    }
  }
}