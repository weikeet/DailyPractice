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

package com.ryg.chapter_2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

/**
 * @author weicools
 * @date 2021.10.21
 */
public class BookActivity extends AppCompatActivity {
  private static final String TAG = "AIDL_BookSample";

  private ServiceConnection serviceConnection;

  private IBookManager bookManager = null;

  private IOnNewBookArrivedListener arrivedListener;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    arrivedListener = new IOnNewBookArrivedListener.Stub() {
      @Override public void onNewBookArrived(Book newBook) throws RemoteException {
        Log.d(TAG, "BookActivity onNewBookArrived: " + newBook + ", thread=" + Thread.currentThread());
      }
    };

    serviceConnection = new ServiceConnection() {
      @Override
      public void onServiceConnected(ComponentName name, IBinder service) {
        Log.d(TAG, "BookActivity onServiceConnected: ");

        bookManager = IBookManager.Stub.asInterface(service);

        try {
          bookManager.registerNewBookArrivedListener(arrivedListener);

          Log.d(TAG, "BookActivity onServiceConnected: addBook: ");

          bookManager.addBook(new Book(1, "Android 开发艺术探索"));
          bookManager.addBook(new Book(2, "Kotlin 实战"));

          List<Book> bookList = bookManager.getBookList();
          for (Book book : bookList) {
            Log.d(TAG, "BookActivity onServiceConnected: getBookList: " + book);
          }
        } catch (RemoteException e) {
          e.printStackTrace();
        }
      }

      @Override
      public void onServiceDisconnected(ComponentName name) {
        Log.d(TAG, "BookActivity onServiceDisconnected: ");
      }
    };

    Log.d(TAG, "BookActivity onCreate: bindService");

    Intent serviceIntent = new Intent(this, BookService.class);
    bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();

    if (bookManager != null && bookManager.asBinder().isBinderAlive()) {
      try {
        bookManager.unregisterNewBookArrivedListener(arrivedListener);
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }

    Log.d(TAG, "BookActivity onDestroy: unbindService");
    unbindService(serviceConnection);
  }
}
