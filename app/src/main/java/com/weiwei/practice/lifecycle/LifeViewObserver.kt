package com.weiwei.practice.lifecycle

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 * @author weicools
 * @date 2021.07.05
 * 从 RootView 遍历到 ChildView, 上层 View 先调用
 */
class LifeViewObserver(private val TAG: String) : DefaultLifecycleObserver {
  var enableActivityLife = true

  //region ActivityLife
  override fun onCreate(owner: LifecycleOwner) {
    if (enableActivityLife) {
      Log.d(TAG, "onCreate: ")
    }
  }

  override fun onStart(owner: LifecycleOwner) {
    if (enableActivityLife) {
      Log.d(TAG, "onStart: ")
    }
  }

  override fun onResume(owner: LifecycleOwner) {
    if (enableActivityLife) {
      Log.d(TAG, "onResume: ")
    }
  }

  override fun onPause(owner: LifecycleOwner) {
    if (enableActivityLife) {
      Log.d(TAG, "onPause: ")
    }
  }

  override fun onStop(owner: LifecycleOwner) {
    if (enableActivityLife) {
      Log.d(TAG, "onStop: ")
    }
  }

  override fun onDestroy(owner: LifecycleOwner) {
    if (enableActivityLife) {
      Log.d(TAG, "onDestroy: ")
    }
  }
  //endregion

}