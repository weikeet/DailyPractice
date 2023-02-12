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

package com.weiwei.practice.mvi.core.container

import com.weiwei.practice.mvi.core.UiEvent
import com.weiwei.practice.mvi.core.UiState

/**
 * @author weiwei
 * @date 2023.02.11
 */
interface MutableContainer<STATE : UiState, EVENT : UiEvent> : Container<STATE, EVENT> {

  fun updateState(block: STATE.() -> STATE)

  fun sendEvent(event: EVENT)

}