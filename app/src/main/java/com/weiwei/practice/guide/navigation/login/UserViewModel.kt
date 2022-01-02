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

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author weiwei
 * @date 2021.12.31
 */
class UserViewModel : ViewModel() {
  fun login(name: String, password: String): MutableLiveData<User> {
    val userData = MutableLiveData<User>()
    val userInfo = User().apply {
      success = true
      username = "weiwei"
    }
    userData.value = userInfo
    user.value = userInfo

    return user
  }

  val user: MutableLiveData<User> = MutableLiveData()
}