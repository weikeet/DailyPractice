package com.weiwei.practice.jetpack

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.weiwei.fluentview.view.WindowInsetsEdgeDelegate
import com.weiwei.practice.R

/**
 * @author weicools
 * @date 2021.01.07
 */
class TestLiveDataObserverActivity : AppCompatActivity() {
  private val TAG = "TestLiveDataObserverActivity"

  private val viewModel: TestViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_test_live_data_observer)

    WindowInsetsEdgeDelegate(this).start()

    supportFragmentManager.beginTransaction()
      .replace(R.id.fragment_container, TestLiveDataObserverFragment.newInstance(), "asd")
      .commit()

    val resultView: TextView = findViewById(R.id.resultView)

    viewModel.stringData.observe(this) {
      resultView.text = it
    }
    viewModel.textData.observe(this) {
      Log.d(TAG, "onCreate: observe textData=$it")
    }
    viewModel.textData.observeForever {
      Log.d(TAG, "onCreate: observeForever textData=$it")
    }

    viewModel.refreshText()
    viewModel.refreshTip()

  }

  override fun onStart() {
    super.onStart()
    Log.d(TAG, "onStart: ")

  }

  override fun onResume() {
    super.onResume()
    Log.d(TAG, "onResume: ")
  }

  override fun onPause() {
    super.onPause()
    Log.d(TAG, "onPause: ")
  }

  override fun onStop() {
    super.onStop()
    Log.d(TAG, "onStop: ")
  }

  override fun onDestroy() {
    super.onDestroy()
    Log.d(TAG, "onDestroy: ")
  }

}