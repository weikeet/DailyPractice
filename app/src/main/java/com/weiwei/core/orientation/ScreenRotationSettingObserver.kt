package com.weiwei.core.orientation

import android.content.ContentResolver
import android.database.ContentObserver
import android.os.Handler
import android.provider.Settings

/**
 * @author weiwei
 * @date 2023.02.20
 * Desc: 监听系统设置中是否打开自动选择，部分手机厂商叫方向锁定
 */
class ScreenRotationSettingObserver(handler: Handler, private val mResolver: ContentResolver) : ContentObserver(handler) {
  interface ScreenRotationSettingListener {
    fun onRotationSettingChanged()
  }

  private var listener: ScreenRotationSettingListener? = null

  fun setSystemOrientationSettingListener(l: ScreenRotationSettingListener?) {
    listener = l
  }

  /**
   * 屏幕旋转设置改变时调用
   */
  override fun onChange(selfChange: Boolean) {
    super.onChange(selfChange)
    listener?.onRotationSettingChanged()
  }

  fun startObserver() {
    mResolver.registerContentObserver(Settings.System.getUriFor(Settings.System.ACCELEROMETER_ROTATION), false, this)
  }

  fun stopObserver() {
    mResolver.unregisterContentObserver(this)
  }
}