package io.weicools.daily.practice.reflect;

/**
 * @author weicools
 * @date 2020.10.31
 */
public class Student {
  private int age;
  private String name;
  private String address;

  private static String sTest;

  public Student() {
    throw new IllegalAccessError("Access to default Constructor Error!");
  }

  private Student(int age, String name, String address) {
    this.age = age;
    this.name = name;
    this.address = address;
    sTest = "测试反射";
  }

  private int getAge() {
    return age;
  }

  private void setAge(int age) {
    this.age = age;
  }

  private String getName() {
    return name;
  }

  private void setName(String name) {
    this.name = name;
  }

  private String getAddress() {
    return address;
  }

  private void setAddress(String address) {
    this.address = address;
  }

  private static String getTest() {
    return sTest;
  }
}
