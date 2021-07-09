@file:Suppress("unused")

package com.weicools.ktx.widget.extensions

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.weicools.ktx.widget.NO_GETTER
import com.weicools.ktx.widget.noGetter

/**
 * @author weicools
 * @date 2020.05.13
 */

//region ImageView extend field
/**
 * Sets a drawable resource as the content of this ImageView.
 * Allows the use of vector drawables when running on older versions of the platform.
 *
 * **This does Bitmap reading and decoding on the UI thread, which can cause a latency hiccup.**
 * If that's a concern, consider using [image_srcCompat] or [image_bitmap] and [BitmapFactory] instead.
 */
inline var ImageView.image_resource: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(@DrawableRes drawableId) = setImageResource(drawableId)

/**
 * Sets a drawable resource as the content of this ImageView.
 * Not allowed the use of vector drawables when running on older versions of the platform.
 */
inline var ImageView.image_src: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(@DrawableRes drawableId) = setImageDrawable(ContextCompat.getDrawable(context, drawableId))

/**
 * Sets a drawable resource as the content of this ImageView.
 * Allows the use of vector drawables when running on older versions of the platform.
 */
inline var ImageView.image_srcCompat: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(@DrawableRes drawableId) = setImageDrawable(AppCompatResources.getDrawable(context, drawableId))

/**
 * Sets a drawable as the content of this ImageView.
 * A null value will clear the content.
 */
inline var ImageView.image_drawable: Drawable?
  get() = drawable
  set(value) = setImageDrawable(value)

/**
 * Sets a Bitmap as the content of this ImageView.
 */
inline var ImageView.image_bitmap: Bitmap
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(bitmap) = setImageBitmap(bitmap)

/**
 * Applies a tint to the image drawable. Does not modify the current tintMode, which is [PorterDuff.Mode.SRC_IN] by default.
 */
inline var ImageView.image_tintColors: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(@ColorRes colorId) {
    if (Build.VERSION.SDK_INT >= 21) {
      imageTintList = ContextCompat.getColorStateList(context, colorId)
    } else {
      ImageViewCompat.setImageTintList(this, ContextCompat.getColorStateList(context, colorId))
    }
  }

inline var ImageView.image_tintList: ColorStateList?
  get() = if (Build.VERSION.SDK_INT >= 21) imageTintList else ImageViewCompat.getImageTintList(this)
  set(tintList) {
    if (Build.VERSION.SDK_INT >= 21) {
      imageTintList = tintList
    } else {
      ImageViewCompat.setImageTintList(this, tintList)
    }
  }

inline var ImageView.image_tintMode: PorterDuff.Mode?
  get() = if (Build.VERSION.SDK_INT >= 21) imageTintMode else ImageViewCompat.getImageTintMode(this)
  set(mode) {
    if (Build.VERSION.SDK_INT >= 21) {
      imageTintMode = mode
    } else {
      ImageViewCompat.setImageTintMode(this, mode)
    }
  }

inline val ImageView.scaleCenter get() = ImageView.ScaleType.CENTER
inline val ImageView.scaleCenterCrop get() = ImageView.ScaleType.CENTER_CROP
inline val ImageView.scaleCenterInside get() = ImageView.ScaleType.CENTER_INSIDE
inline val ImageView.scaleFitCenter get() = ImageView.ScaleType.FIT_CENTER
inline val ImageView.scaleFitStart get() = ImageView.ScaleType.FIT_START
inline val ImageView.scaleFitEnd get() = ImageView.ScaleType.FIT_END
inline val ImageView.scaleFixXy get() = ImageView.ScaleType.FIT_XY
inline val ImageView.scaleMatrix get() = ImageView.ScaleType.MATRIX
//endregion
