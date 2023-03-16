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

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @author weiwei
 * @date 2023.03.13
 *
 * 结构化并发
 */
fun main() {
  runBlocking {
    val scope = CoroutineScope(Job())
    val jobA = scope.launch(CoroutineName("A")) {
      println("jobA start")
      val jobChildA = launch(CoroutineName("child-A")) {
        println("jobChildA start")
        delay(1000)
        println("jobChildA end")
      }
      println("jobA end")
      // jobChildA.cancel()
    }
    // jobA.cancel() // --> first cancel jobChildA, then cancel jobA
    val jobB = scope.launch(CoroutineName("B")) {
      println("jobB start")
      delay(500)
      println("jobB end")
    }
    scope.cancel() // --> cancel all jobs (jobA, jobB, jobChildA)
  }

  runBlocking {
    delay(2000)
  }
}