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
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.weiwei.fluentview.ui.gravityCenter
import com.weiwei.fluentview.ui.gravityCenterHorizontal
import com.weiwei.fluentview.view.appcompat.textView
import com.weiwei.fluentview.view.linearLayout
import com.weiwei.fluentview.view.linearParams
import com.weiwei.fluentview.view.material.materialButton
import com.weiwei.practice.R

/**
 * @author weiwei
 * @date 2023.03.09
 */
class TestLiveEventFirstFragment : Fragment() {

  private val sharedViewModel: TestLiveEventViewModel by activityViewModels()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return requireContext().linearLayout {
      gravity = gravityCenter
      orientation = LinearLayout.VERTICAL

      textView {
        text = "FirstFragment"
        gravity = gravityCenterHorizontal
      }

      materialButton {
        layoutParams = linearParams {
          topMargin = 20
        }
        setOnClickListener {
          sharedViewModel.sendEventOnMain()
        }
        text = "SendEvent"
      }

      materialButton {
        layoutParams = linearParams {
          topMargin = 20
        }
        setOnClickListener {
          findNavController().navigate(R.id.action_TestLiveEventFirstFragment_to_TestLiveEventSecondFragment)
        }
        text = "NavigateToSecond"
      }
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    sharedViewModel.observeSingleLiveEventOnMain(viewLifecycleOwner)
    sharedViewModel.observeLiveEventOnMain(viewLifecycleOwner)
    sharedViewModel.observeLiveDataOnMain(viewLifecycleOwner)
    sharedViewModel.observePeekLiveDataOnMain(viewLifecycleOwner)

  }

}