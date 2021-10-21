// IOnNewBookArrivedListener.aidl
package com.ryg.chapter_2;

import com.ryg.chapter_2.Book;

interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book newBook);
}