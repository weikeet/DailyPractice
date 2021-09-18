package io.weicools.daily.practice.activity.task

import android.content.Intent
import io.weicools.daily.practice.ui.main.data.ModuleContent
import io.weicools.daily.practice.ui.main.data.ModuleFunction

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