package com.weiwei.practice.asyncui

import android.content.Intent
import com.weiwei.practice.ui.main.data.ModuleContent
import com.weiwei.practice.ui.main.data.ModuleFunction

/**
 * @author Weicools
 *
 * @date 2021.08.08
 */
class AsyncUiContent : ModuleContent() {
  override fun setupFunction(function: ModuleFunction) {
    function.apply {
      title = "AsyncUi"
      description = "异步测量布局 UI"
      clickAction = {
        it.startActivity(Intent(it, AsyncUiActivity::class.java))
      }
    }
  }
}