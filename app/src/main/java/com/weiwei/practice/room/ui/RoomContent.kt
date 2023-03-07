package com.weiwei.practice.room.ui

import android.content.Intent
import com.weiwei.main.ui.data.ModuleContent
import com.weiwei.main.ui.data.ModuleFunction

/**
 * @author Weicools
 *
 * @date 2021.08.08
 */
class RoomContent: ModuleContent() {
  override fun setupFunction(function: ModuleFunction) {
    function.apply {
      title = "Room"
      description = "Room 数据库使用"
      clickAction = {
        it.startActivity(Intent(it, RoomActivity::class.java))
      }
    }
  }
}