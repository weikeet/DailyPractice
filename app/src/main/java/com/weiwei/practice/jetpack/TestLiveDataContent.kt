package com.weiwei.practice.jetpack

import android.content.Intent
import com.weiwei.main.ui.data.ModuleContent
import com.weiwei.main.ui.data.ModuleFunction

/**
 * @author Weicools
 *
 * @date 2021.08.08
 */
class TestLiveDataContent: ModuleContent() {
  override fun setupFunction(function: ModuleFunction) {
    function.apply {
      title = "Test LiveData"
      clickAction = {
        it.startActivity(Intent(it, TestLiveDataObserverActivity::class.java))
      }
    }
  }
}