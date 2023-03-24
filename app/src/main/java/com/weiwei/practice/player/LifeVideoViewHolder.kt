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

import android.media.MediaPlayer
import android.util.Log
import android.widget.ImageView
import android.widget.VideoView
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope

/**
 * @author weiwei
 * @date 2023.03.24
 */
class LifeVideoViewHolder(
  private val lifecycleOwner: LifecycleOwner,
  private val videoView: VideoView,
  private val coverView: ImageView? = null,
) : LifecycleEventObserver {

  @Suppress("PropertyName")
  var TAG = "VideoViewWrapper"

  var videoUrl: String? = null

  private val context = videoView.context

  // private fun getAssetsResourceUrl(videoPath: String): String {
  //   return "file:///android_asset/$videoPath"
  // }

  private fun getRawResourceUrl(rawResource: Int): String {
    return "android.resource://${context.packageName}/$rawResource"
  }

  // fun prepareAssets(
  //   videoPath: String,
  //   playWhenReady: Boolean = false,
  //   onVideoPrepared: ((mediaPlayer: MediaPlayer) -> Unit)? = null,
  //   onVideoInfoChanged: ((player: MediaPlayer, what: Int, extra: Int) -> Boolean)? = null,
  // ) {
  //   prepare(
  //     videoUrl = getAssetsResourceUrl(videoPath),
  //     playWhenReady = playWhenReady,
  //     onVideoPrepared = onVideoPrepared,
  //     onVideoInfoChanged = onVideoInfoChanged,
  //   )
  // }

  fun prepareRaw(
    rawResource: Int,
    playWhenReady: Boolean = false,
    onVideoPrepared: ((mediaPlayer: MediaPlayer) -> Unit)? = null,
    onVideoInfoChanged: ((player: MediaPlayer, what: Int, extra: Int) -> Boolean)? = null,
  ) {
    prepare(
      videoUrl = getRawResourceUrl(rawResource),
      playWhenReady = playWhenReady,
      onVideoPrepared = onVideoPrepared,
      onVideoInfoChanged = onVideoInfoChanged,
    )
  }

  fun prepare(
    videoUrl: String,
    playWhenReady: Boolean = false,
    onVideoPrepared: ((mediaPlayer: MediaPlayer) -> Unit)? = null,
    onVideoInfoChanged: ((player: MediaPlayer, what: Int, extra: Int) -> Boolean)? = null,
  ) {
    Log.d(TAG, "prepare: videoUrl=$videoUrl, playWhenReady=$playWhenReady")

    lifecycleOwner.lifecycle.addObserver(this)
    if (coverView != null) {
      coverView.isVisible = true
      lifecycleOwner.lifecycleScope.launchWhenStarted {
        VideoCacheManager.getCoverCacheFlow(videoUrl).collect { cover ->
          coverView.setImageBitmap(cover)
        }
      }
    }

    videoView.alpha = 0f
    videoView.setVideoPath(videoUrl)
    videoView.setOnPreparedListener { mediaPlayer ->
      Log.d(TAG, "prepare: onPrepared mediaPlayer=$mediaPlayer")

      // mediaPlayer.setOnInfoListener { mp, what, extra ->
      //   if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
      //     Log.d(TAG, "prepare: onInfo mp=$mp, what=$what, extra=$extra")
      //   }
      //   onVideoInfoChanged?.invoke(mp, what, extra) ?: false
      // }
      //
      // mediaPlayer.setOnVideoSizeChangedListener { mp, width, height ->
      //   Log.d(TAG, "prepare: onVideoSizeChanged mp=$mp, width=$width, height=$height")
      // }
      // // mediaPlayer.setOnBufferingUpdateListener { mp, percent ->
      // //   Log.d(TAG, "prepare: onBufferingUpdate mp=$mp, percent=$percent")
      // // }
      // mediaPlayer.setOnErrorListener { mp, what, extra ->
      //   Log.d(TAG, "prepare: onError mp=$mp, what=$what, extra=$extra")
      //   false
      // }
      // mediaPlayer.setOnCompletionListener { mp ->
      //   Log.d(TAG, "prepare: onCompletion mp=$mp")
      // }
      // mediaPlayer.setOnSeekCompleteListener { mp ->
      //   Log.d(TAG, "prepare: onSeekComplete mp=$mp")
      // }

      onVideoPrepared?.invoke(mediaPlayer)
      // mediaPlayer.isLooping = isLooping
      // mediaPlayer.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING)

      if (playWhenReady) {
        videoView.start()
      }
    }

    videoView.setOnErrorListener { mp, what, extra ->
      Log.d(TAG, "prepare: onError-view mp=$mp, what=$what, extra=$extra")
      false
    }
    videoView.setOnInfoListener { mp, what, extra ->
      Log.d(TAG, "prepare: onInfo-view mp=$mp, what=$what, extra=$extra")
      coverView?.isVisible = false
      videoView.alpha = 1f
      onVideoInfoChanged?.invoke(mp, what, extra) ?: false
    }
    videoView.setOnCompletionListener { mp ->
      Log.d(TAG, "prepare: onCompletion-view mp=$mp")
    }
  }

  override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
    if (event == Lifecycle.Event.ON_DESTROY) {
      stop()
      release()
      VideoCacheManager.releaseCache(videoUrl)
    }
  }

  //region wrapper
  fun isPlaying(): Boolean {
    return videoView.isPlaying
  }

  fun getCurrentPosition(): Int {
    return videoView.currentPosition
  }

  fun seekTo(msec: Int) {
    Log.d(TAG, "seekTo: msec=$msec")

    videoView.seekTo(msec)
  }

  /**
   * 开始播放
   * [start] 可以先调用, 在 prepare 完成之后会自动播放
   * [start] 之后可以调用 [pause] 暂停播放, 再调用 [start] 继续播放
   * [stop] 后需要调用 [resume] + [start] 才能正常播放 (调用无先后顺序)
   */
  fun start() {
    Log.d(TAG, "start: ")

    videoView.start()
  }

  /**
   * [resume] 操作实际上是执行 VideoView.openVideo() 方法
   * prepare 时 setUri 也会执行 VideoView.openVideo() 方法
   */
  fun resume() {
    Log.d(TAG, "resume: ")

    videoView.resume()
  }

  fun pause() {
    Log.d(TAG, "pause: ")

    videoView.pause()
  }

  fun stop() {
    Log.d(TAG, "stop: ")

    videoView.stopPlayback()
  }

  fun release() {
    Log.d(TAG, "release: ")

    videoView.suspend()
  }
  //endregion

}