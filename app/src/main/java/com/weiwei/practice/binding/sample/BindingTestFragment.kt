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

package com.weiwei.practice.binding.sample

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.weiwei.practice.R
import com.weiwei.practice.databinding.FragmentBindingTestBinding

class BindingTestFragment : Fragment(R.layout.fragment_binding_test) {
  private var _binding: FragmentBindingTestBinding? = null
  private val binding get() = _binding!!

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    _binding = FragmentBindingTestBinding.bind(view)

    binding.tvHello.text = "Hello World."
  }

  override fun onDestroyView() {
    super.onDestroyView()

    _binding = null
  }
}