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

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author weiwei
 * @date 2023.02.23
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
