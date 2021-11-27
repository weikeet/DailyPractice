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

package io.weicools.daily.practice.binder

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.weicools.fluent.widget.dsl.imageView
import com.weicools.fluent.widget.dsl.linearLayout
import com.weicools.fluent.widget.extensions.image_bitmap
import com.weicools.fluent.widget.extensions.image_resource
import com.weicools.fluent.widget.params.linearParams
import io.weicools.daily.practice.TransactLargeService
import io.weicools.daily.practice.R

/**
 * @author weiwei
 * @date 2021.11.27
 *
 * Android跨进程传大图思考及实现——附上原理分析 https://juejin.cn/post/7011276367308750879
 */
class TransactionTooLargeActivity : AppCompatActivity() {
  companion object {
    private const val EXTRA_BITMAP = "EXTRA_BITMAP"

    fun start(context: Context, bitmap: Bitmap) {
      val intent = Intent(context, TransactionTooLargeActivity::class.java)
      // intent.putExtra(EXTRA_BITMAP, bitmap)
      // intent.putExtra(EXTRA_BITMAP, Bundle().apply { putParcelable(EXTRA_BITMAP, bitmap) })
      intent.putExtra(EXTRA_BITMAP, Bundle().apply {
        putBinder(EXTRA_BITMAP, object : TransactLargeService.Stub() {
          override fun getLargeBitmap(): Bitmap = bitmap
        })
      })
      context.startActivity(intent)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val iv = imageView {
      layoutParams = linearParams {
      }
      image_resource = R.drawable.ic_share_white_24dp
    }
    setContentView(linearLayout {
      orientation = LinearLayout.VERTICAL

      addView(iv)
    })

    iv.postDelayed({
      // val b = intent.getParcelableExtra<Bitmap>(EXTRA_BITMAP)
      val b = intent.getParcelableExtra<Bundle>(EXTRA_BITMAP)
      b?.let {
        // iv.image_bitmap = it

        val binder = it.getBinder(EXTRA_BITMAP)
        val bitmapService = TransactLargeService.Stub.asInterface(binder)
        iv.image_bitmap = bitmapService.largeBitmap
      }
    }, 2000L)
  }
}