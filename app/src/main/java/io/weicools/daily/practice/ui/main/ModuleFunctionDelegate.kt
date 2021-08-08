package io.weicools.daily.practice.ui.main

import android.content.Context
import com.drakeet.multitype.ViewDelegate
import com.weicools.ktx.widget.extensions.dp
import com.weicools.ktx.widget.params.defaultParams
import com.weicools.ktx.widget.params.matchParent
import io.weicools.daily.practice.ui.main.data.ModuleContent
import io.weicools.daily.practice.ui.main.data.ModuleFunction

/**
 * @author Weicools
 *
 * @date 2021.07.10
 */
class ModuleFunctionDelegate : ViewDelegate<ModuleContent, ModuleFunctionView>() {
  override fun onCreateView(context: Context): ModuleFunctionView {
    return ModuleFunctionView(context).apply { layoutParams = defaultParams(matchParent, 64.dp) }
  }

  override fun onBindView(view: ModuleFunctionView, item: ModuleContent) {
    view.bindFunction(item.function)
  }
}