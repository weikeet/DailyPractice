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

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

// 搜索框防抖: https://juejin.cn/post/6989782281191686180#heading-0

/**
 * @author weiwei
 * @date 2023.02.22
 *
 * App 搜索
 * 旧流程：在搜索框中输入内容，然后点击搜索按钮，经过一段等待，搜索结果以列表形式展现
 * 新流程：不需要手动点击搜索按钮，输入内容后，搜索是自动触发，为了实现这效果就得监听输入框内容的变化！！！
 */
class SearchTextWatcher(editText: EditText, private val searchBlock: (String) -> Unit) {

  init {
    // 设置输入框内容监听器
    val textWatcher = object : TextWatcher {
      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
      }

      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        search(s.toString())
      }

      override fun afterTextChanged(s: Editable?) {
      }
    }
    editText.addTextChangedListener(textWatcher)
  }

  private fun search(text: String): String {
    // 访问网络进行搜索
    Log.d("SearchTextWatcher", "search: result=$text-result")
    searchBlock("$text-result")
    return "$text-result"
  }

  // 存在很明显问题，比如搜索 kotlin 时，onTextChanged()会被回调 6 次，就触发了 6 次网络请求，而只有最后一次才是有效的。
  // 优化方法则是 在每次输入框内容变化后启动超时倒计时，若倒计时归零时输入框内容没有发生新变化，则用输入框当前内容发起请求，否则将倒计时重置，重新开始倒计时。

}

// Kotlin 预定义了一些限流方法，debounce()就非常契合当前场景
class SearchTextWatcherFlow(editText: EditText, scope: CoroutineScope, private val searchBlock: (String) -> Unit) {

  init {
    val textChangeFlow: Flow<CharSequence> = callbackFlow {
      // 构建输入框监听器
      val watcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        // 在文本变化后向流发射数据
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
          s?.let { trySend(it) }
        }

        override fun afterTextChanged(s: Editable?) {
        }
      }
      // 设置输入框监听器
      editText.addTextChangedListener(watcher)
      // 阻塞以保证流一直运行
      awaitClose { editText.removeTextChangedListener(watcher) }
    }

    textChangeFlow
      .filter { it.isNotEmpty() } // 过滤空内容，避免无效网络请求
      .debounce(1000) // 1000ms防抖
      .flatMapLatest { searchFlow(it.toString()) } // 让搜索在异步线程中执行
      .flowOn(Dispatchers.IO) // 获取搜索结果并更新界面
      .onEach { updateUi(it) }
      .launchIn(scope) // 在主线程收集搜索结果
  }

  private fun searchFlow(text: String): Flow<String> {
    // 访问网络进行搜索
    return flow {
      Log.d("SearchTextWatcher", "searchFlow: result=$text-result")
      emit("$text-result")
    }
  }

  private fun updateUi(result: String) {
    // 更新界面
    Log.d("SearchTextWatcher", "updateUi: result=$result")
    searchBlock(result)
  }

}