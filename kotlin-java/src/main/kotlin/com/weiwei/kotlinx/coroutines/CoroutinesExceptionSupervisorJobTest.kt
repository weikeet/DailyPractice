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
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @author weiwei
 * @date 2023.03.13
 */
fun main() {

  runBlocking {
    val scope = CoroutineScope(SupervisorJob() + CoroutineExceptionHandler { coroutineContext, throwable ->
      println("CoroutineExceptionHandler got ${throwable.message} in $coroutineContext")
    })
    scope.launch(CoroutineName("A")) {
      delay(100)
      throw RuntimeException()
    }
    scope.launch(CoroutineName("B")) {
      delay(200)
      println("正常执行,我不会收到影响")
    }
  }

  runBlocking {
    delay(2000)
  }
}