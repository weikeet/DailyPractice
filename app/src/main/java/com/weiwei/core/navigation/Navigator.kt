package com.weiwei.core.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavOptions

/**
 * @author weiwei
 * @date 2022.06.21
 */

fun NavController.navigateSafely(@IdRes resId: Int) {
  try {
    navigate(resId)
  } catch (e: Exception) {
    // handleNavigateException(resId, currentDestination)
  }
}

fun NavController.navigateSafely(@IdRes resId: Int, args: Bundle?) {
  try {
    navigate(resId, args)
  } catch (e: Exception) {
    // handleNavigateException(resId, currentDestination)
  }
}

fun NavController.navigateSafely(@IdRes resId: Int, args: Bundle?, navOptions: NavOptions?) {
  try {
    navigate(resId, args, navOptions)
  } catch (e: Exception) {
    // handleNavigateException(resId, currentDestination)
  }
}
