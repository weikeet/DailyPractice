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

package com.weiwei.practice.di.sample.ui

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.weiwei.practice.di.sample.data.Log
import com.weiwei.practice.di.sample.util.DateFormatter

/**
 * RecyclerView adapter for the logs list.
 */
class LogsViewAdapter(
  private val logsDataSet: List<Log>,
  private val daterFormatter: DateFormatter
) : RecyclerView.Adapter<LogsViewAdapter.LogsViewHolder>() {

  class LogsViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogsViewHolder {
    val p = (parent.context.resources.displayMetrics.density * 8).toInt()
    return LogsViewHolder(
      AppCompatTextView(parent.context).apply {
        setPadding(p, p, p, p)
      }
    )
  }

  override fun getItemCount(): Int {
    return logsDataSet.size
  }

  @SuppressLint("SetTextI18n")
  override fun onBindViewHolder(holder: LogsViewHolder, position: Int) {
    val log = logsDataSet[position]
    holder.textView.text = "${log.msg}\n\t${daterFormatter.formatDate(log.timestamp)}"
  }
}
