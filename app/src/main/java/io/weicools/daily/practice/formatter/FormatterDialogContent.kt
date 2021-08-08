package io.weicools.daily.practice.formatter

import io.weicools.daily.practice.ui.main.data.ModuleContent
import io.weicools.daily.practice.ui.main.data.ModuleFunction

/**
 * @author Weicools
 *
 * @date 2021.08.08
 */
class FormatterDialogContent: ModuleContent() {
  override fun setupFunction(function: ModuleFunction) {
    function.apply {
      title = "Formatter thread safe"
      clickAction = {
        val dialog = FormatterDialog(it)
        dialog.show()
      }
    }
  }
}