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
import com.weiwei.practice.mvi.core.container.MutableContainer
import com.weiwei.practice.mvi.core.extension.containers
import com.weiwei.practice.workout.state.WorkoutUiState
import com.weiwei.practice.workout.task.WorkoutTaskExecutor

/**
 * @author weiwei
 * @date 2023.02.11
 */
class WorkoutPlayViewModel : ViewModel() {

  val stateContainer: MutableContainer<WorkoutUiState, UiEvent> by containers(WorkoutUiState())

  val executor = WorkoutTaskExecutor(viewModelScope, stateContainer)

  init {
    executor.initailize()
  }
}