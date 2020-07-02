package io.weicools.daily.practice.async

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AsyncRequestActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(AsyncRequestView(this))
  }
}