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

package io.weicools.daily.practice.binding.sample

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import io.weicools.daily.practice.R
import io.weicools.daily.practice.databinding.FragmentBindingTestBinding

class BindingTestV2Fragment : Fragment(R.layout.fragment_binding_test) {
  // private val binding: FragmentBindingTestBinding by viewBindingV2({ v -> FragmentBindingTestBinding.bind(v) })
  private val binding: FragmentBindingTestBinding by viewBindingV2(FragmentBindingTestBinding::bind)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.tvHello.text = "Hello World."
  }
}