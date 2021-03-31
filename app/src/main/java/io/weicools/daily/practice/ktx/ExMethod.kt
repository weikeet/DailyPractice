package io.weicools.daily.practice.ktx

import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup

/**
 * @author weicools
 * @date 2020.05.14
 */

const val matchParent = ViewGroup.LayoutParams.MATCH_PARENT
const val wrapContent = ViewGroup.LayoutParams.WRAP_CONTENT
val widthPixels
  get() = Resources.getSystem().displayMetrics.widthPixels

val heightPixels
  get() = Resources.getSystem().displayMetrics.heightPixels
val Int.dp
  get() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP, this.toFloat(),
    Resources.getSystem().displayMetrics
  ).toInt()

val Float.dp
  get() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP, this,
    Resources.getSystem().displayMetrics
  )

fun Int.convertVisibility(): String {
  var visibility = "GONE"
  if (this == View.VISIBLE) {
    visibility = "VISIBLE"
  } else if (this == View.INVISIBLE) {
    visibility = "INVISIBLE"
  }
  return visibility
}