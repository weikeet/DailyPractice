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

package com.weiwei.practice.widget.recycler

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.weiwei.practice.R

/**
 * @author weiwei
 * @date 2022.05.19
 */
class PayloadAdapter : RecyclerView.Adapter<PayloadAdapter.PayloadViewHolder>() {
  class PayloadViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleView = itemView.findViewById<TextView>(R.id.title)
    val messageView = itemView.findViewById<TextView>(R.id.message)
  }

  val dataList: ArrayList<PayloadEntity> = ArrayList()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PayloadViewHolder {
    Log.d("PayloadAdapter", "onCreateViewHolder: ")
    return PayloadViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_payload, parent, false))
  }

  override fun onBindViewHolder(holder: PayloadViewHolder, position: Int) {
    Log.d("PayloadAdapter", "onBindViewHolder: position=$position")

    holder.titleView.text = dataList[position].title
    holder.messageView.text = dataList[position].msg
  }

  override fun onBindViewHolder(holder: PayloadViewHolder, position: Int, payloads: MutableList<Any>) {
    Log.d("PayloadAdapter", "onBindViewHolder: position=$position payloads=$payloads start")

    if (payloads.isEmpty()) {
      super.onBindViewHolder(holder, position, payloads) // 默认调用 [onBindViewHolder(holder, position)]
    } else {

      holder.messageView.text = dataList[position].msg
      for (i in payloads.indices) {
        holder.messageView.append(" P$i=${payloads[i]} ")
      }
    }

    Log.d("PayloadAdapter", "onBindViewHolder: position=$position payloads=$payloads end")
  }

  override fun getItemCount(): Int = dataList.size
}