package com.weiwei.practice.ui.main

import android.content.Context
import android.widget.LinearLayout
import androidx.fragment.app.FragmentContainerView
import com.weiwei.fluent.widget.autoAddView
import com.weiwei.fluent.widget.toolbar
import com.weiwei.fluent.widget.extensions.background_resource
import com.weiwei.fluent.widget.extensions.color_of
import com.weiwei.fluent.widget.extensions.dimenSize_of
import com.weiwei.fluent.widget.extensions.string_of
import com.weiwei.fluent.widget.params.linearParams
import com.weiwei.fluent.widget.params.matchParent
import com.weiwei.practice.R

/**
 * @author Weicools
 *
 * @date 2021.07.10
 */
class MainLayout(context: Context) : LinearLayout(context) {
  val toolbar = toolbar {
    layoutParams = linearParams(matchParent, dimenSize_of(R.dimen.toolbar_height)) { }
    title = string_of(R.string.app_name)
    setTitleTextColor(color_of(R.color.white))
    background_resource = R.color.colorPrimary
  }

  val containerView = FragmentContainerView(context).autoAddView(this) {
    id = R.id.main_fragment_container
    layoutParams = linearParams(matchParent, matchParent) {}
  }

  init {
    orientation = VERTICAL
  }
}