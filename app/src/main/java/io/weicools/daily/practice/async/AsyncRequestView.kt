package io.weicools.daily.practice.async

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView

/**
 * @author weicools
 * @date 2020.07.02
 */
class AsyncRequestView @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

  private val textView = AppCompatTextView(context)
  private val textHandler = Handler()

  init {
    orientation = VERTICAL
    addView(textView)
    textView.text = "开始"
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    Thread(Runnable {
      Log.d("AsyncRequestView", "onAttachedToWindow: start")
      Thread.sleep(10000L)
      Log.d("AsyncRequestView", "onAttachedToWindow: end textView=null?${textView == null}")
      textHandler.post {
        textView.text = "结束"
        textView?.text = "结束 判空？"
        Log.d("AsyncRequestView", "onAttachedToWindow: end post")
      }
    }).start()
  }

  // override fun onDetachedFromWindow() {
  //   Log.d("AsyncRequestView", "onDetachedFromWindow: ")
  //   textHandler.removeCallbacksAndMessages(null)
  //   super.onDetachedFromWindow()
  // }
}