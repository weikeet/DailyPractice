
### Xiaomi6

初始

```shell
$ adb shell dumpsys window|grep WindowStateAnimator
      Window #0: WindowStateAnimator{2e9fe38 com.android.systemui.ImageWallpaper}
      Window #1: WindowStateAnimator{667e11 com.android.launcher3/com.android.launcher3.CustomLauncher}
      Window #2: WindowStateAnimator{8330b76 com.lzf.easyfloat.example/com.lzf.easyfloat.example.activity.MainActivity}
      Window #3: WindowStateAnimator{e7e0577 com.weiwei.practice/com.weiwei.practice.ui.main.MainActivity}
      Window #4: WindowStateAnimator{b21869a com.weiwei.practice/com.weiwei.practice.keyboard.KeyboardActivity}
      Window #5: WindowStateAnimator{9656bcb PopupWindow:6ba9495}
      Window #6: WindowStateAnimator{d3c6902 InputMethod}
      Window #7: WindowStateAnimator{4438213 ShellDropTarget}
      Window #8: WindowStateAnimator{125c50 StatusBar}
      Window #9: WindowStateAnimator{5d1de49 NotificationShade}
      Window #10: WindowStateAnimator{18c134e NavigationBar0}
      Window #11: WindowStateAnimator{2da2c6f pip-dismiss-overlay}
      Window #12: WindowStateAnimator{d48ed7c SecondaryHomeHandle0}
      Window #13: WindowStateAnimator{a511605 EdgeBackGestureHandler0}
```

弹出无变化
收起无变化

息屏亮屏解锁
Window #5: WindowStateAnimator{9e5db15 PopupWindow:6ba9495} 变了

```shell
$ adb shell dumpsys window|grep WindowStateAnimator
      Window #0: WindowStateAnimator{2e9fe38 com.android.systemui.ImageWallpaper}
      Window #1: WindowStateAnimator{667e11 com.android.launcher3/com.android.launcher3.CustomLauncher}
      Window #2: WindowStateAnimator{8330b76 com.lzf.easyfloat.example/com.lzf.easyfloat.example.activity.MainActivity}
      Window #3: WindowStateAnimator{e7e0577 com.weiwei.practice/com.weiwei.practice.ui.main.MainActivity}
      Window #4: WindowStateAnimator{b21869a com.weiwei.practice/com.weiwei.practice.keyboard.KeyboardActivity}
      Window #5: WindowStateAnimator{9e5db15 PopupWindow:6ba9495}
      Window #6: WindowStateAnimator{d3c6902 InputMethod}
      Window #7: WindowStateAnimator{4438213 ShellDropTarget}
      Window #8: WindowStateAnimator{125c50 StatusBar}
      Window #9: WindowStateAnimator{5d1de49 NotificationShade}
      Window #10: WindowStateAnimator{18c134e NavigationBar0}
      Window #11: WindowStateAnimator{2da2c6f pip-dismiss-overlay}
      Window #12: WindowStateAnimator{d48ed7c SecondaryHomeHandle0}
      Window #13: WindowStateAnimator{a511605 EdgeBackGestureHandler0}
```

### Redmi 8A

初始

```shell
$ adb shell dumpsys window|grep WindowStateAnimator
      Window #0: WindowStateAnimator{f517764 com.android.systemui.ImageWallpaper}
      Window #1: WindowStateAnimator{3687dcd com.android.systemui/com.android.systemui.recents.RecentsActivity}
      Window #2: WindowStateAnimator{3603682 com.miui.home/com.miui.home.launcher.Launcher}
      Window #3: WindowStateAnimator{b1f0193 com.weiwei.practice/com.weiwei.practice.ui.main.MainActivity}
      Window #4: WindowStateAnimator{a341131 com.weiwei.practice/com.weiwei.practice.keyboard.KeyboardActivity}
      Window #5: WindowStateAnimator{24e7016 PopupWindow:695fa0e}
      Window #6: WindowStateAnimator{ddf38ce DockedStackDivider}
      Window #7: WindowStateAnimator{8ae23ef AssistPreviewPanel}
      Window #8: WindowStateAnimator{af24efc Aspect}
      Window #9: WindowStateAnimator{249985 RoundCorner}
      Window #10: WindowStateAnimator{83a13da StatusBar}
      Window #11: WindowStateAnimator{763100b NavigationBar}
      Window #12: WindowStateAnimator{7935ee8 RoundCorner}
      Window #13: WindowStateAnimator{1a4901 InputMethod}
```

