/*
 * Copyright (c) 2020 Weicools
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

package com.weiwei.cockroach.compat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * ActivityManagerNative.getDefault().finishActivity(mToken, resultCode, resultData, finishTask))
 */
public class ActivityKillerV24V25 implements ActivityKiller {
  @Override
  public void finishLaunchActivity(Message message) {
    try {
      Object activityClientRecord = message.obj;

      Field tokenField = activityClientRecord.getClass().getDeclaredField("token");

      tokenField.setAccessible(true);
      IBinder binder = (IBinder) tokenField.get(activityClientRecord);
      finish(binder);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void finishResumeActivity(Message message) {
    finishSomeArgs(message);
  }

  @Override
  public void finishPauseActivity(Message message) {
    finishSomeArgs(message);
  }

  @Override
  public void finishStopActivity(Message message) {
    finishSomeArgs(message);
  }

  private void finishSomeArgs(Message message) {
    try {
      Object someArgs = message.obj;
      Field arg1Field = someArgs.getClass().getDeclaredField("arg1");
      arg1Field.setAccessible(true);
      IBinder binder = (IBinder) arg1Field.get(someArgs);
      finish(binder);
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
  }

  private void finish(IBinder binder) throws Exception {
    // ActivityManagerNative.getDefault().finishActivity(r.token, Activity.RESULT_CANCELED, null, Activity.DONT_FINISH_TASK_WITH_ACTIVITY);

    @SuppressLint("PrivateApi")
    Class<?> activityManagerNativeClass = Class.forName("android.app.ActivityManagerNative");

    @SuppressLint("DiscouragedPrivateApi")
    Method getDefaultMethod = activityManagerNativeClass.getDeclaredMethod("getDefault");

    Object activityManager = getDefaultMethod.invoke(null);

    Method finishActivityMethod = activityManager.getClass().getDeclaredMethod("finishActivity", IBinder.class, int.class, Intent.class, int.class);
    finishActivityMethod.invoke(activityManager, binder, Activity.RESULT_CANCELED, null, 0); // DONT_FINISH_TASK_WITH_ACTIVITY = 0
  }
}
