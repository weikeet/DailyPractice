//
// Created by Weicools on 2021/11/29.
//

#include<jni.h>

#ifndef PRACTICE_HELLOWORLD_H
#define PRACTICE_HELLOWORLD_H

extern "C" {
JNIEXPORT void JNICALL
Java_com_weiwei_practice_ndk_HelloWorld_sayHi(JNIEnv *, jobject);

JNIEXPORT void JNICALL
Java_com_weiwei_practice_ndk_HelloWorld_accessField(JNIEnv *, jobject);

extern "C"
JNIEXPORT void JNICALL
Java_com_weiwei_practice_ndk_HelloWorld_accessMethod(JNIEnv *, jobject thiz);
}

#endif //PRACTICE_HELLOWORLD_H
