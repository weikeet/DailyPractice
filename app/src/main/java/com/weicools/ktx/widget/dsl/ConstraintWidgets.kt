package com.weicools.ktx.widget.dsl

import android.content.Context
import android.view.ViewGroup
import androidx.constraintlayout.helper.widget.Flow
import androidx.constraintlayout.helper.widget.Layer
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Guideline
import com.weicools.ktx.widget.params.constraintParams

/**
 * @author weicools
 * @date 2020.05.14
 */

//region constraintLayout
inline fun ViewGroup.constraintLayout(id: Int? = null, style: Int? = null, init: ConstraintLayout.() -> Unit): ConstraintLayout {
  val widget = ConstraintLayout(wrapContextIfNeeded(context, style))
  if (id != null) widget.id = id
  return widget.apply(init).also { addView(it) }
}

inline fun Context.constraintLayout(id: Int? = null, style: Int? = null, init: ConstraintLayout.() -> Unit): ConstraintLayout {
  val widget = ConstraintLayout(wrapContextIfNeeded(this, style))
  if (id != null) widget.id = id
  return widget.apply(init)
}

inline fun ConstraintLayout.guideline(id: Int, orientationValue: Int, init: Guideline.() -> Unit): Guideline {
  val guideline = Guideline(context)
  guideline.id = id
  guideline.layoutParams = constraintParams { orientation = orientationValue }
  return guideline.apply(init).also { addView(it) }
}

inline fun ConstraintLayout.flow(id: Int? = null, init: Flow.() -> Unit): Flow {
  val widget = Flow(context)
  if (id != null) widget.id = id
  return widget.apply(init).also { addView(it) }
}

inline fun ConstraintLayout.layer(id: Int? = null, init: Layer.() -> Unit): Layer {
  val widget = Layer(context)
  if (id != null) widget.id = id
  return widget.apply(init).also { addView(it) }
}
//endregion
