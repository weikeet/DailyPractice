package com.weiwei.practice.ui.main

import android.content.Context
import android.widget.LinearLayout
import androidx.fragment.app.FragmentContainerView
import com.weiwei.fluentview.ui.backgroundResource
import com.weiwei.fluentview.ui.res.colorResource
import com.weiwei.fluentview.ui.res.dimenSizeResource
import com.weiwei.fluentview.ui.res.stringResource
import com.weiwei.fluentview.view.appcompat.toolbar
import com.weiwei.fluentview.view.autoAddView
import com.weiwei.fluentview.view.linearParams
import com.weiwei.fluentview.view.matchParent
import com.weiwei.practice.R

/**
 * @author Weicools
 *
 * @date 2021.07.10
 */
class MainLayout(context: Context) : LinearLayout(context) {
  val toolbar = toolbar {
    layoutParams = linearParams(matchParent, dimenSizeResource(R.dimen.toolbar_height)) { }
    title = stringResource(R.string.app_name)
    setTitleTextColor(colorResource(R.color.white))
    backgroundResource = R.color.colorPrimary
  }

  val containerView = FragmentContainerView(context).autoAddView(this) {
    id = R.id.main_fragment_container
    layoutParams = linearParams(matchParent, matchParent) {}
  }

  init {
    orientation = VERTICAL
  }
}