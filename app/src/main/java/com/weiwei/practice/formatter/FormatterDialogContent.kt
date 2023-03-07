package com.weiwei.practice.formatter

import com.weiwei.main.ui.data.ModuleContent
import com.weiwei.main.ui.data.ModuleFunction

/**
 * @author Weicools
 *
 * @date 2021.08.08
 */
class FormatterDialogContent: ModuleContent() {
  override fun setupFunction(function: ModuleFunction) {
    function.apply {
      title = "Formatter thread safe"
      description = "格式化数据线程安全测试"
      clickAction = {
        val dialog = FormatterDialog(it)
        dialog.show()
      }
    }
  }
}