package com.weiwei.practice

import android.util.Log

/**
 * @author weicools
 * @date 2020.05.16
 */
object TimeRecorder {
  private var attachTime: Long = 0

  fun recordStartTime(processName: String) {
    attachTime = System.currentTimeMillis()
    Log.d("TimeRecorder", "recordStartTime: processName=$processName, attachTime=$attachTime")
  }

  fun recordStopTime() {
    val startCost = System.currentTimeMillis() - attachTime
    Log.d("TimeRecorder", "recordStopTime: startCost=$startCost")
  }
}