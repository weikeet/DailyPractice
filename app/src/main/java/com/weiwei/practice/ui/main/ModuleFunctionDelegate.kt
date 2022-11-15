package com.weiwei.practice.ui.main

import android.content.Context
import com.drakeet.multitype.ViewDelegate
import com.weiwei.fluentview.ui.unit.dp
import com.weiwei.fluentview.view.defaultParams
import com.weiwei.fluentview.view.matchParent
import com.weiwei.practice.ui.main.data.ModuleContent

/**
 * @author Weicools
 *
 * @date 2021.07.10
 */
class ModuleFunctionDelegate : ViewDelegate<ModuleContent, ModuleFunctionView>() {
  override fun onCreateView(context: Context): ModuleFunctionView {
    return ModuleFunctionView(context).apply { layoutParams = defaultParams(matchParent, 72.dp) }
  }

  override fun onBindView(view: ModuleFunctionView, item: ModuleContent) {
    view.bindFunction(item.function)
  }
}