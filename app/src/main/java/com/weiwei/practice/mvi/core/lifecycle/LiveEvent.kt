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

package com.weiwei.practice.mvi.core.lifecycle

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/**
 * LiveEvents
 * 负责处理多维度一次性 Event，支持多个监听者
 * 比如我们在请求开始时发出 ShowLoading，网络请求成功后发出 DismissLoading 与 Toast 事件
 * 如果我们在请求开始后回到桌面，成功后再回到 App,这样有一个事件就会被覆盖，因此将所有事件通过 List 存储
 */
class LiveEvent<T> : MutableLiveData<T>() {

  private val observers = hashSetOf<ObserverWrapper<in T>>()

  @MainThread
  override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
    observers.find { it.observer === observer }?.let { _ -> // existing
      return
    }
    val wrapper = ObserverWrapper(observer)
    observers.add(wrapper)
    super.observe(owner, wrapper)
  }

  @MainThread
  override fun observeForever(observer: Observer<in T>) {
    observers.find { it.observer === observer }?.let { _ -> // existing
      return
    }
    val wrapper = ObserverWrapper(observer)
    observers.add(wrapper)
    super.observeForever(wrapper)
  }

  @MainThread
  override fun removeObserver(observer: Observer<in T>) {
    if (observer is ObserverWrapper<*> && observers.remove(observer)) {
      super.removeObserver(observer)
      return
    }
    val iterator = observers.iterator()
    while (iterator.hasNext()) {
      val wrapper = iterator.next()
      if (wrapper.observer == observer) {
        iterator.remove()
        super.removeObserver(wrapper)
        break
      }
    }
  }

  @MainThread
  override fun setValue(t: T?) {
    observers.forEach { it.newValue(t) }
    super.setValue(t)
  }

  private class ObserverWrapper<T>(val observer: Observer<in T>) : Observer<T> {

    private val pending = AtomicBoolean(false)
    private val eventList = mutableListOf<T>()

    override fun onChanged(t: T?) {
      if (pending.compareAndSet(true, false)) {
        eventList.forEach { observer.onChanged(it) }
        eventList.clear()
      }
    }

    fun newValue(t: T?) {
      pending.set(true)
      t?.let {
        eventList.add(it)
      }
    }
  }
}
