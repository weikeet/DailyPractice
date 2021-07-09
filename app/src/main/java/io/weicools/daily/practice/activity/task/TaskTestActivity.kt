package io.weicools.daily.practice.activity.task

import android.content.ComponentName
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.weicools.daily.practice.ui.main.MainActivity
import io.weicools.daily.practice.R
import io.weicools.daily.practice.material.ActivityTaskUtils
import kotlinx.android.synthetic.main.activity_task_test.*

/**
 * @author weicools
 * @date 2020.03.03
 */
class TaskTestActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_task_test)

    btnTestTaskActivity.setOnClickListener {
      val testClassName = ComponentName(packageName, TaskTestActivity::class.java.name).className
      val isRunningTasksTop = ActivityTaskUtils.isRunningTasksTop(applicationContext, testClassName)
      val isAppTasksTop = ActivityTaskUtils.isAppTasksTop(applicationContext, testClassName)
      Toast.makeText(
        this, "通过isRunningTasksTop检测：TaskTestActivity ${if (isRunningTasksTop) "在Top" else "不在Top"}" +
            "\n通过isAppTasksTop检测：TaskTestActivity ${if (isAppTasksTop) "在Top" else "不在Top"}", Toast.LENGTH_SHORT
      ).show()
    }

    btnTestMainActivity.setOnClickListener {
      val testClassName = ComponentName(packageName, MainActivity::class.java.name).className
      val isRunningTasksTop = ActivityTaskUtils.isRunningTasksTop(applicationContext, testClassName)
      val isAppTasksTop = ActivityTaskUtils.isAppTasksTop(applicationContext, testClassName)
      Toast.makeText(
        this, "通过isRunningTasksTop检测：MainActivity ${if (isRunningTasksTop) "在Top" else "不在Top"}" +
            "\n通过isAppTasksTop检测：MainActivity ${if (isAppTasksTop) "在Top" else "不在Top"}", Toast.LENGTH_SHORT
      ).show()
    }
  }
}