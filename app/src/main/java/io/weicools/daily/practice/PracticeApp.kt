package io.weicools.daily.practice

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.os.Process
import android.text.TextUtils

/**
 * @author weicools Create on 2018/1/1.
 */
class PracticeApp : Application() {
  private val appProcessName: String = ""
    get() {
      if (!TextUtils.isEmpty(field)) {
        return field
      }
      val pid = Process.myPid()
      val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
      manager.runningAppProcesses?.let {
        for (processInfo in it) {
          if (processInfo.pid == pid) {
            return processInfo.processName
          }
        }
      }
      return ""
    }

  override fun attachBaseContext(base: Context?) {
    super.attachBaseContext(base)
    TimeRecorder.recordStartTime(appProcessName)
  }
}