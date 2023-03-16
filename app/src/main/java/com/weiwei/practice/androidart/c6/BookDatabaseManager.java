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

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author weiwei
 * @date 2023.03.12
 */
public class BookDatabaseManager {

  private static final String TAG = "BookDatabaseManager";

  private BookSQLiteOpenHelper mBookSQLiteOpenHelper;

  private BookDatabaseManager() {
  }

  private static final class InstanceHolder {
    static final BookDatabaseManager sInstance = new BookDatabaseManager();
  }

  public static BookDatabaseManager getInstance() {
    return InstanceHolder.sInstance;
  }

  public void init(Context context) {
    mBookSQLiteOpenHelper = new BookSQLiteOpenHelper(context, "BookStore.db", null, 1);
  }

  public SQLiteDatabase getWritableDatabase() {
    return mBookSQLiteOpenHelper.getWritableDatabase();
  }

  public SQLiteDatabase getReadableDatabase() {
    return mBookSQLiteOpenHelper.getReadableDatabase();
  }

  public void closeDatabase() {
    mBookSQLiteOpenHelper.close();
  }

}
