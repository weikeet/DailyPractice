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

package com.weiwei.practice.keyboard

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginBottom
import androidx.core.view.updateLayoutParams
import com.weiwei.fluent.widget.extensions.dp
import com.weiwei.practice.R
import com.weiwei.practice.window.delegate.EdgeInsetDelegate
import com.weiwei.practice.window.doOnApplyWindowInsets
import com.weiwei.practice.window.navigationBarBottom
import com.weiwei.practice.window.recordInitialMargin

/**
 * @author weiwei
 * @date 2022.08.14
 */
class KeyboardActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // set activity manifest: android:windowSoftInputMode="adjustNothing"
    // set theme: <item name="android:windowSoftInputMode">adjustNothing</item>
    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)

    setContentView(R.layout.activity_keyboard)

    EdgeInsetDelegate(this).start()

    val btnSend: Button = findViewById(R.id.btnSend)
    val editText: EditText = findViewById(R.id.editText)

    btnSend.setOnClickListener {
      EdgeInsetDelegate(this).start()
    }

    val btnMarginRect = btnSend.recordInitialMargin()
    btnSend.doOnApplyWindowInsets { windowInsets ->
      val navigationBarBottom = windowInsets.navigationBarBottom
      btnSend.updateLayoutParams<ViewGroup.MarginLayoutParams> { bottomMargin = navigationBarBottom + btnMarginRect.bottom }
      editText.translationY = -navigationBarBottom.toFloat()
    }

    SoftKeyboardWatcher(this, this) { imeVisible, imeHeight, navigationBarsHeight ->
      Log.d("KeyboardActivity", "imeVisible=$imeVisible, imeHeight=$imeHeight, navigationBarsHeight=$navigationBarsHeight")
      if (imeHeight > 0) {
        val translation = imeHeight + 16f.dp - btnSend.marginBottom
        btnSend.animate().translationY(-translation).setDuration(120L).start()
      } else {
        btnSend.animate().translationY(0f).setDuration(120L).start()
      }
    }

    SoftKeyboardPrinter.start(this, window.decorView)
  }
}