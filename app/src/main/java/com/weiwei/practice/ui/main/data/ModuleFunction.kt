package com.weiwei.practice.ui.main.data

import androidx.appcompat.app.AppCompatActivity

/**
 * @author Weicools
 *
 * @date 2021.07.10
 */
class ModuleFunction{
  var iconResource: Int=0
  var title: String=""
  var description: String=""
  var clickAction: ((AppCompatActivity) -> Unit)={}
}