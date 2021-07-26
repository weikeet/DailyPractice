package io.weicools.daily.practice.widget

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.weicools.daily.practice.R

class LinearGradientActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_linear_gradient)

    val linearGradientView: LinearGradientView = findViewById(R.id.linearGradientView)
    linearGradientView.setOnClickListener {
      linearGradientView.changeColorSmoothly()
    }

    val linearGradientDrawView: LinearGradientDrawView = findViewById(R.id.linearGradientDrawView)
    linearGradientDrawView.setOnClickListener {
      linearGradientDrawView.changeColorSmoothly()
    }

    //测试结果 linearGradientView和linearGradientDrawView 内存占用没有明显区别
  }
}