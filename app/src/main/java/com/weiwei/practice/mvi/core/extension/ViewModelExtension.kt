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

package com.weiwei.practice.mvi.core.extension

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weiwei.practice.mvi.core.UiEvent
import com.weiwei.practice.mvi.core.UiState
import com.weiwei.practice.mvi.core.container.MutableFlowContainer
import com.weiwei.practice.mvi.core.container.MutableLiveDataContainer
import com.weiwei.practice.mvi.core.internal.FlowContainerImpl
import com.weiwei.practice.mvi.core.internal.LiveDataContainerImpl
import kotlinx.coroutines.CoroutineScope

/**
 * @author weiwei
 * @date 2023.02.11
 */

/**
 * 构建viewModel的Ui容器，存储Ui状态和一次性事件
 */
fun <STATE : UiState> ViewModel.flowContainer(
  initialState: STATE,
): Lazy<MutableFlowContainer<STATE, UiEvent>> {
  return FlowContainerLazy(initialState, viewModelScope)
}

private class FlowContainerLazy<STATE : UiState, EVENT : UiEvent>(
  initialState: STATE,
  scope: CoroutineScope,
) : Lazy<MutableFlowContainer<STATE, EVENT>> {

  private var cached: MutableFlowContainer<STATE, EVENT>? = null

  override val value: MutableFlowContainer<STATE, EVENT> =
    cached ?: FlowContainerImpl<STATE, EVENT>(initialState, scope).also { cached = it }

  override fun isInitialized(): Boolean = cached != null
}

fun <STATE : UiState> ViewModel.liveDataContainer(
  initialState: STATE,
): Lazy<MutableLiveDataContainer<STATE, UiEvent>> {
  return LiveDataContainerLazy(initialState)
}

private class LiveDataContainerLazy<STATE : UiState, EVENT : UiEvent>(
  initialState: STATE,
) : Lazy<MutableLiveDataContainer<STATE, EVENT>> {

  private var cached: MutableLiveDataContainer<STATE, EVENT>? = null

  override val value: MutableLiveDataContainer<STATE, EVENT> =
    cached ?: LiveDataContainerImpl<STATE, EVENT>(initialState).also { cached = it }

  override fun isInitialized(): Boolean = cached != null
}