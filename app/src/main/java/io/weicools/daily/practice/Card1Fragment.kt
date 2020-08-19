package io.weicools.daily.practice

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import io.weicools.daily.practice.activity.task.TaskTestActivity
import io.weicools.daily.practice.async.AsyncRequestActivity
import io.weicools.daily.practice.dialog.FetchWidthDialog
import io.weicools.daily.practice.room.ui.RoomActivity
import io.weicools.daily.practice.utils.DisplayUtils
import io.weicools.daily.practice.viewlifecycle.LifecycleXyViewActivity
import io.weicools.daily.practice.widget.LinearGradientActivity
import kotlinx.android.synthetic.main.fragment_card1.*
import me.weicools.widget.tablayout.TabLayoutActivity
import kotlin.time.ExperimentalTime

/**
 * @author weicools
 */
class Card1Fragment : Fragment() {

  @ExperimentalTime
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    // LayoutInflaterCompat.setFactory2(inflater, object : LayoutInflater.Factory2 {
    //   private var sum: Double = 0.0
    //   override fun onCreateView(parent: View?, name: String, context: Context, attrs: AttributeSet): View? {
    //     // 测量构建单个View耗时: 1s = 1000ms / 1ms = 1000us / 1us = 1000ns / 1ns = 1000ps
    //     val (view, duration) = measureTimedValue { (activity as AppCompatActivity).delegate.createView(parent, name, context, attrs) }
    //     sum += duration.inMilliseconds
    //     Log.d("TEST_TAG", "view=${view?.let { it::class.simpleName }} duration=${duration}  sum=${sum}")
    //     return view
    //   }
    //
    //   override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
    //     return null
    //   }
    // })
    return inflater.inflate(R.layout.fragment_card1, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    btnTestRoom.setOnClickListener {
      activity?.startActivity(Intent(activity, RoomActivity::class.java))
    }
    btnTestActivityTask.setOnClickListener {
      activity?.startActivity(Intent(activity, TaskTestActivity::class.java))
    }
    btnTestTabLayout.setOnClickListener {
      activity?.startActivity(Intent(activity, TabLayoutActivity::class.java))
    }
    btnTestViewLifecycle.setOnClickListener {
      activity?.startActivity(Intent(activity, LifecycleXyViewActivity::class.java))
    }
    btnTestAsyncRequest.setOnClickListener {
      activity?.startActivity(Intent(activity, AsyncRequestActivity::class.java))
    }
    btnTestGradientAnim.setOnClickListener {
      activity?.startActivity(Intent(activity, LinearGradientActivity::class.java))
    }
    btnTestDialogWidth.setOnClickListener {
      activity?.let {
        val st = arrayOf("原生", "0.822f", "0.850f", "0.910f","0.920f", "0.950f","0.960f")
        AlertDialog.Builder(it)
          .setSingleChoiceItems(st, 0) { dialog, which ->
            val ratio = when (which) {
              1 -> 0.822f
              2 -> 0.850f
              3 -> 0.910f
              4 -> 0.920f
              5 -> 0.950f
              6 -> 0.960f
              else -> 0f
            }
            FetchWidthDialog(it).apply { windowWidth = DisplayUtils.getDisplayWidth() * ratio }.show()
            dialog?.dismiss()
          }
          .show()
      }
    }
  }
}