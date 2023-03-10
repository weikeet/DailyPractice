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

import com.weiwei.core.recyclerview.Differ

/**
 * @author weiwei
 * @date 2023.03.10
 */
data class ListDifferData2(val text: String, val imageResource: Int, val selected: Boolean) : Differ {
  override fun isSameAs(other: Any?): Boolean {
    return other != null && other is ListDifferData2
  }

  override fun isContentSameAs(other: Any?): Boolean {
    if (other is ListDifferData2) {
      return this == other
    }
    return false
  }

  override fun diff(other: Any?): Any? {
    if (other is ListDifferData2) {
      val list: ArrayList<String> = ArrayList()
      if (text != other.text) {
        list.add("Text")
      }
      if (imageResource != other.imageResource) {
        list.add("ImageResource")
      }
      if (selected != other.selected) {
        list.add("Selected")
      }
      return list
    }
    return null
  }
}