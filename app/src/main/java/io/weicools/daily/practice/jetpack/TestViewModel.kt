package io.weicools.daily.practice.jetpack

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

/**
 * @author weicools
 * @date 2021.01.07
 */
class TestViewModel : ViewModel() {
  private val handler = Handler()

  fun getStringData(): LiveData<String> {
    return SingleData.stringData
  }

  fun refreshTip() {
    handler.postDelayed({
      SingleData.stringData.value = "refreshTip+++"
    }, 2000L)
  }

  override fun onCleared() {
    super.onCleared()
    handler.removeCallbacksAndMessages(null)
  }
}