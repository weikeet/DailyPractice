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

package com.weiwei.practice.jetpack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.weiwei.fluentview.ui.gravityCenter
import com.weiwei.fluentview.ui.gravityCenterHorizontal
import com.weiwei.fluentview.view.appcompat.textView
import com.weiwei.fluentview.view.linearLayout

/**
 * @author weiwei
 * @date 2023.03.09
 */
class TestLiveEventSecondFragment : Fragment() {

  private val sharedViewModel: TestLiveEventViewModel by activityViewModels()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return requireContext().linearLayout {
      gravity = gravityCenter

      textView {
        text = "SecondFragment"
        gravity = gravityCenterHorizontal
      }
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    sharedViewModel.receiveEventOnSecond(viewLifecycleOwner)

    sharedViewModel.observeSingleLiveEventOnSecond(viewLifecycleOwner)
    sharedViewModel.observeLiveEventOnSecond(viewLifecycleOwner)
    sharedViewModel.observeLiveDataOnSecond(viewLifecycleOwner)
    sharedViewModel.observePeekLiveDataOnSecond(viewLifecycleOwner)

  }

}