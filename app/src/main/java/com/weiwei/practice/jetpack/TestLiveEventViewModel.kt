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

package com.weiwei.practice.jetpack

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.weiwei.core.arch.lifecycle.LiveEvent
import com.weiwei.core.arch.lifecycle.SingleLiveEvent
import com.weiwei.core.arch.sendEvent
import com.weiwei.core.lifecycle.MutableUnPeekLiveData

/**
 * @author weiwei
 * @date 2023.03.09
 *
 * SingleLiveEvent 支持粘性事件, 只有第一个观察者会收到 一次事件
 * LiveEvent 不支持粘性事件, 所有观察者都会收到 一次事件
 * LiveData 支持粘性事件, 所有观察者会收到 多次事件 (旋转屏幕，页面重建等)
 * MutableUnPeekLiveData 不支持粘性事件, 所有观察者都会收到 一次事件  (observeSticky 的行为和 LiveData 一致)
 */

/*
enter first
2023-03-09 21:26:12.019 TestEvent D  Main SingleLiveEvent: observe1 value: EventA from main
2023-03-09 21:26:12.020 TestEvent D  Main SingleLiveEvent: observe1 value: EventB from main

2023-03-09 21:26:12.020 TestEvent D  Main LiveEvent: observe1 value: EventA from main
2023-03-09 21:26:12.020 TestEvent D  Main LiveEvent: observe2 value: EventA from main
2023-03-09 21:26:12.020 TestEvent D  Main LiveEvent: observe1 value: EventB from main
2023-03-09 21:26:12.020 TestEvent D  Main LiveEvent: observe2 value: EventB from main

2023-03-09 21:26:12.020 TestEvent D  Main LiveData: observe1 value: EventA from main
2023-03-09 21:26:12.020 TestEvent D  Main LiveData: observe2 value: EventA from main
2023-03-09 21:26:12.020 TestEvent D  Main LiveData: observe1 value: EventB from main
2023-03-09 21:26:12.020 TestEvent D  Main LiveData: observe2 value: EventB from main

2023-03-09 21:26:12.020 TestEvent D  Main PeekLiveData: observe1 value: EventA from main
2023-03-09 21:26:12.020 TestEvent D  Main PeekLiveData: observe2 value: EventA from main
2023-03-09 21:26:12.020 TestEvent D  Main PeekLiveData: observe1 value: EventB from main
2023-03-09 21:26:12.021 TestEvent D  Main PeekLiveData: observe2 value: EventB from main


change orientation
2023-03-09 21:26:43.140 TestEvent D  Main LiveData: observe1 value: EventB from main
2023-03-09 21:26:43.140 TestEvent D  Main LiveData: observe2 value: EventB from main


enter second
2023-03-09 21:26:54.093 TestEvent D  Second ReceiveEvent: singleLiveEvent1: StickyEventA from main
2023-03-09 21:26:54.093 TestEvent D  Second ReceiveEvent: liveData1: StickyEventA from main
2023-03-09 21:26:54.094 TestEvent D  Second LiveData: observe1 value: EventB from main
2023-03-09 21:26:54.094 TestEvent D  Second LiveData: observe2 value: EventB from main


change orientation
2023-03-09 21:27:32.476 TestEvent D  Second ReceiveEvent: liveData1: StickyEventA from main
2023-03-09 21:27:32.476 TestEvent D  Second LiveData: observe1 value: EventB from main
2023-03-09 21:27:32.476 TestEvent D  Second LiveData: observe2 value: EventB from main


back to first
2023-03-09 21:27:48.004 TestEvent D  Main LiveData: observe1 value: EventB from main
2023-03-09 21:27:48.004 TestEvent D  Main LiveData: observe2 value: EventB from main
*/
class TestLiveEventViewModel : ViewModel() {

  // 由于 SingleLiveEvent 的特性，只有第一个 observer 会收到事件，pendingFlag 会被设置为 false，后续的 observer 不会收到事件，支持粘性事件
  private val singleLiveEvent: SingleLiveEvent<String> = SingleLiveEvent() // Only first observer will be notified
  private val liveEvent: LiveEvent<String> = LiveEvent() // Avoid receiving previous events
  private val liveData: MutableLiveData<String> = MutableLiveData()
  private val peekLiveData: MutableUnPeekLiveData<String> = MutableUnPeekLiveData()

