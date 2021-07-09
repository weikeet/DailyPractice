@file:Suppress("unused")

package com.weicools.ktx.widget.extensions

import android.annotation.SuppressLint
import android.os.Build
import android.view.View

/**
 * @author weicools
 * @date 2020.05.13
 */

//region View extend field: LayoutDirection
/**
 * True if layout direction is **left to right**, like in English.
 *
 * This is always true on API 16 and below since layout direction support only came in API 17.
 */
inline val View.isLtr
  @SuppressLint("ObsoleteSdkInt")
  get() = Build.VERSION.SDK_INT < 17 || layoutDirection == View.LAYOUT_DIRECTION_LTR

/**
 * True if layout direction is **right to left**, like in Arabic.
 *
 * This is always false on API 16 and below since layout direction support only came in API 17.
 */
inline val View.isRtl get() = !isLtr
//endregion
