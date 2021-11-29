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

package io.weicools.daily.practice.ndk;

import android.util.Log;

public class HelloWorld {
  private static final String TAG = "HelloWorld";

  private static final int A = 1;

  private static String sName = "静态初始值";
  private String mName = "成员初始值";

  public static String getStaticName() {
    return sName;
  }

  public String getMemberName() {
    return mName;
  }

  private static void sHelloJava() {
    Log.d(TAG, "静态方法 helloJava");
  }

  private void helloJava() {
    Log.d(TAG, "实例方法 helloJava");
  }

  static {
    // TODO: 2021/11/29 impl jni
    System.loadLibrary("Hello-World");
  }

  public native void sayHi();

  public native void accessField();

  public native void accessMethod();
}
