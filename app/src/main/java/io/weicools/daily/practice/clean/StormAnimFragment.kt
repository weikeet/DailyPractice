package io.weicools.daily.practice.clean

import android.animation.*
import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.animation.PathInterpolatorCompat
import androidx.fragment.app.Fragment
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.airbnb.lottie.LottieAnimationView
import io.weicools.daily.practice.R
import io.weicools.daily.practice.ktx.widthPixels

/**
 * @author weicools
 * @date 2021.01.27
 */
class StormAnimFragment : Fragment() {
  companion object {
    fun newInstance(): Fragment {
      return StormAnimFragment()
    }
  }

  data class StormAnimData(
    var v: ImageView,
    var startDelay: Long = 0L,
    var startAlpha: Float = 1f,
    var startAngle: Float = 0f
  )

  private lateinit var stormView1: ImageView
  private lateinit var stormView2: ImageView
  private lateinit var stormView3: ImageView
  private lateinit var stormView4: ImageView
  private lateinit var stormView5: ImageView
  private lateinit var stormView6: ImageView
  private lateinit var stormView7: ImageView
  private lateinit var stormView8: ImageView
  private lateinit var stormView9: ImageView
  private lateinit var stormView10: ImageView
  private lateinit var stormView11: ImageView
  private lateinit var stormView12: ImageView

  private lateinit var stormViewBlue1: ImageView
  private lateinit var stormViewBlue2: ImageView
  private lateinit var stormViewBlue3: ImageView
  private lateinit var stormViewBlue4: ImageView

  private val firstList: MutableList<StormAnimData> = mutableListOf()
  private val secondList: MutableList<StormAnimData> = mutableListOf()

  private var animCancel = false
  private var set: AnimatorSet? = null

  private lateinit var activity: Activity

  override fun onAttach(context: Context) {
    super.onAttach(context)
    activity = context as Activity
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.clean_anim_fragment_storm, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    stormView1 = view.findViewById(R.id.stormView1)
    stormView2 = view.findViewById(R.id.stormView2)
    stormView3 = view.findViewById(R.id.stormView3)
    stormView4 = view.findViewById(R.id.stormView4)
    stormView5 = view.findViewById(R.id.stormView5)
    stormView6 = view.findViewById(R.id.stormView6)
    stormView7 = view.findViewById(R.id.stormView7)
    stormView8 = view.findViewById(R.id.stormView8)
    stormView9 = view.findViewById(R.id.stormView9)
    stormView10 = view.findViewById(R.id.stormView10)
    stormView11 = view.findViewById(R.id.stormView11)
    stormView12 = view.findViewById(R.id.stormView12)

    stormViewBlue1 = view.findViewById(R.id.stormViewBlue1)
    stormViewBlue2 = view.findViewById(R.id.stormViewBlue2)
    stormViewBlue3 = view.findViewById(R.id.stormViewBlue3)
    stormViewBlue4 = view.findViewById(R.id.stormViewBlue4)

    firstList.add(StormAnimData(stormView1, 0L, 0.6f, 38f))
    firstList.add(StormAnimData(stormView2, 0L, 0.8f, 88f))
    firstList.add(StormAnimData(stormView3, 100L, 0.8f, 175f))
    firstList.add(StormAnimData(stormView4, 160L, 0.5f, 216f))
    firstList.add(StormAnimData(stormView5, 180L, 0.8f, 270f))
    firstList.add(StormAnimData(stormView6, 280L, 1f, 0f))

    secondList.add(StormAnimData(stormView7, 0L, 0.6f, 38f))
    secondList.add(StormAnimData(stormView8, 0L, 0.8f, 88f))
    secondList.add(StormAnimData(stormView9, 100L, 0.8f, 175f))
    secondList.add(StormAnimData(stormView10, 160L, 0.5f, 216f))
    secondList.add(StormAnimData(stormView11, 180L, 0.8f, 270f))
    secondList.add(StormAnimData(stormView12, 280L, 1f, 0f))

    set = AnimatorSet().apply {
      playTogether(
        createWhiteStormAnim(StormAnimData(stormView1, 0, 0.6f, 38f)),
        createWhiteStormAnim(StormAnimData(stormView2, 0, 0.8f, 88f)),
        createWhiteStormAnim(StormAnimData(stormView3, 120, 0.8f, 175f)),
        createWhiteStormAnim(StormAnimData(stormView4, 180, 0.5f, 216f)),
        createWhiteStormAnim(StormAnimData(stormView5, 200, 0.8f, 270f)),
        createWhiteStormAnim(StormAnimData(stormView6, 300, 1f, 0f)),

        createBlueStormAnim(StormAnimData(stormViewBlue1, 50L, 1f, -290f)),
        createBlueStormAnim(StormAnimData(stormViewBlue2, 260L, 1f, 73f)),
        createBlueStormAnim(StormAnimData(stormViewBlue3, 430L, 1f, -126f)),
        createBlueStormAnim(StormAnimData(stormViewBlue4, 460L, 1f, 171f))
      )
    }.also { it.start() }

    val lottieView: LottieAnimationView = view.findViewById(R.id.lottieView)
    lottieView.setAnimation("lottie/clean_storm_anim/data.json")
    lottieView.imageAssetsFolder = "lottie/clean_storm_anim/images"
    lottieView.repeatCount = ValueAnimator.INFINITE
    lottieView.addAnimatorListener(object : AnimatorListenerAdapter() {
      override fun onAnimationStart(animation: Animator?) {
        lottieView.visibility = View.VISIBLE
      }
    })
    lottieView.playAnimation()

    // set = AnimatorSet().apply {
    //   playTogether(
    //     createStormAnim2(StormAnimData(stormView1, 0L, 0.8f, 38f)),
    //     createStormAnim2(StormAnimData(stormView2, 100L, 1f, 175f)),
    //     createStormAnim2(StormAnimData(stormView3, 150L, 1f, 276f)),
    //     createStormAnim2(StormAnimData(stormView4, 150L, 0.4f, 226f)),
    //     createStormAnim2(StormAnimData(stormView5, 280L, 1f, 0f)),
    //     createStormAnim2(StormAnimData(stormView7, 400L, 0.6f, 38f)),
    //     createStormAnim2(StormAnimData(stormView8, 400L, 0.8f, 88f)),
    //     createStormAnim2(StormAnimData(stormView9, 500L, 0.8f, 175f)),
    //     createStormAnim2(StormAnimData(stormView10, 560L, 0.5f, 216f)),
    //     createStormAnim2(StormAnimData(stormView11, 580L, 0.8f, 270f)),
    //     createStormAnim2(StormAnimData(stormView12, 680L, 1f, 0f)),
    //     createBlueStormAnim(StormAnimData(stormViewBlue1, 150L, 1f, -290f)),
    //     createBlueStormAnim(StormAnimData(stormViewBlue2, 360L, 1f, 73f)),
    //     createBlueStormAnim(StormAnimData(stormViewBlue3, 530L, 1f, -126f)),
    //     createBlueStormAnim(StormAnimData(stormViewBlue4, 560L, 1f, 171f))
    //   )
    // }.also { it.start() }
    //
    // handler.postDelayed({ startAnim() }, 900L)
  }

