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

package com.weiwei.practice.androidart.c7;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author weiwei
 * @date 2023.03.12
 *
 * 用于访问其他程序的数据
 */
public class BookContentProvider extends ContentProvider {
  @Override
  public boolean onCreate() {
    return false;
  }

  /**
   * 和数据库的查询方法类似，用于查询数据
   * 查询完成返回一个Cursor对象，Cursor对象是一个结果集，用于存放查询结果
   * @param uri The URI to query. This will be the full URI sent by the client;
   *      if the client is requesting a specific record, the URI will end in a record number
   *      that the implementation should parse and add to a WHERE or HAVING clause, specifying
   *      that _id value.
   * @param projection The list of columns to put into the cursor. If
   *      {@code null} all columns are included.
   * @param selection A selection criteria to apply when filtering rows.
   *      If {@code null} then all rows are included.
   * @param selectionArgs You may include ?s in selection, which will be replaced by
   *      the values from selectionArgs, in order that they appear in the selection.
   *      The values will be bound as Strings.
   * @param sortOrder How the rows in the cursor should be sorted.
   *      If {@code null} then the provider is free to define the sort order.
   */
  @Nullable
  @Override
  public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
    return null;
  }

  @Nullable
  @Override
  public String getType(@NonNull Uri uri) {
    return null;
  }

  @Nullable
  @Override
  public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
    return null;
  }

  @Override
  public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
    return 0;
  }

  @Override
  public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
    return 0;
  }
}
