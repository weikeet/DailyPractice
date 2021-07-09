@file:Suppress("unused")

package com.weicools.ktx.widget.extensions

import android.view.View
import com.weicools.ktx.widget.NO_GETTER
import com.weicools.ktx.widget.noGetter

/**
 * @author weicools
 * @date 2020.05.13
 */

//region View extend field: Padding
inline var View.padding_top: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) = setPadding(paddingLeft, value, paddingRight, paddingBottom)

inline var View.padding_bottom: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) = setPadding(paddingLeft, paddingTop, paddingRight, value)

inline var View.padding_topBottom: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) = setPadding(paddingLeft, value, paddingRight, value)

inline var View.padding_left: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) = setPadding(value, paddingTop, paddingRight, paddingBottom)

inline var View.padding_right: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) = setPadding(paddingLeft, paddingTop, value, paddingBottom)

inline var View.padding_leftRight: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) = setPadding(value, paddingTop, value, paddingBottom)

inline var View.paddings: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) = setPadding(value, value, value, value)
//endregion
