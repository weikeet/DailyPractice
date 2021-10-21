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

package io.weicools.daily.practice.eventbus

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * @author weicools
 * @date 2021.10.11
 */
class EventBusActivity : AppCompatActivity() {

@Subscribe(threadMode = ThreadMode.MAIN)
fun onMessageEvent(event: SayHelloEvent) {
  Log.d("TAG", "onMessageEvent: ${event.message}")
}

override fun onStart() {
  super.onStart()
  EventBus.getDefault().register(this)
}

override fun onStop() {
  super.onStop()
  EventBus.getDefault().unregister(this)
}
}