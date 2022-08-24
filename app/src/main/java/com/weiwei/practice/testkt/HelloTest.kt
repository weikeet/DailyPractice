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

package com.weiwei.practice.testkt

/**
 * @author weiwei
 * @date 2022.08.10
 */
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) = runBlocking<Unit> {
  val startTime = System.currentTimeMillis()
  val job = launch(Dispatchers.Default) {
    var nextPrintTime = startTime
    var i = 0
    while (i < 5 && isActive) {
      // print a message twice a second
      if (System.currentTimeMillis() >= nextPrintTime) {
        println("Hello ${i++}")
        nextPrintTime += 500L
      }
    }
    // the coroutine work is completed so we can cleanup
    println("Clean up!")

  }
  delay(1000L)
  println("Cancel!")
  job.cancel()
  println("Done!")
}