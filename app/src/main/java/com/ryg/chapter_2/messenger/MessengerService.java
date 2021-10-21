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

package com.ryg.chapter_2.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

/**
 * @author weicools
 * @date 2021.10.21
 */
public class MessengerService extends Service {
  private static final String TAG = "Messenger_Sample";

  public static final String EXTRA_SEND = "EXTRA_SEND";
  public static final String EXTRA_REPLY = "EXTRA_REPLY";

  public static final int MSG_CLIENT_SERVICE = 0;
  public static final int MSG_SERVICE_CLIENT = 1;

  private static final class MessengerHandler extends Handler {
    public MessengerHandler(@NonNull @NotNull Looper looper) {
      super(looper);
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
      switch (msg.what) {
        case MSG_CLIENT_SERVICE:
          Log.d(TAG, "MessengerService handle msg from client: "
              + msg.getData().getString(EXTRA_SEND));

          Bundle bundle = new Bundle();
          bundle.putString(EXTRA_REPLY, "ok, receive msg success");

          Message replyMessage = Message.obtain(null, MSG_SERVICE_CLIENT);
          replyMessage.setData(bundle);

          try {
            Log.d(TAG, "MessengerService reply msg: ");

            Messenger client = msg.replyTo;
            client.send(replyMessage);
          } catch (RemoteException e) {
            e.printStackTrace();
          }
          break;

        default:
          super.handleMessage(msg);
          break;
      }
    }
  }

  private final Messenger messenger = new Messenger(new MessengerHandler(Looper.getMainLooper()));

  @Override
  public void onCreate() {
    super.onCreate();
    Log.d(TAG, "MessengerService onCreate: ");
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    Log.d(TAG, "MessengerService onStartCommand: ");
    return super.onStartCommand(intent, flags, startId);
  }

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    Log.d(TAG, "MessengerService onBind: ");
    return messenger.getBinder();
  }

  @Override
  public boolean onUnbind(Intent intent) {
    Log.d(TAG, "MessengerService onUnbind: ");
    return super.onUnbind(intent);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    Log.d(TAG, "MessengerService onDestroy: ");
  }
}
