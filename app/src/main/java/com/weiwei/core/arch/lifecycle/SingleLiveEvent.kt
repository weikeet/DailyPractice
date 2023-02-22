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

package com.weiwei.core.arch.lifecycle

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/**
 * @author weiwei
 * @date 2023.02.12
 */

class SingleLiveEvent<T> : MutableLiveData<T>() {
  private val pendingFlag = AtomicBoolean(false)

  override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
    if (hasActiveObservers()) {
      Log.d("SingleLiveEvent", "Multiple observers registered but only one will be notified of changes.")
    }
    Log.d("SingleLiveEvent", "observe: observer: $observer")
    super.observe(owner) {
      Log.d("SingleLiveEvent", "observe: super onChanged, observer: $observer")
      if (pendingFlag.compareAndSet(true, false)) {
        Log.d("SingleLiveEvent", "observe: notify observer, observer: $observer")
        observer.onChanged(it)
      }
    }
  }

  override fun setValue(t: T) {
    pendingFlag.set(true)
    super.setValue(t)
  }
}