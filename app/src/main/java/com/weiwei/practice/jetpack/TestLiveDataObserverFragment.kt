package com.weiwei.practice.jetpack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.weiwei.practice.R

/**
 * @author weicools
 * @date 2021.01.07
 */
class TestLiveDataObserverFragment : Fragment() {
  companion object {
    fun newInstance(): TestLiveDataObserverFragment {
      return TestLiveDataObserverFragment()
    }
  }

  private val viewModel: TestViewModel by viewModels()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_test_live_data_observer, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val resultView: TextView = view.findViewById(R.id.resultView)

    viewModel.getStringData().observe(this, Observer {
      resultView.text = it
    })
  }
}