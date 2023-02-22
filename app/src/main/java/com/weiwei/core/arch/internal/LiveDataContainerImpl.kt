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

package com.weiwei.core.arch.internal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.weiwei.core.arch.UiEvent
import com.weiwei.core.arch.UiState
import com.weiwei.core.arch.MutableLiveDataContainer
import com.weiwei.core.arch.lifecycle.LiveEvent

/**
 * @author weiwei
 * @date 2023.02.11
 */
internal class LiveDataContainerImpl<STATE : UiState, EVENT : UiEvent>(
  initialState: STATE,
) : MutableLiveDataContainer<STATE, EVENT> {

  private val mutableUiState: MutableLiveData<STATE> = MutableLiveData(initialState)

  private val mutableEvent: LiveEvent<EVENT> = LiveEvent()

  override val uiState: LiveData<STATE> get() = mutableUiState

  override val uiEvent: LiveEvent<EVENT> get() = mutableEvent

  override fun updateState(block: STATE.() -> STATE) {
    mutableUiState.value = uiState.value?.block()
  }

  override fun sendEvent(event: EVENT) {
    // mutableEvent.sendEvent(event)
  }
}