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
import android.app.ActivityManager;
import android.app.servertransaction.ClientTransaction;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author weicools
 */
public class ActivityKillerV28 implements ActivityKiller {
  @Override
  public void finishLaunchActivity(Message message) {
    try {
      tryFinish1(message);
      return;
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }

    try {
      tryFinish2(message);
      return;
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }

    try {
      tryFinish3(message);
      return;
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
  }

  private void tryFinish1(Message message) throws Throwable {
    ClientTransaction clientTransaction = (ClientTransaction) message.obj;
    IBinder binder = clientTransaction.getActivityToken();
    finish(binder);
  }

  private void tryFinish3(Message message) throws Throwable {
    Object clientTransaction = message.obj;
    Field mActivityTokenField = clientTransaction.getClass().getDeclaredField("mActivityToken");
    IBinder binder = (IBinder) mActivityTokenField.get(clientTransaction);
    finish(binder);
  }

  private void tryFinish2(Message message) throws Throwable {
    Object clientTransaction = message.obj;
    Method getActivityTokenMethod = clientTransaction.getClass().getDeclaredMethod("getActivityToken");
    IBinder binder = (IBinder) getActivityTokenMethod.invoke(clientTransaction);
    finish(binder);
  }

  @Override
  public void finishResumeActivity(Message message) {
  }

  @Override
  public void finishPauseActivity(Message message) {
  }

  @Override
  public void finishStopActivity(Message message) {
  }

  private void finish(IBinder binder) throws Exception {
    @SuppressLint("DiscouragedPrivateApi")
    Method getServiceMethod = ActivityManager.class.getDeclaredMethod("getService");
    Object activityManager = getServiceMethod.invoke(null);

    Method finishActivityMethod = activityManager.getClass().getDeclaredMethod("finishActivity", IBinder.class, int.class, Intent.class, int.class);
    finishActivityMethod.setAccessible(true);
    finishActivityMethod.invoke(activityManager, binder, Activity.RESULT_CANCELED, null, 0); // DONT_FINISH_TASK_WITH_ACTIVITY = 0;
  }
}