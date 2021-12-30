package com.weiwei.practice.reflect;

import android.util.Log;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author weicools
 * @date 2020.10.31
 */
public class ReflectSample {
  public static final String TAG = "ReflectSample";

  public static void reflectIntent() {
    try {
      Class<?> clazz = Class.forName("android.content.Intent");
      Method[] declaredMethods = clazz.getDeclaredMethods();

      // for (Method method : declaredMethods) {
      //   Log.d(TAG, "reflectIntent: methodName=" + method.getName());
      // }

      Constructor<?> constructors = clazz.getDeclaredConstructor();
      constructors.setAccessible(true);

      Object intent = constructors.newInstance();
      Log.d(TAG, "reflectIntent: intent=" + intent);

      Method setSenderMethod = clazz.getDeclaredMethod("setSender", String.class);
      setSenderMethod.setAccessible(true);
      setSenderMethod.invoke(intent, "com.weiwei.practice");
      Log.d(TAG, "reflectIntent: setSenderMethod finish");

      Method getSenderMethod = clazz.getDeclaredMethod("getSender");
      getSenderMethod.setAccessible(true);
      String sender = (String) getSenderMethod.invoke(intent);
      Log.d(TAG, "reflectIntent: getSender=" + sender);
    } catch (Throwable e) {
      Log.d(TAG, "reflectIntent: e=" + e.getMessage());
    }
  }

  /**
   * see https://juejin.im/post/6844903520479477774
   */
  public static void reflectStudent() {
    try {
      Class<?> clazz = Class.forName("com.weiwei.practice.reflect.Student");
      Constructor<?> constructors = clazz.getDeclaredConstructor(int.class, String.class, String.class);
      constructors.setAccessible(true);

      //利用构造器生成对象
      Object student = constructors.newInstance(27, "小文", "北京市海定区XX号");
      System.out.println(student.toString());

      //获取隐藏的int属性
      Field ageField = clazz.getDeclaredField("age");
      ageField.setAccessible(true);
      int age = (int) ageField.get(student);
      System.out.println("年龄为:" + age);

      //调用隐藏的方法
      Method getAddressMethod = clazz.getDeclaredMethod("getAge");
      getAddressMethod.setAccessible(true);
      int newAge = (int) getAddressMethod.invoke(student);
      System.out.println("年龄为:" + newAge);

      //调用静态方法
      Method getTestMethod = clazz.getDeclaredMethod("getTest");
      getTestMethod.setAccessible(true);
      String result = (String) getTestMethod.invoke(null);
      System.out.println("调用静态方法:" + result);
    } catch (Throwable e) {
      Log.d(TAG, "reflectStudent() Exception: " + e.getMessage());
    }
  }
}
