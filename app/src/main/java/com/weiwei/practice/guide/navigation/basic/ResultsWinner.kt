/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.weiwei.practice.guide.navigation.basic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.weiwei.practice.R

/**
 * Shows the winning screen.
 */
class ResultsWinner : Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
    val view = inflater.inflate(R.layout.fragment_results_winner, container, false)

    view.findViewById<Button>(R.id.play_btn2).setOnClickListener {
      Navigation.findNavController(view).navigate(R.id.action_resultsWinner_to_match)
    }

    view.findViewById<Button>(R.id.leaderboard_btn2).setOnClickListener {
      Navigation.findNavController(view).navigate(R.id.action_resultsWinner_to_leaderboard)
    }
    return view
  }
}
