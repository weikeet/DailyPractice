package com.weiwei.practice.jetpack

import androidx.navigation.NavController
import com.weiwei.main.ui.data.ModuleContent
import com.weiwei.main.ui.data.ModuleFunction
import com.weiwei.practice.R

/**
 * @author Weicools
 *
 * @date 2021.08.08
 */
class TestLiveEventContent(val navController: NavController) : ModuleContent() {
  override fun setupFunction(function: ModuleFunction) {
    function.apply {
      title = "Test LiveEvent"
      clickAction = {
        navController.navigate(R.id.action_MainFragment_to_TestLiveEventFirstFragment)
      }
    }
  }
}