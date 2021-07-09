package io.weicools.daily.practice.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.MultiTypeAdapter
import io.weicools.daily.practice.ui.main.data.ModuleFunction

/**
 * @author Weicools
 *
 * @date 2021.07.10
 */
class MainFragment : Fragment() {
  companion object {
    fun newInstance() = MainFragment()
  }

  private val adapter = MultiTypeAdapter()
  private val items = ArrayList<Any>()

  private lateinit var attachActivity: AppCompatActivity

  override fun onAttach(context: Context) {
    super.onAttach(context)
    attachActivity = context as AppCompatActivity
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    adapter.register(ModuleFunctionDelegate())
    val recyclerView = RecyclerView(attachActivity).apply {
      layoutManager = LinearLayoutManager(attachActivity)
    }
    recyclerView.adapter = adapter
    return recyclerView
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    items.add(ModuleFunction(0, "AAA", "BBB") {
      Toast.makeText(attachActivity, "click ok", Toast.LENGTH_SHORT).show()
    })
    items.add(ModuleFunction(0, "XXX", "YYY") {
      Toast.makeText(attachActivity, "click ok", Toast.LENGTH_SHORT).show()
    })
    adapter.items = items
    adapter.notifyDataSetChanged()
  }
}