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

package com.weiwei.core.arch

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.map
import com.weiwei.core.arch.internal.StateTuple2
import com.weiwei.core.arch.internal.StateTuple3
import com.weiwei.core.arch.lifecycle.LiveEvent
import kotlin.reflect.KProperty1

/**
 * @author weiwei
 * @date 2023.02.12
 */

fun <T> LiveData<List<T>>.observeEvent(lifecycleOwner: LifecycleOwner, action: (T) -> Unit) {
  this.observe(lifecycleOwner) {
    it.forEach { event ->
      action.invoke(event)
    }
  }
}

fun <T, A> LiveData<T>.observeState(
  lifecycleOwner: LifecycleOwner,
  propA: KProperty1<T, A>,
  action: (A) -> Unit
) {
  this.map {
    propA.get(it)
  }.distinctUntilChanged().observe(lifecycleOwner) { a ->
    action.invoke(a)
  }
}

fun <T, A, B> LiveData<T>.observeState(
  lifecycleOwner: LifecycleOwner,
  propA: KProperty1<T, A>,
  propB: KProperty1<T, B>,
  action: (A, B) -> Unit
) {
  this.map {
    StateTuple2(propA.get(it), propB.get(it))
  }.distinctUntilChanged().observe(lifecycleOwner) { (a, b) ->
    action.invoke(a, b)
  }
}

fun <T, A, B, C> LiveData<T>.observeState(
  lifecycleOwner: LifecycleOwner,
  propA: KProperty1<T, A>,
  propB: KProperty1<T, B>,
  propC: KProperty1<T, C>,
  action: (A, B, C) -> Unit
) {
  this.map {
    StateTuple3(propA.get(it), propB.get(it), propC.get(it))
  }.distinctUntilChanged().observe(lifecycleOwner) { (a, b, c) ->
    action.invoke(a, b, c)
  }
}

inline fun <T, R> withState(state: LiveData<T>, block: (T) -> R): R? {
  return state.value?.let(block)
}

fun <T> MutableLiveData<T>.setState(reducer: T.() -> T) {
  this.value = this.value?.reducer()
}

fun <T> LiveEvent<T>.setEvent(event: T) {
  this.value = event
}
