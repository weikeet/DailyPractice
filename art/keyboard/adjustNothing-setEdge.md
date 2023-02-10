
popupViewHeight = decorViewHeight - statusBarHeight - navigationBarHeight
keyboardHeight = decorViewHeight - popupFrameHeight - statusBarHeight

Xiaomi6 响应及时流畅，息屏后亮屏无法收到回调
LGStylo6 响应稍慢，FooView 会影响弹出
Redmi8A 响应及时流畅，FooView 会影响弹出
MeizuNote3 响应及时流畅

#### Android 5.0+ ViewTree

##### setDecorFitsSystemWindows(window, false)

WindowCompat.setDecorFitsSystemWindows(activity.window, false)

```shell
contentHeight = FrameLayoutHeight
screenRealHeight = decorViewHeight = contentHeight

DecorView
├── LinearLayout
│   ├── action_mode_bar_stub - ViewStub
│   ├── FrameLayout
│   │   ├── action_bar_root - FitWindowsLinearLayout
│   │   │   ├── action_mode_bar_stub - ViewStubCompat
│   │   │   ├── content - ContentFrameLayout
```

##### default

```shell
contentHeight = FrameLayoutHeight
screenRealHeight = decorViewHeight = contentHeight + navigationBarHeight + statusBarHeight

DecorView
├── LinearLayout
│   ├── action_mode_bar_stub - ViewStub
│   ├── FrameLayout
│   │   ├── action_bar_root - FitWindowsLinearLayout
│   │   │   ├── action_mode_bar_stub - ViewStubCompat
│   │   │   ├── content - ContentFrameLayout
├── navigationBarBackground - View (有虚拟导航栏时)
├── statusBarBackground - View
```


#### Honor7A

EMUI, Android 8.0

```shell
SoftKeyboardWatcher: widthPixels=720, heightPixels=1358, screenRealHeight=1440
SoftKeyboardWatcher: heightPixels = screenRealHeight - navigationBarBottom
SoftKeyboardWatcher: imBottom=0, statusBarTop=48, navigationBarBottom=82

contentHeight=(1440 - 48 - 82) = 1310

ratio=1310/720 = 1.819
```


#### Xiaomi6

原生, Android 12
手势导航，无刘海

```shell
SoftKeyboardWatcher: widthPixels=1080, heightPixels=1872, screenRealHeight=1920
SoftKeyboardWatcher: heightPixels = screenRealHeight - navigationBarBottom
SoftKeyboardWatcher: imBottom=0, statusBarTop=72, navigationBarBottom=48

contentHeight=(1920 - 48 - 72)=1800

ratio=1800/1080 = 1.67
```

原生, Android 13
手势导航，无刘海

```shell
SoftKeyboardWatcher: widthPixels=1080, heightPixels=1854, screenRealHeight=1920
SoftKeyboardWatcher: heightPixels = screenRealHeight - navigationBarBottom
SoftKeyboardWatcher: imBottom=0, statusBarTop=72, navigationBarBottom=66

contentHeight=(1920 - 66 - 72)=1782

ratio=1782/1080 = 1.65
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
```

##### showIme:

```shell
SoftKeyboardWatcher: imBottom=962, statusBarTop=72, navigationBarBottom=48
SoftKeyboardWatcher: imeVisible=true, statusBarsVisible=true, navigationBarsVisible=true
SoftKeyboardWatcher: decorViewWindowVisibleDisplayFrame top=72, bottom=1872, height=1800, viewHeight=1920
SoftKeyboardWatcher: popupViewWindowVisibleDisplayFrame top=72, bottom=1872, height=1800, viewHeight=1800
SoftKeyboardWatcher: keyboardVisible = false, keyboardHeight = 0

SoftKeyboardWatcherPost: imBottom=962, statusBarTop=72, navigationBarBottom=48
SoftKeyboardWatcherPost: imeVisible=true, statusBarsVisible=true, navigationBarsVisible=true
SoftKeyboardWatcherPost: decorViewWindowVisibleDisplayFrame top=72, bottom=1872, height=1800, viewHeight=1920

SoftKeyboardWatcher: imBottom=962, statusBarTop=72, navigationBarBottom=48
SoftKeyboardWatcher: imeVisible=true, statusBarsVisible=true, navigationBarsVisible=true
SoftKeyboardWatcher: decorViewWindowVisibleDisplayFrame top=72, bottom=1872, height=1800, viewHeight=1920
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
手势导航，有刘海

```shell
SoftKeyboardWatcher: widthPixels=1080, heightPixels=2316, screenRealHeight=2460
SoftKeyboardWatcher: heightPixels = screenRealHeight - navigationBarBottom - statusBarTop
SoftKeyboardWatcher: imBottom=0, statusBarTop=102, navigationBarBottom=42

