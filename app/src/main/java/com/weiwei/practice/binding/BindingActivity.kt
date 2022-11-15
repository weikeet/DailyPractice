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
import androidx.appcompat.app.AppCompatActivity
import com.weiwei.fluentview.view.WindowInsetsEdgeDelegate
import com.weiwei.practice.R
import com.weiwei.practice.databinding.ActivityBindingBinding

// class BindingActivity0 : AppCompatActivity(R.layout.activity_binding) {
//
//   private val binding: ActivityBindingBinding by viewBinding(ActivityBindingBinding::bind, R.id.root_view)
//   // private val binding: ActivityBindingBinding by viewBinding(ActivityBindingBinding::bind)
//
//   override fun onCreate(savedInstanceState: Bundle?) {
//     super.onCreate(savedInstanceState)
//
//     setContentView(binding.root)
//
//     binding.tvHello.text = "Activity: Hello World."
//
//     binding.tvHello.setOnClickListener {
//       BindingDialogFragment().show(supportFragmentManager, "DialogTag")
//     }
//
//     supportFragmentManager.beginTransaction()
//       .replace(R.id.fragment_container, BindingFragment(), "Tag")
//       .commit()
//   }
// }

class BindingActivity : AppCompatActivity() {

  private val binding: ActivityBindingBinding by viewBinding(ActivityBindingBinding::bind)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_binding)

    WindowInsetsEdgeDelegate(this).start()

    binding.tvHello.text = "Activity: Hello World."

    binding.tvHello.setOnClickListener {
      BindingDialogFragment().show(supportFragmentManager, "DialogTag")
    }

    supportFragmentManager.beginTransaction()
      .replace(R.id.fragment_container, BindingFragment(), "Tag")
      .commit()
  }
}