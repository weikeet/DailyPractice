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

package com.weiwei.practice.material

import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MarkerEdgeTreatment
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.OffsetEdgeTreatment
import com.google.android.material.shape.RoundedCornerTreatment
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.android.material.shape.TriangleEdgeTreatment
import com.weiwei.fluentview.ui.unit.dp
import com.weiwei.fluentview.view.horizontalMargin
import com.weiwei.practice.R
import com.weiwei.practice.databinding.MaterialFragmentShapeBinding
import com.weiwei.practice.material.corner.ExtraRoundCornerTreatment
import com.weiwei.practice.material.corner.InnerCutCornerTreatment
import com.weiwei.practice.material.corner.InnerRoundCornerTreatment
import com.weiwei.practice.material.edge.ArgEdgeTreatment
import com.weiwei.practice.material.edge.QuadEdgeTreatment
import com.weiwei.practice.window.doOnApplyWindowInsets
import com.weiwei.practice.window.systemBarBottom
import com.weiwei.practice.window.systemBarTop

/**
 * @author weiwei
 * @date 2022.11.17
 */
// https://xuyisheng.top/mdc-shape/
class MaterialShapeFragment : Fragment(R.layout.material_fragment_shape) {
  private lateinit var binding: MaterialFragmentShapeBinding

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding = MaterialFragmentShapeBinding.bind(view)

    view.doOnApplyWindowInsets { windowInsets ->
      view.updatePadding(top = windowInsets.systemBarTop, bottom = windowInsets.systemBarBottom)
    }

