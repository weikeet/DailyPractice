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
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.findNavController
import com.weiwei.practice.R
import com.weiwei.practice.binding.viewBinding
import com.weiwei.practice.databinding.NavigationLoginLoginBinding

/**
 * @author weiwei
 * @date 2021.12.31
 */
class LoginFragment : Fragment() {
  companion object {
    const val LOGIN_SUCCESSFUL: String = "LOGIN_SUCCESSFUL"

    fun newInstance(): LoginFragment {
      val args = Bundle()

      val fragment = LoginFragment()
      fragment.arguments = args
      return fragment
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.navigation_login_login, container, false)
  }

  private val userViewModel: UserViewModel by activityViewModels()
  private val binding: NavigationLoginLoginBinding by viewBinding(NavigationLoginLoginBinding::bind)

  private lateinit var savedStateHandle: SavedStateHandle

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle
    savedStateHandle.set(LOGIN_SUCCESSFUL, false)

    binding.loginButton.setOnClickListener {
      val username = binding.usernameText.text.toString()
      val password = binding.passwordText.text.toString()
      login(username, password)
    }
  }

  private fun login(username: String, password: String) {
    userViewModel.login(username, password).observe(viewLifecycleOwner, { result ->
      if (result.success) {
        savedStateHandle.set(LOGIN_SUCCESSFUL, true)
        findNavController().popBackStack()
      } else {
        showErrorMessage()
      }
    })
  }

  private fun showErrorMessage() {
    Toast.makeText(requireContext(), "login error", Toast.LENGTH_SHORT).show()
  }
}