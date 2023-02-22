package com.weiwei.core.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

enum class FloatUiOp {
  Show, Hide, Remove
}

interface FloatUiPage {
  val tag: String

  fun createFragment(args: Bundle?): Fragment
}

data class FloatUiState<T : FloatUiPage>(
  val page: T,
  val op: FloatUiOp,
  val args: Bundle? = null,
)

// define float ui page
// sealed class WorkoutFloatUiPage(override val tag: String) : FloatUiPage {
//     object PlayBack : WorkoutFloatUiPage("PlayBack")
//     override fun createFragment(args: Bundle?): Fragment {
//         val fragment = when (this) {
//             PlayBack -> WorkoutPlayBackFragment()
//         }
//         fragment.arguments = args
//         return fragment
//     }
// }

// define observable data in sharedViewModel
// val workoutFloatUiPage: MutableLiveData<FloatUiState<WorkoutFloatUiPage>> = MutableLiveData()

// observe floatUiPage in activity/fragment
// val floatUiManager = FloatUiManager(R.id.floatContainer, supportFragmentManager, this)
// sharedViewModel.workoutFloatUiPage.observe(this) { state ->
//     floatUiManager.commit(state)
// }

// show/remove float ui page
// sharedViewModel.workoutFloatUiPage.value = FloatUiState(WorkoutFloatUiPage.PlayBack, FloatUiOp.Show)
// sharedViewModel.workoutFloatUiPage.value = FloatUiState(WorkoutFloatUiPage.PlayBack, FloatUiOp.Remove)

class FloatUiManager(
  private val containerId: Int,
  private val fragmentManager: FragmentManager,
  lifecycleOwner: LifecycleOwner
) : LifecycleEventObserver {

  private var currEvent: Lifecycle.Event? = null

  init {
    lifecycleOwner.lifecycle.addObserver(this)
  }

  override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
    currEvent = event
  }

  fun <T : FloatUiPage> commit(state: FloatUiState<T>) {
    commit(state.page.tag, state.op) {
      state.page.createFragment(state.args)
    }
  }

  fun commit(tag: String, op: FloatUiOp, createFragment: () -> Fragment) {
    if (currEvent != Lifecycle.Event.ON_CREATE && currEvent != Lifecycle.Event.ON_START && currEvent != Lifecycle.Event.ON_RESUME) {
      // if debug throw IllegalStateException("Please call in lifecycle [Lifecycle.State.CREATED]")
      Log.d("FloatUiManager", "commit: Please call in lifecycle [Lifecycle.State.CREATED]")
      return
    }

    val committedFragment = fragmentManager.findFragmentByTag(tag)

    Log.d("FloatUiManager", "commit: op=$op, tag=$tag, committedFragment=$committedFragment")

    when (op) {
      FloatUiOp.Show -> {
        if (committedFragment != null) {
          Log.d("FloatUiManager", "commit: show $committedFragment")
          fragmentManager.beginTransaction()
            .show(committedFragment)
            .commit()
        } else {
          val newFragment = createFragment()
          Log.d("FloatUiManager", "commit: add $newFragment")
          fragmentManager.beginTransaction()
            .add(containerId, newFragment, tag)
            .commit()
        }
      }
      FloatUiOp.Hide -> {
        if (committedFragment != null) {
          fragmentManager.beginTransaction()
            .hide(committedFragment)
            .commit()
        }
      }
      FloatUiOp.Remove -> {
        if (committedFragment != null) {
          Log.d("FloatUiManager", "commit: remove $createFragment")
          fragmentManager.beginTransaction()
            .remove(committedFragment)
            .commit()
        }
      }
    }
  }

}