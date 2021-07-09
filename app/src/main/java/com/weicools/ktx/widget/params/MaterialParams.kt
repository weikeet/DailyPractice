package com.weicools.ktx.widget.params

import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout

/**
 * @author weicools
 * @date 2020.05.13
 */

inline fun appBarParams(init: AppBarLayout.LayoutParams.() -> Unit) = AppBarLayout.LayoutParams(wrapContent, wrapContent).apply(init)
inline fun appBarParams(width: Int, height: Int, init: AppBarLayout.LayoutParams.() -> Unit) = AppBarLayout.LayoutParams(width, height).apply(init)

inline fun collapsingToolbarParams(init: CollapsingToolbarLayout.LayoutParams.() -> Unit) = CollapsingToolbarLayout.LayoutParams(wrapContent, wrapContent).apply(init)
inline fun collapsingToolbarParams(width: Int, height: Int, init: CollapsingToolbarLayout.LayoutParams.() -> Unit) = CollapsingToolbarLayout.LayoutParams(width, height).apply(init)
