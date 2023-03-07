package com.weiwei.main.ui.data

/**
 * @author Weicools
 *
 * @date 2021.08.08
 */
abstract class ModuleContent {
  val function by lazy(LazyThreadSafetyMode.NONE) {
    ModuleFunction().also { setupFunction(it) }
  }

  abstract fun setupFunction(function : ModuleFunction)
}