  private val singleLiveEvent1: SingleLiveEvent<String> = SingleLiveEvent() // Only first observer will be notified
  private val liveEvent1: LiveEvent<String> = LiveEvent() // Avoid receiving previous events
  private val liveData1: MutableLiveData<String> = MutableLiveData()
  private val peekLiveData1: MutableUnPeekLiveData<String> = MutableUnPeekLiveData()

  fun sendEventOnMain() {
    singleLiveEvent1.setValue("StickyEventA from main")
    liveEvent1.sendEvent("StickyEventA from main")
    liveData1.setValue("StickyEventA from main")
    peekLiveData1.setValue("StickyEventA from main")

    singleLiveEvent.setValue("EventA from main")
    singleLiveEvent.setValue("EventB from main")

    liveEvent.sendEvent("EventA from main")
    liveEvent.sendEvent("EventB from main")

    liveData.value = "EventA from main"
    liveData.value = "EventB from main"

    peekLiveData.value = "EventA from main"
    peekLiveData.value = "EventB from main"
  }

  fun receiveEventOnSecond(viewLifecycleOwner: LifecycleOwner) {
    singleLiveEvent1.observe(viewLifecycleOwner) {
      Log.d("TestEvent", "receiveEventOnSecond: singleLiveEvent1: $it")
    }
    liveEvent1.observe(viewLifecycleOwner) {
      Log.d("TestEvent", "receiveEventOnSecond: liveEvent1: $it")
    }
    liveData1.observe(viewLifecycleOwner) {
      Log.d("TestEvent", "receiveEventOnSecond: liveData1: $it")
    }
    peekLiveData1.observe(viewLifecycleOwner) {
      Log.d("TestEvent", "receiveEventOnSecond: peekLiveData1: $it")
    }
    peekLiveData1.observeSticky(viewLifecycleOwner) {
      Log.d("TestEvent", "receiveEventOnSecond: peekLiveData1-sticky: $it")
    }
  }

  fun observeSingleLiveEventOnMain(viewLifecycleOwner: LifecycleOwner) {
    observeTest(singleLiveEvent, "Main", "SingleLiveEvent", viewLifecycleOwner)
  }

  fun observeLiveEventOnMain(viewLifecycleOwner: LifecycleOwner) {
    observeTest(liveEvent, "Main", "LiveEvent", viewLifecycleOwner)
  }

  fun observeLiveDataOnMain(viewLifecycleOwner: LifecycleOwner) {
    observeTest(liveData, "Main", "LiveData", viewLifecycleOwner)
  }

  fun observePeekLiveDataOnMain(viewLifecycleOwner: LifecycleOwner) {
    observeTest(peekLiveData, "Main", "PeekLiveData", viewLifecycleOwner)
  }

  fun observeSingleLiveEventOnSecond(viewLifecycleOwner: LifecycleOwner) {
    observeTest(singleLiveEvent, "Second", "SingleLiveEvent", viewLifecycleOwner)
  }

  fun observeLiveEventOnSecond(viewLifecycleOwner: LifecycleOwner) {
    observeTest(liveEvent, "Second", "LiveEvent", viewLifecycleOwner)
  }

  fun observeLiveDataOnSecond(viewLifecycleOwner: LifecycleOwner) {
    observeTest(liveData, "Second", "LiveData", viewLifecycleOwner)
  }

  fun observePeekLiveDataOnSecond(viewLifecycleOwner: LifecycleOwner) {
    observeTest(peekLiveData, "Second", "PeekLiveData", viewLifecycleOwner)
  }

  private fun observeTest(event: LiveData<String>, page: String, eventType: String, viewLifecycleOwner: LifecycleOwner) {
    event.observe(viewLifecycleOwner) {
      Log.d("TestEvent", "$page $eventType: observe1 value: $it")
    }

    event.observe(viewLifecycleOwner) {
      Log.d("TestEvent", "$page $eventType: observe2 value: $it")
    }
  }

}
