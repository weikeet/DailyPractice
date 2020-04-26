package io.weicools.daily.practice

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import io.weicools.daily.practice.activity.task.TaskTestActivity
import io.weicools.daily.practice.greenDao.GreenDaoActivity
import io.weicools.daily.practice.room.ui.RoomActivity
import kotlinx.android.synthetic.main.fragment_card1.*
import me.weicools.widget.tablayout.TabLayoutActivity

/**
 * @author weicools
 */
class Card1Fragment : Fragment(), View.OnClickListener {
  var mBtnTestGreenDao: Button? = null
  var mBtnTestRoom: Button? = null
  private var mActivity: Activity? = null
  override fun onAttach(context: Context) {
    super.onAttach(context)
    mActivity = context as Activity
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? { // Inflate the layout for this fragment
    val view = inflater.inflate(R.layout.fragment_card1, container, false)
    mBtnTestGreenDao = view.findViewById(R.id.btn_test_green_dao)
    mBtnTestRoom = view.findViewById(R.id.btn_test_room)
    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    mBtnTestGreenDao!!.setOnClickListener(this)
    mBtnTestRoom!!.setOnClickListener(this)

    btnTestActivityTask.setOnClickListener {
      activity?.startActivity(Intent(activity, TaskTestActivity::class.java))
    }
    btnTestTabLayout.setOnClickListener {
      activity?.startActivity(Intent(activity, TabLayoutActivity::class.java))
    }
  }

  override fun onClick(view: View) {
    val intent = Intent()
    when (view.id) {
      R.id.btn_test_green_dao -> {
        intent.setClass(mActivity!!, GreenDaoActivity::class.java)
        startActivity(intent)
      }
      R.id.btn_test_room -> {
        intent.setClass(mActivity!!, RoomActivity::class.java)
        startActivity(intent)
      }
      else -> {
      }
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
  }

  companion object {
    fun newInstance(): Card1Fragment {
      return Card1Fragment()
    }
  }
}