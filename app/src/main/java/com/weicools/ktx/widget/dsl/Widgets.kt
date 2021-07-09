@file:Suppress("unused", "FunctionName")

package com.weicools.ktx.widget.dsl

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Space
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.widget.NestedScrollView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager

/**
 * @author weicools
 * @date 2020.05.14
 */

@Suppress("NOTHING_TO_INLINE")
inline fun Context.withTheme(theme: Int): Context = ContextThemeWrapper(this, theme)

@Suppress("NOTHING_TO_INLINE")
inline fun wrapContextIfNeeded(context: Context, theme: Int? = null): Context = if (theme == null) context else ContextThemeWrapper(context, theme)

inline fun <T : View> T.autoAddView(parent: ViewGroup, initView: T.() -> Unit): T = apply {
  initView()
  parent.addView(this)
}

//region create widget function by ViewGroup or Context
/**
 * create Widget instance within a [ViewGroup] or [Context]
 * param style an style int value defined in xml
 * param init set attributes for this view in this lambda
 */

//region space
inline fun ViewGroup.space(id: Int? = null, init: Space.() -> Unit): Space {
  val widget = Space(context)
  if (id != null) widget.id = id
  return widget.apply(init).also { addView(it) }
}
//endregion

//region view
inline fun ViewGroup.view(id: Int? = null, style: Int? = null, init: View.() -> Unit): View {
  val widget = View(wrapContextIfNeeded(context, style))
  if (id != null) widget.id = id
  return widget.apply(init).also { addView(it) }
}

inline fun Context.view(id: Int? = null, style: Int? = null, init: View.() -> Unit): View {
  val widget = View(wrapContextIfNeeded(this, style))
  if (id != null) widget.id = id
  return widget.apply(init)
}
//endregion

//region imageView
inline fun ViewGroup.imageView(id: Int? = null, style: Int? = null, init: AppCompatImageView.() -> Unit): AppCompatImageView {
  val widget = AppCompatImageView(wrapContextIfNeeded(context, style))
  if (id != null) widget.id = id
  return widget.apply(init).also { addView(it) }
}

inline fun Context.imageView(id: Int? = null, style: Int? = null, init: AppCompatImageView.() -> Unit): AppCompatImageView {
  val widget = AppCompatImageView(wrapContextIfNeeded(this, style))
  if (id != null) widget.id = id
  return widget.apply(init)
}
//endregion

//region textView
inline fun ViewGroup.textView(id: Int? = null, style: Int? = null, init: AppCompatTextView.() -> Unit): AppCompatTextView {
  val widget = AppCompatTextView(wrapContextIfNeeded(context, style))
  if (id != null) widget.id = id
  return widget.apply(init).also { addView(it) }
}

inline fun Context.textView(id: Int? = null, style: Int? = null, init: AppCompatTextView.() -> Unit): AppCompatTextView {
  val widget = AppCompatTextView(wrapContextIfNeeded(this, style))
  if (id != null) widget.id = id
  return widget.apply(init)
}
//endregion

//region button
inline fun ViewGroup.button(id: Int? = null, style: Int? = null, init: AppCompatButton.() -> Unit): AppCompatButton {
  val widget = AppCompatButton(wrapContextIfNeeded(context, style))
  if (id != null) widget.id = id
  return widget.apply(init).also { addView(it) }
}

inline fun Context.button(id: Int? = null, style: Int? = null, init: AppCompatButton.() -> Unit): AppCompatButton {
  val widget = AppCompatButton(wrapContextIfNeeded(this, style))
  if (id != null) widget.id = id
  return widget.apply(init)
}
//endregion

//region editText
inline fun ViewGroup.editText(id: Int? = null, style: Int? = null, init: EditText.() -> Unit): EditText {
  val widget = EditText(wrapContextIfNeeded(context, style))
  if (id != null) widget.id = id
  return widget.apply(init).also { addView(it) }
}

inline fun Context.editText(id: Int? = null, style: Int? = null, init: EditText.() -> Unit): EditText {
  val widget = EditText(wrapContextIfNeeded(this, style))
  if (id != null) widget.id = id
  return widget.apply(init)
}
//endregion

//region frameLayout
inline fun ViewGroup.frameLayout(id: Int? = null, style: Int? = null, init: FrameLayout.() -> Unit): FrameLayout {
  val widget = FrameLayout(wrapContextIfNeeded(context, style))
  if (id != null) widget.id = id
  return widget.apply(init).also { addView(it) }
}

inline fun Context.frameLayout(id: Int? = null, style: Int? = null, init: FrameLayout.() -> Unit): FrameLayout {
  val widget = FrameLayout(wrapContextIfNeeded(this, style))
  if (id != null) widget.id = id
  return widget.apply(init)
}
//endregion

