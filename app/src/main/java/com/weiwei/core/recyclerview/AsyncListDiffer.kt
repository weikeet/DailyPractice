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
