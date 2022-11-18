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

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.weiwei.practice.app.PracticeApp
import com.weiwei.practice.di.sample.data.LoggerLocalDataSource
import com.weiwei.practice.di.sample.util.DateFormatter
import com.weiwei.practice.window.doOnApplyWindowInsets
import com.weiwei.practice.window.statusBarTop
import com.weiwei.practice.window.systemBarBottom

/**
 * Fragment that displays the database logs.
 */
class LogsFragment : Fragment() {

  private lateinit var logger: LoggerLocalDataSource
  private lateinit var dateFormatter: DateFormatter

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

  override fun onAttach(context: Context) {
    super.onAttach(context)

    populateFields(context)
  }

  private fun populateFields(context: Context) {
    logger = (context.applicationContext as PracticeApp).serviceLocator.loggerLocalDataSource
    dateFormatter = (context.applicationContext as PracticeApp).serviceLocator.provideDateFormatter()
  }

  override fun onResume() {
    super.onResume()

    logger.getAllLogs { logs ->
      recyclerView.adapter = LogsViewAdapter(logs, dateFormatter)
    }
  }
}
