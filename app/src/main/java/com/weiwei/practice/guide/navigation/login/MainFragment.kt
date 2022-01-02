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

package com.weiwei.practice.guide.navigation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.weiwei.practice.R
import com.weiwei.practice.binding.viewBinding
import com.weiwei.practice.databinding.NavigationLoginMainBinding

/**
 * @author weiwei
 * @date 2021.12.31
 */
class MainFragment : Fragment() {
  companion object {
    fun newInstance(): MainFragment {
      val args = Bundle()

      val fragment = MainFragment()
      fragment.arguments = args
      return fragment
    }
  }

  private val binding: NavigationLoginMainBinding by viewBinding(NavigationLoginMainBinding::bind)

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.navigation_login_main, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.toProfiler.setOnClickListener {
      findNavController().navigate(R.id.action_mainFragment_to_profilerFragment)
    }
  }
}