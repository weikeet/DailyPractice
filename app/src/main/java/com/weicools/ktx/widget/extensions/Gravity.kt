@file:Suppress("unused")

package com.weicools.ktx.widget.extensions

import android.view.Gravity
import android.view.View

/**
 * @author weicools
 * @date 2020.05.13
 */

//region View extend field: Gravity
inline val View.gravity_center: Int get() = Gravity.CENTER
inline val View.gravity_centerVertical: Int get() = Gravity.CENTER_VERTICAL
inline val View.gravity_centerHorizontal: Int get() = Gravity.CENTER_HORIZONTAL

inline val View.gravity_start: Int get() = Gravity.START
inline val View.gravity_top: Int get() = Gravity.TOP
inline val View.gravity_end: Int get() = Gravity.END
inline val View.gravity_bottom: Int get() = Gravity.BOTTOM

inline val View.gravity_startTop: Int get() = Gravity.START or Gravity.TOP
inline val View.gravity_startBottom: Int get() = Gravity.START or Gravity.BOTTOM
inline val View.gravity_endTop: Int get() = Gravity.END or Gravity.TOP
inline val View.gravity_endBottom: Int get() = Gravity.END or Gravity.BOTTOM

inline val View.gravity_startCenter: Int get() = Gravity.START or Gravity.CENTER_VERTICAL
inline val View.gravity_topCenter: Int get() = Gravity.TOP or Gravity.CENTER_HORIZONTAL
inline val View.gravity_endCenter: Int get() = Gravity.END or Gravity.CENTER_VERTICAL
inline val View.gravity_bottomCenter: Int get() = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
//endregion
