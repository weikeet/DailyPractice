/*
 * Copyright (c) 2020 Weicools
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

package io.weicools.daily.practice.binding.sample

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Android | ViewBinding 与 Kotlin 委托双剑合璧
 * https://juejin.cn/post/6960914424865488932
 */
/**
 * viewBinder 创建绑定类对象
 */
class FragmentViewBindingPropertyV0<in F : Fragment, out V : ViewBinding>(
  private val viewBinder: ((F) -> V)
) : ReadOnlyProperty<F, V> {
  private var viewBinding: V? = null

  override fun getValue(thisRef: F, property: KProperty<*>): V {
    // 1. viewBinding 不为空说明已经绑定，直接返回
    viewBinding?.let {
      return it
    }

    // 2. 实例化绑定类对象
    val viewBinding = viewBinder(thisRef)

    // 3. Fragment 视图的生命周期
    val lifecycle = thisRef.viewLifecycleOwner.lifecycle

    if (lifecycle.currentState == Lifecycle.State.DESTROYED) {
      // 4.1 如果视图生命周期为 DESTROYED，说明视图被销毁，此时不缓存绑定类对象（避免内存泄漏）
      Log.w("TAG", "Access to viewBinding after Lifecycle is destroyed or hasn't created yet. The instance of viewBinding will be not cached.")
    } else {
      // 4.2 定义视图生命周期监听者
      lifecycle.addObserver(ClearOnDestroyLifecycleObserver(this))
      // 4.3 缓存绑定类对象
      this.viewBinding = viewBinding
    }

    return viewBinding
  }

  @MainThread
  fun clear() {
    viewBinding = null
  }

  private class ClearOnDestroyLifecycleObserver(val vbp: FragmentViewBindingPropertyV0<*, *>) : LifecycleObserver {

    private val mainHandler = Handler(Looper.getMainLooper())

    @MainThread
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(owner: LifecycleOwner) {
      owner.lifecycle.removeObserver(this)
      // Fragment#viewLifecycleOwner 通知生命周期事件 ON_DESTROY 的时机在 Fragment#onDestryoView 之前
      // 所以 onDestroy() 要采用 Handler#post(Message) 完成
      mainHandler.post { vbp.clear() }
    }
  }
}


inline fun <reified VB : ViewBinding> viewBindingV1() = viewBindingV1(VB::class.java)

inline fun <reified VB : ViewBinding> viewBindingV1(clazz: Class<VB>): FragmentViewBindingPropertyV0<Fragment, VB> {
  val bindMethod = clazz.getMethod("bind", View::class.java)
  return FragmentViewBindingPropertyV0 {
    bindMethod(null, it.requireView()) as VB
  }
}

/**
 * 反射调用 bind 函数的目的就是获得一个 ViewBinding 绑定类对象，
 * 或许我们可以试试把创建对象的行为交给外部去定义
 */
inline fun <F : Fragment, VB : ViewBinding> viewBindingV2(
  crossinline viewBinder: (View) -> VB,
  crossinline viewProvider: (F) -> View = Fragment::requireView
) = FragmentViewBindingPropertyV0 { fragment: F ->
  viewBinder(viewProvider(fragment))
}