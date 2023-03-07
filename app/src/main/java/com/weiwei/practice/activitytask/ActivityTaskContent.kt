package com.weiwei.practice.activitytask

import android.content.Intent
import com.weiwei.main.ui.data.ModuleContent
import com.weiwei.main.ui.data.ModuleFunction

/**
 * @author Weicools
 *
 * @date 2021.08.08
 */
class ActivityTaskContent : ModuleContent() {
  override fun setupFunction(function: ModuleFunction) {
    function.apply {
      title = "Activity Task"
      description = "测试 Activity Task top"
      clickAction = {
        it.startActivity(Intent(it, TaskTestActivity::class.java))
      }
    }
  }
}