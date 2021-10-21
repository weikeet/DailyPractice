// IBookManager.aidl
package com.ryg.chapter_2;

import com.ryg.chapter_2.Book;

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
}