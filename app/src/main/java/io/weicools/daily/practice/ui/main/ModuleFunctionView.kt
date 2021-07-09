package io.weicools.daily.practice.ui.main

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import com.weicools.ktx.widget.dsl.imageView
import com.weicools.ktx.widget.dsl.textView
import com.weicools.ktx.widget.extensions.dp
import com.weicools.ktx.widget.extensions.text_colorResource
import com.weicools.ktx.widget.params.center_VerticalOf
import com.weicools.ktx.widget.params.chainPacked
import com.weicools.ktx.widget.params.constraintParams
import com.weicools.ktx.widget.params.parentId
import io.weicools.daily.practice.R
import io.weicools.daily.practice.ui.main.data.ModuleFunction

/**
 * @author Weicools
 *
 * @date 2021.07.10
 */
class ModuleFunctionView(context: Context) : ConstraintLayout(context) {
  private val iconId = generateViewId()
  private val arrowId = generateViewId()
  private val titleId = generateViewId()
  private val descriptionId = generateViewId()

  val iconView = imageView {
    layoutParams = constraintParams(40.dp, 40.dp) {
      leftToLeft = parentId
      center_VerticalOf = parentId
      leftMargin = 8.dp
    }
  }
  val arrowView = imageView {
    layoutParams = constraintParams(40.dp, 40.dp) {
      rightToRight = parentId
      center_VerticalOf = parentId
      rightMargin = 8.dp
    }
  }
  val titleView = textView {
    layoutParams = constraintParams(40.dp, 40.dp) {
      leftToRight = iconId
      rightToLeft = arrowId
      topToTop = parentId
      bottomToTop = descriptionId
      verticalChainStyle = chainPacked
      leftMargin = 8.dp
      rightMargin = 8.dp
    }
    textSize = 16f
    text_colorResource = R.color.md_grey900
  }
  val descriptionView = textView {
    layoutParams = constraintParams(40.dp, 40.dp) {
      leftToRight = iconId
      rightToLeft = arrowId
      topToBottom = titleId
      bottomToBottom = parentId
      topMargin = 8.dp
      leftMargin = 8.dp
      rightMargin = 8.dp
    }
    textSize = 12f
    text_colorResource = R.color.md_grey500
  }

  fun bindFunction(moduleFunction: ModuleFunction) {
    if (moduleFunction.iconResource > 0) {
      iconView.setImageResource(moduleFunction.iconResource)
    }
    titleView.text = moduleFunction.title
    descriptionView.text = moduleFunction.description
    setOnClickListener {
      moduleFunction.clickAction(context)
    }
  }
}