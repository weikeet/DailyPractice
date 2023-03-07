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

package com.weiwei.main.ui

import android.content.Context
import com.drakeet.multitype.ViewDelegate
import com.weiwei.fluentview.ui.unit.dp
import com.weiwei.fluentview.view.defaultParams
import com.weiwei.fluentview.view.matchParent
import com.weiwei.main.ui.data.ModuleContent

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