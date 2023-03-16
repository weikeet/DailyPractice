/*
 * Copyright (c) 2020 Weiwei
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

package com.weiwei.practice.androidart.c6;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author weiwei
 * @date 2023.03.12
 */
public class BookDatabaseOperation {

  public void insertBook() {
    SQLiteDatabase database = BookDatabaseManager.getInstance().getWritableDatabase();
    database.execSQL("insert into book values(null, ?, ?, ?, ?)", new Object[] { "Dan Brown", 16.96, 454, "The Da Vinci Code" });

    ContentValues contentValues = new ContentValues();
    contentValues.put("author", "Dan Brown");
    contentValues.put("price", 16.96);
    contentValues.put("pages", 454);
    contentValues.put("name", "The Lost Symbol");
    database.insert("book", null, contentValues);

    contentValues.clear();

    contentValues.put("author", "Dan hub");
    contentValues.put("price", 24.95);
    contentValues.put("pages", 510);
    contentValues.put("name", "Inferno");
    database.insert("book", null, contentValues);
  }

  public void updateBook() {
    SQLiteDatabase database = BookDatabaseManager.getInstance().getWritableDatabase();
    database.execSQL("update book set price = ? where name = ?", new Object[] { 10.99, "The Da Vinci Code" });

    ContentValues contentValues = new ContentValues();
    contentValues.put("price", 14.95);
    database.update("book", contentValues, "name = ?", new String[] { "The Lost Symbol" });
  }

  public void deleteBook() {
    SQLiteDatabase database = BookDatabaseManager.getInstance().getWritableDatabase();
    database.execSQL("delete from book where pages > ?", new Object[] { 500 });

    database.delete("book", "pages > ?", new String[] { "500" });
  }

  public void queryBook() {
    SQLiteDatabase database = BookDatabaseManager.getInstance().getReadableDatabase();
    database.rawQuery("select * from book where pages > ?", new String[] { "500" });
  }

  public void transaction() {
    SQLiteDatabase database = BookDatabaseManager.getInstance().getWritableDatabase();
    database.beginTransaction();
    try {
      database.execSQL("insert into book values(null, ?, ?, ?, ?)", new Object[] { "Dan Brown", 16.96, 454, "The Da Vinci Code" });
      database.execSQL("insert into book values(null, ?, ?, ?, ?)", new Object[] { "Dan Brown", 16.96, 454, "The Da Vinci Code" });
      database.setTransactionSuccessful();
    } finally {
      database.endTransaction();
    }
  }

}
