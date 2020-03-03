package io.weicools.daily.practice.material

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.weicools.daily.practice.R
import kotlinx.android.synthetic.main.activity_toolbar_test.*

/**
 * @author weicools
 * @date 2020.02.26
 */
class ToolbarTestActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_toolbar_test)

    toolbar.title = "aaa"
    // toolbar.setNavigationIcon(R.mipmap.ic_launcher)
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    toolbar.setNavigationOnClickListener {
      finish()
    }

  }
}