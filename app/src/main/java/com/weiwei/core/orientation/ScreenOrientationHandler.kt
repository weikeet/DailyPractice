package com.weiwei.core.orientation

import android.content.Context
import android.content.res.Configuration
import android.hardware.SensorManager
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import com.weiwei.core.app.mainContext
import com.weiwei.core.arch.lifecycle.SingleLiveEvent

/**
 * @author weiwei
 * @date 2023.02.21
 * link: https://github.com/Numen-fan/Blog/blob/master/Android/Android横竖屏切换实践.md
 *
 * observe [resetOrientation] to change orientation ORIENTATION_UNDEFINED
 * call [updateActivityOrientation] when activity onConfigurationChanged
 * call [manualChangeOrientation] when user manualChangeOrientation
 * call [release] when viewModel onClear
 */
class ScreenOrientationHandler(
  private val context: Context = mainContext,
  private val resetOrientation: SingleLiveEvent<Boolean>,
) : ScreenOrientationDetector.OrientationChangeListener, ScreenRotationSettingObserver.ScreenRotationSettingListener {

  private var activityOrientation: Int = Configuration.ORIENTATION_UNDEFINED

  private val mHandler: Handler = Handler(Looper.getMainLooper())

  private val changeOrientationRunnable = Runnable {
    resetOrientation.setValue(true)
    orientationDetector.disable() // 恢复设置后，结束检测
  }

  private var settingObserver: ScreenRotationSettingObserver = ScreenRotationSettingObserver(mHandler, context.contentResolver)
  private var orientationDetector: ScreenOrientationDetector = ScreenOrientationDetector(context, SensorManager.SENSOR_DELAY_NORMAL)

  init {
    settingObserver.setSystemOrientationSettingListener(this)
    orientationDetector.setOrientationChangeListener(this)

    // 开启屏幕自动旋转开关的监听
    settingObserver.startObserver()
  }

  fun release() {
    settingObserver.setSystemOrientationSettingListener(null)
    settingObserver.stopObserver()
    orientationDetector.setOrientationChangeListener(null)
    orientationDetector.disable()

    mHandler.removeCallbacks(changeOrientationRunnable)
  }

  fun updateActivityOrientation(orientation: Int) {
    activityOrientation = orientation
  }

  fun manualChangeOrientation() {
    // 手动切换，移除之前的延迟任务，避免快速点击带来的问题
    mHandler.removeCallbacks(changeOrientationRunnable)
    startScreenOrientationDetect()
  }

  /**
   * 屏幕自动旋转开关发生变化
   */
  override fun onRotationSettingChanged() {
    startScreenOrientationDetect()
  }

  /**
   * 实时计算的横竖屏发生了变化
   *
   * @param orientation 当前横屏还是竖屏
   */
  override fun onOrientationChanged(orientation: Int) {
    if (canActivityAutoRotate() && activityOrientation == orientation) {
      // 当手机旋转到和手动设置的同一个方向，恢复默认的设置
      mHandler.postDelayed(changeOrientationRunnable, 500)
    }
  }

  /**
   * 打开屏幕旋转监听
   */
  private fun startScreenOrientationDetect() {
    // 如果系统自动旋转打开，则开启横竖屏切换检测
    if (canActivityAutoRotate()) {
      mHandler.postDelayed({
        orientationDetector.initOrientation()
        orientationDetector.enable()
      }, 500)
    } else {
      // 如果关闭了自动旋转，取消一次横竖屏监听
      orientationDetector.disable()
    }
  }

  private fun canActivityAutoRotate(): Boolean {
    try {
      return Settings.System.getInt(context.contentResolver, Settings.System.ACCELEROMETER_ROTATION) == 1
    } catch (e: Exception) {
      e.printStackTrace()
    }
    return false
  }

}