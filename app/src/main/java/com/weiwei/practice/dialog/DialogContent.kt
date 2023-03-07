package com.weiwei.practice.dialog

import com.weiwei.main.ui.data.ModuleContent
import com.weiwei.main.ui.data.ModuleFunction

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