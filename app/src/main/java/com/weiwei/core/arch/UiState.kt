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

package com.weiwei.core.arch

import androidx.annotation.Keep
import kotlin.reflect.KProperty1

/**
 * @author weiwei
 * @date 2023.02.11
 */
interface UiState

/**
 * 如果使用 [observeState]/[StateCollector.collectPartial] 等方法依赖于 [KProperty1] 实现的状态监听，需要 keep UiState class。
 */
@Keep
interface UiStateProp