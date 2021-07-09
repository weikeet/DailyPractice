package com.weicools.ktx

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.R)
fun atLeastR(): Boolean = Build.VERSION.SDK_INT >= 30

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.R)
fun atLeastP(): Boolean = Build.VERSION.SDK_INT >= 28

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.P)
fun atLeastOMR1(): Boolean = Build.VERSION.SDK_INT >= 27

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.O)
fun atLeastO(): Boolean = Build.VERSION.SDK_INT >= 26

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.N_MR1)
fun atLeastNMR1(): Boolean = Build.VERSION.SDK_INT >= 25

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.N)
fun atLeastN(): Boolean = Build.VERSION.SDK_INT >= 24

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.M)
fun atLeastM(): Boolean = Build.VERSION.SDK_INT >= 23

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.LOLLIPOP_MR1)
fun atLeastLMR1(): Boolean = Build.VERSION.SDK_INT >= 22

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.LOLLIPOP)
fun atLeastL(): Boolean = Build.VERSION.SDK_INT >= 21

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.KITKAT)
fun atLeastK(): Boolean = Build.VERSION.SDK_INT >= 19

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
fun atLeastJMR2(): Boolean = Build.VERSION.SDK_INT >= 18

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
fun atLeastJMR1(): Boolean = Build.VERSION.SDK_INT >= 17

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.JELLY_BEAN)
fun atLeastJ(): Boolean = Build.VERSION.SDK_INT >= 16
