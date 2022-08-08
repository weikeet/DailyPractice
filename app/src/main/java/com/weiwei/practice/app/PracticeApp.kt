/*
 * Copyright (c) 2020 Weiwei
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *    https://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.weiwei.practice.app

import android.app.ActivityManager
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Process
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.weiwei.cockroach.Cockroach
import com.weiwei.cockroach.ExceptionHandler
import com.weiwei.core.app.BaseApplication
import com.weiwei.core.global.AppGlobal
import com.weiwei.practice.BuildConfig
import com.weiwei.practice.TimeRecorder
import xcrash.XCrash


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

  companion object {
    lateinit var instance: PracticeApp
  }

  override fun attachBaseContext(base: Context) {
    super.attachBaseContext(base)

    instance = this

    AppGlobal.initAppContext(base)

    val params = XCrash.InitParameters()
    params.setLogDir(getExternalFilesDir("tombstones")?.path)
    XCrash.init(this, params)

    TimeRecorder.recordStartTime(appProcessName)
  }

  override fun onCreate() {
    super.onCreate()

    instance = this

    // installCockroach()

    appInitializers.forEach { it() }

    AppGlobal.initApplication(this)
    AppGlobal.initDebuggable(BuildConfig.DEBUG)
  }

  private fun installCockroach() {
    Cockroach.install(this, object : ExceptionHandler() {
      val toast: Toast = Toast.makeText(applicationContext, "", Toast.LENGTH_SHORT)

      val sysExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()

      override fun onUncaughtExceptionHappened(thread: Thread?, throwable: Throwable?) {
        Log.e("AndroidRuntime", "--->onUncaughtExceptionHappened:" + thread.toString() + "<---", throwable)
        // CrashLog.saveCrashLog(applicationContext, throwable)

        Handler(Looper.getMainLooper()).post(Runnable {
          toast.setText("safe_mode_excep_tips")
          toast.show()
        })
      }

      override fun onBandageExceptionHappened(throwable: Throwable?) {
        throwable?.printStackTrace();//打印警告级别log，该throwable可能是最开始的bug导致的，无需关心
        toast.setText("Cockroach Worked");
        toast.show();
      }

      override fun onEnterSafeMode() {
        Toast.makeText(applicationContext, "safe_mode_excep_tips", Toast.LENGTH_LONG).show()

        // DebugSafeModeUI.showSafeModeUI()
        //
        // if (BuildConfig.DEBUG) {
        //   val intent = Intent(applicationContext, DebugSafeModeTipActivity::class.java)
        //   intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        //   startActivity(intent)
        // }
      }

      override fun onMayBeBlackScreen(e: Throwable?) {
        // super.onMayBeBlackScreen(e)
        val thread = Looper.getMainLooper().thread

        Log.e("AndroidRuntime", "--->onUncaughtExceptionHappened:$thread<---", e)
        //黑屏时建议直接杀死app
        sysExceptionHandler.uncaughtException(thread, RuntimeException("black screen"))
      }
    })
  }
}