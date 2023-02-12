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

import com.weiwei.practice.mvi.core.UiEvent
import com.weiwei.practice.mvi.core.container.MutableFlowContainer
import com.weiwei.practice.workout.state.WorkoutType
import com.weiwei.practice.workout.state.WorkoutUiState
import kotlinx.coroutines.CoroutineScope

/**
 * @author weiwei
 * @date 2023.02.10
 */
class WorkoutTaskExecutor(val scope: CoroutineScope, val container: MutableFlowContainer<WorkoutUiState, UiEvent>) {

  private var currWorkoutTask: WorkoutTask? = null

  fun initailize() {

    val workoutTaskList: ArrayList<WorkoutTask> = ArrayList()
    workoutTaskList.add(WorkoutTask(this, WorkoutType.Preview, "Preview1", 5))
    workoutTaskList.add(WorkoutTask(this, WorkoutType.Preview, "Preview2", 8))
    workoutTaskList.add(WorkoutTask(this, WorkoutType.Preview, "Preview3", 4))
    workoutTaskList.add(WorkoutTask(this, WorkoutType.Preview, "Preview4", 6))
    workoutTaskList.add(WorkoutTask(this, WorkoutType.Preview, "Preview5", 5))

    for (i in 0 until workoutTaskList.size - 1) {
      workoutTaskList[i].nextTask = workoutTaskList[i + 1]
      workoutTaskList[i + 1].prevTask = workoutTaskList[i]
    }

    switchToTask(workoutTaskList[0])
  }

  fun switchToTask(task: WorkoutTask?) {
    currWorkoutTask = task
  }

  fun next() {
    if (currWorkoutTask?.nextTask == null) {
      return
    }
    currWorkoutTask?.stop()
    currWorkoutTask?.nextTask?.start()
    switchToTask(currWorkoutTask?.nextTask)
  }

  fun prev() {
    if (currWorkoutTask?.prevTask == null) {
      return
    }
    currWorkoutTask?.stop()
    currWorkoutTask?.prevTask?.start()
    switchToTask(currWorkoutTask?.prevTask)
  }

  fun start() {
    currWorkoutTask?.start()
  }

  fun stop() {
    currWorkoutTask?.stop()
  }

}