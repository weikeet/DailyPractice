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

package com.weiwei.main.ui

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.drakeet.multitype.MultiTypeAdapter
import com.weiwei.fluentview.ui.unit.dp

/**
 * @author weiwei
 * @date 2023.02.22
 */
class MainItemDecoration(private val adapter: MultiTypeAdapter) : ItemDecoration() {
  private val bottomValue = 1.dp

  private val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

  override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
    val childPosition = parent.getChildAdapterPosition(view)
    if (childPosition < 0 || childPosition >= adapter.items.size) {
      return
    }
    if (childPosition < adapter.items.size - 1) {
      outRect.bottom = bottomValue
    } else {
      super.getItemOffsets(outRect, view, parent, state)
    }
  }


  override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
    super.onDraw(canvas, parent, state)
    for (i in 0 until parent.childCount - 1) {
      val view = parent.getChildAt(i)
      drawDivider(canvas, view)
    }
  }

  /**
   * 在[getItemOffsets]中，我们已经让item底部空出了1dp的位置，现在我们要在这1dp高的区域内填充，实现分割线
   */
  private fun drawDivider(canvas: Canvas, view: View) {
    val left = view.left
    val right = view.right
    val top = view.bottom
    val bottom: Int = top + bottomValue
    canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)
  }

}