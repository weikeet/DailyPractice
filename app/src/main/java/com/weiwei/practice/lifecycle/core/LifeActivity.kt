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

package com.weiwei.practice.lifecycle.core

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnPreDraw
import com.weiwei.fluentview.view.WindowInsetsEdgeDelegate

/**
 * @author weicools
 * @date 2021.07.05
 */
open class LifeActivity : AppCompatActivity() {
  class LifeActivityLog {
    var enable = true
    var enableAttach = true
    var enablePreDraw = true
    var enableWindowFocus = true
    var enableInstanceState = true
    var enableInstanceStatePersistable = true
  }

  private val TAG = javaClass.simpleName

  private val activityLog = LifeActivityLog()

  open fun setupLifeConfig(config: LifeActivityLog) {
  }

  private fun loggerInner(tag: String, msg: String) {
    if (activityLog.enable) {
      logger(tag, msg)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setupLifeConfig(activityLog)

    WindowInsetsEdgeDelegate(this)
      .setNavigationBarColor(0x20000000)
      .start()

    loggerInner(TAG, "onCreate: savedState=$savedInstanceState")
    val contentView: View = findViewById(android.R.id.content)
    contentView.doOnPreDraw {
      if (activityLog.enablePreDraw) {
        loggerInner(TAG, "doOnPreDraw: ")
      }
    }
  }

  override fun onNewIntent(intent: Intent?) {
    super.onNewIntent(intent)
    loggerInner(TAG, "onNewIntent: newIntent=$intent, oldIntent=${getIntent()}")
  }

  override fun onRestart() {
    super.onRestart()
    loggerInner(TAG, "onRestart: ")
  }

  override fun onStart() {
    super.onStart()
    loggerInner(TAG, "onStart: ")
  }

  override fun onResume() {
    super.onResume()
    loggerInner(TAG, "onResume: ")
  }

  override fun onPause() {
    super.onPause()
    loggerInner(TAG, "onPause: ")
  }

  override fun onStop() {
    super.onStop()
    loggerInner(TAG, "onStop: ")
  }

  override fun onDestroy() {
    super.onDestroy()
    loggerInner(TAG, "onDestroy: ")
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    if (activityLog.enableAttach) {
      loggerInner(TAG, "onAttachedToWindow: ")
    }
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    if (activityLog.enableAttach) {
      loggerInner(TAG, "onDetachedFromWindow: ")
    }
  }

  override fun onWindowFocusChanged(hasFocus: Boolean) {
    super.onWindowFocusChanged(hasFocus)
    if (activityLog.enableWindowFocus) {
      loggerInner(TAG, "onWindowFocusChanged: ")
    }
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    if (activityLog.enableInstanceState) {
      loggerInner(TAG, "onSaveInstanceState: outState=$outState")
    }
  }

  override fun onRestoreInstanceState(savedInstanceState: Bundle) {
    super.onRestoreInstanceState(savedInstanceState)
    if (activityLog.enableInstanceState) {
      loggerInner(TAG, "onRestoreInstanceState: savedState=$savedInstanceState")
    }
  }

  override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
    super.onSaveInstanceState(outState, outPersistentState)
    if (activityLog.enableInstanceStatePersistable) {
      loggerInner(TAG, "onSaveInstanceState: outState=$outState, outPersistentState=$outPersistentState")
    }
  }

  override fun onRestoreInstanceState(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
    super.onRestoreInstanceState(savedInstanceState, persistentState)
    if (activityLog.enableInstanceStatePersistable) {
      loggerInner(TAG, "onRestoreInstanceState: savedState=$savedInstanceState, persistentState=$persistentState")
    }
  }
}