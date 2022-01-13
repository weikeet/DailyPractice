package com.weiwei.practice.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.drakeet.multitype.MultiTypeAdapter
import com.weiwei.core.app.BaseFragment
import com.weiwei.fluent.widget.extensions.dp
import com.weiwei.practice.R
import com.weiwei.practice.activity.task.ActivityTaskContent
import com.weiwei.practice.adb.AdbTestContent
import com.weiwei.practice.androidart.chapter_2.BookContent
import com.weiwei.practice.androidart.chapter_2.messenger.MessengerContent
import com.weiwei.practice.animator.AnimatedVectorContent
import com.weiwei.practice.asyncui.AsyncUiContent
import com.weiwei.practice.autostart.AutoStartContent
import com.weiwei.practice.binder.TransactionTooLargeContent
import com.weiwei.practice.binding.BindingContent
import com.weiwei.practice.binding.viewBinding
import com.weiwei.practice.cockroach.NoCrashContent
import com.weiwei.practice.databinding.FragmentMainBinding
import com.weiwei.practice.dialog.DialogContent
import com.weiwei.practice.formatter.FormatterDialogContent
import com.weiwei.practice.guide.navigation.basic.NavigationBasicContent
import com.weiwei.practice.jetpack.SingleLiveDataContent
import com.weiwei.practice.lifecycle.LifecycleContent
import com.weiwei.practice.memoryleak.MemoryLeakContent
import com.weiwei.practice.ndk.HelloWorldContent
import com.weiwei.practice.player.VideoPlayerContent
import com.weiwei.practice.room.ui.RoomContent
import com.weiwei.practice.viewevent.ViewEventContent
import com.weiwei.practice.wave.WaveViewContent
import com.weiwei.practice.widget.LinearGradientContent
import com.weiwei.practice.window.*

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

    binding.statusBarView.doOnApplyWindowInsets { windowInsets, padding, margin ->
      binding.statusBarView.updateLayoutParams {
        height = windowInsets.systemBarTop
      }
    }

    binding.recyclerView.clipToPadding = false
    binding.recyclerView.doOnApplyWindowInsets { windowInsets, padding, margin ->
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

    items.add(AdbTestContent())
    items.add(ActivityTaskContent())
    items.add(AnimatedVectorContent())
    items.add(AsyncUiContent())
    items.add(AutoStartContent())
    items.add(BindingContent())
    items.add(WindowInsetsContent())
    items.add(VideoPlayerContent(findNavController()))
    items.add(WaveViewContent(findNavController()))
    items.add(HelloWorldContent())
    items.add(NavigationBasicContent())
    items.add(TransactionTooLargeContent())
    items.add(ViewEventContent())
    items.add(DialogContent())
    items.add(FormatterDialogContent())
    items.add(SingleLiveDataContent())
    items.add(LifecycleContent())
    items.add(MemoryLeakContent())
    items.add(NoCrashContent())
    items.add(RoomContent())
    items.add(LinearGradientContent())

    items.add(BookContent())
    items.add(MessengerContent())
  }
}