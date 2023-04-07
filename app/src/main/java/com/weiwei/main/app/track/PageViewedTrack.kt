package com.weiwei.main.app.track

import android.app.Application
import androidx.annotation.WorkerThread
import com.google.gson.Gson
import com.weiwei.core.app.mainContext
import com.weiwei.core.track.PageViewedReportResult
import com.weiwei.core.track.PageViewedReportState
import com.weiwei.core.track.PageViewedSaveState
import com.weiwei.core.track.PageViewedStateMonitor
import com.weiwei.core.track.PageViewedTracker
import com.weiwei.main.app.crashes.AppCrashes
import timber.log.Timber
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author weiwei
 * @date 2023.03.30
 */
object PageViewedTrack : AppCrashes.CrashesCallback {

  private var pageViewedStateMonitor: PageViewedStateMonitor? = null

  private val pageViewedSaveStateList: LinkedList<PageViewedSaveState> = LinkedList()

  fun initialize(application: Application) {
    if (pageViewedStateMonitor != null) {
      return
    }

    val stateMonitor = PageViewedStateMonitor()
    stateMonitor.onPageViewedStateChange = { page, isVisible ->
      if (page is PageViewedTracker) {
        val pageViewedState = PageViewedSaveState(
          dateTime = System.currentTimeMillis(),
          trackId = page.trackId,
          isVisible = isVisible,
          extraPrams = page.extraPrams,
        )
        if (pageViewedSaveStateList.size >= 64) {
          pageViewedSaveStateList.removeFirst()
        }
        pageViewedSaveStateList.add(pageViewedState)
      }
    }
    application.registerActivityLifecycleCallbacks(stateMonitor)

    pageViewedStateMonitor = stateMonitor

    AppCrashes.registerCallback(this)
  }

  fun trackViewedState(trackId: String, isVisible: Boolean = true) {
    if (trackId.isEmpty()) {
      return
    }
    val pageViewedState = PageViewedSaveState(
      dateTime = System.currentTimeMillis(),
      trackId = trackId,
      isVisible = isVisible,
    )
    if (pageViewedSaveStateList.size >= 64) {
      pageViewedSaveStateList.removeFirst()
    }
    pageViewedSaveStateList.add(pageViewedState)
  }

  override fun uncaughtException(t: Thread, e: Throwable) {
    writePageViewedStateJson()
  }

  private fun isAppForeground(): Boolean {
    return pageViewedStateMonitor?.isAppForeground ?: false
  }

  @WorkerThread
  fun readPageViewedStateJson(): String? {
    try {
      val jsonFile = getPageViewedStateJsonFile()
      if (jsonFile == null) {
        Timber.d("readPageViewedStateJson: jsonFile is null")
        return null
      }

      val json: String = jsonFile.readText()

      Timber.d("readPageViewedStateJson: success")

      return json
    } catch (e: Exception) {
      Timber.e("readPageViewedStateJson:", e)
    }
    return null
  }

  @WorkerThread
  private fun writePageViewedStateJson() {
    try {
      val jsonFile = getPageViewedStateJsonFile()
      if (jsonFile == null) {
        Timber.d("writePageViewedStateJson: jsonFile is null")
        return
      }

      val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
      val reportStateList: ArrayList<PageViewedReportState> = ArrayList(pageViewedSaveStateList.size)

      val iterator = pageViewedSaveStateList.iterator()
      while (iterator.hasNext()) {
        val pageViewedState = iterator.next()
        val reportState = PageViewedReportState(
          dateText = sdf.format(Date(pageViewedState.dateTime)),
          trackId = pageViewedState.trackId,
          isVisible = pageViewedState.isVisible,
          extraPrams = pageViewedState.extraPrams,
        )
        reportStateList.add(reportState)
      }

      // val type: Type = object : TypeToken<ArrayList<PageViewedReportState>>() {}.type
      // val json: String = Gson().toJson(reportStateList, type)

      val reportResult = PageViewedReportResult(
        isForeground = isAppForeground(),
        pageViewedStateList = reportStateList,
      )
      val json = Gson().toJson(reportResult)

      FileWriter(jsonFile).use { writer -> writer.write(json) }

      Timber.d("writePageViewedStateJson: success")
    } catch (e: Exception) {
      Timber.e("writePageViewedStateJson:", e)
    }
  }

  private fun getPageViewedStateJsonFile(): File? {
    val targetDir = mainContext.getExternalFilesDir("PageViewedState") ?: return null
    if (!targetDir.exists()) {
      targetDir.mkdirs()
    }
    return File(targetDir, "data.json")
  }

}