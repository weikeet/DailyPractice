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
import android.util.Log
import android.view.MotionEvent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ViewDelegate
import com.weiwei.core.recyclerview.FluentAdapter
import com.weiwei.fluentview.ui.horizontalPadding
import com.weiwei.fluentview.ui.unit.dp
import com.weiwei.fluentview.view.defaultParams
import com.weiwei.fluentview.view.matchParent

/**
 * @author weiwei
 * @date 2023.03.10
 */
class ListDayDataViewBinder : ViewDelegate<ListDayData, RecyclerView>() {

  class MyRecyclerView(context: Context) : RecyclerView(context) {
    override fun onTouchEvent(e: MotionEvent?): Boolean {
      if (e?.action == MotionEvent.ACTION_DOWN) {
        Log.d("FuckEvent", "rv-2 onTouchEvent: down")
      } else if (e?.action == MotionEvent.ACTION_UP) {
        Log.d("FuckEvent", "rv-2 onTouchEvent: up")
      }
      return super.onTouchEvent(e)
    }
  }

  override fun onCreateView(context: Context): RecyclerView {
    return MyRecyclerView(context).apply {
      layoutParams = defaultParams(matchParent, 60.dp)
      clipToPadding = false
      horizontalPadding = 16.dp
    }
  }

  private var fluentAdapter: FluentAdapter? = null

  override fun onBindView(view: RecyclerView, item: ListDayData) {
    if (fluentAdapter == null) {
      val layoutManager = LinearLayoutManager(view.context).apply {
        orientation = LinearLayoutManager.HORIZONTAL
      }
      view.layoutManager = layoutManager

      val fluentAdapter = FluentAdapter()
      view.adapter = fluentAdapter

      fluentAdapter.register(ListDayItemDataViewBinder())

      fluentAdapter.dataList = item.dayList
    }
  }
}