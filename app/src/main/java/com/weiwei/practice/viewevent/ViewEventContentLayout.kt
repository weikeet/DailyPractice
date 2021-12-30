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
import android.util.Log
import android.view.MotionEvent
import com.weicools.fluent.widget.dsl.autoAddView
import com.weicools.fluent.widget.extensions.background_colorResource
import com.weicools.fluent.widget.extensions.dp
import com.weicools.fluent.widget.params.linearParams
import com.weicools.fluent.widget.params.matchParent
import com.weiwei.practice.R

/**
 * @author weicools
 * @date 2021.11.15
 */
class ViewEventContentLayout(context: Context) : ViewEventBaseLayout(context) {

  init {
    orientation = VERTICAL
  }

  val viewGroup1Layout = ViewGroup1Layout(context).autoAddView(this) {
    layoutParams = linearParams(matchParent, 120.dp) {
    }
    background_colorResource = R.color.colorPrimary16
  }

  override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
    Log.d(TAG, "$vTag dispatchTouchEvent: ${ev.toText}")
    return super.dispatchTouchEvent(ev)
  }

  override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
    Log.d(TAG, "$vTag onInterceptTouchEvent: ${ev.toText}")
    return super.onInterceptTouchEvent(ev)
  }

  override fun onTouchEvent(event: MotionEvent?): Boolean {
    Log.d(TAG, "$vTag onTouchEvent: ${event.toText}")
    return super.onTouchEvent(event)
  }
}