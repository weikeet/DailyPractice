package com.weiwei.practice.activity.task;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import java.util.List;

/**
 * @author weicools
 * @date 2020.03.03
 */
public class ActivityTaskUtils {

  public static boolean isRunningTasksTop(Context context, String className) {
    ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    if (activityManager == null) {
      Log.d("ActivityTaskUtils", "isRunningTasksTop: activityManager == null");
      return false;
    }
    try {
      List<ActivityManager.RunningTaskInfo> taskInfoList = activityManager.getRunningTasks(1);
      if (taskInfoList == null) {
        Log.d("ActivityTaskUtils", "isRunningTasksTop: taskInfoList == null");
        return false;
      }
      for (ActivityManager.RunningTaskInfo taskInfo : taskInfoList) {
        if (taskInfo.topActivity == null) {
          Log.d("ActivityTaskUtils", "isRunningTasksTop: taskInfo.topActivity == null");
          continue;
        }
        Log.d("ActivityTaskUtils", "isRunningTasksTop: ClassName=" + taskInfo.topActivity.getClassName()
            + ", ShortClassName=" + taskInfo.topActivity.getShortClassName());
        if (taskInfo.topActivity.getClassName().contains(className)) {
          Log.d("ActivityTaskUtils", "isRunningTasksTop: taskInfo.topActivity is " + className);
          return true;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      Log.d("ActivityTaskUtils", "isRunningTasksTop: Exception=" + e.getMessage());
    }
    Log.d("ActivityTaskUtils", "isRunningTasksTop: taskInfo.topActivity isn't " + className);
    return false;
  }

  public static boolean isAppTasksTop(Context context, String className) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
      Log.d("ActivityTaskUtils", "isAppTasksTop: DevicesVersion < Android M");
      return false;
    }
    ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    if (activityManager == null) {
      Log.d("ActivityTaskUtils", "isAppTasksTop: activityManager == null");
      return false;
    }

    try {
      List<ActivityManager.AppTask> taskList = activityManager.getAppTasks();
      if (taskList == null) {
        Log.d("ActivityTaskUtils", "isAppTasksTop: taskList == null");
        return false;
      }

      for (int i = 0; i < taskList.size(); i++) {
        ActivityManager.AppTask task = taskList.get(i);
        if (task == null || task.getTaskInfo() == null || task.getTaskInfo().topActivity == null) {
          Log.d("ActivityTaskUtils", "isAppTasksTop: task == null || info == null || topActivity == null");
          continue;
        }
        String claName = task.getTaskInfo().topActivity.getClassName();
        Log.d("ActivityTaskUtils", "isAppTasksTop: ClassName=" + claName
            + ", ShortClassName=" + task.getTaskInfo().topActivity.getShortClassName());
        if (TextUtils.equals(claName, className)) {
          Log.d("ActivityTaskUtils", "isAppTasksTop: task.getTaskInfo().topActivity is " + className);
          return true;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      Log.d("ActivityTaskUtils", "isAppTasksTop: Exception=" + e.getMessage());
    }
    Log.d("ActivityTaskUtils", "isAppTasksTop: task.getTaskInfo().topActivity isn't " + className);
    return false;
  }
}
