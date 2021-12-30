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

package com.weiwei.practice.binding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.weiwei.practice.R
import com.weiwei.practice.databinding.FragmentBindingBinding

// class BindingFragment0 : Fragment(R.layout.fragment_binding) {
//
//   private val binding: FragmentBindingBinding by viewBinding(FragmentBindingBinding::bind)
//
//   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//     super.onViewCreated(view, savedInstanceState)
//
//     binding.tvHello.text = "Fragment: Hello World."
//   }
// }

class BindingFragment : Fragment() {

  private val binding: FragmentBindingBinding by viewBinding(FragmentBindingBinding::bind)

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_binding, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.tvHello.text = "Fragment: Hello World."
  }
}