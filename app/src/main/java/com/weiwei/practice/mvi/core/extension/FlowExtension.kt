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

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.weiwei.practice.mvi.core.UiEvent
import com.weiwei.practice.mvi.core.UiState
import com.weiwei.practice.mvi.core.internal.StateTuple2
import com.weiwei.practice.mvi.core.internal.StateTuple3
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.reflect.KProperty1

/**
 * @author weiwei
 * @date 2023.02.11
 */

fun <STATE : UiState> Flow<STATE>.collectState(
  lifecycleOwner: LifecycleOwner,
  lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
  action: StateCollector<STATE>.() -> Unit
) {
  StateCollector(this@collectState, lifecycleOwner, lifecycleState).action()
}

fun <EVENT : UiEvent> Flow<EVENT>.collectEvent(
  lifecycleOwner: LifecycleOwner,
  lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
  action: (EVENT) -> Unit
) {
  lifecycleOwner.lifecycleScope.launch {
    lifecycleOwner.repeatOnLifecycle(lifecycleState) {
      this@collectEvent.collect {
        action(it)
      }
    }
  }
}

class StateCollector<STATE : UiState>(
  private val flow: Flow<STATE>,
  private val lifecycleOwner: LifecycleOwner,
  private val lifecycleState: Lifecycle.State,
) {

  fun collect(
    action: (STATE) -> Unit
  ) {
    lifecycleOwner.lifecycleScope.launch {
      lifecycleOwner.repeatOnLifecycle(lifecycleState) {
        flow.collect {
          action(it)
        }
      }
    }
  }

  fun <A> collectPartial(
    propA: KProperty1<STATE, A>,
    block: (A) -> Unit,
  ) {
    lifecycleOwner.lifecycleScope.launch {
      lifecycleOwner.repeatOnLifecycle(lifecycleState) {
        flow
          .map { state ->
            propA.get(state)
          }
          .distinctUntilChanged()
          .collect { partialState ->
            block(partialState)
          }
      }
    }
  }

  fun <A, B> collectPartial(
    propA: KProperty1<STATE, A>,
    propB: KProperty1<STATE, B>,
    block: (A, B) -> Unit,
  ) {
    lifecycleOwner.lifecycleScope.launch {
      lifecycleOwner.repeatOnLifecycle(lifecycleState) {
        flow
          .map { state ->
            StateTuple2(propA.get(state), propB.get(state))
          }
          .distinctUntilChanged()
          .collect { partialState ->
            block(partialState.a, partialState.b)
          }
      }
    }
  }

  fun <A, B, C> collectPartial(
    propA: KProperty1<STATE, A>,
    propB: KProperty1<STATE, B>,
    propC: KProperty1<STATE, C>,
    block: (A, B, C) -> Unit,
  ) {
    lifecycleOwner.lifecycleScope.launch {
      lifecycleOwner.repeatOnLifecycle(lifecycleState) {
        flow
          .map { state ->
            StateTuple3(propA.get(state), propB.get(state), propC.get(state))
          }
          .distinctUntilChanged()
          .collect { partialState ->
            block(partialState.a, partialState.b, partialState.c)
          }
      }
    }
  }
}
