package io.weicools.daily.practice.dialog

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.weicools.ktx.widthPixels
import io.weicools.daily.practice.R

/**
 * @author weicools
 * @date 2020.08.19
 */
class FetchWidthDialog(context: Context) : AlertDialog(context) {
  var windowWidth = 0f

  @SuppressLint("SetTextI18n")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (windowWidth > 0) {
      try {
        window?.attributes?.width = windowWidth.toInt()
      } catch (e: Exception) {
        Log.d("FetchWidthDialog", "onCreate: e=" + e.message)
      }
    }
    setContentView(R.layout.dialog_fetch_width)

    val titleView = findViewById<TextView>(R.id.titleView)
    titleView!!.post {
      titleView.text = "ViewWidth = ${titleView.width}, " +
          "WindowWidth = ${window?.attributes?.width ?: 0}, " +
          "DisplayWidth = $widthPixels"
    }
  }
}