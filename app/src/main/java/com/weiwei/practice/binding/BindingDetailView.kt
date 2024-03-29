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

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.weiwei.fluentview.ui.backgroundColorResource
import com.weiwei.fluentview.ui.gravityCenter
import com.weiwei.practice.R
import com.weiwei.practice.databinding.ItemBindingDetalBinding

class BindingDetailView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : LinearLayout(context, attrs) {

  private val binding by viewBinding(ItemBindingDetalBinding::bind)

  init {
    // use merge tag
    inflate(context, R.layout.item_binding_detal, this)
    orientation = VERTICAL
    gravity = gravityCenter
    backgroundColorResource = R.color.colorAccent12
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()

    post {
      binding.tvHello.text = "View: Hello World."
    }
  }
}