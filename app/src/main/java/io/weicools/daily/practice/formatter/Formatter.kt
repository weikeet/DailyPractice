package io.weicools.daily.practice.formatter

import android.util.Log
import com.optimizer.test.utils.format.FormatSize
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

/**
 * @author weicools
 * @date 2020.11.20
 */
object Formatter {
  const val TAG = "Formatter"

  private val decimalOneLocal: ThreadLocal<DecimalFormat> by lazy {
    object : ThreadLocal<DecimalFormat>() {
      override fun initialValue(): DecimalFormat? = createDecimalOne()
    }
  }

  private val decimalTwoLocal: ThreadLocal<DecimalFormat> by lazy {
    object : ThreadLocal<DecimalFormat>() {
      override fun initialValue(): DecimalFormat? = createDecimalTwo()
    }
  }

  private val dateFormatLocal: ThreadLocal<SimpleDateFormat> by lazy {
    object : ThreadLocal<SimpleDateFormat>() {
      override fun initialValue(): SimpleDateFormat? {
        Log.d(TAG, "dateFormatLocal initialValue: ")
        return createDateFormat()
      }
    }
  }

  private fun createDecimalOne(): DecimalFormat {
    return DecimalFormat("###0.0", DecimalFormatSymbols(Locale.ENGLISH)).apply { roundingMode = RoundingMode.HALF_UP }
  }

  private fun createDecimalTwo(): DecimalFormat {
    return DecimalFormat("##0.00", DecimalFormatSymbols(Locale.ENGLISH)).apply { roundingMode = RoundingMode.HALF_UP }
  }

  private fun createDateFormat(): SimpleDateFormat {
    Log.d(TAG, "createDateFormat(): ")
    return SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.ENGLISH)
  }

  @JvmStatic
  fun getDecimalOne(): DecimalFormat {
    return decimalOneLocal.get() ?: createDecimalOne()
  }

  @JvmStatic
  fun getDecimalTwo(): DecimalFormat {
    return decimalTwoLocal.get() ?: createDecimalTwo()
  }

  fun getDateFormat(): SimpleDateFormat {
    return dateFormatLocal.get() ?: createDateFormat()
  }

  // TODO: 2020/11/20 delete
  val sdFormat = createDateFormat()

  @JvmStatic
  fun parseSize(sizeBytes: Long): FormatSize {
    val formatSize = FormatSize()

    formatSize.unit = "B"
    var result: Float = sizeBytes.toFloat()
    if (result > 900) {
      formatSize.unit = "KB"
      result /= 1024
    }
    if (result > 900) {
      formatSize.unit = "MB"
      result /= 1024
    }
    if (result > 900) {
      formatSize.unit = "GB"
      result /= 1024
    }
    if (result > 900) {
      formatSize.unit = "TB"
      result /= 1024
    }
    if (result > 900) {
      formatSize.unit = "PB"
      result /= 1024
    }

    formatSize.size = when {
      result <= 0 -> "0"
      result < 10 -> getDecimalOne().format(result.toDouble())
      result < 100 -> getDecimalTwo().format(result.toDouble())
      else -> result.roundToInt().toString()
    }
    formatSize.sizeUnit = formatSize.size + formatSize.unit
    return formatSize
  }

  @JvmStatic
  fun parseSpeed(speedBytesPerSecond: Long): FormatSize {
    val formatSize = FormatSize()

    formatSize.unit = "b/s"

    //单位换成bit/s (1MB/s = 8Mbps = 8Mb/s)
    var result: Float = speedBytesPerSecond * 8f
    if (result > 900) {
      formatSize.unit = "Kb/s"
      result /= 1024
    }
    if (result > 900) {
      formatSize.unit = "Mb/s"
      result /= 1024
    }
    if (result > 900) {
      formatSize.unit = "Gb/s"
      result /= 1024
    }
    if (result > 900) {
      formatSize.unit = "Tb/s"
      result /= 1024
    }
    if (result > 900) {
      formatSize.unit = "Pb/s"
      result /= 1024
    }

    formatSize.size = when {
      result <= 0 -> "0.0"
      result < 10 -> getDecimalTwo().format(result.toDouble()) ?: "0"
      result < 100 -> getDecimalOne().format(result.toDouble()) ?: "0"
      else -> result.roundToInt().toString()
    }

    formatSize.sizeUnit = formatSize.size + formatSize.unit
    return formatSize
  }
}