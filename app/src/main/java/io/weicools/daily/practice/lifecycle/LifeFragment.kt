package io.weicools.daily.practice.lifecycle

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * @author weicools
 * @date 2021.07.05
 */
open class LifeFragment : Fragment() {

  class LifeFragmentLog {
    var enable = true
    var enableInstanceState = true
  }

  private val TAG = javaClass.simpleName

  private val fragmentLog = LifeFragmentLog()

  open fun setupFragmentLog(fragmentLog: LifeFragmentLog) {
  }

  private fun loggerInner(tag: String, msg: String) {
    if (fragmentLog.enable) {
      logger(tag, msg)
    }
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    setupFragmentLog(fragmentLog)
    loggerInner(TAG, "onAttach: ")
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    loggerInner(TAG, "onCreate: savedState=$savedInstanceState")
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    loggerInner(TAG, "onCreateView: savedState=$savedInstanceState")
    return createView(inflater, container, savedInstanceState)
  }

  open fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    return View(requireContext())
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    loggerInner(TAG, "onViewCreated: savedState=$savedInstanceState")
  }

  override fun onStart() {
    super.onStart()
    loggerInner(TAG, "onStart: ")
  }

  override fun onResume() {
    super.onResume()
    loggerInner(TAG, "onResume: ")
  }

  override fun onPause() {
    super.onPause()
    loggerInner(TAG, "onPause: ")
  }

  override fun onStop() {
    super.onStop()
    loggerInner(TAG, "onStop: ")
  }

  override fun onDestroyView() {
    super.onDestroyView()
    loggerInner(TAG, "onDestroyView: ")
  }

  override fun onDestroy() {
    super.onDestroy()
    loggerInner(TAG, "onDestroy: ")
  }

  override fun onDetach() {
    super.onDetach()
    loggerInner(TAG, "onDetach: ")
  }

  override fun setUserVisibleHint(isVisibleToUser: Boolean) {
    super.setUserVisibleHint(isVisibleToUser)
    loggerInner(TAG, "setUserVisibleHint: isVisibleToUser=$isVisibleToUser")
  }

  override fun onHiddenChanged(hidden: Boolean) {
    super.onHiddenChanged(hidden)
    loggerInner(TAG, "onHiddenChanged: hidden=$hidden")
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    if (fragmentLog.enableInstanceState) {
      loggerInner(TAG, "onSaveInstanceState: outState=$outState")
    }
  }

  override fun onViewStateRestored(savedInstanceState: Bundle?) {
    super.onViewStateRestored(savedInstanceState)
    if (fragmentLog.enableInstanceState) {
      loggerInner(TAG, "onViewStateRestored: savedState=$savedInstanceState")
    }
  }

}