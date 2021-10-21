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

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.Nullable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author weicools
 * @date 2021.10.21
 */
public class BookService extends Service {
  private static final String TAG = "AIDL_BookSample";

  private final CopyOnWriteArrayList<Book> bookList = new CopyOnWriteArrayList<>();

  private final IBinder binder = new IBookManager.Stub() {
    @Override public List<Book> getBookList() throws RemoteException {
      Log.d(TAG, "BookService getBookList: " + bookList.size() + ", thread=" + Thread.currentThread());
      return bookList;
    }

    @Override public void addBook(Book book) throws RemoteException {
      Log.d(TAG, "BookService addBook: " + book + ", thread=" + Thread.currentThread());
      bookList.add(book);
    }
  };

  // manual
  private final IBinder bookManager = new com.ryg.chapter_2.manual.IBookManager.BookManager() {
    @Override public List<Book> getBookList() throws RemoteException {
      Log.d(TAG, "BookService getBookList: ");
      return bookList;
    }

    @Override public void addBook(Book book) throws RemoteException {
      Log.d(TAG, "BookService addBook: " + book);
      bookList.add(book);
    }
  };

  @Override
  public void onCreate() {
    super.onCreate();
    Log.d(TAG, "BookService onCreate: ");

    bookList.add(new Book(0, "DefaultBook"));
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    Log.d(TAG, "BookService onStartCommand: ");
    return super.onStartCommand(intent, flags, startId);
  }

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    Log.d(TAG, "BookService onBind: ");
    return binder;
  }

  @Override
  public boolean onUnbind(Intent intent) {
    Log.d(TAG, "BookService onUnbind: ");
    return super.onUnbind(intent);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    Log.d(TAG, "BookService onDestroy: ");
  }
}
