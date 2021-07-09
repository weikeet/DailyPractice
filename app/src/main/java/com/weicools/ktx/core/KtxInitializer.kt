package com.weicools.ktx.core

import android.content.Context

object KtxInitializer {
  var appContext: Context? = null

  val context: Context
    get() = if (appContext == null) throw RuntimeException("Please init application context: KtxInitializer.appContext = applicationContext") else appContext!!
}