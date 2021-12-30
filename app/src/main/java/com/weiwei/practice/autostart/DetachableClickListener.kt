package com.weiwei.practice.autostart

import android.content.DialogInterface
import android.view.ViewTreeObserver
import androidx.appcompat.app.AlertDialog

/**
 * 防止内存泄漏
 */
open class DetachableClickListener(delegate: DialogInterface.OnClickListener?) : DialogInterface.OnClickListener {

  private var delegateOrNull: DialogInterface.OnClickListener? = delegate

  companion object {
    fun wrap(delegate: DialogInterface.OnClickListener?): DetachableClickListener? {
      return DetachableClickListener(delegate)
    }
  }

  override fun onClick(dialog: DialogInterface?, which: Int) {
    delegateOrNull?.onClick(dialog, which)
  }

  fun clearOnDetach(dialog: AlertDialog) {
    dialog.window?.decorView?.viewTreeObserver
      ?.addOnWindowAttachListener(object : ViewTreeObserver.OnWindowAttachListener {
        override fun onWindowAttached() {
        }

        override fun onWindowDetached() {
          dialog.window?.decorView?.viewTreeObserver?.removeOnWindowAttachListener(this)
          delegateOrNull = null
        }
      })
  }

}