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

package com.weiwei.practice.di.sample.ui

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.weiwei.fluentview.view.WindowInsetsEdgeDelegate
import com.weiwei.practice.R
import com.weiwei.practice.app.PracticeApp
import com.weiwei.practice.di.sample.navigator.AppNavigator
import com.weiwei.practice.di.sample.navigator.Screens

/**
 * @author weiwei
 * @date 2022.11.18
 */
class DiMainActivity : AppCompatActivity() {

  private lateinit var navigator: AppNavigator

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(FrameLayout(this).apply {
      id = R.id.fragment_container
    })

    WindowInsetsEdgeDelegate(this).start()

    navigator = (applicationContext as PracticeApp).serviceLocator.provideNavigator(this)

    if (savedInstanceState == null) {
      navigator.navigateTo(Screens.BUTTONS)
    }
  }

  @Suppress("DEPRECATION")
  @Deprecated("Deprecated in Java")
  override fun onBackPressed() {
    super.onBackPressed()

    if (supportFragmentManager.backStackEntryCount == 0) {
      finish()
    }
  }
}
