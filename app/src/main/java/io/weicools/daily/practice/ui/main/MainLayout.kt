package io.weicools.daily.practice.ui.main

import android.content.Context
import android.widget.LinearLayout
import androidx.fragment.app.FragmentContainerView
import com.weicools.ktx.widget.dsl.autoAddView
import com.weicools.ktx.widget.dsl.toolbar
import com.weicools.ktx.widget.extensions.background_resource
import com.weicools.ktx.widget.extensions.color_of
import com.weicools.ktx.widget.extensions.dimenSize_of
import com.weicools.ktx.widget.extensions.string_of
import com.weicools.ktx.widget.params.linearParams
import com.weicools.ktx.widget.params.matchParent
import io.weicools.daily.practice.R

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