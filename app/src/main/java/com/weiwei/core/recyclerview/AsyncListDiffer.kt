package com.weiwei.core.recyclerview

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import com.drakeet.multitype.MultiTypeAdapter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executor

class AsyncListDiffer(
  private val adapter: MultiTypeAdapter,

  /**
   * [ListUpdateCallback] which diff result dispatched to
   */
  private val listUpdateCallback: ListUpdateCallback,

  /**
   * a [CoroutineDispatcher] defined by yourself
   * common usage is to turn existing [Executor] into [CoroutineDispatcher]
   */
  dispatcher: CoroutineDispatcher
) {

  private val externalScope = CoroutineScope(SupervisorJob() + dispatcher)

  /**
   * the result list after last diffing
   */
  var oldList = listOf<Any>()
    private set

  /**
   * an Int to auto-increase by the times of [submitList] invocation
   */
  private var maxSubmitGeneration: Int = 0

  /**
   * submit a new list and begin diffing with the old list in a background thread,
   * when the diff is completed, the result will be dispatched to [ListUpdateCallback]
   */
  fun submitList(newList: List<Any>) {
    if (newList == oldList) {
      return
    }

    val submitGeneration = ++maxSubmitGeneration

    // fast return: old list is empty, just add all new list
    if (oldList.isEmpty()) {
      oldList = newList
      adapter.items = newList
      listUpdateCallback.onInserted(0, newList.size)
      return
    }

    // begin diffing in a new coroutine
    externalScope.launch {
      val diffResult = DiffUtil.calculateDiff(DiffCallback(oldList, newList))

      // dispatch the diff result to main thread
      withContext(Dispatchers.Main) {
        // just apply the last diffResult, discard the others
        if (submitGeneration == maxSubmitGeneration) {
          oldList = newList
          adapter.items = newList
          diffResult.dispatchUpdatesTo(listUpdateCallback)
        }
      }
    }
  }
}


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


interface Differ {

  /**
   * whether this object and [other] is the same object
   *
   * Check that both data use the same ViewType
   */
  fun isSameAs(other: Any?): Boolean

  /**
   * whether this object has the same content with [other]
   *
   * check data content is same (if [isSameAs] is true)
   */
  fun isContentSameAs(other: Any?): Boolean

  /**
   * diff one object to [other] object
   *
   * diff data content (if [isContentSameAs] is false])
   *
   * @return the detail of difference defined by yourself
   */
  fun diff(other: Any?): Any?

}
