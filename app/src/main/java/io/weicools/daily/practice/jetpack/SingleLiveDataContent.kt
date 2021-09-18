package io.weicools.daily.practice.jetpack

import android.content.Intent
import io.weicools.daily.practice.ui.main.data.ModuleContent
import io.weicools.daily.practice.ui.main.data.ModuleFunction

/**
 * @author Weicools
 *
 * @date 2021.08.08
 */
class SingleLiveDataContent: ModuleContent() {
  override fun setupFunction(function: ModuleFunction) {
    function.apply {
      title = "SingleLiveData"
      description = "SingleLiveData 测试"
      clickAction = {
        it.startActivity(Intent(it, TestLiveDataObserverActivity::class.java))
      }
    }
  }
}