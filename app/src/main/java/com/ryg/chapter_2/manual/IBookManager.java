package com.ryg.chapter_2.manual;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ryg.chapter_2.Book;
import java.util.List;

/**
 * @author weicools
 * @date 2021.10.21
 */
public interface IBookManager extends IInterface {

  public List<Book> getBookList() throws RemoteException;
  public void addBook(Book book) throws RemoteException;

  /** Default implementation for IBookManager. */
  public static class Default implements IBookManager {
    @Override public List<Book> getBookList() throws RemoteException {
      return null;
    }

    @Override public void addBook(Book book) throws RemoteException {
    }

    @Override public IBinder asBinder() {
      return null;
    }
  }

  /** Local-side IPC implementation stub class. */
  public abstract class Stub extends Binder implements IBookManager {
    private static final String DESCRIPTOR = "com.ryg.chapter_2.IBookManager";

    static final int TRANSACTION_getBookList = (IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_addBook = (IBinder.FIRST_CALL_TRANSACTION + 1);

    public Stub() {
      this.attachInterface(this, DESCRIPTOR);
    }

    public static IBookManager asInterface(IBinder obj) {
      if (obj == null) {
        return null;
      }
      IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (iin instanceof IBookManager) {
        return ((IBookManager) iin);
      }
      return new Stub.Proxy(obj);
    }

    @Override public IBinder asBinder() {
      return this;
    }

    @Override protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
      String descriptor = DESCRIPTOR;
      switch (code) {
        case INTERFACE_TRANSACTION: {
          reply.writeString(descriptor);
          return true;
        }
        case TRANSACTION_getBookList: {
          data.enforceInterface(descriptor);
          List<Book> _result = this.getBookList();
          reply.writeNoException();
          reply.writeTypedList(_result);
          return true;
        }
        case TRANSACTION_addBook: {
          data.enforceInterface(descriptor);
          Book book = null;
          if (0 != data.readInt()) {
            book = Book.CREATOR.createFromParcel(data);
          }
          this.addBook(book);
          reply.writeNoException();
          return true;
        }
        default: {
          return super.onTransact(code, data, reply, flags);
        }
      }
    }

    private static class Proxy implements IBookManager {
      private IBinder mRemote;

      Proxy(IBinder remote) {
        mRemote = remote;
      }

      @Override public IBinder asBinder() {
        return mRemote;
      }

      public String getInterfaceDescriptor() {
        return DESCRIPTOR;
      }

      @Override public List<Book> getBookList() throws RemoteException {
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        List<Book> _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(TRANSACTION_getBookList, _data, _reply, 0);
          if (!_status) { // getDefaultImpl() != null
            return null; // return getDefaultImpl().getBookList();
          }
          _reply.readException();
          _result = _reply.createTypedArrayList(Book.CREATOR);
        } finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }

      @Override public void addBook(Book book) throws RemoteException {
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
            return; // getDefaultImpl().addBook(book);
          }

          _reply.readException();
        } finally {
          _reply.recycle();
          _data.recycle();
        }
      }
    }
  }
}
