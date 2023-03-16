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

package com.weiwei.practice.differ

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.weiwei.core.recyclerview.FluentAdapter
import com.weiwei.fluentview.ui.bottomPadding
import com.weiwei.fluentview.ui.gravityBottom
import com.weiwei.fluentview.ui.res.colorResource
import com.weiwei.fluentview.ui.unit.dp
import com.weiwei.fluentview.view.autoAddView
import com.weiwei.fluentview.view.doOnApplyWindowInsets
import com.weiwei.fluentview.view.frameLayout
import com.weiwei.fluentview.view.frameParams
import com.weiwei.fluentview.view.horizontalMargin
import com.weiwei.fluentview.view.linearParams
import com.weiwei.fluentview.view.matchParent
import com.weiwei.fluentview.view.material.materialButton
import com.weiwei.fluentview.view.recycler.recyclerView
import com.weiwei.fluentview.view.systemBarBottom
import com.weiwei.practice.R
import com.weiwei.practice.widget.topAppToolbar

/**
 * @author weiwei
 * @date 2023.03.10
 */
class ListDifferFragment : Fragment() {

  class MyRecyclerView(context: Context) : RecyclerView(context) {
    override fun onTouchEvent(e: MotionEvent?): Boolean {
      if (e?.action == MotionEvent.ACTION_DOWN) {
        Log.d("FuckEvent", "rv-1 onTouchEvent: down")
      } else if (e?.action == MotionEvent.ACTION_UP) {
        Log.d("FuckEvent", "rv-1 onTouchEvent: up")
      }
      return super.onTouchEvent(e)
    }
  }

  private lateinit var recyclerView: RecyclerView

  private lateinit var multiTypeAdapter: FluentAdapter

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

    return requireContext().topAppToolbar(
      title = "ListDiffer",
      titleColor = Color.WHITE,
      backgroundColor = colorResource(R.color.colorPrimary),
      navigationIconRes = R.drawable.common_ic_arrow,
      navigationIconClick = {
        findNavController().navigateUp()
      }
    ) {
      frameLayout {
        layoutParams = linearParams(matchParent, matchParent) {
        }

        recyclerView = MyRecyclerView(context).autoAddView(this) {
          layoutParams = frameParams(matchParent, matchParent) {
          }
          bottomPadding = 144.dp
          clipToPadding = false
        }

        materialButton {
          layoutParams = frameParams(matchParent, 40.dp) {
            horizontalMargin = 8.dp
            bottomMargin = 8.dp
            gravity = gravityBottom
          }
          insetTop = 0
          insetBottom = 0
          isAllCaps = false

          text = "Insert"

          setOnClickListener {
            insertData()
          }
        }

        materialButton {
          layoutParams = frameParams(matchParent, 40.dp) {
            horizontalMargin = 8.dp
            bottomMargin = 56.dp
            gravity = gravityBottom
          }
          insetTop = 0
          insetBottom = 0
          isAllCaps = false

          text = "Remove"

          setOnClickListener {
            removeData()
          }
        }

        materialButton {
          layoutParams = frameParams(matchParent, 40.dp) {
            horizontalMargin = 8.dp
            bottomMargin = 104.dp
            gravity = gravityBottom
          }
          insetTop = 0
          insetBottom = 0
          isAllCaps = false

          text = "Update"

          var s = false
          setOnClickListener {
            s = !s
            updateData(s)
          }
        }
      }
    }
  }

  private fun insertData() {
    val oldItems = multiTypeAdapter.items

    val dataList: ArrayList<Any> = ArrayList()
    for (i in oldItems.indices) {
      if (i == 3) {
        dataList.add(ListDifferData1("title-$i-insert", false))
        for (j in 0 until 2) {
          val image = when (j) {
            0 -> R.drawable.ic_menu_camera
            1 -> R.drawable.ic_menu_share
            else -> R.drawable.ic_menu_gallery
          }
          dataList.add(ListDifferData2("text-$i-$j-insert", image, false))
        }
      }
      dataList.add(oldItems[i])
    }

    multiTypeAdapter.dataList = dataList
  }

  private fun removeData() {
    val oldItems = multiTypeAdapter.items

    val dataList: ArrayList<Any> = ArrayList()
    for (i in oldItems.indices) {
      if (i % 4 == 0) {
        continue
      }
      dataList.add(oldItems[i])
    }

    multiTypeAdapter.dataList = dataList
  }

  private fun updateData(selected: Boolean) {
    val oldItems = multiTypeAdapter.items

    val time = DateFormat.format("HH:mm:ss", System.currentTimeMillis())
    val dataList: ArrayList<Any> = ArrayList()
    for (i in oldItems.indices) {
      if (i % 4 == 0) {
        val data = oldItems[i]
        if (data is ListDifferData1) {
          dataList.add(data.copy(title = "title-$i-update-$time", selected = selected))
        } else if (data is ListDifferData2) {
          dataList.add(data.copy(text = "text-$i-update-$time", selected = selected))
        }
      } else {
        dataList.add(oldItems[i])
      }
    }

    multiTypeAdapter.dataList = dataList
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    view.doOnApplyWindowInsets { windowInsets ->
      view.updatePadding(bottom = windowInsets.systemBarBottom)
    }

    recyclerView.layoutManager = LinearLayoutManager(requireContext())

    val multiTypeAdapter = FluentAdapter()
    multiTypeAdapter.register(ListDifferData1ViewBinder())
    multiTypeAdapter.register(ListDifferData2ViewBinder())
    multiTypeAdapter.register(ListDayDataViewBinder())

    this.multiTypeAdapter = multiTypeAdapter
    recyclerView.adapter = multiTypeAdapter

    val dataList: ArrayList<Any> = ArrayList()

    val dayList: ArrayList<ListDayItemData> = ArrayList()
    for (i in 1..10) {
      dayList.add(ListDayItemData("day-$i"))
    }
    dataList.add(ListDayData(dayList))

    for (i in 0 until 10) {
      dataList.add(ListDifferData1("title-$i", false))
      for (j in 0 until 5) {
        val image = when (j) {
          0 -> R.drawable.ic_menu_camera
          1 -> R.drawable.ic_menu_share
          2 -> R.drawable.ic_menu_send
          3 -> R.drawable.ic_menu_slideshow
          else -> R.drawable.ic_menu_gallery
        }
        dataList.add(ListDifferData2("text-$i-$j", image, false))
      }
    }
    multiTypeAdapter.dataList = dataList

    val d1 = ListDifferData1("title-0", false)
    val d2 = d1.copy(title = "title-0")
    val d3 = d1.copy(title = "title-0-c")
    Log.d("ListDiffer", "onViewCreated: d1=$d1, d2=$d2, d3=$d3")
    Log.d("ListDiffer", "onViewCreated: (d1==d2)=${d1 == d2}, (d1==d3)=${d1 == d3}")
    Log.d("ListDiffer", "onViewCreated: (d1===d2)=${d1 === d2}, (d1===d3)=${d1 === d3}")
  }

}