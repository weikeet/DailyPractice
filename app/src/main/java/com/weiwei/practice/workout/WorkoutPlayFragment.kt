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

package com.weiwei.practice.workout

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.weiwei.core.arch.collectState
import com.weiwei.practice.R
import com.weiwei.practice.databinding.FragmentWorkoutPlayBinding

/**
 * @author weiwei
 * @date 2023.02.10
 */
class WorkoutPlayFragment : Fragment(R.layout.fragment_workout_play) {

  private val viewModel: WorkoutPlayViewModel by viewModels()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val binding = FragmentWorkoutPlayBinding.bind(view)

    viewModel.stateContainer.uiStateFlow.collectState(viewLifecycleOwner) {
      collect { state ->
        binding.tvName.text = state.name
        binding.tvTime.text = state.tick.toString()
      }
    }

    binding.startButton.setOnClickListener {
      viewModel.executor.start()
    }
    binding.stopButton.setOnClickListener {
      viewModel.executor.stop()
    }
    binding.btnLast.setOnClickListener {
      viewModel.executor.prev()
    }
    binding.btnNext.setOnClickListener {
      viewModel.executor.next()
    }

  }
}