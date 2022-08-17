
popupViewHeight = decorViewHeight - statusBarHeight - navigationBarHeight
keyboardHeight = decorViewHeight - popupFrameHeight - statusBarHeight

#### Pixel 5

Android 12

```shell
SoftKeyboardWatcher: widthPixels=1080, heightPixels=1872, screenRealHeight=1920
SoftKeyboardWatcher: heightPixels = screenRealHeight - navigationBarBottom
```

##### start:

```shell
SoftKeyboardWatcher: imBottom=0, statusBarTop=72, navigationBarBottom=48
SoftKeyboardWatcher: imeVisible=false, statusBarsVisible=true, navigationBarsVisible=true
SoftKeyboardWatcher: decorViewWindowVisibleDisplayFrame top=72, bottom=1872, height=1800, viewHeight=1920
SoftKeyboardWatcher: popupViewWindowVisibleDisplayFrame top=72, bottom=1872, height=1800, viewHeight=1800
SoftKeyboardWatcher: keyboardVisible = false, keyboardHeight = 0

SoftKeyboardWatcher: imBottom=0, statusBarTop=72, navigationBarBottom=48
SoftKeyboardWatcher: imeVisible=false, statusBarsVisible=true, navigationBarsVisible=true
SoftKeyboardWatcher: decorViewWindowVisibleDisplayFrame top=72, bottom=1872, height=1800, viewHeight=1920
SoftKeyboardWatcher: popupViewWindowVisibleDisplayFrame top=72, bottom=1872, height=1800, viewHeight=1800
SoftKeyboardWatcher: keyboardVisible = false, keyboardHeight = 0

VS nonEdge: 多一次回调
SoftKeyboardWatcher: imBottom=0, statusBarTop=72, navigationBarBottom=48
SoftKeyboardWatcher: imeVisible=false, statusBarsVisible=true, navigationBarsVisible=true
SoftKeyboardWatcher: decorViewWindowVisibleDisplayFrame top=72, bottom=1872, height=1800, viewHeight=1920
SoftKeyboardWatcher: popupViewWindowVisibleDisplayFrame top=72, bottom=1872, height=1800, viewHeight=1800
SoftKeyboardWatcher: keyboardVisible = false, keyboardHeight = 0
```

##### showIme:

```shell
SoftKeyboardWatcher: imBottom=962, statusBarTop=72, navigationBarBottom=48
SoftKeyboardWatcher: imeVisible=true, statusBarsVisible=true, navigationBarsVisible=true
SoftKeyboardWatcher: decorViewWindowVisibleDisplayFrame top=72, bottom=958, height=886, viewHeight=1920
SoftKeyboardWatcher: popupViewWindowVisibleDisplayFrame top=72, bottom=1872, height=1800, viewHeight=1800
SoftKeyboardWatcher: keyboardVisible = false, keyboardHeight = 0

SoftKeyboardWatcherPost: imBottom=962, statusBarTop=72, navigationBarBottom=48
SoftKeyboardWatcherPost: imeVisible=true, statusBarsVisible=true, navigationBarsVisible=true
SoftKeyboardWatcherPost: decorViewWindowVisibleDisplayFrame top=72, bottom=958, height=886, viewHeight=1920
VS nonEdge: show 第二次回调 keyboardHeight 才能正确计算

SoftKeyboardWatcher: imBottom=962, statusBarTop=72, navigationBarBottom=48
SoftKeyboardWatcher: imeVisible=true, statusBarsVisible=true, navigationBarsVisible=true
SoftKeyboardWatcher: decorViewWindowVisibleDisplayFrame top=72, bottom=958, height=886, viewHeight=1920
SoftKeyboardWatcher: popupViewWindowVisibleDisplayFrame top=72, bottom=958, height=886, viewHeight=886
SoftKeyboardWatcher: keyboardVisible = true, keyboardHeight = 962
```

##### hideIme:

```shell
SoftKeyboardWatcher: imBottom=0, statusBarTop=72, navigationBarBottom=48
SoftKeyboardWatcher: imeVisible=false, statusBarsVisible=true, navigationBarsVisible=true
SoftKeyboardWatcher: decorViewWindowVisibleDisplayFrame top=72, bottom=1872, height=1800, viewHeight=1920
SoftKeyboardWatcher: popupViewWindowVisibleDisplayFrame top=72, bottom=1872, height=1800, viewHeight=1800
SoftKeyboardWatcher: keyboardVisible = false, keyboardHeight = 0
```


#### LG Stylo 6

Android 10

```shell
SoftKeyboardWatcher: widthPixels=1080, heightPixels=2316, screenRealHeight=2460
SoftKeyboardWatcher: heightPixels = screenRealHeight - navigationBarBottom - statusBarTop
```

##### start:

