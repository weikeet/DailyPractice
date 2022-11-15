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
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * @author weicools
 * @date 2017/12/30
 */
@Suppress("DEPRECATION")
class FragmentAdapter : FragmentPagerAdapter {
  private val fragmentDataList: List<FragmentData>

  constructor(fm: FragmentManager, fragmentDataList: List<FragmentData>) : super(fm, BEHAVIOR_SET_USER_VISIBLE_HINT) {
    this.fragmentDataList = fragmentDataList
  }

  constructor(fm: FragmentManager, behavior: Int, fragmentDataList: List<FragmentData>) : super(fm, behavior) {
    this.fragmentDataList = fragmentDataList
  }

  override fun getItem(position: Int): Fragment {
    return fragmentDataList[position].fragment
  }

  override fun getCount(): Int {
    return fragmentDataList.size
  }

  override fun getPageTitle(position: Int): CharSequence? {
    return fragmentDataList[position].title
  }
}