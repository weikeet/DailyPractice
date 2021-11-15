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

package io.weicools.daily.practice.viewevent

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity

/**
 * @author weicools
 * @date 2021.11.15
 */
class ViewEventActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(ViewEventContentLayout(this))
  }

  override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
    return super.dispatchTouchEvent(ev)
  }

  override fun onTouchEvent(event: MotionEvent?): Boolean {
    return super.onTouchEvent(event)
  }
}