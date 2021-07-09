@file:Suppress("unused")

package com.weicools.ktx.widget.extensions

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.InputFilter
import android.util.TypedValue
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.weicools.ktx.widget.NO_GETTER
import com.weicools.ktx.widget.noGetter

/**
 * @author weicools
 * @date 2020.05.13
 */

//region TextView extend field
inline var TextView.text_resource: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(@StringRes stringId) = setText(stringId)

inline var TextView.text_color: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(@ColorInt colorInt) = setTextColor(colorInt)

inline var TextView.text_colors: ColorStateList?
  get() = textColors
  set(colorList) = setTextColor(colorList)

inline var TextView.text_colorResource: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(@ColorRes colorId) = setTextColor(ContextCompat.getColor(context, colorId))

inline var TextView.text_colorsResource: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(@ColorRes colorId) = setTextColor(ContextCompat.getColorStateList(context, colorId))

inline var TextView.text_colorString: String
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(colorString) = setTextColor(Color.parseColor(colorString))

inline var TextView.text_sizeDp: Float
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(sizeDp) = setTextSize(TypedValue.COMPLEX_UNIT_DIP, sizeDp)

inline var TextView.text_style: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(style) = setTypeface(typeface, style)

inline var TextView.text_fontFamily: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(@FontRes fontId) {
    typeface = ResourcesCompat.getFont(context, fontId)
  }

inline var TextView.text_ems: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) = setEms(value)

inline var TextView.text_lines: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) = setLines(value)

inline var TextView.text_maxLine: Int
  get() = maxLines
  set(value) {
    maxLines = value
  }

inline var TextView.text_maxLength: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(maxLength) {
    filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))
  }

inline var TextView.hint_colorResource: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(@ColorRes colorId) = setHintTextColor(ContextCompat.getColor(context, colorId))

inline var TextView.hint_colorString: String
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(colorString) = setHintTextColor(Color.parseColor(colorString))

inline var TextView.text_hintResource: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(@StringRes stringId) = setHint(stringId)

inline var TextView.text_hint: String
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(hintText) {
    hint = hintText
  }

inline var TextView.text_lineSpacingMultiplier: Float
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(multi) = setLineSpacing(lineSpacingExtra, multi)

inline var TextView.text_lineSpacingExtra: Float
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(add) = setLineSpacing(add, lineSpacingMultiplier)

inline val TextView.sans_serif: Typeface
  get() = Typeface.create("sans-serif", Typeface.NORMAL)

inline val TextView.sans_serifMedium: Typeface
  get() = Typeface.create("sans-serif-medium", Typeface.NORMAL)

@Suppress("NOTHING_TO_INLINE")
inline fun TextView.setCompoundDrawables(
  start: Drawable? = null, top: Drawable? = null,
  end: Drawable? = null, bottom: Drawable? = null,
  intrinsicBounds: Boolean = false
) {
  val left = if (isLtr) start else end
  val right = if (isRtl) start else end
  if (intrinsicBounds) setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom)
  else setCompoundDrawables(left, top, right, bottom)
}

@Suppress("NOTHING_TO_INLINE")
inline fun TextView.setCompoundDrawables(
  @DrawableRes start: Int = 0, @DrawableRes top: Int = 0,
  @DrawableRes end: Int = 0, @DrawableRes bottom: Int = 0
) {
  val left = if (isLtr) start else end
  val right = if (isRtl) start else end
  setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom)
}

@Suppress("NOTHING_TO_INLINE")
inline fun TextView.clearCompoundDrawables() = setCompoundDrawables(null, null, null, null)
//endregion
