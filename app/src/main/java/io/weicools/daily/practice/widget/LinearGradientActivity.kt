package io.weicools.daily.practice.widget

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.weicools.daily.practice.R
import kotlinx.android.synthetic.main.activity_linear_gradient.*

class LinearGradientActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_linear_gradient)

    linearGradientView.setOnClickListener {
      linearGradientView.changeColorSmoothly()
    }

    linearGradientDrawView.setOnClickListener {
      linearGradientDrawView.changeColorSmoothly()
    }

    //测试结果 linearGradientView和linearGradientDrawView 内存占用没有明显区别
  }
}