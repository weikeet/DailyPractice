package io.weicools.daily.practice.jetpack

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author weicools
 * @date 2021.01.07
 */
class TestViewModel : ViewModel() {
  private val handler = Handler(Looper.getMainLooper())

  val textData: MutableLiveData<String> = MutableLiveData()

  fun refreshText() {
    handler.postDelayed({
      textData.value = "2233"
    }, 5000L)
  }

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