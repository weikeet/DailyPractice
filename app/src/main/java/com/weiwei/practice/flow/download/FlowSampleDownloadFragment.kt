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

package com.weiwei.practice.flow.download

import android.os.Bundle
import android.view.View
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import com.weiwei.practice.R
import com.weiwei.practice.databinding.FragmentFlowSampleDownloadBinding
import com.weiwei.practice.window.doOnApplyWindowInsets
import com.weiwei.practice.window.systemBarTop

/**
 * @author weiwei
 * @date 2022.12.10
 */
class FlowSampleDownloadFragment : Fragment(R.layout.fragment_flow_sample_download) {

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val binding = FragmentFlowSampleDownloadBinding.bind(view)

    view.doOnApplyWindowInsets { windowInsets ->
      binding.statusBarView.updateLayoutParams { height = windowInsets.systemBarTop }
    }

  }

}