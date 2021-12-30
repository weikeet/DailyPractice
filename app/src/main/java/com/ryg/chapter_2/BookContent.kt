package com.ryg.chapter_2

import android.content.Intent
import com.weiwei.practice.ui.main.data.ModuleContent
import com.weiwei.practice.ui.main.data.ModuleFunction

/**
 * @author Weicools
 *
 * @date 2021.08.08
 */
class BookContent : ModuleContent() {
  override fun setupFunction(function: ModuleFunction) {
    function.apply {
      title = "AIDL Sample"
      description = "Android 开发艺术探索 CH2 AIDL Sample"
      clickAction = {
        it.startActivity(Intent(it, BookActivity::class.java))
      }
    }
  }
}