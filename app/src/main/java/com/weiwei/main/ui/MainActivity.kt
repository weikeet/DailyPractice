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
import androidx.lifecycle.lifecycleScope
import com.weiwei.fluentview.view.WindowInsetsEdgeDelegate
import com.weiwei.practice.R
import com.weiwei.practice.TimeRecorder.recordStopTime
import com.weiwei.practice.apm.ThreadMonitor
import kotlinx.coroutines.delay

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

    lifecycleScope.launchWhenStarted {
      while (true) {
        delay(5000L)
        ThreadMonitor.trackThreadCount()
      }
    }

    // val handlerScheduler = HandlerScheduler()
    // handlerScheduler.startScheduleRefresh()
    // val coroutineScheduler = CoroutineScheduler()
    // coroutineScheduler.startScheduleRefresh()

    // assets 视频首帧可以正常加载，只是 VideoView 无法播放 assets 的资源
    // VideoCacheManager.loadAssetsCover("Wakeup/melody_video_happy_ukulele.mp4")
    // VideoCacheManager.loadRawCover(applicationContext, R.raw.melody_video_ocean_waves)
    // VideoCacheManager.loadCover("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4")

    // // 不让 decorView 给状态栏导航栏留白
    // WindowCompat.setDecorFitsSystemWindows(this.window, false)
    //
    // this.window.statusBarColor = Color.TRANSPARENT
    // this.window.navigationBarColor = Color.TRANSPARENT

  }

}