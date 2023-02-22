package com.weiwei.practice.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.drakeet.multitype.MultiTypeAdapter
import com.weiwei.core.app.BaseFragment
import com.weiwei.core.app.mainHandler
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
import com.weiwei.practice.flow.FlowSampleContent
import com.weiwei.practice.formatter.FormatterDialogContent
import com.weiwei.practice.guide.navigation.basic.NavigationBasicContent
import com.weiwei.practice.jetpack.SingleLiveDataContent
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

  private val sharedViewModel: MainSharedViewModel by activityViewModels()

  private val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    return inflater.inflate(R.layout.fragment_main, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    Log.d("TestEvent", "main-onViewCreated: ")
    sharedViewModel.event.observe(viewLifecycleOwner) {
      Log.d("TestEvent", "main-onViewCreated: 111, $it")
    }
    sharedViewModel.event.observe(viewLifecycleOwner) {
      Log.d("TestEvent", "main-onViewCreated: 222, $it")
    }
    mainHandler.postDelayed({
      sharedViewModel.event.observe(viewLifecycleOwner) {
        Log.d("TestEvent", "main-onViewCreated: 333, $it")
      }
      sharedViewModel.event.setValue("test1")
      sharedViewModel.event.setValue("test2")
      sharedViewModel.event.observe(viewLifecycleOwner) {
        // 使用 LiveEvent 不会收到事件，必须在 setValue 之前监听才会收到
        // MutableLiveData 可以在 setValue 之后监听到事件
        Log.d("TestEvent", "main-onViewCreated: 444, $it")
      }
    }, 2000)

    binding.statusBarView.doOnApplyWindowInsets { windowInsets ->
      binding.statusBarView.updateLayoutParams {
        height = windowInsets.systemBarTop
      }
    }

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
    items.add(SingleLiveDataContent())
    items.add(LifecycleContent())
    items.add(MemoryLeakContent())
    items.add(CrashTestContent())
    items.add(RoomContent())
    items.add(LinearGradientContent())

    items.add(BookContent())
    items.add(MessengerContent())
  }
}