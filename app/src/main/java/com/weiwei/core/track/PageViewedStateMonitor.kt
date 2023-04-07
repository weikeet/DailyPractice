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

package com.weiwei.core.track

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import timber.log.Timber

/**
 * @author weiwei
 * @date 2023.03.30
 *
 * Page 曝光难点分析 https://juejin.cn/post/7165428849197940749
 * View 可见性检测 https://juejin.cn/post/7165427955902971918
 * 列表项可见性检测 https://juejin.cn/post/7165428399282847757
 */
class PageViewedStateMonitor : Application.ActivityLifecycleCallbacks {

  var isAppForeground = false
    private set

  private var activityReferences = 0
  private var isActivityChangingConfigurations = false

  // 页面可见性变化回调
  var onPageViewedStateChange: ((page: Fragment, isVisible: Boolean) -> Unit)? = null

  private val fragmentLifecycleCallbacks by lazy(LazyThreadSafetyMode.NONE) {
    object : FragmentManager.FragmentLifecycleCallbacks() {
      override fun onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?) {
        // 注册 Fragment 根视图可见性监听器
        if (f is PageViewedTracker) {
          checkVisibilityChange(targetView = v, enableViewScrollDetect = false) { _, isVisible ->
            Timber.d("onFragmentViewCreated: isVisible=$isVisible f=$f")
            onPageViewedStateChange?.invoke(f, isVisible)
          }
        }
      }
    }
  }

  override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
    // 注册 Fragment 生命周期监听器
    (activity as? FragmentActivity)?.supportFragmentManager?.registerFragmentLifecycleCallbacks(fragmentLifecycleCallbacks, true)
  }

  override fun onActivityStarted(activity: Activity) {
    if (++activityReferences == 1 && !isActivityChangingConfigurations) {
      isAppForeground = true
    }
    Timber.d("onActivityStarted: activity=$activity isAppForeground=$isAppForeground")
  }

  override fun onActivityResumed(activity: Activity) {
  }

  override fun onActivityPaused(activity: Activity) {
  }

  override fun onActivityStopped(activity: Activity) {
    isActivityChangingConfigurations = activity.isChangingConfigurations
    if (--activityReferences == 0 && !isActivityChangingConfigurations) {
      isAppForeground = false
    }
    Timber.d("onActivityStopped: activity=$activity isAppForeground=$isAppForeground")
  }

  override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
  }

  override fun onActivityDestroyed(activity: Activity) {
    // 注销 Fragment 生命周期监听器
    (activity as? FragmentActivity)?.supportFragmentManager?.unregisterFragmentLifecycleCallbacks(fragmentLifecycleCallbacks)
  }
}
