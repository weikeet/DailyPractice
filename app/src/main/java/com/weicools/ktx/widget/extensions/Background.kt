@file:Suppress("unused")

package com.weicools.ktx.widget.extensions

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.weicools.ktx.widget.NO_GETTER
import com.weicools.ktx.widget.noGetter

/**
 * @author weicools
 * @date 2020.05.13
 */

//region View extend field: Background
inline var View.background_resource: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) = setBackgroundResource(value)

/**
 * Set only property that takes a color int.
 */
inline var View.background_color: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(@ColorInt color) = setBackgroundColor(color)

/**
 * Set only property that takes a color resource.
 */
inline var View.background_colorResource: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(@ColorRes colorId) = setBackgroundColor(ContextCompat.getColor(context, colorId))

/**
 * Set only property that takes a color String.
 */
inline var View.background_colorString: String
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(colorValue) = setBackgroundColor(Color.parseColor(colorValue))

inline var View.background_tintColors: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(@ColorRes colorId) {
    if (Build.VERSION.SDK_INT >= 21) {
      backgroundTintList = ContextCompat.getColorStateList(context, colorId)
    } else {
      ViewCompat.setBackgroundTintList(this, ContextCompat.getColorStateList(context, colorId))
    }
  }

inline var View.background_tintList: ColorStateList?
  get() = if (Build.VERSION.SDK_INT >= 21) backgroundTintList else ViewCompat.getBackgroundTintList(this)
  set(colorList) {
    if (Build.VERSION.SDK_INT >= 21) {
      backgroundTintList = colorList
    } else {
      ViewCompat.setBackgroundTintList(this, colorList)
    }
  }

inline var View.background_tintMode: PorterDuff.Mode?
  get() = if (Build.VERSION.SDK_INT >= 21) backgroundTintMode else ViewCompat.getBackgroundTintMode(this)
  set(mode) {
    if (Build.VERSION.SDK_INT >= 21) {
      backgroundTintMode = mode
    } else {
      ViewCompat.setBackgroundTintMode(this, mode)
    }
  }
//endregion
