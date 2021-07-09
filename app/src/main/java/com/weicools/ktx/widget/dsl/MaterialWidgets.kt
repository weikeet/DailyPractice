package com.weicools.ktx.widget.dsl

import android.content.Context
import android.view.ViewGroup
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout

/**
 * @author weicools
 * @date 2020.05.14
 */

//region materialButton
inline fun ViewGroup.materialButton(id: Int? = null, style: Int? = null, init: MaterialButton.() -> Unit): MaterialButton {
  val widget = MaterialButton(wrapContextIfNeeded(context, style))
  if (id != null) widget.id = id
  return widget.apply(init).also { addView(it) }
}

inline fun Context.materialButton(id: Int? = null, style: Int? = null, init: MaterialButton.() -> Unit): MaterialButton {
  val widget = MaterialButton(wrapContextIfNeeded(this, style))
  if (id != null) widget.id = id
  return widget.apply(init)
}
//endregion

inline fun ViewGroup.tabLayout(id: Int? = null, style: Int? = null, init: TabLayout.() -> Unit): TabLayout {
  val widget = TabLayout(wrapContextIfNeeded(context, style))
  if (id != null) widget.id = id
  return widget.apply(init).also { addView(it) }
}

inline fun ViewGroup.appBarLayout(id: Int? = null, style: Int? = null, init: AppBarLayout.() -> Unit): AppBarLayout {
  val widget = AppBarLayout(wrapContextIfNeeded(context, style))
  if (id != null) widget.id = id
  return widget.apply(init).also { addView(it) }
}

inline fun ViewGroup.collapsingToolbarLayout(id: Int? = null, style: Int? = null, init: CollapsingToolbarLayout.() -> Unit): CollapsingToolbarLayout {
  val widget = CollapsingToolbarLayout(wrapContextIfNeeded(context, style))
  if (id != null) widget.id = id
  return widget.apply(init).also { addView(it) }
}
