@file:Suppress("unused")

package com.weicools.ktx.widget.extensions

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.constraintlayout.helper.widget.Flow
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.MarginLayoutParamsCompat
import com.weicools.ktx.widget.NO_GETTER
import com.weicools.ktx.widget.noGetter

/**
 * @author weicools
 * @date 2020.05.13
 */

//region View extend common params field
inline var View.layout_width: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    val h = layoutParams?.height ?: 0
    layoutParams = if (layoutParams == null) {
      ViewGroup.MarginLayoutParams(value, h)
    } else {
      layoutParams.append {
        width = value
        height = h
      }
    }
  }

inline var View.layout_height: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    val w = layoutParams?.width ?: 0
    layoutParams = if (layoutParams == null) {
      ViewGroup.MarginLayoutParams(w, value)
    } else {
      layoutParams.append {
        width = w
        height = value
      }
    }
  }

inline var View.margin_top: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    (layoutParams as? ViewGroup.MarginLayoutParams)?.apply { topMargin = value }
  }

inline var View.margin_bottom: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    (layoutParams as? ViewGroup.MarginLayoutParams)?.apply { bottomMargin = value }
  }

inline var View.margin_left: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    (layoutParams as? ViewGroup.MarginLayoutParams)?.apply { leftMargin = value }
  }

inline var View.margin_right: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    (layoutParams as? ViewGroup.MarginLayoutParams)?.apply { rightMargin = value }
  }

inline var View.margin_start: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    (layoutParams as? ViewGroup.MarginLayoutParams)?.apply { MarginLayoutParamsCompat.setMarginStart(this, value) }
  }

inline var View.margin_end: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    (layoutParams as? ViewGroup.MarginLayoutParams)?.apply { MarginLayoutParamsCompat.setMarginEnd(this, value) }
  }
//endregion

//region View extend linear params field
inline var View.linearWeight: Float
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    layoutParams = LinearLayout.LayoutParams(layoutParams.width, layoutParams.height).also {
      it.gravity = (layoutParams as? LinearLayout.LayoutParams)?.gravity ?: -1
      it.weight = value
    }
  }

inline var View.linearGravity: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    layoutParams = LinearLayout.LayoutParams(layoutParams.width, layoutParams.height).also {
      it.weight = (layoutParams as? LinearLayout.LayoutParams)?.weight ?: 0f
      it.gravity = value
    }
  }
//endregion

//region View extend relative params field
inline var View.alignParentStart: Boolean
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    if (!value) return
    layoutParams = RelativeLayout.LayoutParams(layoutParams.width, layoutParams.height).apply {
      (layoutParams as? RelativeLayout.LayoutParams)?.rules?.forEachIndexed { index, i ->
        addRule(index, i)
      }
      addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE)
    }
  }

inline var View.alignParentEnd: Boolean
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    if (!value) return
    layoutParams = RelativeLayout.LayoutParams(layoutParams.width, layoutParams.height).apply {
      (layoutParams as? RelativeLayout.LayoutParams)?.rules?.forEachIndexed { index, i ->
        addRule(index, i)
      }
      addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE)
    }
  }

inline var View.centerInParent: Boolean
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    if (!value) return
    layoutParams = RelativeLayout.LayoutParams(layoutParams.width, layoutParams.height).apply {
      (layoutParams as? RelativeLayout.LayoutParams)?.rules?.forEachIndexed { index, i ->
        addRule(index, i)
      }
      addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
    }
  }

inline var View.centerHorizontal: Boolean
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    if (!value) return
    layoutParams = RelativeLayout.LayoutParams(layoutParams.width, layoutParams.height).apply {
      (layoutParams as? RelativeLayout.LayoutParams)?.rules?.forEachIndexed { index, i ->
        addRule(index, i)
      }
      addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE)
    }
  }

inline var View.centerVertical: Boolean
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    if (!value) return
    layoutParams = RelativeLayout.LayoutParams(layoutParams.width, layoutParams.height).apply {
      (layoutParams as? RelativeLayout.LayoutParams)?.rules?.forEachIndexed { index, i ->
        addRule(index, i)
      }
      addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)
    }
  }
//endregion

//region View extend constraint params field
inline var View.top_toTopOf: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    layoutParams = layoutParams.append {
      topToTop = value
      topToBottom = -1
    }
  }

inline var View.top_toBottomOf: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    layoutParams = layoutParams.append {
      topToBottom = value
      topToTop = -1
    }
  }

inline var View.bottom_toBottomOf: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    layoutParams = layoutParams.append {
      bottomToBottom = value
      bottomToTop = -1
    }
  }

inline var View.bottom_toTopOf: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    layoutParams = layoutParams.append {
      bottomToTop = value
      bottomToBottom = -1
    }
  }

inline var View.start_toStartOf: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    layoutParams = layoutParams.append {
      startToStart = value
      startToEnd = -1
    }
  }

inline var View.start_toEndOf: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    layoutParams = layoutParams.append {
      startToEnd = value
      startToStart = -1
    }
  }

inline var View.end_toEndOf: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    layoutParams = layoutParams.append {
      endToEnd = value
      endToStart = -1
    }
  }

inline var View.end_toStartOf: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    layoutParams = layoutParams.append {
      endToStart = value
      endToEnd = -1
    }
  }

inline var View.baseline_toBaselineOf: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    layoutParams = layoutParams.append {
      baselineToBaseline = value
    }
  }

inline var View.center_of: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    start_toStartOf = value
    end_toEndOf = value
    top_toTopOf = value
    bottom_toBottomOf = value
  }

inline var View.center_horizontalOf: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    start_toStartOf = value
    end_toEndOf = value
  }

inline var View.center_verticalOf: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    top_toTopOf = value
    bottom_toBottomOf = value
  }

inline var View.horizontal_bias: Float
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    layoutParams = layoutParams.append {
      horizontalBias = value
    }
  }

inline var View.vertical_bias: Float
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    layoutParams = layoutParams.append {
      verticalBias = value
    }
  }

inline var View.horizontal_chainStyle: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    layoutParams = layoutParams.append {
      horizontalChainStyle = value
    }
  }

inline var View.vertical_chainStyle: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    layoutParams = layoutParams.append {
      verticalChainStyle = value
    }
  }

inline var Flow.flow_horizontalGap: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    setHorizontalGap(value)
  }

inline var Flow.flow_verticalGap: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    setVerticalGap(value)
  }

inline var Flow.flow_wrapMode: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    setWrapMode(value)
  }
//endregion

//region layout params helper
fun ViewGroup.MarginLayoutParams.toConstraintLayoutParam() =
  ConstraintLayout.LayoutParams(width, height).also {
    it.topMargin = this.topMargin
    it.bottomMargin = this.bottomMargin
    it.leftMargin = this.leftMargin
    it.rightMargin = this.rightMargin
  }

fun ViewGroup.LayoutParams.append(set: ConstraintLayout.LayoutParams.() -> Unit) =
  (this as? ConstraintLayout.LayoutParams)?.apply(set) ?: (this as? ViewGroup.MarginLayoutParams)?.toConstraintLayoutParam()?.apply(set)
//endregion
