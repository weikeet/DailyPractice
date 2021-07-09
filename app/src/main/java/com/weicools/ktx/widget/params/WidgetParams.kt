@file:Suppress("FunctionName", "unused")

package com.weicools.ktx.widget.params

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.drawerlayout.widget.DrawerLayout
import com.weicools.ktx.widget.NO_GETTER
import com.weicools.ktx.widget.noGetter

/**
 * @author Weicools Create on 2021.03.27
 *
 * desc:
 */

const val matchParent = ViewGroup.LayoutParams.MATCH_PARENT
const val wrapContent = ViewGroup.LayoutParams.WRAP_CONTENT

@Suppress("NOTHING_TO_INLINE")
inline fun defaultParams(width: Int = wrapContent, height: Int = wrapContent) = ViewGroup.LayoutParams(width, height)

inline fun frameParams(init: FrameLayout.LayoutParams.() -> Unit) = FrameLayout.LayoutParams(wrapContent, wrapContent).apply(init)
inline fun frameParams(width: Int, height: Int, init: FrameLayout.LayoutParams.() -> Unit) = FrameLayout.LayoutParams(width, height).apply(init)

inline fun linearParams(init: LinearLayout.LayoutParams.() -> Unit) = LinearLayout.LayoutParams(wrapContent, wrapContent).apply(init)
inline fun linearParams(width: Int, height: Int, init: LinearLayout.LayoutParams.() -> Unit) = LinearLayout.LayoutParams(width, height).apply(init)

inline fun relativeParams(init: RelativeLayout.LayoutParams.() -> Unit) = RelativeLayout.LayoutParams(wrapContent, wrapContent).apply(init)
inline fun relativeParams(width: Int, height: Int, init: RelativeLayout.LayoutParams.() -> Unit) = RelativeLayout.LayoutParams(width, height).apply(init)

inline fun drawerParams(init: DrawerLayout.LayoutParams.() -> Unit) = DrawerLayout.LayoutParams(wrapContent, wrapContent).apply(init)
inline fun drawerParams(width: Int, height: Int, init: DrawerLayout.LayoutParams.() -> Unit) = DrawerLayout.LayoutParams(width, height).apply(init)

inline fun coordinatorParams(init: CoordinatorLayout.LayoutParams.() -> Unit) = CoordinatorLayout.LayoutParams(wrapContent, wrapContent).apply(init)
inline fun coordinatorParams(width: Int, height: Int, init: CoordinatorLayout.LayoutParams.() -> Unit) = CoordinatorLayout.LayoutParams(width, height).apply(init)

//region RelativeLayout.LayoutParams extend field
inline var RelativeLayout.LayoutParams.above: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    addRule(RelativeLayout.ABOVE, value)
  }

inline var RelativeLayout.LayoutParams.below: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    addRule(RelativeLayout.BELOW, value)
  }

inline var RelativeLayout.LayoutParams.align_Baseline: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    addRule(RelativeLayout.ALIGN_BASELINE, value)
  }

inline var RelativeLayout.LayoutParams.align_Left: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    addRule(RelativeLayout.ALIGN_LEFT, value)
  }

inline var RelativeLayout.LayoutParams.align_Top: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    addRule(RelativeLayout.ALIGN_TOP, value)
  }

inline var RelativeLayout.LayoutParams.align_Right: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    addRule(RelativeLayout.ALIGN_RIGHT, value)
  }

inline var RelativeLayout.LayoutParams.align_Bottom: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    addRule(RelativeLayout.ALIGN_BOTTOM, value)
  }

inline var RelativeLayout.LayoutParams.align_ParentLeft: Boolean
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    if (value) addRule(RelativeLayout.ALIGN_PARENT_LEFT)
  }

inline var RelativeLayout.LayoutParams.align_ParentTop: Boolean
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    if (value) addRule(RelativeLayout.ALIGN_PARENT_TOP)
  }

inline var RelativeLayout.LayoutParams.align_ParentRight: Boolean
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    if (value) addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
  }

inline var RelativeLayout.LayoutParams.align_ParentBottom: Boolean
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    if (value) addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
  }

inline var RelativeLayout.LayoutParams.center_InParent: Boolean
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    if (value) addRule(RelativeLayout.CENTER_IN_PARENT)
  }

inline var RelativeLayout.LayoutParams.center_Horizontal: Boolean
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    if (value) addRule(RelativeLayout.CENTER_HORIZONTAL)
  }

inline var RelativeLayout.LayoutParams.center_Vertical: Boolean
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    if (value) addRule(RelativeLayout.CENTER_VERTICAL)
  }

inline var RelativeLayout.LayoutParams.to_LeftOf: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    addRule(RelativeLayout.LEFT_OF, value)
  }

inline var RelativeLayout.LayoutParams.to_RightOf: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    addRule(RelativeLayout.RIGHT_OF, value)
  }
//endregion
