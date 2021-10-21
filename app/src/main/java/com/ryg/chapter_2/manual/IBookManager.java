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

package com.ryg.chapter_2.manual;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.ryg.chapter_2.Book;
import java.util.List;

/**
 * @author weicools
 * @date 2021.10.21
 */
public interface IBookManager extends android.os.IInterface {
  static final String DESCRIPTOR = "com.ryg.chapter_2.manual.IBookManager";

  static final int TRANSACTION_getBookList = IBinder.FIRST_CALL_TRANSACTION;
  static final int TRANSACTION_addBook = IBinder.FIRST_CALL_TRANSACTION + 1;

  public List<Book> getBookList() throws RemoteException;
  public void addBook(Book book) throws RemoteException;

  /**
   * stub
   */
  public abstract class BookManager extends Binder implements IBookManager {
    public BookManager() {
      this.attachInterface(this, DESCRIPTOR);
    }

    public static IBookManager asInterface(IBinder obj) {
      if (obj == null) {
        return null;
      }

      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (iin instanceof IBookManager) {
        return (IBookManager) iin;
      }

      return new Proxy(obj);
    }

    @Override
    public IBinder asBinder() {
      return this;
    }

    @Override
    protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
      switch (code) {
        case INTERFACE_TRANSACTION:
          reply.writeString(DESCRIPTOR);
          return true;

        case TRANSACTION_getBookList:
          data.enforceInterface(DESCRIPTOR);
          List<Book> _result = this.getBookList();
          reply.writeNoException();
          reply.writeTypedList(_result);
          return true;

        case TRANSACTION_addBook:
          data.enforceInterface(DESCRIPTOR);
          Book book = null;
          if (0 != data.readInt()) {
            book = Book.CREATOR.createFromParcel(data);
          }
          this.addBook(book);
          reply.writeNoException();
          return true;

        default:
          return super.onTransact(code, data, reply, flags);
      }
    }

    public static final class Proxy implements IBookManager {
      private android.os.IBinder mRemote;

      public Proxy(IBinder remote) {
        this.mRemote = remote;
      }

      @Override
      public IBinder asBinder() {
        return mRemote;
      }

      public String getInterfaceDescriptor() {
        return DESCRIPTOR;
      }

      @Override
      public List<Book> getBookList() throws RemoteException {
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();

        List<Book> _result = null;

        try {
          _data.writeInterfaceToken(DESCRIPTOR);

          boolean _status = mRemote.transact(TRANSACTION_getBookList, _data, _reply, 0);
          if (!_status) { // getDefaultImpl() != null
            // return getDefaultImpl().getBookList();
            return null;
          }

          _reply.readException();
          _result = _reply.createTypedArrayList(Book.CREATOR);
        } finally {
          _data.recycle();
          _reply.recycle();
        }
        return _result;
      }

      @Override
      public void addBook(Book book) throws RemoteException {
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);

          if (book != null) {
            _data.writeInt(1);
            book.writeToParcel(_data, 0);
          } else {
            _data.writeInt(0);
          }

          boolean _status = mRemote.transact(TRANSACTION_addBook, _data, _reply, 0);
          if (!_status) { // getDefaultImpl() != null
            // getDefaultImpl().addBook(book);
            return;
          }

          _reply.readException();
        } finally {
          _data.recycle();
          _reply.recycle();
        }
      }
    }
  }
}