contentHeight=(2460 - 102 - 42)=2316

ratio=2316/1080 = 2.14
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
SoftKeyboardWatcher: imBottom=0, statusBarTop=102, navigationBarBottom=42
SoftKeyboardWatcher: imeVisible=false, statusBarsVisible=true, navigationBarsVisible=true
SoftKeyboardWatcher: decorViewWindowVisibleDisplayFrame top=102, bottom=2418, height=2316, viewHeight=2460
SoftKeyboardWatcher: popupViewWindowVisibleDisplayFrame top=102, bottom=1529, height=1427, viewHeight=1427
SoftKeyboardWatcher: keyboardVisible = true, keyboardHeight = 931

SoftKeyboardWatcherPost: imBottom=0, statusBarTop=102, navigationBarBottom=42
SoftKeyboardWatcherPost: imeVisible=false, statusBarsVisible=true, navigationBarsVisible=true
SoftKeyboardWatcherPost: decorViewWindowVisibleDisplayFrame top=102, bottom=2418, height=2316, viewHeight=2460

SoftKeyboardWatcher: imBottom=0, statusBarTop=102, navigationBarBottom=42
SoftKeyboardWatcher: imeVisible=false, statusBarsVisible=true, navigationBarsVisible=true
SoftKeyboardWatcher: decorViewWindowVisibleDisplayFrame top=102, bottom=2418, height=2316, viewHeight=2460
SoftKeyboardWatcher: popupViewWindowVisibleDisplayFrame top=102, bottom=1529, height=1427, viewHeight=1427
SoftKeyboardWatcher: keyboardVisible = true, keyboardHeight = 931

SoftKeyboardWatcherPost: imBottom=0, statusBarTop=102, navigationBarBottom=42
SoftKeyboardWatcherPost: imeVisible=false, statusBarsVisible=true, navigationBarsVisible=true
SoftKeyboardWatcherPost: decorViewWindowVisibleDisplayFrame top=102, bottom=2418, height=2316, viewHeight=2460
```

##### hideIme:

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


#### Redmi 8A

MIUI 11, Android 9
虚拟按键导航，有刘海

```shell
SoftKeyboardWatcher: widthPixels=720, heightPixels=1369, screenRealHeight=1520
SoftKeyboardWatcher: heightPixels = screenRealHeight - navigationBarBottom - statusBarTop
SoftKeyboardWatcher: imBottom=0, statusBarTop=55, navigationBarBottom=96

contentHeight=(2460 - 55 - 96)=1369

ratio=1369/720 = 1.90
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

SoftKeyboardWatcherPost: imBottom=0, statusBarTop=55, navigationBarBottom=96
SoftKeyboardWatcherPost: imeVisible=false, statusBarsVisible=true, navigationBarsVisible=true
SoftKeyboardWatcherPost: decorViewWindowVisibleDisplayFrame top=55, bottom=1424, height=1369, viewHeight=1520
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

Flyme 7, Android 7
物理按键导航，无刘海，Log.d 无法输出

```shell
SoftKeyboardWatcher: widthPixels=1080, heightPixels=1920, screenRealHeight=1920
SoftKeyboardWatcher: heightPixels = screenRealHeight - navigationBarBottom (0)
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
