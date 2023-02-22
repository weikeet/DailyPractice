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

import android.os.SystemClock
import android.view.View
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow

// 点击事件防抖 https://juejin.cn/post/6989782281191686180#heading-1

/**
 * @author weiwei
 * @date 2023.02.22
 *
 * 将每次有效点击的时间保存在变量中，每次点击时都判断当前时间和上次时间差，如果超过阈值则允许点击。
 */
class DebounceClickListener(private val clickListener: View.OnClickListener) : View.OnClickListener {

  private var triggerTime = 0L

  override fun onClick(v: View) {
    if (!isFastClick()) {
      clickListener.onClick(v)
    }
  }

  // 判断是否快速点击
  private fun isFastClick(): Boolean {
    val currentTime = SystemClock.elapsedRealtime()
    return if (currentTime - this.triggerTime >= 300) {
      this.triggerTime = currentTime
      false
    } else {
      true
    }
  }

}

// 用流的思想重新样理解这个场景：每个点击事件都是流上的新数据。
// 要对流做限流，即发射第一个数据，然后抛弃时间窗口中紧跟其后的所有数据，直到新的时间窗口到来。
// 自定义 Flow 实现
fun <T> Flow<T>.throttleFirst(thresholdMillis: Long): Flow<T> = flow {
  // 上次发射数据的时间
  var lastTime = 0L

  collect { upstream ->
    val currentTime = SystemClock.elapsedRealtime()
    if (currentTime - lastTime > thresholdMillis) {
      // 时间差超过阈值则发送数据并记录时间
      lastTime = currentTime
      emit(upstream)
    }
  }
}

// 将点击事件组织成流
fun View.clickFlow(): Flow<Unit> = callbackFlow {
  setOnClickListener { trySend(Unit) }
  awaitClose { setOnClickListener(null) }
}
