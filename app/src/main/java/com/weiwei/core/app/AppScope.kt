/*
 * Copyright (c) 2020 Weiwei
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *    https://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.weiwei.core.app

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.core.os.HandlerCompat
import com.weiwei.practice.app.PracticeApp

/**
 * @author weiwei
 * @date 2022.08.08
 */

val mainHandler = HandlerCompat.createAsync(Looper.getMainLooper())

val mainSyncHandler = Handler(Looper.getMainLooper())

inline val isMainThread: Boolean get() = (Looper.getMainLooper().thread === Thread.currentThread())

inline val mainContext: Context get() = PracticeApp.instance.applicationContext

inline val mainApplication: Application get() = PracticeApp.instance
