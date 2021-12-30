
* Android跨进程传大图思考及实现——附上原理分析 https://juejin.cn/post/7011276367308750879

## Crash LOG

```
2021-11-28 01:10:24.100 9938-9938/? E/AndroidRuntime: FATAL EXCEPTION: main
    Process: com.weiwei.practice, PID: 9938
    java.lang.RuntimeException: Could not copy bitmap to parcel blob.
        at android.graphics.Bitmap.nativeWriteToParcel(Native Method)
        at android.graphics.Bitmap.writeToParcel(Bitmap.java:2124)
        at android.os.Parcel.writeParcelable(Parcel.java:1952)
        at android.os.Parcel.writeValue(Parcel.java:1858)
        at android.os.Parcel.writeArrayMapInternal(Parcel.java:1023)
        at android.os.BaseBundle.writeToParcelInner(BaseBundle.java:1620)
        at android.os.Bundle.writeToParcel(Bundle.java:1304)
        at android.os.Parcel.writeBundle(Parcel.java:1092)
        at android.content.Intent.writeToParcel(Intent.java:11100)
        at android.app.IActivityTaskManager$Stub$Proxy.startActivity(IActivityTaskManager.java:2254)
        at android.app.Instrumentation.execStartActivity(Instrumentation.java:1743)
        at android.app.Activity.startActivityForResult(Activity.java:5404)
        at androidx.activity.ComponentActivity.startActivityForResult(ComponentActivity.java:597)
        at android.app.Activity.startActivityForResult(Activity.java:5362)
        at androidx.activity.ComponentActivity.startActivityForResult(ComponentActivity.java:583)
        at android.app.Activity.startActivity(Activity.java:5748)
        at android.app.Activity.startActivity(Activity.java:5701)
        at com.weiwei.practice.binder.TransactionTooLargeActivity$Companion.start(TransactionTooLargeActivity.kt:40)
```

## 单进程下putBinder用法

```kotlin
//定义一个IntentBinder，此方法仅在『同一个进程』下有效哦，切记切记！！！！
class IntentBinder(val imageBmp:Bitmap? = null): Binder()

//------------------------使用如下--------------------------//
//com.xxx.xxx.MainActivity
val bitmap = BitmapFactory.decodeStream(...)
startActivity(Intent(this,SecondActivity::class.java).putExtras(Bundle().apply {
        putBinder("myBinder",IntentBinder(bitmap))
}))

//------------------------获取Bitmap并显示如下--------------------------//
//com.xxx.xxx.SecondActivity
val bundle: Bundle? = intent.extras
val imageBinder:IntentBinder? = bundle?.getBinder("myBinder") as IntentBinder?
//拿到Binder中的Bitmap
val bitmap = imageBinder?.imageBmp
//自行压缩后显示到ImageView上.....
```

多进程场景下，你会发现会报一个强制转换的异常错误

```
//错误的用在多进程场景下，报错如下：
java.lang.ClassCastException: android.os.BinderProxy cannot be cast to com.xxx.xxx.IntentBinder
```

为什么可以通过这种方式传递对象？
Binder会为我们的对象创建一个全局的JNI引用,点击查看android_util_Binder.cpp

## 多进程下putBinder用法

```java
//先定义一个IGetBitmapService.aidl
package com.xxx.aidl;
interface IGetBitmapService {
    Bitmap getIntentBitmap();
}
```

```kotlin
//------------------------使用如下--------------------------//
//com.xxx.xxx.MainActivity      👉进程A
val bitmap = BitmapFactory.decodeStream(...)
startActivity(Intent(this,SecondActivity::class.java).putExtras(Bundle().apply {
    putBinder("myBinder",object: IGetBitmapService.Stub() {
        override fun getIntentBitmap(): Bitmap {
            return bitmap
        }
    })
}))

//------------------------获取Bitmap并显示如下--------------------------//
//com.xxx.xxx.SecondActivity      👉进程B
val bundle: Bundle? = intent.extras
//返回IGetBitmapService类型
val getBitmapService = IGetBitmapService.Stub.asInterface(bundle?.getBinder("myBinder"))
val bitmap = getBitmapService.intentBitmap
//自行压缩后显示到ImageView上.....
```