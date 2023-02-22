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

package com.weiwei.practice.flow.case

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

// https://juejin.cn/post/6989032238079803429#heading-2

/**
 * @author weiwei
 * @date 2023.02.22
 */
class Countdown {
}

fun <T> countdown(
  duration: Long,
  interval: Long,
  onCountdown: suspend (Long) -> T
): Flow<T> =
  flow { (duration - interval downTo 0 step interval).forEach { emit(it) } }
    .onEach { delay(interval) }
    .onStart { emit(duration) }
    .map { onCountdown(it) }
    .flowOn(Dispatchers.Default)


