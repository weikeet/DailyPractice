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

package com.weiwei.main.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weiwei.main.ui.data.UserAssets
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * @author weiwei
 * @date 2023.04.26
 */
class MainViewModel : ViewModel() {

  // userAssetsState: UserAssets(isVip=false, date=)
  //
  // emit: UserAssets(isVip=true, date=2021-04-26)
  // userAssetsEvent: UserAssets(isVip=true, date=2021-04-26)
  // userAssetsState: UserAssets(isVip=true, date=2021-04-26)
  //
  // emit: UserAssets(isVip=true, date=2021-04-26)
  // userAssetsEvent: UserAssets(isVip=true, date=2021-04-26)
  //
  // emit: UserAssets(isVip=true, date=2021-04-26)
  // userAssetsEvent: UserAssets(isVip=true, date=2021-04-26)
  //
  // emit: UserAssets(isVip=true, date=2021-04-26)
  // userAssetsEvent: UserAssets(isVip=true, date=2021-04-26)
  // 结论：
  // MutableSharedFlow 不区分数据是否相同，每次都会发送，观察者只会收到订阅之后发送的最新数据
  // MutableStateFlow 只有数据不同才会发送，具有粘性特性

  val userAssetsEvent: MutableSharedFlow<UserAssets> = MutableSharedFlow()
  val userAssetsState: MutableStateFlow<UserAssets> = MutableStateFlow(UserAssets())

  init {
    viewModelScope.launch {
      var index = 0
      while (++index < 5) {
        delay(2000)
        val assets = UserAssets(isVip = true, date = "2021-04-26")
        Timber.tag("MainFlowEmit").d("emit: $assets")
        userAssetsEvent.emit(assets)
        userAssetsState.value = assets
      }
    }
  }

}