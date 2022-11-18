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

package com.weiwei.practice.di.sample.navigator

import androidx.fragment.app.FragmentActivity
import com.weiwei.practice.R
import com.weiwei.practice.di.sample.ui.ButtonsFragment
import com.weiwei.practice.di.sample.ui.LogsFragment
import javax.inject.Inject

/**
 * Navigator implementation.
 */
class AppNavigatorImpl @Inject constructor(private val activity: FragmentActivity) : AppNavigator {

  override fun navigateTo(screen: Screens) {
    val fragment = when (screen) {
      Screens.BUTTONS -> ButtonsFragment()
      Screens.LOGS -> LogsFragment()
    }

    activity.supportFragmentManager.beginTransaction()
      .replace(R.id.fragment_container, fragment)
      .addToBackStack(fragment::class.java.canonicalName)
      .commit()
  }
}