```shell
SoftKeyboardWatcher: imBottom=0, statusBarTop=102, navigationBarBottom=42
SoftKeyboardWatcher: imeVisible=false, statusBarsVisible=true, navigationBarsVisible=true
SoftKeyboardWatcher: decorViewWindowVisibleDisplayFrame top=102, bottom=2418, height=2316, viewHeight=2460
SoftKeyboardWatcher: popupViewWindowVisibleDisplayFrame top=102, bottom=2418, height=2316, viewHeight=2316
SoftKeyboardWatcher: keyboardVisible = false, keyboardHeight = 0

SoftKeyboardWatcher: imBottom=0, statusBarTop=102, navigationBarBottom=42
SoftKeyboardWatcher: imeVisible=false, statusBarsVisible=true, navigationBarsVisible=true
SoftKeyboardWatcher: decorViewWindowVisibleDisplayFrame top=102, bottom=2418, height=2316, viewHeight=2460
SoftKeyboardWatcher: popupViewWindowVisibleDisplayFrame top=102, bottom=2418, height=2316, viewHeight=2316
SoftKeyboardWatcher: keyboardVisible = false, keyboardHeight = 0
```

##### showIme:

```shell
SoftKeyboardWatcher: imBottom=931, statusBarTop=102, navigationBarBottom=42
SoftKeyboardWatcher: imeVisible=true, statusBarsVisible=true, navigationBarsVisible=true
SoftKeyboardWatcher: decorViewWindowVisibleDisplayFrame top=102, bottom=1529, height=1427, viewHeight=2460
SoftKeyboardWatcher: popupViewWindowVisibleDisplayFrame top=102, bottom=1529, height=1427, viewHeight=1427
SoftKeyboardWatcher: keyboardVisible = true, keyboardHeight = 931

SoftKeyboardWatcher: imBottom=931, statusBarTop=102, navigationBarBottom=42
SoftKeyboardWatcher: imeVisible=true, statusBarsVisible=true, navigationBarsVisible=true
SoftKeyboardWatcher: decorViewWindowVisibleDisplayFrame top=102, bottom=1529, height=1427, viewHeight=2460
SoftKeyboardWatcher: popupViewWindowVisibleDisplayFrame top=102, bottom=1529, height=1427, viewHeight=1427
SoftKeyboardWatcher: keyboardVisible = true, keyboardHeight = 931
```

##### hideIme:

```shell
SoftKeyboardWatcher: imBottom=931, statusBarTop=102, navigationBarBottom=42
SoftKeyboardWatcher: imeVisible=true, statusBarsVisible=true, navigationBarsVisible=true
SoftKeyboardWatcher: decorViewWindowVisibleDisplayFrame top=102, bottom=1529, height=1427, viewHeight=2460
SoftKeyboardWatcher: popupViewWindowVisibleDisplayFrame top=102, bottom=2418, height=2316, viewHeight=2316
SoftKeyboardWatcher: keyboardVisible = false, keyboardHeight = 0

SoftKeyboardWatcherPost: imBottom=0, statusBarTop=102, navigationBarBottom=42
SoftKeyboardWatcherPost: imeVisible=false, statusBarsVisible=true, navigationBarsVisible=true
SoftKeyboardWatcherPost: decorViewWindowVisibleDisplayFrame top=102, bottom=2418, height=2316, viewHeight=2460

SoftKeyboardWatcher: imBottom=0, statusBarTop=102, navigationBarBottom=42
SoftKeyboardWatcher: imeVisible=false, statusBarsVisible=true, navigationBarsVisible=true
SoftKeyboardWatcher: decorViewWindowVisibleDisplayFrame top=102, bottom=2418, height=2316, viewHeight=2460
SoftKeyboardWatcher: popupViewWindowVisibleDisplayFrame top=102, bottom=2418, height=2316, viewHeight=2316
SoftKeyboardWatcher: keyboardVisible = false, keyboardHeight = 0
```


#### Redmi 8A

Android 10

```shell
SoftKeyboardWatcher: widthPixels=720, heightPixels=1369, screenRealHeight=1520
SoftKeyboardWatcher: heightPixels = screenRealHeight - navigationBarBottom - statusBarTop
```

##### start:

```shell
SoftKeyboardWatcher: imBottom=0, statusBarTop=55, navigationBarBottom=96
SoftKeyboardWatcher: imeVisible=false, statusBarsVisible=true, navigationBarsVisible=true
SoftKeyboardWatcher: decorViewWindowVisibleDisplayFrame top=55, bottom=1424, height=1369, viewHeight=1520
SoftKeyboardWatcher: popupViewWindowVisibleDisplayFrame top=55, bottom=1424, height=1369, viewHeight=1369
SoftKeyboardWatcher: keyboardVisible = false, keyboardHeight = 0

SoftKeyboardWatcher: imBottom=0, statusBarTop=55, navigationBarBottom=96
SoftKeyboardWatcher: imeVisible=false, statusBarsVisible=true, navigationBarsVisible=true
SoftKeyboardWatcher: decorViewWindowVisibleDisplayFrame top=55, bottom=1424, height=1369, viewHeight=1520
SoftKeyboardWatcher: popupViewWindowVisibleDisplayFrame top=55, bottom=1424, height=1369, viewHeight=1369
SoftKeyboardWatcher: keyboardVisible = false, keyboardHeight = 0
```

