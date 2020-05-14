package io.weicools.daily.practice.ktx

import android.content.res.Resources
import android.util.TypedValue
import android.view.View

/**
 * @author weicools
 * @date 2020.05.14
 */

fun Int.dp(): Int =
  TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP, this.toFloat(),
    Resources.getSystem().displayMetrics
  ).toInt()

fun Int.convertVisibility(): String {
  var visibility = "GONE"
  if (this == View.VISIBLE) {
    visibility = "VISIBLE"
  } else if (this == View.INVISIBLE) {
    visibility = "INVISIBLE"
  }
  return visibility
}