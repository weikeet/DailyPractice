package io.weicools.daily.practice

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import io.weicools.daily.practice.activity.task.TaskTestActivity
import io.weicools.daily.practice.dialog.FetchWidthDialog
import io.weicools.daily.practice.formatter.FormatterDialog
import io.weicools.daily.practice.lifecycle.sample.SampleLifeActivity
import io.weicools.daily.practice.lifecycle.view.LifecycleViewActivity
import io.weicools.daily.practice.reflect.ReflectSample
import io.weicools.daily.practice.room.ui.RoomActivity
import io.weicools.daily.practice.utils.DisplayUtils
import io.weicools.daily.practice.widget.LinearGradientActivity

/**
 * @author weicools
 */
class Card1Fragment : Fragment() {
  private lateinit var activity: AppCompatActivity

  override fun onAttach(context: Context) {
    super.onAttach(context)
    activity = context as AppCompatActivity
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_card1, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val btnTestRoom: Button = view.findViewById(R.id.btnTestRoom)
    btnTestRoom.setOnClickListener {
      activity.startActivity(Intent(activity, RoomActivity::class.java))
    }

    val btnTestActivityTask: Button = view.findViewById(R.id.btnTestActivityTask)
    btnTestActivityTask.setOnClickListener {
      activity.startActivity(Intent(activity, TaskTestActivity::class.java))
    }

    val btnTestTabLayout: Button = view.findViewById(R.id.btnTestTabLayout)
    btnTestTabLayout.setOnClickListener {
    }

    val btnTestViewLifecycle: Button = view.findViewById(R.id.btnTestViewLifecycle)
    btnTestViewLifecycle.setOnClickListener {
      activity.startActivity(Intent(activity, LifecycleViewActivity::class.java))
    }

    val btnTestGradientAnim: Button = view.findViewById(R.id.btnTestGradientAnim)
    btnTestGradientAnim.setOnClickListener {
      activity.startActivity(Intent(activity, LinearGradientActivity::class.java))
    }

    val btnTestDialogWidth: Button = view.findViewById(R.id.btnTestDialogWidth)
    btnTestDialogWidth.setOnClickListener {
      val st = arrayOf("原生", "0.822f", "0.850f", "0.910f", "0.920f", "0.950f", "0.960f")
      AlertDialog.Builder(activity)
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
          FetchWidthDialog(activity).apply { windowWidth = DisplayUtils.getDisplayWidth() * ratio }.show()
          dialog?.dismiss()
        }
        .show()
    }

    val btnTestReflect: Button = view.findViewById(R.id.btnTestReflect)
    btnTestReflect.setOnClickListener {
      Log.d("ReflectSample", "btnTestReflect: ")
      ReflectSample.reflectIntent()
    }

    val btnTestSDF: Button = view.findViewById(R.id.btnTestSDF)
    btnTestSDF.setOnClickListener {
      FormatterDialog(activity).show()
    }

    val btnCleanAnim: Button = view.findViewById(R.id.btnCleanAnim)
    btnCleanAnim.setOnClickListener {
      PracticeActivity.start(activity, 0)
    }

    val btnFragment: Button = view.findViewById(R.id.btnFragment)
    btnFragment.setOnClickListener {
      activity.startActivity(Intent(activity, SampleLifeActivity::class.java))
    }
  }
}