package com.weiwei.practice.shadow

import android.content.Intent
import com.weiwei.main.ui.data.ModuleContent
import com.weiwei.main.ui.data.ModuleFunction

/**
 * @author Weicools
 *
 * @date 2021.08.08
 */
class ShadowContainerContent : ModuleContent() {
  override fun setupFunction(function: ModuleFunction) {
    function.apply {
      title = "Shadow plugin"
      clickAction = {
        PluginManager.get().setContext(it)
        // adb push sample-plugin/build/outputs/apk/debug/sample-plugin-debug.apk /sdcard/Android/data/com.weiwei.practice/files/dex/sample-plugin-debug.apk
        PluginManager.get().loadPlugin("sample-plugin-debug.apk/sample-plugin-debug.apk")

        it.startActivity(Intent(it, PluginContainerActivity::class.java))
      }
    }
  }
}