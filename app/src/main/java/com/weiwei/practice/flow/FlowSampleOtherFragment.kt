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

package com.weiwei.practice.flow

import android.os.Bundle
import android.view.View
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.weiwei.practice.R
import com.weiwei.practice.databinding.FragmentFlowSampleOtherBinding
import com.weiwei.practice.flow.case.SearchTextWatcher
import com.weiwei.practice.flow.case.SearchTextWatcherFlow
import com.weiwei.practice.flow.case.clickFlow
import com.weiwei.practice.flow.case.countdown
import com.weiwei.practice.flow.case.throttleFirst
import com.weiwei.practice.window.doOnApplyWindowInsets
import com.weiwei.practice.window.systemBarTop
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.launch

/**
 * @author weiwei
 * @date 2022.12.08
 */
class FlowSampleOtherFragment : Fragment(R.layout.fragment_flow_sample_other) {
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val binding = FragmentFlowSampleOtherBinding.bind(view)

    view.doOnApplyWindowInsets { windowInsets ->
      binding.statusBarView.updateLayoutParams { height = windowInsets.systemBarTop }
    }

    SearchTextWatcher(binding.editText1) {
      binding.tvResult.text = it
    }
    SearchTextWatcherFlow(binding.editText2, lifecycleScope) {
      binding.tvResult.text = it
    }

    binding.fastClickButton.clickFlow()
      .throttleFirst(500)
      .onEach {
        binding.tvResult.text = "Fast Click ${System.currentTimeMillis()}"
      }
      .launchIn(lifecycleScope)

    binding.flowCountdownButton.setOnClickListener {
      lifecycleScope.launch {
        countdown(10, 1) {
          binding.tvResult.text = "Countdown $it"
        }
        val ret = countdown(60_000, 2_000) { remainingTime ->
          // calculate(remainingTime)
          remainingTime + 2
        }
          .onStart {
            binding.tvResult.text = "countdown start"
          }
          .onCompletion {
            binding.tvResult.text = "countdown completion"
          }
          .reduce { acc, value ->
            binding.tvResult.text = "countdown acc = $acc, value = $value, res=${acc + value}"
            acc + value
          }
        binding.tvResult.text = "countdown acc ret = $ret"
      }
    }
  }
}