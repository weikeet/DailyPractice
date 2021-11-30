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

package io.weicools.daily.practice.cockroach

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.weicools.fluent.widget.dsl.linearLayout
import com.weicools.fluent.widget.dsl.textView
import com.weicools.fluent.widget.extensions.dp
import com.weicools.fluent.widget.extensions.gravity_center
import com.weicools.fluent.widget.extensions.paddings
import com.weicools.fluent.widget.params.linearParams
import kotlin.concurrent.thread

class NoCrashActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val tvMain = textView {
      layoutParams = linearParams { }
      paddings = 16.dp
      text = "TEST Main Crash"
    }

    val tvWork = textView {
      layoutParams = linearParams { topMargin = 20.dp }
      paddings = 16.dp
      text = "TEST Crash"
    }

    val view = linearLayout {
      orientation = LinearLayout.VERTICAL
      gravity = gravity_center
      addView(tvMain)
      addView(tvWork)
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
        thread {
          throw RuntimeException("test crash on thread delay 2s")
        }
      }, 2000L)
    }
  }
}