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

@file:Suppress("OPT_IN_USAGE")

package com.weiwei.kotlinx.flow

import com.weiwei.kotlinx.flow.data.WeatherInfo
import com.weiwei.kotlinx.flow.request.UserInfoRequest
import com.weiwei.kotlinx.flow.request.WeatherRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.flow.sample
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking

// Kotlin Flow响应式编程，操作符函数进阶: https://mp.weixin.qq.com/s/fuUB-iYPjWaflCyPt5AQgQ

fun main() {
  runBlocking {
    executeMapOperation()

    executeFilterOperation()

    executeOnEachOperation()

    executeDebounceOperation()

    // executeSampleOperation()
    //
    // executeReduceOperation()
    //
    // executeFoldOperation()

    executeFlatMapConcatOperation()

    executeFlatMapMergeOperation()

    executeFlatMapLatestOperation()

    executeZipOperation()

    executeBufferOperation()

    executeConflateOperation()
  }
}

private fun printStart(operation: String) {
  val builder = StringBuilder("##########-Flow-$operation-start-")
  if (builder.length < 36) {
    for (i in 1..(36 - builder.length)) {
      builder.append("#")
    }
  }
  println(builder)
}

private fun printComplete(operation: String) {
  val builder = StringBuilder("##########-Flow-$operation-complete-")
  if (builder.length < 36) {
    for (i in 1..(36 - builder.length)) {
      builder.append("#")
    }
  }
  println(builder)
}

/*
map collect=1
map collect=4
map collect=9
map collect=16
map collect=25
 */
private suspend fun executeMapOperation() {
  flowOf(1, 2, 3, 4, 5)
    .map {
      it * it
    }
    .onStart {
      printStart("map")
    }
    .onCompletion {
      printComplete("map")
    }
    .collect {
      println("map collect=$it")
    }
}

/*
filter collect=2
filter collect=4
 */
private suspend fun executeFilterOperation() {
  flowOf(1, 2, 3, 4, 5)
    .filter {
      it % 2 == 0
    }
    .onStart {
      printStart("filter")
    }
    .onCompletion {
      printComplete("filter")
    }
    .collect {
      println("filter collect=$it")
    }
}

/*
onEach=1
onEach collect=1
onEach=2
onEach collect=2
onEach=3
onEach collect=3
onEach=4
onEach collect=4
onEach=5
onEach collect=5
 */
/**
 * 常用语查看 flow 中间状态的数据
 */
private suspend fun executeOnEachOperation() {
  flowOf(1, 2, 3, 4, 5)
    .onEach {
      println("onEach=$it")
    }
    .onStart {
      printStart("onEach")
    }
    .onCompletion {
      printComplete("onEach")
    }
    .collect {
      println("onEach collect=$it")
    }
}

/*
debounce collect=2
debounce collect=5
 */
/**
 * 保证各项数据存在一定时间间隔，时间临近的数据会保留最后一条。
 * 常用于输入关键字搜索场景等
 */
private suspend fun executeDebounceOperation() {
  flow {
    emit(1)
    emit(2)
    delay(600L)
    emit(3)
    delay(100L)
    emit(4)
    delay(100L)
    emit(5)
  }.debounce(500L)
    .onStart {
      printStart("debounce")
    }
    .onCompletion {
      printComplete("debounce")
    }
    .collect {
      println("debounce collect=$it")
    }
}

/*
sample collect=发送弹幕 5
sample collect=发送弹幕 10
sample collect=发送弹幕 15
sample collect=发送弹幕 20
 */
/**
 * 数据源数据量很大时，且只需要展示少量数据比较适合
 */
private suspend fun executeSampleOperation() {
  flow {
    var i = 0
    while (i < 20) {
      i++
      emit("发送弹幕 $i")
      delay(200L)
    }
  }.sample(1000L)
    .flowOn(Dispatchers.IO)
    .onStart {
      printStart("sample")
    }
    .onCompletion {
      printComplete("sample")
    }
    .collect {
      println("sample collect=$it")
    }
}

