package com.weiwei.practice.memoryleak

import android.content.Intent
import com.weiwei.main.ui.data.ModuleContent
import com.weiwei.main.ui.data.ModuleFunction

/**
 * @author Weicools
 *
 * @date 2021.08.08
 */
class MemoryLeakContent : ModuleContent() {
  override fun setupFunction(function: ModuleFunction) {
    function.apply {
      title = "MemoryLeak"
      description = "测试 memory leak"
      clickAction = {
        it.startActivity(Intent(it, LeakTestActivity::class.java))
      }
    }
  }
}