package com.weiwei.practice.lifecycle.extensions

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

/**
 * @author weicools
 * @date 2020.07.08
 *
 * Notes: @Drakeet 直接实现 DefaultLifecycleObserver 的子类会导致无法完全混淆，所以需要做一层封装
 */

fun Lifecycle.addObserver(observer: ObscureDefaultLifecycleObserver) {
  addObserver(ObscureDefaultLifecycleObserverWrapper(observer))
}

fun Lifecycle.removeObscureObserver(observer: ObscureDefaultLifecycleObserver) {
  removeObserver(ObscureDefaultLifecycleObserverWrapper(observer))
}

interface ObscureDefaultLifecycleObserver {

  fun onCreate(owner: LifecycleOwner) {}

  fun onResume(owner: LifecycleOwner) {}

  fun onStart(owner: LifecycleOwner) {}

  fun onPause(owner: LifecycleOwner) {}

  fun onStop(owner: LifecycleOwner) {}

  fun onDestroy(owner: LifecycleOwner) {}
}

data class ObscureDefaultLifecycleObserverWrapper(val base: ObscureDefaultLifecycleObserver) : DefaultLifecycleObserver {

  override fun onCreate(owner: LifecycleOwner) = base.onCreate(owner)

  override fun onResume(owner: LifecycleOwner) = base.onResume(owner)

  override fun onStart(owner: LifecycleOwner) = base.onStart(owner)

  override fun onPause(owner: LifecycleOwner) = base.onPause(owner)

  override fun onStop(owner: LifecycleOwner) = base.onStop(owner)

  override fun onDestroy(owner: LifecycleOwner) = base.onDestroy(owner)
}