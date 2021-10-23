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
import android.os.Parcel;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.PermissionChecker;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.jetbrains.annotations.NotNull;

/**
 * @author weicools
 * @date 2021.10.21
 */
public class BookService extends Service {
  private static final String TAG = "AIDL_BookSample";

  private final CopyOnWriteArrayList<Book> bookList = new CopyOnWriteArrayList<>();

  // 客户端传来的对象在服务端会变成不同对象，导致 register 和 unregister 不是同一对象
  // private final CopyOnWriteArrayList<IOnNewBookArrivedListener> listeners = new CopyOnWriteArrayList<>();
  private final RemoteCallbackList<IOnNewBookArrivedListener> listeners = new RemoteCallbackList<>();

  private final IBinder binder = new IBookManager.Stub() {
    @Override public List<Book> getBookList() throws RemoteException {
      Log.d(TAG, "BookService getBookList: " + bookList.size() + ", thread=" + Thread.currentThread());
      return bookList;
    }

    @Override public void addBook(Book book) throws RemoteException {
      Log.d(TAG, "BookService addBook: " + book + ", thread=" + Thread.currentThread());

      // for (IOnNewBookArrivedListener listener : listeners) {
      //   listener.onNewBookArrived(book);
      // }

      final int n = listeners.beginBroadcast();

      for (int i = 0; i < n; i++) {
        IOnNewBookArrivedListener listener = listeners.getBroadcastItem(i);
        if (listener != null) {
          try {
            listener.onNewBookArrived(book);
          } catch (RemoteException e) {
            e.printStackTrace();
          }
        }
      }

      listeners.finishBroadcast();

      bookList.add(book);
    }

    @Override public void registerNewBookArrivedListener(IOnNewBookArrivedListener listener) throws RemoteException {
      // if (listeners.contains(listener)) {
      //   Log.d(TAG, "BookService registerNewBookArrivedListener: listener exist");
      // } else {
      //   Log.d(TAG, "BookService registerNewBookArrivedListener: add listener");
      //   listeners.add(listener);
      // }

      listeners.register(listener);
    }

    @Override public void unregisterNewBookArrivedListener(IOnNewBookArrivedListener listener) throws RemoteException {
      // if (listeners.contains(listener)) {
      //   Log.d(TAG, "BookService unregisterNewBookArrivedListener: remove listener");
      //   listeners.remove(listener);
      // } else {
      //   Log.d(TAG, "BookService unregisterNewBookArrivedListener: listener not exist");
      // }

      listeners.unregister(listener);
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

    @Override
    protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
      // 验证权限
      int checkResult = checkCallingOrSelfPermission(PERMISSION);
      if (checkResult == PermissionChecker.PERMISSION_DENIED) {
        return false;
      }

      // 验证 Uid 和 Pid
      String packageName = null;
      String[] packages = getPackageManager().getPackagesForUid(getCallingUid());
      if (packages != null && packages.length > 0) {
        packageName = packages[0];
      }
      if (packageName != null && !packageName.startsWith("com.ryg")) {
        return false;
      }

      return super.onTransact(code, data, reply, flags);
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

  private static final String PERMISSION = "com.ryg.chapter_2.permission.ACCESS_BOOK_SERVICE";

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    Log.d(TAG, "BookService onBind: ");

    // 权限验证方法1：onBind 中判断，不通过返回 null 客户端无法绑定服务
    int checkResult = checkCallingOrSelfPermission(PERMISSION);
    if (checkResult == PermissionChecker.PERMISSION_DENIED) {
      Log.d(TAG, "BookService onBind: ");
      return null;
    }
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
