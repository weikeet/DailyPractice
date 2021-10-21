/*
 * Copyright (c) 2020 Weicools
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

package com.ryg.chapter_2;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author weicools
 * @date 2021.10.21
 */
public final class Book implements Parcelable {
  public int bookId;
  public String bookName;

  public Book(int bookId, String bookName) {
    this.bookId = bookId;
    this.bookName = bookName;
  }

  @Override public String toString() {
    return "Book{" +
        "bookId=" + bookId +
        ", bookName='" + bookName + '\'' +
        '}';
  }

  @Override
  public int describeContents() { return 0; }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.bookId);
    dest.writeString(this.bookName);
  }

  public void readFromParcel(Parcel source) {
    this.bookId = source.readInt();
    this.bookName = source.readString();
  }

  protected Book(Parcel in) {
    this.bookId = in.readInt();
    this.bookName = in.readString();
  }

  public static final Creator<Book> CREATOR = new Creator<Book>() {
    @Override public Book createFromParcel(Parcel source) {
      return new Book(source);
    }

    @Override public Book[] newArray(int size) {
      return new Book[size];
    }
  };
}
