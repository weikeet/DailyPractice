package com.weiwei.practice.adb

import android.content.Intent
import com.weiwei.practice.ui.main.data.ModuleContent
import com.weiwei.practice.ui.main.data.ModuleFunction

/**
 * @author Weicools
 *
 * @date 2021.08.08
 */
class AdbTestContent : ModuleContent() {
  override fun setupFunction(function: ModuleFunction) {
    function.apply {
      title = "Android adb"
      description = "测试 adb"
      clickAction = {
        it.startActivity(Intent(it, AdbTestActivity::class.java))
      }
    }
  }
}