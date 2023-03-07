package com.weiwei.practice.gesture

import android.content.Intent
import com.weiwei.main.ui.data.ModuleContent
import com.weiwei.main.ui.data.ModuleFunction

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