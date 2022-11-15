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

package com.weiwei.practice.crash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.weiwei.fluentview.ui.allPadding
import com.weiwei.fluentview.ui.gravityCenter
import com.weiwei.fluentview.ui.unit.dp
import com.weiwei.fluentview.view.WindowInsetsEdgeDelegate
import com.weiwei.fluentview.view.linearLayout
import com.weiwei.fluentview.view.linearParams
import com.weiwei.fluentview.view.matchParent
import com.weiwei.fluentview.view.material.materialButton
import com.weiwei.fluentview.view.wrapContent
import com.weiwei.practice.ndk.HelloWorld
import com.weiwei.task.scheduler.TaskScheduler

class CrashTestActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    WindowInsetsEdgeDelegate(this).start()

    val tvMain = materialButton {
      layoutParams = linearParams(matchParent, wrapContent) { }
      allPadding = 16.dp
      text = "TEST Main Crash"
    }

    val tvWork = materialButton {
      layoutParams = linearParams(matchParent, wrapContent) { topMargin = 20.dp }
      allPadding = 16.dp
      text = "TEST Crash"
    }

    val tvNative = materialButton {
      layoutParams = linearParams(matchParent, wrapContent) { topMargin = 20.dp }
      allPadding = 16.dp
      text = "TEST native crash"
    }

    val view = linearLayout {
      orientation = LinearLayout.VERTICAL
      gravity = gravityCenter
      addView(tvMain)
      addView(tvWork)
      addView(tvNative)
    }

    setContentView(view)

    if (false) {
      throw RuntimeException("test crash on activity lifecycle")
    }

    val handler = Handler(Looper.getMainLooper())

    tvMain.setOnClickListener {
      handler.postDelayed({
        throw RuntimeException("test crash on handle delay 2s")
      }, 2000L)
    }

    tvWork.setOnClickListener {
      handler.postDelayed({
        // thread {
        //   throw RuntimeException("test crash on thread delay 2s")
        // }
        TaskScheduler.executeTask {
          throw RuntimeException("test crash on thread delay 2s")
        }
      }, 2000L)
    }

    tvNative.setOnClickListener {
      HelloWorld().testNativeCrash()
    }
  }
}