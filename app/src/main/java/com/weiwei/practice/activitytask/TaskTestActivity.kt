package com.weiwei.practice.activitytask

import android.content.ComponentName
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.weiwei.fluentview.view.WindowInsetsEdgeDelegate
import com.weiwei.main.ui.MainActivity
import com.weiwei.practice.R

/**
 * @author weicools
 * @date 2020.03.03
 */
class TaskTestActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_task_test)

    WindowInsetsEdgeDelegate(this).start()

    val btnTestTaskActivity: Button = findViewById(R.id.btnTestTaskActivity)
    btnTestTaskActivity.setOnClickListener {
      val testClassName = ComponentName(packageName, TaskTestActivity::class.java.name).className
      val isRunningTasksTop = ActivityTaskUtils.isRunningTasksTop(applicationContext, testClassName)
      val isAppTasksTop = ActivityTaskUtils.isAppTasksTop(applicationContext, testClassName)
      Toast.makeText(
        this, "通过isRunningTasksTop检测：TaskTestActivity ${if (isRunningTasksTop) "在Top" else "不在Top"}" +
            "\n通过isAppTasksTop检测：TaskTestActivity ${if (isAppTasksTop) "在Top" else "不在Top"}", Toast.LENGTH_SHORT
      ).show()
    }

    val btnTestMainActivity: Button = findViewById(R.id.btnTestMainActivity)
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