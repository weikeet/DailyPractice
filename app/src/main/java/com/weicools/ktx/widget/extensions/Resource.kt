@file:Suppress("unused")

package com.weicools.ktx.widget.extensions

import android.content.res.Resources
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat

/**
 * @author weicools
 * @date 2020.05.13
 */

//region displayMetrics
val Int.dp get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

val Float.dp get() = (this * Resources.getSystem().displayMetrics.density)

val widthPixels get() = Resources.getSystem().displayMetrics.widthPixels

val heightPixels get() = Resources.getSystem().displayMetrics.heightPixels
//endregion

//region View extend field: Resource
@Suppress("NOTHING_TO_INLINE")
inline fun View.string_of(@StringRes stringId: Int) = context.getString(stringId)

@Suppress("NOTHING_TO_INLINE")
inline fun View.strings_of(@StringRes stringId: Int, vararg formatArgs: Any) = context.getString(stringId, *formatArgs)

@Suppress("NOTHING_TO_INLINE")
inline fun View.color_of(@ColorRes colorId: Int) = ContextCompat.getColor(context, colorId)

@Suppress("NOTHING_TO_INLINE")
inline fun View.colors_of(@ColorRes colorId: Int) = ContextCompat.getColorStateList(context, colorId)

@Suppress("NOTHING_TO_INLINE")
inline fun View.drawable_of(@DrawableRes drawableId: Int) = ContextCompat.getDrawable(context, drawableId)

@Suppress("NOTHING_TO_INLINE")
inline fun View.drawable_ofCompat(@DrawableRes drawableId: Int) = AppCompatResources.getDrawable(context, drawableId)

@Suppress("NOTHING_TO_INLINE")
inline fun View.dimen_of(@DimenRes dimenId: Int) = context.resources.getDimension(dimenId)

@Suppress("NOTHING_TO_INLINE")
inline fun View.dimenSize_of(@DimenRes dimenId: Int) = context.resources.getDimensionPixelSize(dimenId)

@Suppress("NOTHING_TO_INLINE")
inline fun View.dimenOffset_of(@DimenRes dimenId: Int) = context.resources.getDimensionPixelOffset(dimenId)

//endregion
