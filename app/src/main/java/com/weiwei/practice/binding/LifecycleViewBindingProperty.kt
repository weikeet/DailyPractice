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

package com.weiwei.practice.binding

import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.annotation.IdRes
import androidx.annotation.MainThread
import androidx.annotation.RestrictTo
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

// -------------------------------------------------------------------------------------
// ViewBindingProperty for Activity
// -------------------------------------------------------------------------------------

//region Description
@JvmName("viewBindingActivity")
inline fun <VB : ViewBinding> ComponentActivity.viewBinding(
  crossinline viewBinder: (View) -> VB,
  crossinline viewProvider: (ComponentActivity) -> View = { activity -> findRootView(activity) }
): LifecycleViewBindingProperty<ComponentActivity, VB> {

  return ActivityViewBindingProperty { activity: ComponentActivity ->
    viewBinder(viewProvider(activity))
  }
}

@JvmName("viewBindingActivity")
inline fun <VB : ViewBinding> ComponentActivity.viewBinding(
  crossinline viewBinder: (View) -> VB,
  @IdRes viewBindingRootId: Int
): LifecycleViewBindingProperty<ComponentActivity, VB> {

  return ActivityViewBindingProperty { activity: ComponentActivity ->
    viewBinder(activity.requireViewByIdCompat(viewBindingRootId))
  }
}
//endregion


// -------------------------------------------------------------------------------------
// ViewBindingProperty for Fragment / DialogFragment
// -------------------------------------------------------------------------------------

//region Description
@JvmName("viewBindingFragment")
inline fun <F : Fragment, VB : ViewBinding> Fragment.viewBinding(
  crossinline viewBinder: (View) -> VB,
  crossinline viewProvider: (F) -> View = Fragment::requireView
): LifecycleViewBindingProperty<F, VB> {

  if (this is DialogFragment) {
    @Suppress("UNCHECKED_CAST")
    return DialogFragmentViewBindingProperty { fragment: F ->
      viewBinder(viewProvider(fragment))
    } as LifecycleViewBindingProperty<F, VB>
  }

  return FragmentViewBindingProperty { fragment: F ->
    viewBinder(viewProvider(fragment))
  }
}

@JvmName("viewBindingFragment")
inline fun <F : Fragment, V : ViewBinding> Fragment.viewBinding(
  crossinline viewBinder: (View) -> V,
  @IdRes viewBindingRootId: Int
): LifecycleViewBindingProperty<F, V> {

  if (this is DialogFragment) {
    @Suppress("UNCHECKED_CAST")
    return viewBinding(viewBinder) { fragment: DialogFragment ->
      fragment.getRootView(viewBindingRootId)
    } as LifecycleViewBindingProperty<F, V>
  }

  return viewBinding(viewBinder) { fragment: F ->
    fragment.requireView().requireViewByIdCompat(viewBindingRootId)
  }
}
//endregion


// -------------------------------------------------------------------------------------
// ViewBindingProperty for ViewGroup
// -------------------------------------------------------------------------------------

//region ViewBindingProperty for ViewGroup
@JvmName("viewBindingViewGroup")
inline fun <V : ViewBinding> ViewGroup.viewBinding(
  crossinline viewBinder: (View) -> V,
  crossinline viewProvider: (ViewGroup) -> View = { this }
): ViewBindingProperty<ViewGroup, V> {

  return LazyViewBindingProperty { viewGroup: ViewGroup ->
    viewBinder(viewProvider(viewGroup))
  }
}

@JvmName("viewBindingViewGroup")
inline fun <V : ViewBinding> ViewGroup.viewBinding(
  crossinline viewBinder: (View) -> V,
  @IdRes viewBindingRootId: Int
): ViewBindingProperty<ViewGroup, V> {

  return LazyViewBindingProperty { viewGroup: ViewGroup ->
    viewBinder(viewGroup.requireViewByIdCompat(viewBindingRootId))
  }
}
//endregion


// -------------------------------------------------------------------------------------
// ViewBindingProperty for RecyclerView#ViewHolder
// -------------------------------------------------------------------------------------

//region ViewBindingProperty for RecyclerView#ViewHolder
@JvmName("viewBindingViewHolder")
inline fun <V : ViewBinding> RecyclerView.ViewHolder.viewBinding(
  crossinline viewBinder: (View) -> V,
  crossinline viewProvider: (RecyclerView.ViewHolder) -> View = RecyclerView.ViewHolder::itemView
): ViewBindingProperty<RecyclerView.ViewHolder, V> {

  return LazyViewBindingProperty { holder: RecyclerView.ViewHolder ->
    viewBinder(viewProvider(holder))
  }
}

@JvmName("viewBindingViewHolder")
inline fun <V : ViewBinding> RecyclerView.ViewHolder.viewBinding(
  crossinline viewBinder: (View) -> V,
  @IdRes viewBindingRootId: Int
): ViewBindingProperty<RecyclerView.ViewHolder, V> {

  return LazyViewBindingProperty { holder: RecyclerView.ViewHolder ->
    viewBinder(holder.itemView.requireViewByIdCompat(viewBindingRootId))
  }
}
//endregion


