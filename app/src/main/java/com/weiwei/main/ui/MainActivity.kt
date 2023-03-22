/*
 * Copyright (c) 2020 Weiwei
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *    https://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.weiwei.main.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.weiwei.fluentview.view.WindowInsetsEdgeDelegate
import com.weiwei.practice.R
import com.weiwei.practice.TimeRecorder.recordStopTime

/**
 * @author weicools
 */
class MainActivity : AppCompatActivity() {
  companion object {
    private const val TAG = "MainActivity_"

    private const val tag_main = "TAG_MAIN"
  }

  override fun onWindowFocusChanged(hasFocus: Boolean) {
    super.onWindowFocusChanged(hasFocus)
    if (hasFocus) {
      recordStopTime()
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_main)

    WindowInsetsEdgeDelegate(this)
      // .setNavigationBarColor(0x20000000)
      .start()

    // // 不让 decorView 给状态栏导航栏留白
    // WindowCompat.setDecorFitsSystemWindows(this.window, false)
    //
    // this.window.statusBarColor = Color.TRANSPARENT
    // this.window.navigationBarColor = Color.TRANSPARENT

    // Log.d("TestEvent", "onCreate: ")
    // sharedViewModel.event.observe(this) {
    //   Log.d("TestEvent", "onCreate: 111-$it")
    // }
    // sharedViewModel.event.observe(this) {
    //   Log.d("TestEvent", "onCreate: 222-$it")
    // }
    //
    // mainHandler.postDelayed({
    //   sharedViewModel.event.setValue("test1")
    // }, 1000)
    // mainHandler.postDelayed({
    //   sharedViewModel.event.observe(this) {
    //     Log.d("TestEvent", "onCreate: 123-$it")
    //   }
    //   sharedViewModel.event.setValue("test2")
    //   sharedViewModel.event.observe(this) {
    //     Log.d("TestEvent", "onCreate: 456-$it")
    //   }
    // }, 3000)
  }

}