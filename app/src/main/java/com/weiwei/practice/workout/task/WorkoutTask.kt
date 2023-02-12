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

package com.weiwei.practice.workout.task

import android.util.Log
import com.weiwei.practice.workout.state.WorkoutPlayState
import com.weiwei.practice.workout.state.WorkoutType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

/**
 * @author weiwei
 * @date 2023.02.10
 */
data class WorkoutTask(
  val executor: WorkoutTaskExecutor,
  val type: WorkoutType,
  val name: String,
  val time: Long
) {
  var prevTask: WorkoutTask? = null
  var nextTask: WorkoutTask? = null

  private var remainingTime: Long = time

  private var countdownTime: Long = time

  private var timeJob: Job? = null

  private var canceled = false

  fun start() {
    canceled = false

    val timeFlow = flow {
      for (i in time downTo 0) {
        emit(i)
        delay(1000)
      }
    }
    timeJob = timeFlow.flowOn(Dispatchers.Main)
      .onStart {
        Log.d("WorkoutTask", "start: name: $name,countdownTime: $countdownTime, remainingTime: $remainingTime")
      }
      .onCompletion {
        Log.d("WorkoutTask", "end: countdownTime: $countdownTime, remainingTime: $remainingTime")
        if (!canceled) {
          nextTask?.start()
          executor.switchToTask(nextTask)
        }
      }
      .onEach {
        countdownTime = it
        Log.d("WorkoutTask", "each: countdownTime: $countdownTime, remainingTime: $remainingTime")
        val state = when (countdownTime) {
          0L -> WorkoutPlayState.End
          time -> WorkoutPlayState.Start
          else -> WorkoutPlayState.Running
        }
        executor.container.updateState {
          copy(
            state = state,
            type = WorkoutType.Preview,
            name = this@WorkoutTask.name,
            tick = this@WorkoutTask.countdownTime,
          )
        }
      }
      .launchIn(executor.scope)
  }

  fun stop() {
    canceled = true
    timeJob?.cancel()
  }

}