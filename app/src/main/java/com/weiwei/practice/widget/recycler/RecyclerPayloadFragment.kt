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

package com.weiwei.practice.widget.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.weiwei.core.app.BaseFragment
import com.weiwei.practice.R
import com.weiwei.practice.binding.viewBinding
import com.weiwei.practice.databinding.FragmentRecyclerPayloadBinding

/**
 * @author weiwei
 * @date 2022.05.19
 */
class RecyclerPayloadFragment : BaseFragment() {

  private val binding: FragmentRecyclerPayloadBinding by viewBinding(FragmentRecyclerPayloadBinding::bind)

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_recycler_payload, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val layoutManager = LinearLayoutManager(requireContext())
    binding.recyclerView.layoutManager = layoutManager

    val adapter = PayloadAdapter()
    binding.recyclerView.adapter = adapter

    val dataList = ArrayList<PayloadEntity>()
    for (i in 0..3) {
      dataList.add(PayloadEntity("Title $i", "Message $i"))
    }
    adapter.dataList.addAll(dataList)
    adapter.notifyDataSetChanged()
    // adapter.notifyItemRangeInserted(0, dataList.size)

    binding.notifyWithout.setOnClickListener {
      dataList[1].msg = "Message notifyWithout"
      adapter.notifyItemChanged(1)
    }
    binding.notifyWith.setOnClickListener {
      dataList[2].msg = "Message notifyWith"
      adapter.notifyItemChanged(2, "PayloadData")
    }
  }
}