package io.weicools.daily.practice.lifecycle.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import io.weicools.daily.practice.lifecycle.LifecycleSecondActivity

/**
 * @author weicools
 * @date 2021.03.31
 */
class ViewPagerFragment : Fragment() {
  companion object {
    private const val TAG = "ViewPagerFragment"

    private const val EXTRA_INDEX = "EXTRA_INDEX"

    fun newInstance(index: Int): ViewPagerFragment {
      val bundle = Bundle().apply {
        putInt(EXTRA_INDEX, index)
      }
      val fragment = ViewPagerFragment()
      fragment.attachIndex = index
      fragment.arguments = bundle
      return fragment
    }
  }

  private lateinit var act: Activity
  private lateinit var tv: TextView

  private var attachIndex = 0

  private var index = 0

  override fun onAttach(context: Context) {
    super.onAttach(context)
    Log.d(LifeConstants.TAG, "$TAG onAttach: index=$attachIndex")
    act = context as Activity
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    arguments?.let { index = it.getInt(EXTRA_INDEX, 0) }
    Log.d(LifeConstants.TAG, "$TAG onCreate: index=$index, (index==attachIndex?)=${index == attachIndex}")
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    Log.d(LifeConstants.TAG, "$TAG onCreateView: index=$index")
    tv = AppCompatTextView(act).apply {
      layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
      gravity = Gravity.CENTER
      textSize = 24f
      text = "$index"

      setOnClickListener { act.startActivity(Intent(act, LifecycleSecondActivity::class.java)) }
    }
    return tv
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Log.d(LifeConstants.TAG, "$TAG onViewCreated: index=$index")
  }

  override fun onStart() {
    super.onStart()
    Log.d(LifeConstants.TAG, "$TAG onStart: index=$index")
  }

  override fun onResume() {
    super.onResume()
    Log.d(LifeConstants.TAG, "$TAG onResume: index=$index")
  }

  override fun onStop() {
    super.onStop()
    Log.d(LifeConstants.TAG, "$TAG onStop: index=$index")
  }

  override fun onDestroyView() {
    super.onDestroyView()
    Log.d(LifeConstants.TAG, "$TAG onDestroyView: index=$index")
  }

  override fun onDestroy() {
    super.onDestroy()
    Log.d(LifeConstants.TAG, "$TAG onDestroy: index=$index")
  }

  @Suppress("DEPRECATION")
  override fun setUserVisibleHint(isVisibleToUser: Boolean) {
    super.setUserVisibleHint(isVisibleToUser)
    Log.d(LifeConstants.TAG, "$TAG setUserVisibleHint: index=$attachIndex, getUserVisibleHint=$userVisibleHint, isVisible=$isVisible")
  }

  override fun onHiddenChanged(hidden: Boolean) {
    super.onHiddenChanged(hidden)
    Log.d(LifeConstants.TAG, "$TAG onHiddenChanged: index=$index, hidden=$hidden")
  }
}