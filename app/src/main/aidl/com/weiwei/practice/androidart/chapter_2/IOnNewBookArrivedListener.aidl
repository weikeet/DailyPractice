// IOnNewBookArrivedListener.aidl
package com.weiwei.practice.androidart.chapter_2;

import com.weiwei.practice.androidart.chapter_2.Book;

interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book newBook);
}