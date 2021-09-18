package io.weicools.daily.practice.dialog

import io.weicools.daily.practice.ui.main.data.ModuleContent
import io.weicools.daily.practice.ui.main.data.ModuleFunction

/**
 * @author Weicools
 *
 * @date 2021.08.08
 */
class DialogContent: ModuleContent() {
  override fun setupFunction(function: ModuleFunction) {
    function.apply {
      title = "FetchWidthDialog"
      description = "获取 Dialog 宽度"
      clickAction = {
        val dialog = FetchWidthDialog(it)
        dialog.show()
      }
    }
  }
}