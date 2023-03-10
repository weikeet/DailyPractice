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
import com.drakeet.multitype.ViewDelegate
import com.weiwei.fluentview.ui.backgroundColor
import com.weiwei.fluentview.ui.unit.dp
import com.weiwei.fluentview.view.matchParent
import com.weiwei.fluentview.view.recycler.recyclerParams

/**
 * @author weiwei
 * @date 2023.03.10
 */
class ListDifferData2ViewBinder : ViewDelegate<ListDifferData2, ListDifferData2Layout>() {

  override fun onCreateView(context: Context): ListDifferData2Layout {
    return ListDifferData2Layout(context).apply {
      layoutParams = recyclerParams(matchParent, 64.dp) {
      }
    }
  }

  override fun onBindViewHolder(holder: Holder<ListDifferData2Layout>, item: ListDifferData2, payloads: List<Any>) {
    if (payloads.isEmpty() || payloads[0] !is List<*>) {
      super.onBindViewHolder(holder, item, payloads)
    } else {
      val payloadValue = payloads[0] as List<*>
      Log.d("ListDiffer", "ListDifferData2ViewBinder onBindViewHolder: payload=$payloadValue")

      if (payloadValue.contains("Text")) {
        holder.view.tv.text = item.text
      }
      if (payloadValue.contains("ImageResource")) {
        holder.view.iv.setImageResource(item.imageResource)
      }
      if (payloadValue.contains("Selected")) {
        holder.view.backgroundColor = if (item.selected) 0xFF00FF00.toInt() else 0xFFFFFFFF.toInt()
      }
    }
  }

  override fun onBindView(view: ListDifferData2Layout, item: ListDifferData2) {
    view.backgroundColor = if (item.selected) 0xFF00FF00.toInt() else 0xFFFFFFFF.toInt()
    view.iv.setImageResource(item.imageResource)
    view.tv.text = item.text
  }
}