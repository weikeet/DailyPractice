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

package com.weiwei.practice.differ

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import com.drakeet.multitype.ViewDelegate
import com.weiwei.fluentview.ui.backgroundColor
import com.weiwei.fluentview.ui.gravityCenter
import com.weiwei.fluentview.ui.unit.dp
import com.weiwei.fluentview.view.appcompat.textView
import com.weiwei.fluentview.view.defaultParams
import com.weiwei.fluentview.view.horizontalMargin
import com.weiwei.fluentview.view.matchParent
import com.weiwei.fluentview.view.recycler.recyclerParams

/**
 * @author weiwei
 * @date 2023.03.10
 */
class ListDayItemDataViewBinder : ViewDelegate<ListDayItemData, AppCompatTextView>() {
  class MyTextView(context: Context) : AppCompatTextView(context) {
    override fun onTouchEvent(event: MotionEvent?): Boolean {
      if (event?.action == MotionEvent.ACTION_DOWN) {
        Log.d("FuckEvent", "tv-1 onTouchEvent: down")
      } else if (event?.action == MotionEvent.ACTION_UP) {
        Log.d("FuckEvent", "tv-1 onTouchEvent: up")
      }
      return super.onTouchEvent(event)
    }
  }

  override fun onCreateView(context: Context): AppCompatTextView {
    return MyTextView(context).apply {
      layoutParams = recyclerParams(72.dp, matchParent) {
        horizontalMargin = 8.dp
      }
      backgroundColor = Color.CYAN
      gravity = gravityCenter
    }
  }

  override fun onBindView(view: AppCompatTextView, item: ListDayItemData) {
    view.setOnClickListener {
      Toast.makeText(view.context, "ListDayItemData click: ${item.text}", Toast.LENGTH_SHORT).show()
    }
    view.text = item.text
  }
}