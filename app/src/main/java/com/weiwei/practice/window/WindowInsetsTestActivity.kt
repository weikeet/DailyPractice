/*
 * Copyright (c) 2020 Weicools
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

package com.weiwei.practice.window

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnPreDraw
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import com.weiwei.practice.R
import com.weiwei.practice.binding.viewBinding
import com.weiwei.practice.databinding.ActivityWindowInsetsTestBinding
import com.weiwei.practice.window.delegate.EdgeInsetDelegate

/**
 * @author weiwei
 * @date 2022.01.11
 */
class WindowInsetsTestActivity : AppCompatActivity() {

  private val binding: ActivityWindowInsetsTestBinding by viewBinding(ActivityWindowInsetsTestBinding::bind)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_window_insets_test)

    // 野路子也是有野路子的好
    BarUtils.getStatusBarHeightViaResource(this)
    BarUtils.getNavigationBarHeightViaResource(this)

    val rootLayout: View = findViewById(R.id.rootLayout)

    val windowInsetsManager = WindowInsetsManager()

    // 要视图树构建完成后才能获取到RootWindowInsets
    rootLayout.doOnPreDraw {
      windowInsetsManager.getSystemBarInfo(window.decorView)

      // windowInsetsManager.setLightStatusBars(window.decorView, false)
      // windowInsetsManager.setLightNavigationBars(window.decorView, true)
    }

    // windowInsetsManager.fitEdgeToEdge(window, rootLayout)

    var fitTop = true
    binding.toolbar.doOnApplyWindowInsets { windowInsets, padding, _ ->
      Log.d("weiowo", "onCreate: toolbar h=${binding.toolbar.layoutParams.height}")
      Log.d("weiowo", "onCreate: toolbar systemBarTop=${windowInsets.systemBarTop}")
      if (fitTop) {
        fitTop = false
        binding.toolbar.updateLayoutParams {
          height += windowInsets.systemBarTop
        }
        binding.toolbar.updatePadding(top = padding.top + windowInsets.systemBarTop)
      }
    }

    var fitBottom = true
    binding.bottomTab.doOnApplyWindowInsets { windowInsets, padding, _ ->
      Log.d("weiowo", "onCreate: bottomTab h=${binding.bottomTab.layoutParams.height}")
      Log.d("weiowo", "onCreate: bottomTab systemBarBottom=${windowInsets.systemBarBottom}")
      if (fitBottom) {
        fitBottom = false
        binding.bottomTab.updateLayoutParams {
          height += windowInsets.systemBarBottom
        }
        binding.bottomTab.updatePadding(bottom = padding.bottom + windowInsets.systemBarBottom)
      }
    }

    EdgeInsetDelegate(this).start()

  }

}