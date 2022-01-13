package com.weiwei.practice.ui.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.weiwei.fluent.widget.imageView
import com.weiwei.fluent.widget.textView
import com.weiwei.fluent.widget.extensions.dp
import com.weiwei.fluent.widget.extensions.text_colorResource
import com.weiwei.fluent.widget.params.center_VerticalOf
import com.weiwei.fluent.widget.params.chainPacked
import com.weiwei.fluent.widget.params.constraintParams
import com.weiwei.fluent.widget.params.parentId
import com.weiwei.fluent.widget.params.wrapContent
import com.weiwei.practice.R
import com.weiwei.practice.ui.main.data.ModuleFunction

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

  private val iconView = imageView(iconId) {
    layoutParams = constraintParams(40.dp, 40.dp) {
      leftToLeft = parentId
      center_VerticalOf = parentId
      leftMargin = 8.dp
    }
  }

  val arrowView = imageView(arrowId) {
    layoutParams = constraintParams(40.dp, 40.dp) {
      rightToRight = parentId
      center_VerticalOf = parentId
      rightMargin = 8.dp
    }
  }

  private val titleView = textView(titleId) {
    layoutParams = constraintParams(0, wrapContent) {
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

  private val descriptionView = textView(descriptionId) {
    layoutParams = constraintParams(0, wrapContent) {
      leftToRight = iconId
      rightToLeft = arrowId
      topToBottom = titleId
      bottomToBottom = parentId
      topMargin = 2.dp
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
      moduleFunction.clickAction(context as AppCompatActivity)
    }
  }
}