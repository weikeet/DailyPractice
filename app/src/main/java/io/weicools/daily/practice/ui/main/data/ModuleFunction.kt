package io.weicools.daily.practice.ui.main.data

import android.content.Context

/**
 * @author Weicools
 *
 * @date 2021.07.10
 */
data class ModuleFunction(
  val iconResource: Int, val title: String, val description: String, val clickAction: (Context) -> Unit
)