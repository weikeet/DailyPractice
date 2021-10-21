// IBookManager.aidl
package com.ryg.chapter_2;

import com.ryg.chapter_2.Book;
import com.ryg.chapter_2.IOnNewBookArrivedListener;

interface IBookManager {
    List<Book> getBookList();

    void addBook(in Book book);

    void registerNewBookArrivedListener(IOnNewBookArrivedListener listener);

    void unregisterNewBookArrivedListener(IOnNewBookArrivedListener listener);
}