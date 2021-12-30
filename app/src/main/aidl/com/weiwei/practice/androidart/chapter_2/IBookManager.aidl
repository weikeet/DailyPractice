// IBookManager.aidl
package com.weiwei.practice.androidart.chapter_2;

import com.weiwei.practice.androidart.chapter_2.Book;
import com.weiwei.practice.androidart.chapter_2.IOnNewBookArrivedListener;

interface IBookManager {
    List<Book> getBookList();

    void addBook(in Book book);

    void registerNewBookArrivedListener(IOnNewBookArrivedListener listener);

    void unregisterNewBookArrivedListener(IOnNewBookArrivedListener listener);
}