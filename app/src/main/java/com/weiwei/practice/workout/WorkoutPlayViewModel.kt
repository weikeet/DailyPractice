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

package com.weiwei.practice.workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weiwei.practice.mvi.core.UiEvent
import com.weiwei.practice.mvi.core.container.MutableFlowContainer
import com.weiwei.practice.mvi.core.extension.flowContainer
import com.weiwei.practice.mvi.core.lifecycle.SingleLiveEvent
import com.weiwei.practice.workout.state.WorkoutUiState
import com.weiwei.practice.workout.task.WorkoutTaskExecutor

/**
 * @author weiwei
 * @date 2023.02.11
 */
class WorkoutPlayViewModel : ViewModel() {

  // onViewCreated:
  // observe: observer: @88ecd7d
  // observe: observer: @12590c3
  // observe: observer: @6bfc479
  // observe: super onChanged, observer: @88ecd7d
  // observe: notify observer, observer: @88ecd7d
  // onViewCreated: 111
  // observe: super onChanged, observer: @12590c3
  // observe: super onChanged, observer: @6bfc479
  // 由于 SingleLiveEvent 的特性，只有第一个 observer 会收到事件，pendingFlag 会被设置为 false，后续的 observer 不会收到事件
  val event: SingleLiveEvent<String> = SingleLiveEvent() // Only first observer will be notified
  // val event: LiveEvent<String> = LiveEvent() // Avoid receiving previous events
  // val event: MutableLiveData<String> = MutableLiveData()

  val stateContainer: MutableFlowContainer<WorkoutUiState, UiEvent> by flowContainer(WorkoutUiState())

  val executor = WorkoutTaskExecutor(viewModelScope, stateContainer)

  init {
    executor.initailize()
  }
}