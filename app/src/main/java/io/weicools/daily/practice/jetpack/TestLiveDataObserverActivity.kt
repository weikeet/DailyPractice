package io.weicools.daily.practice.jetpack

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import io.weicools.daily.practice.R

/**
 * @author weicools
 * @date 2021.01.07
 */
class TestLiveDataObserverActivity : AppCompatActivity() {

  private val viewModel: TestViewModel by viewModels()

  private lateinit var ob: Observer<String>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_test_live_data_observer)

    supportFragmentManager.beginTransaction()
      .replace(R.id.fragment_container, TestLiveDataObserverFragment.newInstance(), "asd")
      .commit()

    val resultView: TextView = findViewById(R.id.resultView)

    ob = Observer {
      resultView.text = it
    }
    viewModel.getStringData().observe(this, ob)

    // viewModel.getStringData().observe(this, Observer {
    //   resultView.text = it
    // })
  }

  override fun onStart() {
    super.onStart()

    viewModel.getStringData().observe(this, ob)
    viewModel.refreshTip()
  }
}