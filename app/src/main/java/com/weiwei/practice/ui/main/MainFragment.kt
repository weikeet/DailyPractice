package com.weiwei.practice.ui.main

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.drakeet.multitype.MultiTypeAdapter
import com.ryg.chapter_2.BookContent
import com.ryg.chapter_2.messenger.MessengerContent
import com.weicools.core.app.BaseFragment
import com.weicools.fluent.widget.extensions.dp
import com.weicools.ktx.colorOf
import com.weiwei.practice.R
import com.weiwei.practice.activity.task.ActivityTaskContent
import com.weiwei.practice.adb.AdbTestContent
import com.weiwei.practice.animator.AnimatedVectorContent
import com.weiwei.practice.asyncui.AsyncUiContent
import com.weiwei.practice.autostart.AutoStartContent
import com.weiwei.practice.binder.TransactionTooLargeContent
import com.weiwei.practice.binding.BindingContent
import com.weiwei.practice.cockroach.NoCrashContent
import com.weiwei.practice.dialog.DialogContent
import com.weiwei.practice.formatter.FormatterDialogContent
import com.weiwei.practice.jetpack.SingleLiveDataContent
import com.weiwei.practice.lifecycle.LifecycleContent
import com.weiwei.practice.memoryleak.MemoryLeakContent
import com.weiwei.practice.ndk.HelloWorldContent
import com.weiwei.practice.room.ui.RoomContent
import com.weiwei.practice.viewevent.ViewEventContent
import com.weiwei.practice.widget.LinearGradientContent

/**
 * @author Weicools
 *
 * @date 2021.07.10
 */
class MainFragment : BaseFragment() {
  companion object {
    fun newInstance() = MainFragment()
  }

  class RecyclerViewDivider(private val dividerHeight: Int) : ItemDecoration() {
    private val dividerPaint: Paint = Paint()

    init {
      dividerPaint.color = colorOf(R.color.material_on_surface_stroke)
      dividerPaint.style = Paint.Style.FILL
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
      super.getItemOffsets(outRect, view, parent, state)

      outRect.set(0, 0, 0, dividerHeight)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {

      val childCount = parent.childCount
      for (i in 0 until childCount - 1) {
        val view = parent.getChildAt(i)

        val left = view.left.toFloat()
        val right = view.right.toFloat()

        val params = view.layoutParams as MarginLayoutParams
        val top = (view.bottom + params.bottomMargin).toFloat()
        val bottom = (view.bottom + dividerHeight + params.bottomMargin).toFloat()

        c.drawRect(left, top, right, bottom, dividerPaint)
      }
    }
  }

  private val adapter = MultiTypeAdapter()
  private val items = ArrayList<Any>()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    adapter.register(ModuleFunctionDelegate())
    val recyclerView = RecyclerView(attachActivity).apply {
      layoutManager = LinearLayoutManager(attachActivity)
    }
    recyclerView.addItemDecoration(RecyclerViewDivider((0.8f.dp).toInt()))
    recyclerView.adapter = adapter
    return recyclerView
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

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
    items.add(HelloWorldContent())
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