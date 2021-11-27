package io.weicools.daily.practice.autostart

import android.content.ComponentName
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import io.weicools.daily.practice.R
import io.weicools.daily.practice.autostart.RomUtil.isHarmonyOS

////////////////////：具体怎么提示，下面的弹框「仅供参考」////////////////////

/**
 * 提示用户打开「白名单管理列表页面」失败(即：自启动权限)
 */
fun openWhiteListErrorDialog(context: Context) {
  val msgId = when {
    RomUtil.isMiui() -> R.string.miui_open_white_list_failed
    RomUtil.isSamsung() -> R.string.samsung_open_white_list_failed
    RomUtil.isOnePlus() -> R.string.oneplus_open_white_list_failed
    RomUtil.isEmui() -> R.string.huawei_open_white_list_failed
    RomUtil.isVivo() -> R.string.vivo_open_white_list_failed
    RomUtil.isFlyme() -> R.string.flyme_open_white_list_failed
    RomUtil.isOppo() -> R.string.oppo_open_white_list_failed
    RomUtil.isSmartisan() -> R.string.smartisan_open_white_list_failed
    else -> R.string.open_white_list_failed
  }
  val checkDetachableClickListener = DetachableClickListener.wrap(
    DialogInterface.OnClickListener { dialog, _ ->
      dialog?.dismiss()
    })
  val confirmDialog = AlertDialog.Builder(context)
    .setTitle(R.string.dialog_open_white_list_error_title)
    .setMessage(msgId)
    .setPositiveButton(R.string.main_dialog_ok, checkDetachableClickListener).create()
  confirmDialog.setCanceledOnTouchOutside(false)
  checkDetachableClickListener?.clearOnDetach(confirmDialog)
  confirmDialog.show()
}

/**
 * 提示用户打开「白名单管理列表页面」(即：自启动权限)
 */
fun openWhiteListDialog(context: Context) {
  val message = when {
    RomUtil.isSmartisan() -> R.string.smartisan_open_white_list_failed
    RomUtil.isSamsung() -> R.string.open_white_list_samsung_tips
    RomUtil.isEmui() -> R.string.huawei_open_white_list_failed
    else -> R.string.open_white_list_common_tips
  }
  val checkDetachableClickListener = DetachableClickListener.wrap { dialog, _ ->
    dialog?.dismiss()
    if (!startWhiteListSetting(context)) {
      openWhiteListErrorDialog(context)
    }
  }
  val confirmDialog = AlertDialog.Builder(context)
    .setMessage(message)
    .setPositiveButton(R.string.main_dialog_setting, checkDetachableClickListener).create()
  confirmDialog.setCanceledOnTouchOutside(false)
  checkDetachableClickListener?.clearOnDetach(confirmDialog)
  confirmDialog.show()
}

////////////////////仅供参考：具体怎么提示，上面的弹框「仅供参考」////////////////////

/**
 * 跳转到「白名单管理列表页面」(无法保证所有设备系统都能正常跳转，会有无法跳转提示和容错界面)
 */
fun startWhiteListSetting(context: Context): Boolean {
  val intent = Intent()
  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
  var componentName: ComponentName? = null
  try {
    when {
      RomUtil.isMiui() -> {
        intent.action = "miui.intent.action.OP_AUTO_START"
        intent.addCategory(Intent.CATEGORY_DEFAULT)
      }
      RomUtil.isSamsung() -> componentName = getSamSungComponentName()
      RomUtil.isOnePlus() -> componentName = getOnePlusComponentName()
      RomUtil.isEmui() -> componentName = getHuaweiComponentName()
      RomUtil.isVivo() -> componentName = getViVoComponentName()
      RomUtil.isFlyme() -> componentName = getFlyMeComponentName()
      RomUtil.isOppo() -> componentName = getOppoComponentName()
      RomUtil.isQiku() -> componentName = get360ComponentName()
      RomUtil.isSmartisan() -> intent.action = Settings.ACTION_SETTINGS
      RomUtil.isLetv() -> intent.action = "com.letv.android.permissionautoboot"
    }
    if (null != componentName || null != intent.action) {
      intent.component = componentName
      context.startActivity(intent)
      return true
    }
  } catch (e: Exception) {
  }
  return false
}

//region Brand ComponentName
private fun getHuaweiComponentName(): ComponentName {
  when {
    //鸿蒙系统跳转失败容错：直接打开手机管家首页
    isHarmonyOS() -> return ComponentName(
      "com.huawei.systemmanager",
      "com.huawei.systemmanager.mainscreen.MainScreenActivity"
    )
    (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && Build.VERSION.SDK_INT < Build.VERSION_CODES.P) -> return ComponentName(
      "com.huawei.systemmanager",
      "com.huawei.systemmanager.appcontrol.activity.StartupAppControlActivity"
    )
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.P -> return ComponentName(
      "com.huawei.systemmanager",
      "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity"
    )
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> return ComponentName(
      "com.huawei.systemmanager",
      "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity"
    )
    else -> return ComponentName(
      "com.huawei.systemmanager",
      "com.huawei.systemmanager.optimize.process.ProtectActivity"
    )
  }
}

private fun getSamSungComponentName(): ComponentName = ComponentName(
  "com.samsung.android.sm_cn",
  "com.samsung.android.sm.ui.cstyleboard.SmartManagerDashBoardActivity"
)

private fun getViVoComponentName(): ComponentName? {
  return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    ComponentName.unflattenFromString("com.iqoo.secure/.safeguard.PurviewTabActivity")
  } else {
    ComponentName(
      "com.iqoo.secure",
      "com.iqoo.secure.ui.phoneoptimize.SoftwareManagerActivity"
    )
  }
}

private fun getOppoComponentName(): ComponentName {
  return ComponentName(
    "com.coloros.safecenter",
    "com.coloros.safecenter.startupapp.StartupAppListActivity"
  )
}

private fun getOnePlusComponentName(): ComponentName = ComponentName(
  "com.oneplus.security",
  "com.oneplus.security.chainlaunch.view.ChainLaunchAppListActivity"
)

private fun get360ComponentName(): ComponentName = ComponentName(
  "com.yulong.android.coolsafe",
  "com.yulong.android.coolsafe.ui.activity.autorun.AutoRunListActivity"
)

private fun getFlyMeComponentName(): ComponentName? =
  ComponentName.unflattenFromString("com.meizu.safe/.permission.PermissionMainActivity")
//endregion
