package com.weicools.ktx.widget.params

import androidx.constraintlayout.widget.ConstraintLayout
import com.weicools.ktx.widget.NO_GETTER
import com.weicools.ktx.widget.noGetter

/**
 * @author weicools
 * @date 2020.05.13
 */

const val parentId = ConstraintLayout.LayoutParams.PARENT_ID

const val chainSpread = ConstraintLayout.LayoutParams.CHAIN_SPREAD
const val chainPacked = ConstraintLayout.LayoutParams.CHAIN_PACKED
const val chainSpreadInside = ConstraintLayout.LayoutParams.CHAIN_SPREAD_INSIDE
const val constraintHorizontal = ConstraintLayout.LayoutParams.HORIZONTAL
const val constraintVertical = ConstraintLayout.LayoutParams.VERTICAL

inline fun constraintParams(init: ConstraintLayout.LayoutParams.() -> Unit) = ConstraintLayout.LayoutParams(wrapContent, wrapContent).apply(init)
inline fun constraintParams(width: Int, height: Int, init: ConstraintLayout.LayoutParams.() -> Unit) = ConstraintLayout.LayoutParams(width, height).apply(init)

//region ConstraintLayout.LayoutParams extend field
inline var ConstraintLayout.LayoutParams.center_Of: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    leftToLeft = value
    topToTop = value
    rightToRight = value
    bottomToBottom = value
  }

inline var ConstraintLayout.LayoutParams.center_HorizontalOf: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    leftToLeft = value
    rightToRight = value
  }

inline var ConstraintLayout.LayoutParams.center_VerticalOf: Int
  @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter
  set(value) {
    topToTop = value
    bottomToBottom = value
  }
//endregion
