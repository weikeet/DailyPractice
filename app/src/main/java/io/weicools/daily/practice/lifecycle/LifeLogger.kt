package io.weicools.daily.practice.lifecycle

import android.util.Log

/**
 * @author weicools
 * @date 2021.07.05
 */
const val LIFE_TAG: String = "ComponentLifecycle"

fun logger(tag: String, msg: String) {
  Log.d(LIFE_TAG, "$tag# $msg")
}