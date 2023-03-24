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

package com.weiwei.practice.player

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.util.Log
import androidx.annotation.WorkerThread
import com.bumptech.glide.Glide
import com.weiwei.core.app.mainContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author weiwei
 * @date 2023.03.24
 */
object VideoCacheManager {
  private const val TAG = "VideoCacheManager"

  private val context = mainContext

  private val externalScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

  private val coverCacheState: HashMap<String, MutableStateFlow<Bitmap?>> = HashMap()

  private fun getAssetsResourceUrl(videoPath: String): String {
    return "file:///android_asset/$videoPath"
  }

  private fun getRawResourceUrl(context: Context, rawResource: Int): String {
    return "android.resource://${context.packageName}/$rawResource"
  }

  fun loadAssetsCover(videoPath: String): StateFlow<Bitmap?> {
    return loadCover(getAssetsResourceUrl(videoPath))
  }

  fun loadRawCover(context: Context, rawResource: Int): StateFlow<Bitmap?> {
    return loadCover(getRawResourceUrl(context, rawResource))
  }

  fun loadCover(videoUrl: String): StateFlow<Bitmap?> {
    val cacheFlow = coverCacheState[videoUrl]
    if (cacheFlow != null) {
      return cacheFlow
    }

    val newCacheFlow: MutableStateFlow<Bitmap?> = MutableStateFlow(null)
    coverCacheState[videoUrl] = newCacheFlow

    externalScope.launch {
      val bitmap = withContext(Dispatchers.IO) {
        getVideoMetadataViaGlide(videoUrl)
      }
      newCacheFlow.value = bitmap
    }

    return newCacheFlow
  }

  fun getCoverCacheFlow(videoUrl: String): StateFlow<Bitmap?> {
    val cacheFlow = coverCacheState[videoUrl]
    if (cacheFlow == null) {
      Log.d(TAG, "getCoverCacheFlow: cacheFlow == null")
      return loadCover(videoUrl)
    }
    return cacheFlow
  }

  fun releaseCache(videoUrl: String?) {
    if (videoUrl == null) return
    coverCacheState.remove(videoUrl)?.value = null
  }

  @WorkerThread
  private fun getVideoMetadataViaGlide(videoUrl: String): Bitmap? {
    val start = System.currentTimeMillis()
    var bitmap: Bitmap? = null
    try {
      bitmap = Glide.with(VideoCacheManager.context)
        .asBitmap()
        .load(videoUrl)
        .submit()
        .get()
      val endTime = System.currentTimeMillis()
      Log.d(TAG, "getVideoMetadataViaGlide: bitmap=$bitmap, t=${endTime - start}, w=${bitmap?.width}, h=${bitmap?.height}, videoUrl=$videoUrl")
    } catch (e: Exception) {
      Log.d(TAG, "getVideoMetadataViaGlide: e=${e.message}, videoUrl=$videoUrl")
    }
    return bitmap
  }

  @WorkerThread
  private fun getVideoMetadata(videoUrl: String): Bitmap? {
    var bitmap: Bitmap? = null
    val retriever = MediaMetadataRetriever()
    try {
      //根据url获取缩略图
      retriever.setDataSource(videoUrl, HashMap())
      //获得第一帧图片
      bitmap = retriever.frameAtTime
      Log.d(TAG, "getVideoMetadataBitmap: bitmap=$bitmap, w=${bitmap?.width}, h=${bitmap?.height}, videoUrl=$videoUrl")
    } catch (e: Exception) {
      Log.d(TAG, "getVideoMetadataBitmap: e=${e.message}, videoUrl=$videoUrl")
    } finally {
      retriever.release()
    }
    return bitmap
  }

}