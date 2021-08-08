package io.weicools.daily.practice.room.ui

import android.content.Intent
import io.weicools.daily.practice.ui.main.data.ModuleContent
import io.weicools.daily.practice.ui.main.data.ModuleFunction

/**
 * @author Weicools
 *
 * @date 2021.08.08
 */
class RoomContent: ModuleContent() {
  override fun setupFunction(function: ModuleFunction) {
    function.apply {
      title = "Room"
      clickAction = {
        it.startActivity(Intent(it, RoomActivity::class.java))
      }
    }
  }
}