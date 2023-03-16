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
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;

/*
创建名为 BookStore.db 的数据库，在数据库中新建表 book，表中有 4 个字段，分别为 id(主键)、author、price、pages 和 name。
create table book(
id integer primary key autoincrement,
author text,
price real,
pages integer,
name text
);
 */

/**
 * @author weiwei
 * @date 2023.03.12
 */
public class BookSQLiteOpenHelper extends SQLiteOpenHelper {

  private static final String TAG = "BookSQLiteOpenHelper";

  private static final String CREATE_BOOK = "create table book("
      + "id integer primary key autoincrement,"
      + "author text,"
      + "price real,"
      + "pages integer,"
      + "name text)";

  private static final String CREATE_CATEGORY = "create table category("
      + "id integer primary key autoincrement,"
      + "category_name text,"
      + "category_code integer)";

  private Context context;

  /**
   * @param context 上下文
   * @param name 数据库名
   * @param factory 游标工厂
   * @param version 数据库版本号，当数据库版本号发生变化时，会调用 onUpgrade() 方法。
   */
  public BookSQLiteOpenHelper(
      @Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version
  ) {
    super(context, name, factory, version);
    this.context = context;
  }

  // 创建或打卡一个数据库，并返回可以对数据库进行操作的对象。
  // @Override
  // public SQLiteDatabase getReadableDatabase() {
  //   return super.getReadableDatabase();
  // }

  /**
   * 创建数据库
   * 只会在数据库不存在时调用一次
   */
  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(CREATE_BOOK);
    db.execSQL(CREATE_CATEGORY);
    Log.d(TAG, "onCreate: create table book");
  }

  /**
   * 升级数据库
   *
   * @param db The database.
   * @param oldVersion The old database version.
   * @param newVersion The new database version.
   */
  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // 直接删除表过于粗暴，可以根据版本号进行升级
    // db.execSQL("drop table if exists book");
    // db.execSQL("drop table if exists category");
    // onCreate(db);

    switch (oldVersion) {
      case 1:
        db.execSQL(CREATE_CATEGORY);
      case 2:
        db.execSQL("alter table book add column category_id integer");
      default:
    }
  }
}
