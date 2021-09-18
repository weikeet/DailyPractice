package io.weicools.daily.practice

import android.app.ActivityManager
import android.content.Context
import android.os.Process
import android.text.TextUtils
import com.weicools.core.app.BaseApplication
import com.weicools.core.global.AppGlobal
import com.weicools.ktx.core.KtxInitializer

/**
 * @author weicools Create on 2018/1/1.
 */
class PracticeApp : BaseApplication() {
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

  override fun attachBaseContext(base: Context) {
    super.attachBaseContext(base)

    AppGlobal.initAppContext(base)

    TimeRecorder.recordStartTime(appProcessName)
  }

  override fun onCreate() {
    super.onCreate()

    KtxInitializer.appContext = this

    AppGlobal.initApplication(this)
    AppGlobal.initDebuggable(BuildConfig.DEBUG)
  }
}