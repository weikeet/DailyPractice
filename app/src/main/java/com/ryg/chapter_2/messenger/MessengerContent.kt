package com.ryg.chapter_2.messenger

import android.content.Intent
import io.weicools.daily.practice.ui.main.data.ModuleContent
import io.weicools.daily.practice.ui.main.data.ModuleFunction

/**
 * @author Weicools
 *
 * @date 2021.08.08
 */
class MessengerContent : ModuleContent() {
  override fun setupFunction(function: ModuleFunction) {
    function.apply {
      title = "Messenger Sample"
      description = "Android 开发艺术探索 CH2 Messenger Sample"
      clickAction = {
        it.startActivity(Intent(it, MessengerActivity::class.java))
      }
    }
  }
}