package com.weiwei.core.recyclerview

import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.MultiTypeAdapter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlin.math.max

class FluentAdapter(dispatcher: CoroutineDispatcher = Dispatchers.IO) : MultiTypeAdapter() {

  private val dataDiffer = AsyncListDiffer(this, AdapterListUpdateCallback(this), dispatcher)

  /**
   * the data of this adapter
   */
  var dataList: List<Any>
    set(value) {
      dataDiffer.submitList(value)
    }
    get() = dataDiffer.oldList

  //region Preload
  /**
   * preload threshold. [onPreload] will be called if there is [preloadItemCount] items remain in the list
   */
  var preloadItemCount = 0

  /**
   * to avoid preload action when there is not enough item to fill the screen
   */
  var minPreloadItemCount = 0

  /**
   * an lambda will be invoked in [onBindViewHolder] when preload threshold is satisfied
   * implement this lambda with actual data loading action
   */
  var onPreload: (() -> Unit)? = null

  /**
   * scroll state of [RecyclerView] which this adapter attached to
   */
  var scrollState = RecyclerView.SCROLL_STATE_IDLE

  override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
    /**
     * keep scroll state in [scrollState] which is used in preloading
     */
    recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
      override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        scrollState = newState
        super.onScrollStateChanged(recyclerView, newState)
      }
    })
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    super.onBindViewHolder(holder, position)

    checkPreload(position)
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>) {
    super.onBindViewHolder(holder, position, payloads)

    checkPreload(position)
  }

  /**
   * check if preload threshold is satisfied
   */
  private fun checkPreload(position: Int) {
    if (onPreload != null
      && position == max(itemCount - 1 - preloadItemCount, 0)// reach the preload threshold position
      && itemCount > minPreloadItemCount // to avoid preload when there is not enough items to fill the screen
      && scrollState != RecyclerView.SCROLL_STATE_IDLE // the list is scrolling
    ) {
      onPreload?.invoke()
    }
  }
  //endregion

}