  override fun onDestroyView() {
    super.onDestroyView()
    handler.removeCallbacksAndMessages(null)
    animCancel = true
    set?.cancel()
  }

  private fun createWhiteStormAnim(data: StormAnimData): Animator {
    if (true) {
      return ValueAnimator.ofFloat(0f, 1f).apply {
        duration = 500L
        startDelay = data.startDelay
        repeatCount = ValueAnimator.INFINITE
        interpolator = FastOutSlowInInterpolator()
        addUpdateListener {
          val value: Float = it.animatedValue as Float
          data.v.rotation = data.startAngle + (-319f * value)
          data.v.scaleX = 1.65f * (1f - value)
          data.v.scaleY = 1.65f * (1f - value)
          data.v.alpha = 1f - value
        }
      }
    }
    val rotationAnim = ObjectAnimator.ofFloat(data.v, "rotation", data.startAngle, data.startAngle - 319f).apply {
      duration = 500L
      startDelay = data.startDelay
      repeatCount = ValueAnimator.INFINITE
      interpolator = PathInterpolatorCompat.create(0.1f, 0.17f, 0.63f, 0.56f)
      addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator?) {
          data.v.visibility = View.VISIBLE
        }
      })
    }
    val scaleAnim = ValueAnimator.ofFloat(0f, 1f).apply {
      duration = 500L
      startDelay = data.startDelay
      repeatCount = ValueAnimator.INFINITE
      interpolator = PathInterpolatorCompat.create(0.01f, 0.7f, 0.74f, 0.8f)
      addUpdateListener {
        val value: Float = it.animatedValue as Float
        data.v.scaleX = 1.65f * (1f - value)
        data.v.scaleY = 1.65f * (1f - value)
      }
    }
    val alphaAnim = ObjectAnimator.ofFloat(data.v, "alpha", 1f, 0f).apply {
      duration = 500L
      startDelay = data.startDelay
      repeatCount = ValueAnimator.INFINITE
    }
    return AnimatorSet().apply { playTogether(rotationAnim, scaleAnim, alphaAnim) }
  }

  private fun createBlueStormAnim(data: StormAnimData): Animator {
    return ValueAnimator.ofFloat(0f, 1f).apply {
      duration = 420L
      startDelay = data.startDelay
      repeatCount = ValueAnimator.INFINITE
      interpolator = PathInterpolatorCompat.create(0f, 0.12f, 0.56f, 0.63f)
      addUpdateListener {
        val value: Float = it.animatedValue as Float
        data.v.rotation = data.startAngle + (-235f * value)
        data.v.scaleX = 1.76f * (1f - value)
        data.v.scaleY = 1.76f * (1f - value)
      }
      addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator?) {
          data.v.visibility = View.VISIBLE
        }
      })
    }
  }

  private var reset = true
  private fun startAnim() {
    if (animCancel) return
    val dataList = if (reset) firstList else secondList
    for (data in dataList) {
      createStormAnim2(data).start()
    }
    reset = !reset
    handler.postDelayed({ startAnim() }, 400L)
  }

  private val handler = Handler(Looper.getMainLooper())

  private fun createStormAnim2(data: StormAnimData): Animator {
    val a0 = ObjectAnimator.ofFloat(data.v, "rotation", data.startAngle, data.startAngle - 319f).apply {
      duration = 500L
      startDelay = data.startDelay
      interpolator = PathInterpolatorCompat.create(0.1f, 0.17f, 0.63f, 0.56f)
      addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator?) {
          data.v.visibility = View.VISIBLE
        }
      })
    }
    val a1 = ValueAnimator.ofFloat(0f, 1f).apply {
      duration = 500L
      startDelay = data.startDelay
      interpolator = PathInterpolatorCompat.create(0.01f, 0.7f, 0.74f, 0.8f)
      addUpdateListener {
        val value: Float = it.animatedValue as Float
        data.v.rotation = data.startAngle + (-319f * value)
        data.v.scaleX = 1.65f * (1f - value)
        data.v.scaleY = 1.65f * (1f - value)
      }
    }
    val a2 = ObjectAnimator.ofFloat(data.v, "alpha", 1f, 0f).apply {
      duration = 470L
      startDelay = data.startDelay + 30L
    }
    return AnimatorSet().apply { playTogether(a0, a1, a2) }
  }

  //region MergeAnim
  private fun createStormAnim3(data: StormAnimData): ValueAnimator {
    return createStormAnim2(data.v, data.startDelay, data.startAlpha, data.startAngle)
  }

  private fun createStormAnim2(stormView: ImageView, delay: Long, startAlpha: Float, startAngle: Float): ValueAnimator {
    return ValueAnimator.ofFloat(0f, 1f).apply {
      duration = 500L
      startDelay = delay
      interpolator = PathInterpolatorCompat.create(0.01f, 0.7f, 0.74f, 0.8f)
      addUpdateListener {
        val value: Float = it.animatedValue as Float
        stormView.rotation = startAngle + (-319f * value)
        stormView.scaleX = 1.65f * (1f - value)
        stormView.scaleY = 1.65f * (1f - value)
        stormView.alpha = startAlpha * (1f - value)
      }
      addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator?) {
          stormView.visibility = View.VISIBLE
        }
      })
    }
  }
  //endregion

  //region TwoPart
  private fun createStormAnim(data: StormAnimData): ValueAnimator {
    return createStormAnim(data.v, data.startDelay, data.startAlpha, data.startAngle)
  }

  private fun createStormAnim(stormView: ImageView, delay: Long, startAlpha: Float, startAngle: Float): ValueAnimator {
    return ValueAnimator.ofFloat(0f, 1f).apply {
      duration = 70L
      startDelay = delay
      interpolator = LinearInterpolator()
      addUpdateListener {
        val value: Float = it.animatedValue as Float
        stormView.rotation = startAngle + (-80f * value)
        stormView.scaleX = 1.1f - (0.5f * value)
        stormView.scaleY = 1.1f - (0.5f * value)
      }
      addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator?) {
          stormView.visibility = View.VISIBLE
        }

        override fun onAnimationEnd(animation: Animator?) {
          startSecondAnim(stormView, startAlpha)
        }
      })
    }
  }

  private fun startSecondAnim(stormView: ImageView, startAlpha: Float) {
    val scale = stormView.scaleX
    val rotation = stormView.rotation
    ValueAnimator.ofFloat(0f, 1f).apply {
      duration = 430L
      interpolator = LinearInterpolator()
      addUpdateListener {
        val value: Float = it.animatedValue as Float
        stormView.rotation = rotation + (-238f * value)
        stormView.scaleX = scale * (1f - value)
        stormView.scaleY = scale * (1f - value)
        stormView.alpha = startAlpha * (1 - value)
      }
    }.start()
  }
  //endregion

  private fun createStormView(drawable: Drawable): ImageView {
    return AppCompatImageView(activity).apply {
      layoutParams = FrameLayout.LayoutParams(widthPixels, widthPixels).apply { gravity = Gravity.CENTER }
      visibility = View.INVISIBLE
      setImageDrawable(drawable)
    }
  }
}