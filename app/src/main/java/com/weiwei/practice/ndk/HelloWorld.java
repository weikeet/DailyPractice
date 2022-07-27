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

package com.weiwei.practice.ndk;

import android.util.Log;

/**
 * @author weiwei
 */
public final class HelloWorld {
  private static final String TAG = "NativePractice";

  private static String sName = "静态变量初始值";
  private String mName = "成员变量初始值";

  public static String getStaticName() {
    return sName;
  }

  public String getMemberName() {
    return mName;
  }

  public void printName() {
    Log.d(TAG, "printName: sName=" + sName + ", mName=" + mName);
  }

  private static void sHelloJava() {
    Log.d(TAG, "===静态方法 helloJava");
  }

  private void helloJava() {
    Log.d(TAG, "===实例方法 helloJava");
  }

  static {
    System.loadLibrary("practice");
  }

  public native void sayHi();

  public native void accessField();

  public native void accessMethod();

  public native void testNativeCrash();
}
