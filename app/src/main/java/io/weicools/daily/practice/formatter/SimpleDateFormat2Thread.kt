package io.weicools.daily.practice.formatter

import android.util.Log

/**
 * @author weicools
 * @date 2020.11.20
 */
class SimpleDateFormat2Thread : Thread() {
  override fun run() {
    var i = 100
    while (i > 0) {
      i--

      try {
        this.join(1000)
      } catch (e: Exception) {
        e.printStackTrace()
      }

      try {
        //1580646020
        val a = Formatter.getDateFormat().parse("2020-02-02 20:20:20")?.time ?: 0L
        Log.d(Formatter.TAG, "${this.name} SafeResult=$a")
      } catch (e: Exception) {
        e.printStackTrace()
      }
    }
  }
}