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

package io.weicools.daily.practice.asyncui

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
import androidx.savedstate.ViewTreeSavedStateRegistryOwner
import com.weicools.core.app.BaseActivity
import com.weicools.ktx.widget.dsl.frameLayout
import com.weicools.ktx.widget.dsl.textView
import com.weicools.ktx.widget.dsl.view
import com.weicools.ktx.widget.extensions.background_colorResource
import com.weicools.ktx.widget.extensions.dp
import com.weicools.ktx.widget.extensions.gravity_center
import com.weicools.ktx.widget.extensions.text_color
import com.weicools.ktx.widget.params.frameParams
import com.weicools.ktx.widget.params.matchParent
import io.weicools.daily.practice.R
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
              gravity = gravity_center
              text_color = Color.BLACK
              text = "AddView"
              setOnClickListener {
                Toast.makeText(activity, "CurrThread=${Thread.currentThread()}", Toast.LENGTH_SHORT).show()
                view {
                  layoutParams = frameParams(matchParent, 20.dp) {
                    topMargin = 4.dp
                  }
                  background_colorResource = R.color.colorPrimary
                }
              }
            }
          }
          Log.d(TAG, "handleMessage: addView")
          windowManager.addView(decorView, attributes)

          ViewTreeLifecycleOwner.set(decorView, activity)
          ViewTreeViewModelStoreOwner.set(decorView, activity)
          ViewTreeSavedStateRegistryOwner.set(decorView, activity)
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
  }
}