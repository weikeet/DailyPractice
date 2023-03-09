package com.weiwei.practice.jetpack

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author weicools
 * @date 2021.01.07
 */
class TestViewModel : ViewModel() {
  companion object {
    val stringData1: MutableLiveData<String> = MutableLiveData()
  }

  val textData: MutableLiveData<String> = MutableLiveData()

  val stringData get() = stringData1

  fun refreshText() {
    viewModelScope.launch {
      delay(5000)
      textData.value = "2233"
    }
  }

  fun refreshTip() {
    viewModelScope.launch {
      delay(5000)
      stringData.value = "refreshTip+++"
    }
  }

}