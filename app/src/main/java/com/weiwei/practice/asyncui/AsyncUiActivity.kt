/*
 * Copyright (c) 2020 Weicools
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

package com.weiwei.practice.asyncui

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.lifecycle.ViewTreeViewModelStoreOwner
import com.weiwei.core.app.BaseActivity
import com.weiwei.fluentview.ui.backgroundColorResource
import com.weiwei.fluentview.ui.gravityCenter
import com.weiwei.fluentview.ui.textColor
import com.weiwei.fluentview.ui.unit.dp
import com.weiwei.fluentview.view.WindowInsetsEdgeDelegate
import com.weiwei.fluentview.view.appcompat.textView
import com.weiwei.fluentview.view.frameLayout
import com.weiwei.fluentview.view.frameParams
import com.weiwei.fluentview.view.matchParent
import com.weiwei.fluentview.view.view
import com.weiwei.practice.R
import java.lang.ref.WeakReference

/**
 * @author weicools
 * @date 2021.07.26
 */
class AsyncUiActivity : BaseActivity() {
  private class AsyncUiHandler(a: AsyncUiActivity, looper: Looper) : Handler(looper) {
    val uiActivity: WeakReference<AsyncUiActivity> = WeakReference(a)

    override fun handleMessage(msg: Message) {
      val activity = uiActivity.get() ?: return
      when (msg.what) {
        INIT_VIEW -> {
          val window = activity.window
          val windowManager = activity.windowManager
          val attributes: WindowManager.LayoutParams = window.attributes
          attributes.type = WindowManager.LayoutParams.TYPE_BASE_APPLICATION
          // val attributes = WindowManager.LayoutParams()
          // attributes.width = WindowManager.LayoutParams.MATCH_PARENT
          // attributes.height = WindowManager.LayoutParams.MATCH_PARENT

          // val decorView = window.decorView
          val decorView = activity.frameLayout {
            textView {
              layoutParams = frameParams(100.dp, 100.dp) {}
              gravity = gravityCenter
              textColor = Color.BLACK
              text = "AddView"
              setOnClickListener {
                Toast.makeText(activity, "CurrThread=${Thread.currentThread()}", Toast.LENGTH_SHORT).show()
                view {
                  layoutParams = frameParams(matchParent, 20.dp) {
                    topMargin = 4.dp
                  }
                  backgroundColorResource = R.color.colorPrimary
                }
              }
            }
          }
          Log.d(TAG, "handleMessage: addView")
          windowManager.addView(decorView, attributes)

          ViewTreeLifecycleOwner.set(decorView, activity)
          ViewTreeViewModelStoreOwner.set(decorView, activity)
          // ViewTreeSavedStateRegistryOwner.set(decorView, activity)
        }
      }
    }
  }

  companion object {
    private const val INIT_VIEW = 0
    private const val TAG = "AsyncUI"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val uiHandler = AsyncUiHandler(this, HandlerThread("Async-UI").also { it.start() }.looper)
    // uiHandler.sendEmptyMessageDelayed(INIT_VIEW, 1000L)
    uiHandler.sendEmptyMessage(INIT_VIEW)

    WindowInsetsEdgeDelegate(this).start()
  }
}