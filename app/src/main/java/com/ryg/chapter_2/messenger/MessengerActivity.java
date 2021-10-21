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

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
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
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author weicools
 * @date 2021.10.21
 */
public class MessengerActivity extends AppCompatActivity {
  private static final String TAG = "Messenger_Sample";

  private ServiceConnection serviceConnection;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Messenger replyMessenger = new Messenger(new Handler(Looper.getMainLooper()) {
      @Override public void handleMessage(@NonNull Message msg) {
        switch (msg.what) {
          case MessengerService.MSG_SERVICE_CLIENT:
            Log.d(TAG, "MessengerActivity handle msg from service: "
                + msg.getData().getString(MessengerService.EXTRA_REPLY));
            break;

          default:
            super.handleMessage(msg);
        }
      }
    });

    Intent serviceIntent = new Intent(this, MessengerService.class);
    serviceConnection = new ServiceConnection() {
      @Override
      public void onServiceConnected(ComponentName name, IBinder service) {
        Log.d(TAG, "MessengerActivity onServiceConnected: ");

        Bundle data = new Bundle();
        data.putString(MessengerService.EXTRA_SEND, "hello, this is client");

        Message message = Message.obtain(null, MessengerService.MSG_CLIENT_SERVICE);
        message.replyTo = replyMessenger;
        message.setData(data);

        try {
          Log.d(TAG, "MessengerActivity onServiceConnected: send message");

          Messenger messenger = new Messenger(service);
          messenger.send(message);
        } catch (RemoteException e) {
          e.printStackTrace();
        }
      }

      @Override
      public void onServiceDisconnected(ComponentName name) {
        Log.d(TAG, "MessengerActivity onServiceDisconnected: ");
      }
    };
    bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);

    Log.d(TAG, "MessengerActivity onCreate: bindService");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    unbindService(serviceConnection);
    Log.d(TAG, "MessengerActivity onDestroy: unbindService");
  }
}
