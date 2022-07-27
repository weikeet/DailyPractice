//
// Created by Weicools on 2021/11/29.
//

#include <android/log.h>
#include<jni.h>

#include "practice_HelloWorld.h"
#include "logger.h"

extern "C"
JNIEXPORT void JNICALL
Java_com_weiwei_practice_ndk_HelloWorld_sayHi(JNIEnv *, jobject) {
  LOGD("Hello World!");
}

extern "C"
JNIEXPORT void JNICALL
Java_com_weiwei_practice_ndk_HelloWorld_accessField(JNIEnv *env, jobject thiz) {
  // jclass clz = env->GetObjectClass(thiz);
  // FindClass 支持提示 Method 和 Field 参数类型
  jclass clz = env->FindClass("com/weiwei/practice/ndk/HelloWorld");

  // 静态字段 ID
  jfieldID sFieldId = env->GetStaticFieldID(clz, "sName", "Ljava/lang/String;");
  // 访问静态字段
  if (sFieldId) {
    auto jStr = static_cast<jstring>(env->GetStaticObjectField(clz, sFieldId));

    // 转换为 C 字符串
    const char *sStr = env->GetStringUTFChars(jStr, NULL);
    env->ReleaseStringUTFChars(jStr, sStr);
    LOGD("静态字段=%s", sStr);

    jstring newStr = env->NewStringUTF("Native set 静态字段");
    if (newStr) {
      env->SetStaticObjectField(clz, sFieldId, newStr);
    }
  }

  // 实例字段 ID
  jfieldID mFieldId = env->GetFieldID(clz, "mName", "Ljava/lang/String;");
  // 访问实例字段
  if (mFieldId) {
    auto jStr = static_cast<jstring>(env->GetObjectField(thiz, mFieldId));

    // 转换为 C 字符串
    const char *sStr = env->GetStringUTFChars(jStr, NULL);
    env->ReleaseStringUTFChars(jStr, sStr);
    LOGD("实例字段=%s", sStr);

    jstring newStr = env->NewStringUTF("Native set 实例字段");
    if (newStr) {
      env->SetObjectField(thiz, mFieldId, newStr);
    }
  }
}

extern "C"
JNIEXPORT void JNICALL
Java_com_weiwei_practice_ndk_HelloWorld_accessMethod(JNIEnv *env, jobject thiz) {
  // jclass clz = env->GetObjectClass(thiz);
  // FindClass 支持提示 Method 和 Field 参数类型
  jclass clz = env->FindClass("com/weiwei/practice/ndk/HelloWorld");

  // 静态方法 ID
  jmethodID sMethodId = env->GetStaticMethodID(clz, "sHelloJava", "()V");
  if (sMethodId) {
    env->CallStaticVoidMethod(clz, sMethodId);
  }

  // 实例方法 ID
  jmethodID mMethodId = env->GetMethodID(clz, "helloJava", "()V");
  if (mMethodId) {
    env->CallVoidMethod(thiz, mMethodId);
  }
}
extern "C"
JNIEXPORT void JNICALL
Java_com_weiwei_practice_ndk_HelloWorld_testNativeCrash(JNIEnv *env, jobject thiz) {
  int *p = nullptr;

  LOGD("Print arr[0]=%d", p[0]);
}