//region linearLayout
inline fun ViewGroup.linearLayout(id: Int? = null, style: Int? = null, init: LinearLayout.() -> Unit): LinearLayout {
  val widget = LinearLayout(wrapContextIfNeeded(context, style))
  if (id != null) widget.id = id
  return widget.apply(init).also { addView(it) }
}

inline fun Context.linearLayout(id: Int? = null, style: Int? = null, init: LinearLayout.() -> Unit): LinearLayout {
  val widget = LinearLayout(wrapContextIfNeeded(this, style))
  if (id != null) widget.id = id
  return widget.apply(init)
}
//endregion

//region relativeLayout
inline fun ViewGroup.relativeLayout(id: Int? = null, style: Int? = null, init: RelativeLayout.() -> Unit): RelativeLayout {
  val widget = RelativeLayout(wrapContextIfNeeded(context, style))
  if (id != null) widget.id = id
  return widget.apply(init).also { addView(it) }
}

inline fun Context.relativeLayout(id: Int? = null, style: Int? = null, init: RelativeLayout.() -> Unit): RelativeLayout {
  val widget = RelativeLayout(wrapContextIfNeeded(this, style))
  if (id != null) widget.id = id
  return widget.apply(init)
}
//endregion

//region recyclerView
inline fun ViewGroup.recyclerView(id: Int? = null, style: Int? = null, init: RecyclerView.() -> Unit): RecyclerView {
  val widget = RecyclerView(wrapContextIfNeeded(context, style))
  if (id != null) widget.id = id
  return widget.apply(init).also { addView(it) }
}

inline fun Context.recyclerView(id: Int? = null, style: Int? = null, init: RecyclerView.() -> Unit): RecyclerView {
  val widget = RecyclerView(wrapContextIfNeeded(this, style))
  if (id != null) widget.id = id
  return widget.apply(init)
}
//endregion

//region nestedScrollView
inline fun ViewGroup.nestedScrollView(id: Int? = null, style: Int? = null, init: NestedScrollView.() -> Unit): NestedScrollView {
  val widget = NestedScrollView(wrapContextIfNeeded(context, style))
  if (id != null) widget.id = id
  return widget.apply(init).also { addView(it) }
}

inline fun Context.nestedScrollView(id: Int? = null, style: Int? = null, init: NestedScrollView.() -> Unit): NestedScrollView {
  val widget = NestedScrollView(wrapContextIfNeeded(this, style))
  if (id != null) widget.id = id
  return widget.apply(init)
}
//endregion

//region toolbar
inline fun ViewGroup.toolbar(id: Int? = null, style: Int? = null, init: Toolbar.() -> Unit): Toolbar {
  val widget = Toolbar(wrapContextIfNeeded(context, style))
  if (id != null) widget.id = id
  return widget.apply(init).also { addView(it) }
}

inline fun Context.toolbar(id: Int? = null, style: Int? = null, init: Toolbar.() -> Unit): Toolbar {
  val widget = Toolbar(wrapContextIfNeeded(this, style))
  if (id != null) widget.id = id
  return widget.apply(init)
}
//endregion

//region viewPager
inline fun ViewGroup.viewPager(id: Int? = null, style: Int? = null, init: ViewPager.() -> Unit): ViewPager {
  val widget = ViewPager(wrapContextIfNeeded(context, style))
  if (id != null) widget.id = id
  return widget.apply(init).also { addView(it) }
}

inline fun Context.viewPager(id: Int? = null, style: Int? = null, init: ViewPager.() -> Unit): ViewPager {
  val widget = ViewPager(wrapContextIfNeeded(this, style))
  if (id != null) widget.id = id
  return widget.apply(init)
}
//endregion

//region drawerLayout
inline fun ViewGroup.drawerLayout(id: Int? = null, style: Int? = null, init: DrawerLayout.() -> Unit): DrawerLayout {
  val widget = DrawerLayout(wrapContextIfNeeded(context, style))
  if (id != null) widget.id = id
  return widget.apply(init).also { addView(it) }
}

inline fun Context.drawerLayout(id: Int? = null, style: Int? = null, init: DrawerLayout.() -> Unit): DrawerLayout {
  val widget = DrawerLayout(wrapContextIfNeeded(this, style))
  if (id != null) widget.id = id
  return widget.apply(init)
}
//endregion

//region coordinatorLayout
inline fun ViewGroup.coordinatorLayout(id: Int? = null, style: Int? = null, init: CoordinatorLayout.() -> Unit): CoordinatorLayout {
  val widget = CoordinatorLayout(wrapContextIfNeeded(context, style))
  if (id != null) widget.id = id
  return widget.apply(init).also { addView(it) }
}

inline fun Context.coordinatorLayout(id: Int? = null, style: Int? = null, init: CoordinatorLayout.() -> Unit): CoordinatorLayout {
  val widget = CoordinatorLayout(wrapContextIfNeeded(this, style))
  if (id != null) widget.id = id
  return widget.apply(init)
}
//endregion

//endregion
