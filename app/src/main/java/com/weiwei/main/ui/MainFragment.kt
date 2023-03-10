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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.drakeet.multitype.MultiTypeAdapter
import com.weiwei.core.app.BaseFragment
import com.weiwei.fluentview.ui.unit.dp
import com.weiwei.practice.R
import com.weiwei.practice.activitytask.ActivityTaskContent
import com.weiwei.practice.androidart.chapter_2.BookContent
import com.weiwei.practice.androidart.chapter_2.messenger.MessengerContent
import com.weiwei.practice.animator.AnimatedVectorContent
import com.weiwei.practice.asyncui.AsyncUiContent
import com.weiwei.practice.autostart.AutoStartContent
import com.weiwei.practice.binder.TransactionTooLargeContent
import com.weiwei.practice.binding.BindingContent
import com.weiwei.practice.binding.viewBinding
import com.weiwei.practice.crash.CrashTestContent
import com.weiwei.practice.databinding.FragmentMainBinding
import com.weiwei.practice.di.sample.ui.DiSampleContent
import com.weiwei.practice.dialog.DialogContent
import com.weiwei.practice.differ.ListDifferContent
import com.weiwei.practice.flow.FlowSampleContent
import com.weiwei.practice.formatter.FormatterDialogContent
import com.weiwei.practice.guide.navigation.basic.NavigationBasicContent
import com.weiwei.practice.jetpack.TestLiveDataContent
import com.weiwei.practice.jetpack.TestLiveEventContent
import com.weiwei.practice.keyboard.KeyboardContent
import com.weiwei.practice.lifecycle.LifecycleContent
import com.weiwei.practice.material.MaterialShapeContent
import com.weiwei.practice.memoryleak.MemoryLeakContent
import com.weiwei.practice.ndk.HelloWorldContent
import com.weiwei.practice.player.VideoPlayerContent
import com.weiwei.practice.room.ui.RoomContent
import com.weiwei.practice.service.ForegroundServiceContent
import com.weiwei.practice.shadow.ShadowContainerContent
import com.weiwei.practice.viewevent.ViewEventContent
import com.weiwei.practice.wave.WaveViewContent
import com.weiwei.practice.widget.LinearGradientContent
import com.weiwei.practice.widget.recycler.PayloadContent
import com.weiwei.practice.window.WindowInsetsContent
import com.weiwei.practice.window.doOnApplyWindowInsets
import com.weiwei.practice.window.systemBarBottom
import com.weiwei.practice.window.systemBarTop
import com.weiwei.practice.workout.WorkoutContent

/**
 * @author Weicools
 *
 * @date 2021.07.10
 */
class MainFragment : BaseFragment() {

  private val adapter = MultiTypeAdapter()
  private val items = ArrayList<Any>()

  private val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    return inflater.inflate(R.layout.fragment_main, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.statusBarView.doOnApplyWindowInsets { windowInsets ->
      binding.statusBarView.updateLayoutParams {
        height = windowInsets.systemBarTop
      }
    }

    binding.recyclerView.addItemDecoration(MainItemDecoration(adapter))
    binding.recyclerView.clipToPadding = false
    binding.recyclerView.doOnApplyWindowInsets { windowInsets, padding, _ ->
      binding.recyclerView.updatePadding(bottom = padding.bottom + windowInsets.systemBarBottom)
    }

    // view.doOnPreDraw {
    //   val insetsManager = WindowInsetsManager()
    //   insetsManager.setLightNavigationBars(requireActivity().window.decorView, true)
    // }

    adapter.register(ModuleFunctionDelegate())

    binding.recyclerView.layoutManager = LinearLayoutManager(attachActivity)
    binding.recyclerView.addItemDecoration(RecyclerViewDivider((0.8f.dp).toInt()))
    binding.recyclerView.adapter = adapter

    refreshItems()

    adapter.items = items
    adapter.notifyDataSetChanged()
  }

  private fun refreshItems() {
    items.clear()

    items.add(ListDifferContent(findNavController()))
    items.add(WorkoutContent(findNavController()))
    items.add(ActivityTaskContent())
    items.add(AnimatedVectorContent())
    items.add(AsyncUiContent())
    items.add(AutoStartContent())
    items.add(BindingContent())
    items.add(DiSampleContent())
    items.add(KeyboardContent())
    items.add(WindowInsetsContent())
    items.add(FlowSampleContent(findNavController()))
    items.add(MaterialShapeContent(findNavController()))
    items.add(VideoPlayerContent(findNavController()))
    items.add(WaveViewContent(findNavController()))
    items.add(PayloadContent(findNavController()))
    items.add(HelloWorldContent())
    items.add(ForegroundServiceContent())
    items.add(NavigationBasicContent())
    items.add(TransactionTooLargeContent())
    items.add(ViewEventContent())
    items.add(DialogContent())
    items.add(FormatterDialogContent())
    items.add(ShadowContainerContent())
    items.add(TestLiveDataContent())
    items.add(TestLiveEventContent(findNavController()))
    items.add(LifecycleContent())
    items.add(MemoryLeakContent())
    items.add(CrashTestContent())
    items.add(RoomContent())
    items.add(LinearGradientContent())

    items.add(BookContent())
    items.add(MessengerContent())
  }
}