package com.weiwei.core.recyclerview

import androidx.recyclerview.widget.DiffUtil

class DiffCallback(private val oldList: List<Any>, private val newList: List<Any>) : DiffUtil.Callback() {

  override fun getOldListSize(): Int = oldList.size

  override fun getNewListSize(): Int = newList.size

  override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    val oldItem = oldList[oldItemPosition]
    val newItem = newList[newItemPosition]
    val oldDiffer = oldItem as? Differ
    val newDiffer = newItem as? Differ

    if (oldDiffer == null || newDiffer == null) {
      return oldItem.hashCode() == newItem.hashCode()
    }
    return oldDiffer.isSameAs(newItem)
  }

  override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    val oldItem = oldList[oldItemPosition]
    val newItem = newList[newItemPosition]
    val oldDiffer = oldItem as? Differ
    val newDiffer = newItem as? Differ

    if (oldDiffer == null || newDiffer == null) {
      return oldItem == newItem
    }
    return oldDiffer.isContentSameAs(newItem)
  }

  override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
    val oldItem = oldList[oldItemPosition] as? Differ
    val newItem = newList[newItemPosition] as? Differ

    if (oldItem == null || newItem == null) {
      return null
    }
    // if new and old items are the same object but have different content, call diff() to find the precise difference
    return oldItem.diff(newItem)
  }
}