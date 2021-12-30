package com.weiwei.practice.common.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import java.util.*

/**
 * @author weicools
 * @date 2020.05.16
 */
object LauncherUtils {
  fun isLauncherApp(context: Context, packageName: String?): Boolean {
    val intentToResolve = Intent(Intent.ACTION_MAIN, null)
    intentToResolve.addCategory(Intent.CATEGORY_HOME)
    intentToResolve.setPackage(packageName)

    var infoList: List<ResolveInfo?>? = null
    try {
      infoList = context.packageManager.queryIntentActivities(intentToResolve, 0)
    } catch (e: Exception) {
      e.printStackTrace()
    }
    return infoList != null && infoList.isNotEmpty()
  }

  fun getLauncherAppList(context: Context): List<String> {
    val intent = Intent(Intent.ACTION_MAIN)
    intent.addCategory(Intent.CATEGORY_HOME)

    val resolveInfoList = context.packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
    val launcherAppList: MutableList<String> = ArrayList()
    for (resolveInfo in resolveInfoList) {
      launcherAppList.add(resolveInfo.activityInfo.packageName)
    }
    return launcherAppList
  }
}