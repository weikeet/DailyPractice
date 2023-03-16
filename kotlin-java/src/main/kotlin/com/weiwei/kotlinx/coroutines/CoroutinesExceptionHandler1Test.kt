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

package com.weiwei.kotlinx.coroutines

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @author weiwei
 * @date 2023.03.13
 *
 * CoroutineExceptionHandler 使用方法1：声明 CoroutineScope 时设置 CoroutineExceptionHandler
 */
fun main() {

  runBlocking {
    val scope = CoroutineScope(Job() + CoroutineExceptionHandler { coroutineContext, throwable ->
      println("CoroutineExceptionHandler got ${throwable.message} in $coroutineContext")
    })
    scope.launch() {
      launch {
        delay(100)
        throw RuntimeException()
      }
    }
  }

  runBlocking {
    delay(2000)
  }
}