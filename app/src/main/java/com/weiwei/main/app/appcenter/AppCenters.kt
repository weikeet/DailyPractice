/*
 * Copyright (c) 2020 Weiwei
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *    https://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.weiwei.main.app.appcenter

import android.app.Application
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.AbstractCrashesListener
import com.microsoft.appcenter.crashes.Crashes
import com.microsoft.appcenter.crashes.ingestion.models.ErrorAttachmentLog
import com.microsoft.appcenter.crashes.model.ErrorReport
import com.weiwei.main.app.track.PageViewedTrack
import timber.log.Timber

/**
 * @author weiwei
 * @date 2023.04.07
 */
object AppCenters {

  fun initialize(application: Application) {

    AppCenter.start(
      application, "099eeecc-024e-4815-ada2-c5fdc4b4d0f8",
      Analytics::class.java, Crashes::class.java
    )

    Crashes.setListener(object : AbstractCrashesListener() {
      override fun getErrorAttachments(report: ErrorReport?): MutableIterable<ErrorAttachmentLog>? {
        if (true) return null
        val attachmentLogList: ArrayList<ErrorAttachmentLog> = ArrayList()

        val pageViewedStateJson = PageViewedTrack.readPageViewedStateJson()
        if (pageViewedStateJson != null) {
          val attachmentLog = ErrorAttachmentLog.attachmentWithText(pageViewedStateJson, "PageViewedState.json")
          attachmentLogList.add(attachmentLog)
        }

        // val testTopicJson = TestSolutions.readTestTopicJson()
        // if (testTopicJson != null) {
        //   val attachmentLog = ErrorAttachmentLog.attachmentWithText(testTopicJson, "TestTopic.json")
        //   attachmentLogList.add(attachmentLog)
        // }

        Timber.d("getErrorAttachments: report=$report, size=${attachmentLogList.size}")

        return if (attachmentLogList.isEmpty()) null else attachmentLogList
      }

      override fun onSendingSucceeded(report: ErrorReport?) {
        Timber.d("onSendingSucceeded: report=$report")
      }

      override fun onSendingFailed(report: ErrorReport?, e: Exception?) {
        Timber.d("onSendingFailed: report=$report, e=$e")
      }
    })
  }

}