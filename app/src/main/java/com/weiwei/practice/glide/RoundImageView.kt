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

package com.weiwei.practice.glide

import android.content.Context
import android.graphics.Canvas
import androidx.appcompat.widget.AppCompatImageView

/**
 * @author weicools
 * @date 2021.11.22
 */
class RoundImageView(context: Context) : AppCompatImageView(context) {
  override fun onDraw(canvas: Canvas?) {
    super.onDraw(canvas)
  }

  /**
   * https://juejin.cn/post/6844903501668188174 comment
   */
  private fun drawLeftTop(canvas: Canvas, radius: Float) {
    // initPath()
    // mPath.moveTo(0, radius)
    // mPath.lineTo(0, 0)
    // mPath.lineTo(radius, 0)
    // mRect.set(0, 0, radius * 2, radius * 2)
    // mPath.arcTo(mRect, -90, -90)
    // mPath.close()
    // canvas.drawPath(mPath, mPaint)
  }
}