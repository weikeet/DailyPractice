package com.weiwei.main.app.crashes

import android.os.Process
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.system.exitProcess

/**
 * @author weiwei
 * @date 2023.04.07
 */
object AppCrashes : Thread.UncaughtExceptionHandler {

    fun interface CrashesCallback {
        fun uncaughtException(t: Thread, e: Throwable)
    }

    private val callbacks: CopyOnWriteArrayList<CrashesCallback> = CopyOnWriteArrayList()

    private fun notifyCrashes(t: Thread, e: Throwable) {
        callbacks.forEach {
            it.uncaughtException(t, e)
        }
    }

    fun registerCallback(callback: CrashesCallback) {
        callbacks.add(callback)
    }

    fun unregisterCallback(callback: CrashesCallback) {
        callbacks.remove(callback)
    }

    private var defaultHandler: Thread.UncaughtExceptionHandler? = null

    fun initialize() {
        setupUncaughtExceptionHandler()
    }

    private fun setupUncaughtExceptionHandler() {
        defaultHandler = Thread.getDefaultUncaughtExceptionHandler()

        try {
            Thread.setDefaultUncaughtExceptionHandler(this)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun uncaughtException(t: Thread, e: Throwable) {
        notifyCrashes(t, e)

        if (defaultHandler != null) {
            defaultHandler?.uncaughtException(t, e)
        } else {
            Process.killProcess(Process.myPid())
            exitProcess(10)
        }
    }

}
