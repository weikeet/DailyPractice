package com.weiwei.practice

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

/**
 * @author weicools
 * @date 2021.01.27
 */
class PracticeActivity : AppCompatActivity() {
  companion object {
    fun start(context: Context, key: Int) {
      val i = Intent(context, PracticeActivity::class.java)
      i.putExtra("fuck", key)
      context.startActivity(i)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(FrameLayout(this).apply { id = R.id.fragment_container })

    when (intent.getIntExtra("fuck", 0)) {
    }
  }
}