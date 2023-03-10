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
import com.drakeet.multitype.ViewDelegate
import com.weiwei.fluentview.ui.unit.dp
import com.weiwei.fluentview.view.matchParent
import com.weiwei.fluentview.view.recycler.recyclerParams

/**
 * @author weiwei
 * @date 2023.03.10
 */
class ListDifferData1ViewBinder : ViewDelegate<ListDifferData1, ListDifferData1Layout>() {

  override fun onCreateView(context: Context): ListDifferData1Layout {
    return ListDifferData1Layout(context).apply {
      layoutParams = recyclerParams(matchParent, 40.dp) {
      }
    }
  }

  override fun onBindViewHolder(holder: Holder<ListDifferData1Layout>, item: ListDifferData1, payloads: List<Any>) {
    if (payloads.isEmpty() || payloads[0] !is List<*>) {
      super.onBindViewHolder(holder, item, payloads)
    } else {
      val payloadValue = payloads[0] as List<*>
      Log.d("ListDiffer", "ListDifferData1ViewBinder onBindViewHolder: payload=$payloadValue")

      if (payloadValue.contains("Title")) {
        holder.view.tv.text = item.title
      }
      if (payloadValue.contains("Selected")) {
        holder.view.tv.setTextColor(if (item.selected) Color.RED else Color.BLACK)
      }
    }
  }

  override fun onBindView(view: ListDifferData1Layout, item: ListDifferData1) {
    view.tv.text = item.title
    view.tv.setTextColor(if (item.selected) Color.RED else Color.BLACK)
  }

}