弹出
{1a4901 InputMethod} 从最后移到 {24e7016 PopupWindow:695fa0e} 下
新增 {1530d52 PopupWindow:1336295}

```shell
$ adb shell dumpsys window|grep WindowStateAnimator
      Window #0: WindowStateAnimator{f517764 com.android.systemui.ImageWallpaper}
      Window #1: WindowStateAnimator{3687dcd com.android.systemui/com.android.systemui.recents.RecentsActivity}
      Window #2: WindowStateAnimator{3603682 com.miui.home/com.miui.home.launcher.Launcher}
      Window #3: WindowStateAnimator{b1f0193 com.weiwei.practice/com.weiwei.practice.ui.main.MainActivity}
      Window #4: WindowStateAnimator{a341131 com.weiwei.practice/com.weiwei.practice.keyboard.KeyboardActivity}
      Window #5: WindowStateAnimator{24e7016 PopupWindow:695fa0e}
      Window #6: WindowStateAnimator{1a4901 InputMethod}
      Window #7: WindowStateAnimator{1530d52 PopupWindow:1336295}
      Window #8: WindowStateAnimator{ddf38ce DockedStackDivider}
      Window #9: WindowStateAnimator{8ae23ef AssistPreviewPanel}
      Window #10: WindowStateAnimator{af24efc Aspect}
      Window #11: WindowStateAnimator{249985 RoundCorner}
      Window #12: WindowStateAnimator{83a13da StatusBar}
      Window #13: WindowStateAnimator{763100b NavigationBar}
      Window #14: WindowStateAnimator{7935ee8 RoundCorner}
```

收起 变为初始状态

息屏亮屏解锁 没有变化


##### 打开 FooView

初始

```shell
$ adb shell dumpsys window|grep WindowStateAnimator
      Window #0: WindowStateAnimator{f517764 com.android.systemui.ImageWallpaper}
      Window #1: WindowStateAnimator{3687dcd com.android.systemui/com.android.systemui.recents.RecentsActivity}
      Window #2: WindowStateAnimator{3603682 com.miui.home/com.miui.home.launcher.Launcher}
      Window #3: WindowStateAnimator{b1f0193 com.weiwei.practice/com.weiwei.practice.ui.main.MainActivity}
      Window #4: WindowStateAnimator{d65e2f4 com.weiwei.practice/com.weiwei.practice.keyboard.KeyboardActivity}
      Window #5: WindowStateAnimator{c402a1d PopupWindow:12f30f3}
      Window #6: WindowStateAnimator{ddf38ce DockedStackDivider}
      Window #7: WindowStateAnimator{8ae23ef AssistPreviewPanel}
      Window #8: WindowStateAnimator{af24efc Aspect}
      Window #9: WindowStateAnimator{394f492 }
      Window #10: WindowStateAnimator{3dcce63 }
      Window #11: WindowStateAnimator{f783a60 }
      Window #12: WindowStateAnimator{e3a4b19 }
      Window #13: WindowStateAnimator{9c7de }
      Window #14: WindowStateAnimator{2c3edbf }
      Window #15: WindowStateAnimator{249985 RoundCorner}
      Window #16: WindowStateAnimator{83a13da StatusBar}
      Window #17: WindowStateAnimator{763100b NavigationBar}
      Window #18: WindowStateAnimator{7935ee8 RoundCorner}
      Window #19: WindowStateAnimator{1a4901 InputMethod}
```

弹出
{1a4901 InputMethod} 从最后移到 {3dcce63 } 下
新增 {9a8d3e PopupWindow:1336295}

```shell
$ adb shell dumpsys window|grep WindowStateAnimator
      Window #0: WindowStateAnimator{f517764 com.android.systemui.ImageWallpaper}
      Window #1: WindowStateAnimator{3687dcd com.android.systemui/com.android.systemui.recents.RecentsActivity}
      Window #2: WindowStateAnimator{3603682 com.miui.home/com.miui.home.launcher.Launcher}
      Window #3: WindowStateAnimator{b1f0193 com.weiwei.practice/com.weiwei.practice.ui.main.MainActivity}
      Window #4: WindowStateAnimator{d65e2f4 com.weiwei.practice/com.weiwei.practice.keyboard.KeyboardActivity}
      Window #5: WindowStateAnimator{c402a1d PopupWindow:12f30f3}
      Window #6: WindowStateAnimator{ddf38ce DockedStackDivider}
      Window #7: WindowStateAnimator{8ae23ef AssistPreviewPanel}
      Window #8: WindowStateAnimator{af24efc Aspect}
      Window #9: WindowStateAnimator{394f492 }
      Window #10: WindowStateAnimator{3dcce63 }
      Window #11: WindowStateAnimator{1a4901 InputMethod}
      Window #12: WindowStateAnimator{9a8d3e PopupWindow:1336295}
      Window #13: WindowStateAnimator{f783a60 }
      Window #14: WindowStateAnimator{e3a4b19 }
      Window #15: WindowStateAnimator{9c7de }
      Window #16: WindowStateAnimator{2c3edbf }
      Window #17: WindowStateAnimator{249985 RoundCorner}
      Window #18: WindowStateAnimator{83a13da StatusBar}
      Window #19: WindowStateAnimator{763100b NavigationBar}
      Window #20: WindowStateAnimator{7935ee8 RoundCorner}
```

