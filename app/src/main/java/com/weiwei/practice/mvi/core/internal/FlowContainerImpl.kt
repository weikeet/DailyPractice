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

package com.weiwei.practice.mvi.core.internal

import com.weiwei.practice.mvi.core.UiEvent
import com.weiwei.practice.mvi.core.UiState
import com.weiwei.practice.mvi.core.container.MutableFlowContainer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * @author weiwei
 * @date 2023.02.11
 */
internal class FlowContainerImpl<STATE : UiState, EVENT : UiEvent>(
  initialState: STATE,
  private val scope: CoroutineScope,
) : MutableFlowContainer<STATE, EVENT> {

  private val mutableUiStateFlow: MutableStateFlow<STATE> = MutableStateFlow(initialState)

  private val mutableEventFlow: MutableSharedFlow<EVENT> = MutableSharedFlow()

  override val uiStateFlow: StateFlow<STATE> get() = mutableUiStateFlow

  override val uiEventFlow: Flow<EVENT> get() = mutableEventFlow

  override fun updateState(block: STATE.() -> STATE) {
    mutableUiStateFlow.value = uiStateFlow.value.block()
  }

  override fun sendEvent(event: EVENT) {
    scope.launch {
      mutableEventFlow.emit(event)
    }
  }
}