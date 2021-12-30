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

package com.weiwei.practice.viewevent

import android.content.Context
import android.view.MotionEvent
import android.widget.LinearLayout

/**
 * @author weicools
 * @date 2021.11.15
 */
open class ViewEventBaseLayout(context: Context) : LinearLayout(context) {
  init {
    orientation = VERTICAL
  }

  val TAG = "ViewEventTag"
  val vTag = javaClass.simpleName

  val MotionEvent?.toText: String
    get() = when (this?.action) {
      MotionEvent.ACTION_DOWN -> "ACTION_DOWN"
      MotionEvent.ACTION_MOVE -> "ACTION_MOVE"
      MotionEvent.ACTION_UP -> "ACTION_UP"
      MotionEvent.ACTION_CANCEL -> "ACTION_CANCEL"
      MotionEvent.ACTION_OUTSIDE -> "ACTION_OUTSIDE"
      else -> "OTHER ${this?.action}"
    }
  //   String {
  // val ev = this ?: return "NULL"
  // return when (ev.action) {
  //   MotionEvent.ACTION_DOWN -> "ACTION_DOWN"
  //   MotionEvent.ACTION_MOVE -> "ACTION_MOVE"
  //   MotionEvent.ACTION_UP -> "ACTION_UP"
  //   MotionEvent.ACTION_CANCEL -> "ACTION_CANCEL"
  //   MotionEvent.ACTION_OUTSIDE -> "ACTION_OUTSIDE"
  //   else -> "OTHER ${ev.action}"
  // }
}