    applyAppearanceModel1()
    applyAppearanceModel2()
    applyAppearanceModel3()
    applyAppearanceModel4()
    applyAppearanceModel5()
    applyAppearanceModel6()
    applyAppearanceModel7()
    applyAppearanceModel8()
    applyAppearanceModel9()
    applyAppearanceModel10()
    applyAppearanceModel101()
    applyAppearanceModel11()
  }

  private fun createItemView(): View {
    val v = View(requireContext()).apply {
      layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 100.dp).apply {
        horizontalMargin = 16.dp
        topMargin = 16.dp
      }
    }
    binding.container.addView(v)
    return v
  }

  private fun applyAppearanceModel1() {
    val shapePathModel = ShapeAppearanceModel.builder()
      .setAllCorners(RoundedCornerTreatment())
      .setAllCornerSizes(8f.dp)
      .build()
    val backgroundDrawable = MaterialShapeDrawable(shapePathModel).apply {
      setTint(Color.parseColor("#bebebe"))
      paintStyle = Paint.Style.FILL
    }
    createItemView().background = backgroundDrawable
  }

  private fun applyAppearanceModel2() {
    val shapePathModel = ShapeAppearanceModel.builder()
      .setAllCorners(CornerFamily.ROUNDED, 8f.dp)
      .build()
    val backgroundDrawable = MaterialShapeDrawable(shapePathModel).apply {
      setTint(Color.parseColor("#bebebe"))
      paintStyle = Paint.Style.FILL
    }
    createItemView().background = backgroundDrawable
  }

  private fun applyAppearanceModel3() {
    val shapePathModel = ShapeAppearanceModel.builder()
      .setAllCorners(CornerFamily.ROUNDED, 8f.dp)
      .setAllEdges(TriangleEdgeTreatment(8f.dp, true))
      .build()
    val backgroundDrawable = MaterialShapeDrawable(shapePathModel).apply {
      setTint(Color.parseColor("#bebebe"))
      paintStyle = Paint.Style.FILL_AND_STROKE
      strokeWidth = 2f.dp
    }
    createItemView().background = backgroundDrawable
  }

  /**
   * require parent view clipChildren=false
   */
  private fun applyAppearanceModel4() {
    val shapePathModel = ShapeAppearanceModel.builder()
      .setAllCorners(CornerFamily.ROUNDED, 8f.dp)
      .setAllEdges(TriangleEdgeTreatment(8f.dp, false))
      .build()
    val backgroundDrawable = MaterialShapeDrawable(shapePathModel).apply {
      setTint(Color.parseColor("#bebebe"))
      paintStyle = Paint.Style.FILL
    }
    createItemView().background = backgroundDrawable
  }

  private fun applyAppearanceModel5() {
    val shapePathModel = ShapeAppearanceModel.builder()
      .setAllCorners(CornerFamily.ROUNDED, 8f.dp)
      .setAllEdges(MarkerEdgeTreatment(8f.dp))
      .build()
    val backgroundDrawable = MaterialShapeDrawable(shapePathModel).apply {
      setTint(Color.parseColor("#bebebe"))
      paintStyle = Paint.Style.FILL
    }
    createItemView().background = backgroundDrawable
  }

  private fun applyAppearanceModel6() {
    val shapePathModel = ShapeAppearanceModel.builder()
      .setAllCorners(CornerFamily.ROUNDED, 8f.dp)
      .setAllEdges(OffsetEdgeTreatment(MarkerEdgeTreatment(8f.dp), 16f.dp))
      .build()
    val backgroundDrawable = MaterialShapeDrawable(shapePathModel).apply {
      setTint(Color.parseColor("#bebebe"))
      paintStyle = Paint.Style.FILL
    }
    createItemView().background = backgroundDrawable
  }

  private fun applyAppearanceModel7() {
    val shapePathModel = ShapeAppearanceModel.builder()
      .setAllCorners(InnerCutCornerTreatment())
      .build()
    val backgroundDrawable = MaterialShapeDrawable(shapePathModel).apply {
      setTint(Color.parseColor("#bebebe"))
      paintStyle = Paint.Style.FILL
    }
    createItemView().background = backgroundDrawable
  }

  private fun applyAppearanceModel8() {
    val shapePathModel = ShapeAppearanceModel.builder()
      .setAllCorners(InnerRoundCornerTreatment())
      .build()
    val backgroundDrawable = MaterialShapeDrawable(shapePathModel).apply {
      setTint(Color.parseColor("#bebebe"))
      paintStyle = Paint.Style.FILL
    }
    createItemView().background = backgroundDrawable
  }

  private fun applyAppearanceModel9() {
    val shapePathModel = ShapeAppearanceModel.builder()
      .setAllCorners(ExtraRoundCornerTreatment())
      .build()
    val backgroundDrawable = MaterialShapeDrawable(shapePathModel).apply {
      setTint(Color.parseColor("#bebebe"))
      paintStyle = Paint.Style.FILL
    }
    createItemView().background = backgroundDrawable
  }

  private fun applyAppearanceModel10() {
    val shapePathModel = ShapeAppearanceModel.builder()
      .setLeftEdge(ArgEdgeTreatment(16f.dp, true))
      .setRightEdge(ArgEdgeTreatment(16f.dp, true))
      .build()
    val backgroundDrawable = MaterialShapeDrawable(shapePathModel).apply {
      setTint(Color.parseColor("#bebebe"))
      paintStyle = Paint.Style.FILL
    }
    createItemView().background = backgroundDrawable
  }

  private fun applyAppearanceModel101() {
    val shapePathModel = ShapeAppearanceModel.builder()
      .setLeftEdge(ArgEdgeTreatment(16f.dp, true))
      .setRightEdge(ArgEdgeTreatment(16f.dp, true))
      .build()
    val backgroundDrawable = MaterialShapeDrawable(shapePathModel).apply {
      setTint(Color.parseColor("#bebebe"))
      paintStyle = Paint.Style.FILL
    }
    val v = FrameLayout(requireContext()).apply {
      layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 100.dp).apply {
        horizontalMargin = 16.dp
        topMargin = 16.dp
      }
    }
    binding.container.addView(v)
    v.background = backgroundDrawable

    val cv = FrameLayout(requireContext()).apply {
      layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
    }
    cv.background = GradientDrawable().apply {
      colors = intArrayOf(Color.parseColor("#e7115b"), Color.parseColor("#f00039"))
    }
    v.addView(cv)
  }

  private fun applyAppearanceModel11() {
    val shapePathModel = ShapeAppearanceModel.builder()
      .setLeftEdge(QuadEdgeTreatment(16f.dp))
      .setRightEdge(QuadEdgeTreatment(16f.dp))
      .build()
    val backgroundDrawable = MaterialShapeDrawable(shapePathModel).apply {
      setTint(Color.parseColor("#bebebe"))
      paintStyle = Paint.Style.FILL
    }
    createItemView().background = backgroundDrawable
  }
}