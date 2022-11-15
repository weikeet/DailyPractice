/*
 * Copyright (c) 2020 Weicools
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

package com.weiwei.practice.memoryleak

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.weiwei.fluentview.ui.unit.dp
import com.weiwei.fluentview.view.WindowInsetsEdgeDelegate
import com.weiwei.fluentview.view.appcompat.button
import com.weiwei.fluentview.view.linearLayout
import com.weiwei.fluentview.view.linearParams
import com.weiwei.fluentview.view.matchParent

/**
 * [内存泄露监测与问题排查](https://blog.csdn.net/wumeixinjiazu/article/details/124347893)
 * docs: [DailyStudy/Performance/MemoryLeak-监测和排查]
 */
class LeakTestActivity : AppCompatActivity() {
  companion object {
    @JvmStatic
    private val viewList: ArrayList<View> = ArrayList()
  }

  private val handler = Handler(Looper.getMainLooper()) { msg ->
    if (msg.what == 100) {
      handlerButton.text = "handleMessage"
    }
    true
  }

  private lateinit var handlerButton: Button
  private lateinit var threadButton: Button

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    WindowInsetsEdgeDelegate(this).start()

    val rootView = linearLayout {
      orientation = LinearLayout.VERTICAL

      handlerButton = button {
        layoutParams = linearParams(matchParent, 48.dp) {
          topMargin = 16.dp
        }
        text = "test handler delay leak"
      }

      threadButton = button {
        layoutParams = linearParams(matchParent, 48.dp) {
          topMargin = 16.dp
        }
        text = "test run thread leak"
      }

      button {
        layoutParams = linearParams(matchParent, 48.dp) {
          topMargin = 16.dp
        }
        setOnClickListener {
          finish()
        }
        text = "finish activity"
      }
    }

    setContentView(rootView)

    viewList.add(rootView)
    viewList.add(handlerButton)
    viewList.add(threadButton)

    handlerButton.setOnClickListener {
      val msg = handler.obtainMessage().apply { what = 100 }
      handler.sendMessageDelayed(msg, 8000L)
    }

    threadButton.setOnClickListener {
      Thread {
        Thread.sleep(8000L)
        handler.sendEmptyMessage(0)
      }.start()
    }
  }
}