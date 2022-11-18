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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.weiwei.practice.di.sample.data.InMemoryLogger
import com.weiwei.practice.di.sample.data.LoggerDataSource
import com.weiwei.practice.di.sample.util.DateFormatter
import com.weiwei.practice.window.doOnApplyWindowInsets
import com.weiwei.practice.window.statusBarTop
import com.weiwei.practice.window.systemBarBottom
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Fragment that displays the database logs.
 */
@AndroidEntryPoint
class LogsFragment : Fragment() {

  @InMemoryLogger
  @Inject
  lateinit var logger: LoggerDataSource
  @Inject
  lateinit var dateFormatter: DateFormatter

  private lateinit var recyclerView: RecyclerView

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    recyclerView = RecyclerView(requireContext()).apply {
      layoutManager = LinearLayoutManager(requireContext())
      setHasFixedSize(true)
    }
    return recyclerView
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    view.doOnApplyWindowInsets { windowInsets ->
      view.updatePadding(top = windowInsets.statusBarTop, bottom = windowInsets.systemBarBottom)
    }
  }

  override fun onResume() {
    super.onResume()

    logger.getAllLogs { logs ->
      recyclerView.adapter = LogsViewAdapter(logs, dateFormatter)
    }
  }
}