// -------------------------------------------------------------------------------------
// ViewBindingProperty
// -------------------------------------------------------------------------------------

//region ViewBindingProperty
interface ViewBindingProperty<in R : Any, out V : ViewBinding> : ReadOnlyProperty<R, V> {
  @MainThread
  fun clear()
}

abstract class LifecycleViewBindingProperty<in R : Any, out VB : ViewBinding>(
  private val viewBinder: (R) -> VB
) : ViewBindingProperty<R, VB> {

  private var viewBinding: VB? = null

  protected abstract fun getLifecycleOwner(thisRef: R): LifecycleOwner

  override fun getValue(thisRef: R, property: KProperty<*>): VB {
    viewBinding?.let {
      return it
    }

    val lifecycle = getLifecycleOwner(thisRef).lifecycle

    val viewBinding = viewBinder(thisRef)

    if (lifecycle.currentState == Lifecycle.State.DESTROYED) {
      Log.w("ViewBindingProperty", "Access to viewBinding after Lifecycle is destroyed or hasn't created yet. The instance of viewBinding will be not cached.")
      // We can access to ViewBinding after Fragment.onDestroyView(), but don't save it to prevent memory leak
    } else {
      lifecycle.addObserver(ClearOnDestroyLifecycleObserver(this))
      this.viewBinding = viewBinding
    }

    return viewBinding
  }

  override fun clear() {
    viewBinding = null
  }

  private class ClearOnDestroyLifecycleObserver(
    private val property: ViewBindingProperty<*, *>
  ) : LifecycleEventObserver {

    private companion object {
      private val mainHandler = Handler(Looper.getMainLooper())
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
      if (event == Lifecycle.Event.ON_DESTROY) {
        mainHandler.post { property.clear() }
      }
    }
  }
}

class LazyViewBindingProperty<in R : Any, out V : ViewBinding>(
  private val viewBinder: (R) -> V
) : ViewBindingProperty<R, V> {

  private var viewBinding: V? = null

  @MainThread
  override fun getValue(thisRef: R, property: KProperty<*>): V {
    // Already bound
    viewBinding?.let {
      return it
    }

    return viewBinder(thisRef).also {
      this.viewBinding = it
    }
  }

  @MainThread
  override fun clear() {
    viewBinding = null
  }
}

@RestrictTo(RestrictTo.Scope.LIBRARY)
class ActivityViewBindingProperty<in A : ComponentActivity, VB : ViewBinding>(
  viewBinder: (A) -> VB
) : LifecycleViewBindingProperty<A, VB>(viewBinder) {

  override fun getLifecycleOwner(thisRef: A): LifecycleOwner = thisRef
}

class FragmentViewBindingProperty<in F : Fragment, VB : ViewBinding>(
  viewBinder: (F) -> VB
) : LifecycleViewBindingProperty<F, VB>(viewBinder) {

  override fun getLifecycleOwner(thisRef: F): LifecycleOwner =
    try {
      thisRef.viewLifecycleOwner
    } catch (ignored: Exception) {
      error("Fragment doesn't have view associated with it or the view has been destroyed")
    }
}

class DialogFragmentViewBindingProperty<in F : DialogFragment, VB : ViewBinding>(
  viewBinder: (F) -> VB
) : LifecycleViewBindingProperty<F, VB>(viewBinder) {

  override fun getLifecycleOwner(thisRef: F): LifecycleOwner =
    if (thisRef.showsDialog) {
      thisRef
    } else {
      try {
        thisRef.viewLifecycleOwner
      } catch (ignored: Exception) {
        error("Fragment doesn't have view associated with it or the view has been destroyed")
      }
    }
}
//endregion


// -------------------------------------------------------------------------------------
// Utils
// -------------------------------------------------------------------------------------

//region Utils extensions
fun findRootView(activity: Activity): View {
  val contentView: ViewGroup? = activity.findViewById(android.R.id.content)

  checkNotNull(contentView) { "Activity has no content view" }

  return when (contentView.childCount) {
    1 -> contentView.getChildAt(0)
    0 -> error("Content view has no children. Provide root view explicitly")
    else -> error("More than one child view found in Activity content view")
  }
}

fun DialogFragment.getRootView(viewBindingRootId: Int): View {
  val dialog = checkNotNull(dialog) {
    "DialogFragment doesn't have dialog. Use viewBinding delegate after onCreateDialog"
  }

  val window = checkNotNull(dialog.window) { "Fragment's Dialog has no window" }

  return with(window.decorView) {
    if (viewBindingRootId == 0) this else requireViewByIdCompat(viewBindingRootId)
  }
}

fun <V : View> View.requireViewByIdCompat(@IdRes id: Int): V = ViewCompat.requireViewById(this, id)

fun <V : View> Activity.requireViewByIdCompat(@IdRes id: Int): V = ActivityCompat.requireViewById(this, id)
//endregion
