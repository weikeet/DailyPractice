package io.weicools.daily.practice.lifecycle.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import io.weicools.daily.practice.R
import io.weicools.daily.practice.ktx.convertVisibility
import io.weicools.daily.practice.lifecycle.LifeView

/**
 * @author weicools
 * @date 2020.05.14
 */
class LifecycleView1 @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LifeView(context, attrs, defStyleAttr)