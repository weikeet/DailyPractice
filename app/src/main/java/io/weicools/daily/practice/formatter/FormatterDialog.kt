package io.weicools.daily.practice.formatter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import com.weicools.ktx.widget.extensions.dp
import com.weicools.ktx.widget.params.matchParent
import java.util.*

/**
 * @author weicools
 * @date 2020.11.20
 */
class FormatterDialog(context: Context) : AlertDialog(context) {

  @SuppressLint("SetTextI18n")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val rootView = LinearLayout(context).apply {
      orientation = LinearLayout.VERTICAL
    }
    setContentView(rootView)

    val button1 = AppCompatButton(context).apply {
      text = "UnsafeFormatTest"
      setOnClickListener {
        for (i in 1..3) {
          Log.d(Formatter.TAG, "NewFormatTest: ")
          SimpleDateFormat1Thread().start()
        }
      }
    }.also { rootView.addView(it, matchParent, 48.dp) }

    val button2 = AppCompatButton(context).apply {
      text = "LocalFormatTest"
      setOnClickListener {
        for (i in 1..3) {
          Log.d(Formatter.TAG, "LocalFormatTest: ")
          SimpleDateFormat2Thread().start()
        }
      }
    }.also { rootView.addView(it, matchParent, 48.dp) }

    val button3 = AppCompatButton(context).apply {
      text = "SingleFormatTest"
      setOnClickListener {
        var i = 50
        while (i > 0) {
          Log.d(Formatter.TAG, "SingleFormatTest: ")
          Log.d(Formatter.TAG, "SingleFormatTest: result=${Formatter.getDateFormat().format(Date())}")
          i--
        }
      }
    }.also { rootView.addView(it, matchParent, 48.dp) }
  }
}