/*
reduce result=5050
 */
private suspend fun executeReduceOperation() {
  val reduceResult = flow {
    for (i in 1..100) {
      emit(i)
    }
  }
    .onStart {
      printStart("reduce")
    }
    .onCompletion {
      printComplete("reduce")
    }
    .reduce { acc, value ->
      acc + value
    }
  println("reduce result=$reduceResult")
}

/*
fold result=5150
 */
/**
 * 与 reduce 区别只在于 fold 需要指定初始值
 */
private suspend fun executeFoldOperation() {
  val foldResult = flow {
    for (i in 1..100) {
      emit(i)
    }
  }
    .onStart {
      printStart("fold")
    }
    .onCompletion {
      printComplete("fold")
    }
    .fold(100) { acc, value ->
      acc + value
    }
  println("fold result=$foldResult")
}

/*
flatMapConcat collect=a1
flatMapConcat collect=b1
flatMapConcat collect=a2
flatMapConcat collect=b2
flatMapConcat collect=a3
flatMapConcat collect=b3
 */
/**
 * 有序执行
 * 使用场景：
 * 请求一个网络资源需要依赖于先请求另外一个网络资源
 */
private suspend fun executeFlatMapConcatOperation() {
  flowOf(1, 2, 3)
    .flatMapConcat {
      flowOf("a$it", "b$it")
    }
    .onStart {
      printStart("flatMapConcat")
    }
    .onCompletion {
      printComplete("flatMapConcat")
    }
    .collect {
      println("flatMapConcat collect=$it")
    }

  // 回调方式
  val userInfoRequest = UserInfoRequest()
  userInfoRequest.getToken { token ->
    println("callback token=$token")
    userInfoRequest.getUserInfo(token) { userInfo ->
      println("callback userInfo=$userInfo")
    }
  }

  // 流处理方式
  userInfoRequest.getTokenFlow()
    .flatMapConcat { token ->
      println("flow token=$token")
      userInfoRequest.getUserInfoFlow(token)
    }
    .flowOn(Dispatchers.IO)
    .collect { userInfo ->
      println("flow userInfo=$userInfo")
    }
}

/*
flatMapConcat 保证先后顺序
flatMapConcat-m collect=a300
flatMapConcat-m collect=b300
flatMapConcat-m collect=a200
flatMapConcat-m collect=b200
flatMapConcat-m collect=a100
flatMapConcat-m collect=b100

flatMapMerge 是并发执行且不保证顺序
flatMapMerge collect=a100
flatMapMerge collect=b100
flatMapMerge collect=a200
flatMapMerge collect=b200
flatMapMerge collect=a300
flatMapMerge collect=b300
 */
private suspend fun executeFlatMapMergeOperation() {
  flowOf(300, 200, 100)
    .flatMapConcat {
      flow {
        delay(it.toLong())
        emit("a$it")
        emit("b$it")
      }
    }
    .onStart {
      printStart("flatMapConcat-m")
    }
    .onCompletion {
      printComplete("flatMapConcat-m")
    }
    .collect {
      println("flatMapConcat-m collect=$it")
    }

  flowOf(300, 200, 100)
    .flatMapMerge {
      flow {
        delay(it.toLong())
        emit("a$it")
        emit("b$it")
      }
    }
    .onStart {
      printStart("flatMapMerge")
    }
    .onCompletion {
      printComplete("flatMapMerge")
    }
    .collect {
      println("flatMapMerge collect=$it")
    }
}

/*
flatMapLatest collect=latest-1
flatMapLatest collect=latest-3
 */
private suspend fun executeFlatMapLatestOperation() {
  flow {
    emit(1)
    delay(150)
    emit(2)
    delay(50)
    emit(3)
  }
    .flatMapLatest {
      flow {
        delay(100)
        emit("latest-$it")
      }
    }
    .onStart {
      printStart("flatMapLatest")
    }
    .onCompletion {
      printComplete("flatMapLatest")
    }
    .collect {
      println("flatMapLatest collect=$it")
    }
}