收起 变为初始状态
息屏亮屏解锁 没有变化

##### 提高 Window type

初始

```shell
$ adb shell dumpsys window|grep WindowStateAnimator
      Window #0: WindowStateAnimator{f517764 com.android.systemui.ImageWallpaper}
      Window #1: WindowStateAnimator{3687dcd com.android.systemui/com.android.systemui.recents.RecentsActivity}
      Window #2: WindowStateAnimator{3603682 com.miui.home/com.miui.home.launcher.Launcher}
      Window #3: WindowStateAnimator{71c322a com.weiwei.practice/com.weiwei.practice.ui.main.MainActivity}
      Window #4: WindowStateAnimator{ed69096 com.weiwei.practice/com.weiwei.practice.keyboard.KeyboardActivity}
      Window #5: WindowStateAnimator{b6d7417 PopupWindow:743e8c0}
      Window #6: WindowStateAnimator{ddf38ce DockedStackDivider}
      Window #7: WindowStateAnimator{8ae23ef AssistPreviewPanel}
      Window #8: WindowStateAnimator{af24efc Aspect}
      Window #9: WindowStateAnimator{14c7791 }
      Window #10: WindowStateAnimator{52346f6 }
      Window #11: WindowStateAnimator{7e85af7 }
      Window #12: WindowStateAnimator{bb02d64 }
      Window #13: WindowStateAnimator{72ffbcd }
      Window #14: WindowStateAnimator{bb4dc82 }
      Window #15: WindowStateAnimator{249985 RoundCorner}
      Window #16: WindowStateAnimator{83a13da StatusBar}
      Window #17: WindowStateAnimator{763100b NavigationBar}
      Window #18: WindowStateAnimator{7935ee8 RoundCorner}
      Window #19: WindowStateAnimator{1a4901 InputMethod}
```

弹出
{1a4901 InputMethod} 从最后移到 {52346f6 }下
新增 {d597d1e PopupWindow:1336295}

```shell
$ adb shell dumpsys window|grep WindowStateAnimator
      Window #0: WindowStateAnimator{f517764 com.android.systemui.ImageWallpaper}
      Window #1: WindowStateAnimator{3687dcd com.android.systemui/com.android.systemui.recents.RecentsActivity}
      Window #2: WindowStateAnimator{3603682 com.miui.home/com.miui.home.launcher.Launcher}
      Window #3: WindowStateAnimator{71c322a com.weiwei.practice/com.weiwei.practice.ui.main.MainActivity}
      Window #4: WindowStateAnimator{ed69096 com.weiwei.practice/com.weiwei.practice.keyboard.KeyboardActivity}
      Window #5: WindowStateAnimator{b6d7417 PopupWindow:743e8c0}
      Window #6: WindowStateAnimator{ddf38ce DockedStackDivider}
      Window #7: WindowStateAnimator{8ae23ef AssistPreviewPanel}
      Window #8: WindowStateAnimator{af24efc Aspect}
      Window #9: WindowStateAnimator{14c7791 }
      Window #10: WindowStateAnimator{52346f6 }
      Window #11: WindowStateAnimator{1a4901 InputMethod}
      Window #12: WindowStateAnimator{d597d1e PopupWindow:1336295}
      Window #13: WindowStateAnimator{7e85af7 }
      Window #14: WindowStateAnimator{bb02d64 }
      Window #15: WindowStateAnimator{72ffbcd }
      Window #16: WindowStateAnimator{bb4dc82 }
      Window #17: WindowStateAnimator{249985 RoundCorner}
      Window #18: WindowStateAnimator{83a13da StatusBar}
      Window #19: WindowStateAnimator{763100b NavigationBar}
      Window #20: WindowStateAnimator{7935ee8 RoundCorner}
```

收起 变为初始状态
息屏亮屏解锁 没有变化
