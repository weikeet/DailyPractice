/*
 * Copyright (c) 2020 Weiwei
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *    https://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.weiwei.practice.lifecycle.sample

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @author weicools
 * @date 2021.07.09
 */
class FragmentAdapter2 : FragmentStateAdapter {
  private val fragmentList: MutableList<Fragment> = ArrayList()

  fun updateFragmentList(fragmentList: List<Fragment>) {
    this.fragmentList.clear()
    this.fragmentList.addAll(fragmentList)
  }

  constructor(fragment: Fragment) : super(fragment)

  constructor(fragmentActivity: FragmentActivity) : super(fragmentActivity)

  constructor(fragmentManager: FragmentManager, lifecycle: Lifecycle) : super(fragmentManager, lifecycle) {}

  override fun createFragment(position: Int): Fragment {
    return fragmentList[position]
  }

  override fun getItemCount(): Int {
    return fragmentList.size
  }
}