/*
zip collect=a1
zip collect=b2
zip collect=c3

zip weather=WeatherInfo(realtime=RealtimeWeather(x), sevenDays=SevenDaysWeather(y))
 */
/**
 * zip 连接了两个 flow, 并且在 zip 函数体中进行了拼接，两个 flow 是并行执行的
 * 注意：数据量不想同的时候，只要其中一个 flow 处理完成就会结束执行
 *
 * 使用场景：
 * 两个请求之间没有先后顺序，但是需要两个接口都返回数据后再展示
 */
private suspend fun executeZipOperation() {
  val flow1 = flowOf("a", "b", "c")
  val flow2 = flowOf(1, 2, 3, 4, 5)
  flow1.zip(flow2) { v1, v2 ->
    v1 + v2
  }
    .onStart {
      printStart("zip")
    }
    .onCompletion {
      printComplete("zip")
    }
    .collect {
      println("zip collect=$it")
    }

  val weatherRequest = WeatherRequest()
  weatherRequest.requestRealtimeWeatherFlow().zip(weatherRequest.requestSevenDaysWeatherFlow()) { realtimeWeather, sevenDaysWeather ->
    WeatherInfo(realtimeWeather, sevenDaysWeather)
  }.collect {
    println("zip weather=$it")
  }
}

/*
Buffer-no onEach=1, t=1670245713532
Buffer-no collect=1, t=1670245714537
Buffer-no onEach=2, t=1670245715542
Buffer-no collect=2, t=1670245716548
Buffer-no onEach=3, t=1670245717552
Buffer-no collect=3, t=1670245718557

Buffer onEach=1, t=1670245718562
Buffer onEach=2, t=1670245719567
Buffer collect=1, t=1670245719568
Buffer onEach=3, t=1670245720570
Buffer collect=2, t=1670245720571
Buffer collect=3, t=1670245721574
 */
/**
 * 默认情况下：collect 和 flow 函数运行在一个协程，此时 collect 必须处理完当前数据才可以发射新的数据
 * buffer 则可以实现 collect 和 flow 函数运行在不同协程上, 其实就是一种背压策略, 发射的数据不管是否处理都会缓存到 buffer 中
 */
private suspend fun executeBufferOperation() {
  flow {
    emit(1)
    delay(1000)
    emit(2)
    delay(1000)
    emit(3)
  }.onEach {
    println("Buffer-no onEach=$it, t=${System.currentTimeMillis()}")
  }.collect {
    delay(1000)
    println("Buffer-no collect=$it, t=${System.currentTimeMillis()}")
  }

  flow {
    emit(1)
    delay(1000)
    emit(2)
    delay(1000)
    emit(3)
  }.onEach {
    println("Buffer onEach=$it, t=${System.currentTimeMillis()}")
  }.buffer()
    .collect {
      delay(1000)
      println("Buffer collect=$it, t=${System.currentTimeMillis()}")
    }
}

/*
collectLatest start handle 1
collectLatest start handle 2
collectLatest start handle 3
collectLatest start handle 4
collectLatest start handle 5
collectLatest end handle 5
 */
/**
 * collectLatest 特性：当有新的数据到来了，前一个还没处理完，则会将剩余处理逻辑全部取消，所以除了最后一个 end handle 其他都不会得到处理
 *
 * conflate 则可以保证所有都可以得到执行
 */
private suspend fun executeConflateOperation() {
  flow {
    var count = 0
    while (count < 5) {
      count++
      emit(count)
      delay(1000)
    }
  }.collectLatest {
    println("collectLatest start handle $it")
    delay(2000)
    println("collectLatest end handle $it")
  }

  flow {
    var count = 0
    while (count < 5) {
      count++
      emit(count)
      delay(1000)
    }
  }.conflate()
    .collect {
      println("conflate start handle $it")
      delay(2000)
      println("conflate end handle $it")
    }
}
