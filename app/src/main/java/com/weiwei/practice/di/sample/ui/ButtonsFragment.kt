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

package com.weiwei.practice.di.sample.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.weiwei.fluentview.ui.onClick
import com.weiwei.fluentview.ui.unit.dp
import com.weiwei.fluentview.view.appcompat.button
import com.weiwei.fluentview.view.appcompat.textView
import com.weiwei.fluentview.view.horizontalMargin
import com.weiwei.fluentview.view.linearLayout
import com.weiwei.fluentview.view.linearParams
import com.weiwei.fluentview.view.matchParent
import com.weiwei.fluentview.view.wrapContent
import com.weiwei.practice.app.PracticeApp
import com.weiwei.practice.di.sample.data.LoggerLocalDataSource
import com.weiwei.practice.di.sample.navigator.AppNavigator
import com.weiwei.practice.di.sample.navigator.Screens

/**
 * Fragment that displays buttons whose interactions are recorded.
 */
class ButtonsFragment : Fragment() {

  private lateinit var logger: LoggerLocalDataSource
  private lateinit var navigator: AppNavigator

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    return requireContext().linearLayout {
      orientation = LinearLayout.VERTICAL

      textView {
        layoutParams = linearParams(matchParent, wrapContent) {
          horizontalMargin = 32.dp
          topMargin = 32.dp
        }
        text = "Please interact with the buttons, we\'ll track your clicks!"
        textSize = 20f
      }

      button {
        layoutParams = linearParams(matchParent, wrapContent) {
          horizontalMargin = 32.dp
          topMargin = 32.dp
        }
        onClick {
          logger.addLog("Interaction with 'Button 1'")
        }
        isAllCaps = false
        text = "Button 1"
      }

      button {
        layoutParams = linearParams(matchParent, wrapContent) {
          horizontalMargin = 32.dp
          topMargin = 32.dp
        }
        onClick {
          logger.addLog("Interaction with 'Button 2'")
        }
        isAllCaps = false
        text = "Button 2"
      }

      button {
        layoutParams = linearParams(matchParent, wrapContent) {
          horizontalMargin = 32.dp
          topMargin = 32.dp
        }
        onClick {
          logger.addLog("Interaction with 'Button 3'")
        }
        isAllCaps = false
        text = "Button 3"
      }

      button {
        layoutParams = linearParams(matchParent, wrapContent) {
          horizontalMargin = 32.dp
          topMargin = 32.dp
        }
        onClick {
          navigator.navigateTo(Screens.LOGS)
        }
        isAllCaps = false
        text = "AllLogs"
      }

      button {
        layoutParams = linearParams(matchParent, wrapContent) {
          horizontalMargin = 32.dp
          topMargin = 32.dp
        }
        onClick {
          logger.removeLogs()
        }
        isAllCaps = false
        text = "DeleteLogs"
      }
    }
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)

    populateFields(context)
  }

  private fun populateFields(context: Context) {
    logger = (context.applicationContext as PracticeApp).serviceLocator.loggerLocalDataSource

    navigator = (context.applicationContext as PracticeApp).serviceLocator.provideNavigator(requireActivity())
  }
}
