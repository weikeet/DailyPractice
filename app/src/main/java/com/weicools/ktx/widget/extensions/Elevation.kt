@file:Suppress("unused")

package com.weicools.ktx.widget.extensions

import android.os.Build
import android.view.View

/**
 * @author weicools
 * @date 2020.05.13
 */

//region View extend field: Elevation
inline var View.elevation_compat: Float
  get() = if (Build.VERSION.SDK_INT >= 21) elevation else 0f
  set(value) {
    if (Build.VERSION.SDK_INT >= 21) elevation = value
  }
//endregion
