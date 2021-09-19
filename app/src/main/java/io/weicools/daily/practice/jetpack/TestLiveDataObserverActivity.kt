package io.weicools.daily.practice.jetpack

import android.os.Bundle
import android.util.Log
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
  private val TAG = "TestLiveDataObserverActivity"

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

    viewModel.textData.observe(this, {
      Log.d(TAG, "onCreate: observe textData=$it")
    })
    viewModel.textData.observeForever {
      Log.d(TAG, "onCreate: observeForever textData=$it")
    }

    viewModel.refreshText()

  }

  override fun onStart() {
    super.onStart()
    Log.d(TAG, "onStart: ")

    viewModel.getStringData().observe(this, ob)
    viewModel.refreshTip()
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