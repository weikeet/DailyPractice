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

package com.weiwei.flow.sample

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Kotlin协程之Flow使用 https://juejin.cn/post/7034381227025465375
// 【Kotlin Flow】 一眼看全——Flow操作符大全 https://juejin.cn/post/6989536876096913439

/**
 * @author weiwei
 * @date 2022.12.05
 */

fun main() {
  asyncTest()
}

private fun asyncTest() {
  val currentTime = System.currentTimeMillis()
  GlobalScope.launch() {
    println("start-->${System.currentTimeMillis() - currentTime}")
    // val withA = withContext(Dispatchers.Default) {
    //   delay(2000)
    //   "a"
    // }
    val awaitB = async {
      delay(2000)
      "b"
    }
    val awaitC = async {
      delay(1000)
      "c"
    }
    // println("withA: $withA start-->${System.currentTimeMillis() - currentTime}")
    println("awaitB:${awaitB.await()} start-->${System.currentTimeMillis() - currentTime}")
    println("awaitC:${awaitC.await()} start-->${System.currentTimeMillis() - currentTime}")

    // start-->30
    // awaitB:b start-->2043
    // awaitC:c start-->2043
  }

  Thread.sleep(4000)
}