##### showIme:

```shell
SoftKeyboardWatcher: imBottom=0, statusBarTop=55, navigationBarBottom=96
SoftKeyboardWatcher: imeVisible=false, statusBarsVisible=true, navigationBarsVisible=true
SoftKeyboardWatcher: decorViewWindowVisibleDisplayFrame top=55, bottom=1424, height=1369, viewHeight=1520
SoftKeyboardWatcher: popupViewWindowVisibleDisplayFrame top=55, bottom=909, height=854, viewHeight=854
SoftKeyboardWatcher: keyboardVisible = true, keyboardHeight = 611

VS nonEdge: 第一次需要 Post 才能正确获取 imBottom
SoftKeyboardWatcherPost: imBottom=611, statusBarTop=55, navigationBarBottom=96
SoftKeyboardWatcherPost: imeVisible=true, statusBarsVisible=true, navigationBarsVisible=true
SoftKeyboardWatcherPost: decorViewWindowVisibleDisplayFrame top=55, bottom=909, height=854, viewHeight=1520
```

##### hideIme:

```shell
SoftKeyboardWatcher: imBottom=0, statusBarTop=55, navigationBarBottom=96
SoftKeyboardWatcher: imeVisible=false, statusBarsVisible=true, navigationBarsVisible=true
SoftKeyboardWatcher: decorViewWindowVisibleDisplayFrame top=55, bottom=1424, height=1369, viewHeight=1520
SoftKeyboardWatcher: popupViewWindowVisibleDisplayFrame top=55, bottom=1424, height=1369, viewHeight=1369
SoftKeyboardWatcher: keyboardVisible = false, keyboardHeight = 0
```


#### Meizu Note3

Android 7 Log.d 无法输出

```shell
SoftKeyboardWatcher: widthPixels=1080, heightPixels=1920, screenRealHeight=1920
SoftKeyboardWatcher: heightPixels = screenRealHeight
```

##### start:

```shell
SoftKeyboardWatcher: imBottom=0, statusBarTop=66, navigationBarBottom=0
SoftKeyboardWatcher: imeVisible=false, statusBarsVisible=true, navigationBarsVisible=false
SoftKeyboardWatcher: decorViewWindowVisibleDisplayFrame top=66, bottom=1920, height=1854, viewHeight=1920
SoftKeyboardWatcher: popupViewWindowVisibleDisplayFrame top=66, bottom=1920, height=1854, viewHeight=1854
SoftKeyboardWatcher: keyboardVisible = false, keyboardHeight = 0

SoftKeyboardWatcher: imBottom=0, statusBarTop=66, navigationBarBottom=0
SoftKeyboardWatcher: imeVisible=false, statusBarsVisible=true, navigationBarsVisible=false
SoftKeyboardWatcher: decorViewWindowVisibleDisplayFrame top=66, bottom=1920, height=1854, viewHeight=1920
SoftKeyboardWatcher: popupViewWindowVisibleDisplayFrame top=66, bottom=1920, height=1854, viewHeight=1854
SoftKeyboardWatcher: keyboardVisible = false, keyboardHeight = 0
```

##### showIme:

```shell
SoftKeyboardWatcher: imBottom=0, statusBarTop=66, navigationBarBottom=0
SoftKeyboardWatcher: imeVisible=false, statusBarsVisible=true, navigationBarsVisible=false
SoftKeyboardWatcher: decorViewWindowVisibleDisplayFrame top=66, bottom=1920, height=1854, viewHeight=1920
SoftKeyboardWatcher: popupViewWindowVisibleDisplayFrame top=66, bottom=1161, height=1095, viewHeight=1095
SoftKeyboardWatcher: keyboardVisible = true, keyboardHeight = 759

VS nonEdge: post 仍然无法获取到 imBottom
SoftKeyboardWatcherPost: imBottom=0, statusBarTop=66, navigationBarBottom=0
SoftKeyboardWatcherPost: imeVisible=false, statusBarsVisible=true, navigationBarsVisible=false
SoftKeyboardWatcherPost: decorViewWindowVisibleDisplayFrame top=66, bottom=1920, height=1854, viewHeight=1920
```

##### hideIme:

```shell
SoftKeyboardWatcher: imBottom=0, statusBarTop=66, navigationBarBottom=0
SoftKeyboardWatcher: imeVisible=false, statusBarsVisible=true, navigationBarsVisible=false
SoftKeyboardWatcher: decorViewWindowVisibleDisplayFrame top=66, bottom=1920, height=1854, viewHeight=1920
SoftKeyboardWatcher: popupViewWindowVisibleDisplayFrame top=66, bottom=1920, height=1854, viewHeight=1854
SoftKeyboardWatcher: keyboardVisible = false, keyboardHeight = 0
```
