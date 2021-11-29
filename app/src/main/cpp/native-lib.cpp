////
//// Created by Weicools on 2021/11/29.
////
//
//#include "io_weicools_daily_practice_ndk_HelloWorld.h"
//
//#include <android/log.h>
//
//#define TAG "Weiwei"
//#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)
//
//extern "C"
//JNIEXPORT void JNICALL
//Java_io_weicools_daily_practice_ndk_HelloWorld_sayHi (JNIEnv *env, jobject thiz) {
//  LOGD("sayHie:%s", "HelloWorld!");
//}
//
//extern "C"
//JNIEXPORT void JNICALL
//Java_io_weicools_daily_practice_ndk_HelloWorld_accessField (JNIEnv *env, jobject thiz) {
//  // 获取 jclass
//  jclass clz = env->GetObjectClass(thiz);
//
//  // 静态字段 ID
//  jfieldID sFieldId = env->GetStaticFieldID(clz, "sName", "Ljava/lang/String;");
//  // 访问静态字段
//  if(sFieldId) {
//    jstring jStr = static_cast<jstring>(env->GetStaticObjectField(clz, sFieldId));
//    // 转换为 C 字符串
//    const char *sStr = env->GetStringUTFChars(jStr, NULL);
//    LOGD("静态字段：%s", sStr);
//
//    env->ReleaseStringUTFChars(jStr, sStr);
//
//    jstring newStr = env->NewStringUTF("静态字段 - Peng");
//    if (newStr) {
//      env->SetStaticObjectField(clz, sFieldId, newStr);
//    }
//  }
//
//  // 实例字段 ID
//  jfieldID mFieldId = env->GetFieldID(clz, "mName", "Ljava/lang/String;");
//  // 访问实例字段
//  if (mFieldId) {
//    jstring jStr = static_cast<jstring>(env->GetObjectField(thiz, mFieldId));
//    // 转换为 C 字符串
//    const char *sStr = env->GetStringUTFChars(jStr, NULL);
//    LOGD("实例字段：%s", sStr);
//
//    env->ReleaseStringUTFChars(jStr, sStr);
//
//    jstring newStr = env->NewStringUTF("实例字段 - Weiwei");
//    if (newStr) {
//      env->SetObjectField(thiz, mFieldId, newStr);
//    }
//  }
//}
//
//extern "C"
//JNIEXPORT void JNICALL
//Java_io_weicools_daily_practice_ndk_HelloWorld_accessMethod (JNIEnv *env, jobject thiz) {
//  // 获取 jclass
//  jclass clz = env->GetObjectClass(thiz);
//
//  // 静态方法 ID
//  jmethodID sMethodId = env->GetStaticMethodID(clz, "sHelloJava", "()V");
//  if (sMethodId) {
//    env->CallStaticVoidMethod(clz, sMethodId);
//  }
//
//  // 实例方法 ID
//  jmethodID mMethodId = env->GetMethodID(clz, "helloJava", "()V");
//  if (mMethodId) {
//    env->CallVoidMethod(thiz, mMethodId);
//  }
//}