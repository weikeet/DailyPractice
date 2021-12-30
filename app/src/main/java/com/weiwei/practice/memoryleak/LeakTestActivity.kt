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
import android.os.Message
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.weicools.fluent.widget.dsl.button
import com.weicools.fluent.widget.dsl.linearLayout
import com.weicools.fluent.widget.extensions.dp
import com.weicools.fluent.widget.params.linearParams
import com.weicools.fluent.widget.params.matchParent

class LeakTestActivity : AppCompatActivity() {

  private val handler = Handler(Looper.getMainLooper(), object : Handler.Callback {
    override fun handleMessage(msg: Message): Boolean {
      if (msg.what == 100) {
        handlerButton.text = "handleMessage"
      }
      return true
    }
  })

  private lateinit var handlerButton: Button
  private lateinit var threadButton: Button

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

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

    handlerButton.setOnClickListener {
      val msg = handler.obtainMessage().apply { what = 100 }
      handler.sendMessageDelayed(msg, 20000L)
    }

    threadButton.setOnClickListener {
      Thread {
        Thread.sleep(20000L)
        handler.sendEmptyMessage(0)
      }.start()
    }
  }
}