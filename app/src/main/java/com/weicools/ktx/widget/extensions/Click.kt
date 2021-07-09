@file:Suppress("unused")

package com.weicools.ktx.widget.extensions

import android.view.View

/**
 * @author weicools
 * @date 2020.05.13
 */

//region View extend field: Click
/**
 * Registers the [block] lambda as [View.OnClickListener] to this View.
 *
 * If this View is not clickable, it becomes clickable.
 */
@Suppress("NOTHING_TO_INLINE")
inline fun View.onClick(block: View.OnClickListener) = setOnClickListener(block)

/**
 * Register the [block] lambda as [View.OnLongClickListener] to this View.
 * By default, [consume] is set to true because it's the most common use case, but you can set it to false.
 * If you want to return a value dynamically, use [View.setOnLongClickListener] instead.
 *
 * If this view is not long clickable, it becomes long clickable.
 */
inline fun View.onLongClick(consume: Boolean = true, crossinline block: () -> Unit) {
  setOnLongClickListener { block(); consume }
}
//endregion
