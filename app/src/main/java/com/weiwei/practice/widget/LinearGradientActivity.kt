package com.weiwei.practice.widget

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.weiwei.fluentview.view.WindowInsetsEdgeDelegate
import com.weiwei.practice.R

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

    WindowInsetsEdgeDelegate(this).start()

    //测试结果 linearGradientView和linearGradientDrawView 内存占用没有明显区别
  }
}