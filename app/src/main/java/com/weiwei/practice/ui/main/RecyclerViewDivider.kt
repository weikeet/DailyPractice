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

package com.weiwei.practice.ui.main

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.weiwei.extensions.colorOf
import com.weiwei.practice.R

/**
 * @author weiwei
 * @date 2022.01.13
 */
class RecyclerViewDivider(private val dividerHeight: Int) : RecyclerView.ItemDecoration() {
  private val dividerPaint: Paint = Paint()

  init {
    dividerPaint.color = colorOf(R.color.material_on_surface_stroke)
    dividerPaint.style = Paint.Style.FILL
  }

  override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
    super.getItemOffsets(outRect, view, parent, state)

    outRect.set(0, 0, 0, dividerHeight)
  }

  override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {

    val childCount = parent.childCount
    for (i in 0 until childCount - 1) {
      val view = parent.getChildAt(i)

      val left = view.left.toFloat()
      val right = view.right.toFloat()

      val params = view.layoutParams as ViewGroup.MarginLayoutParams
      val top = (view.bottom + params.bottomMargin).toFloat()
      val bottom = (view.bottom + dividerHeight + params.bottomMargin).toFloat()

      c.drawRect(left, top, right, bottom, dividerPaint)
    }
  }
}