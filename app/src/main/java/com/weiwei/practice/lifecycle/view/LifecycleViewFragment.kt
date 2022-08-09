package com.weiwei.practice.lifecycle.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.weiwei.practice.lifecycle.core.LifeFragment

/**
 * @author weicools
 * @date 2020.08.18
 */
class LifecycleViewFragment : LifeFragment() {

  override fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    return LifecycleView3(requireContext())
  }

}