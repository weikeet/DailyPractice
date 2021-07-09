package com.weicools.ktx

import android.view.View

/**
 * @author Weicools
 *
 * @date 2021.07.10
 */
fun Int.convertVisibility(): String {
  var visibility = "GONE"
  if (this == View.VISIBLE) {
    visibility = "VISIBLE"
  } else if (this == View.INVISIBLE) {
    visibility = "INVISIBLE"
  }
  return visibility
}