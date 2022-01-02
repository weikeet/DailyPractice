/*
 * Copyright (c) 2020 Weicools
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

package com.weiwei.practice.guide.navigation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.weiwei.practice.R
import com.weiwei.practice.binding.viewBinding
import com.weiwei.practice.databinding.NavigationLoginProfilerBinding

/**
 * @author weiwei
 * @date 2021.12.31
 */
class ProfilerFragment : Fragment() {
  companion object {
    fun newInstance(): ProfilerFragment {
      val args = Bundle()

      val fragment = ProfilerFragment()
      fragment.arguments = args
      return fragment
    }
  }

  private val userViewModel: UserViewModel by activityViewModels()
  private val binding: NavigationLoginProfilerBinding by viewBinding(NavigationLoginProfilerBinding::bind)

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.navigation_login_profiler, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val navController = findNavController()

    userViewModel.user.observe(viewLifecycleOwner, { user ->
      if (user != null) {
        showWelcomeMessage(user)
      } else {
        navController.navigate(R.id.loginFragment)
      }
    })

    val currentBackStackEntry = navController.currentBackStackEntry!!
    val savedStateHandle = currentBackStackEntry.savedStateHandle
    savedStateHandle.getLiveData<Boolean>(LoginFragment.LOGIN_SUCCESSFUL)
      .observe(currentBackStackEntry, { success ->
        if (!success) {
          val startDestination = navController.graph.startDestinationId
          val navOptions = NavOptions.Builder()
            .setPopUpTo(startDestination, true)
            .build()
          navController.navigate(startDestination, null, navOptions)
        }
      })
  }

  private fun showWelcomeMessage(user: User) {
    binding.msgView.append(user.toString())
  }
}