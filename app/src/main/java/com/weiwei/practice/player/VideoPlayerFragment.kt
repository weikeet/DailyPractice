/*
 * Copyright (c) 2020 Weicools
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
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.weiwei.practice.R
import com.weiwei.practice.binding.viewBinding
import com.weiwei.practice.databinding.PlayerFragmentVideoBinding
import com.weiwei.practice.window.doOnApplyWindowInsets
import com.weiwei.practice.window.systemBarBottom

/**
 * @author weiwei
 * @date 2022.01.09
 */
class VideoPlayerFragment : Fragment() {

  private val binding: PlayerFragmentVideoBinding by viewBinding(PlayerFragmentVideoBinding::bind)

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.player_fragment_video, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    view.doOnApplyWindowInsets { windowInsets ->
      binding.guidelineBottom.setGuidelineEnd(windowInsets.systemBarBottom)
    }

    val lifeVideoViewHolder = LifeVideoViewHolder(viewLifecycleOwner, binding.videoView, binding.coverView)

    lifeVideoViewHolder.prepareRaw(
      rawResource = R.raw.melody_video_ocean_waves,
      playWhenReady = true,
      onVideoPrepared = { mp ->
        mp.isLooping = true
        mp.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING)
      }
    )

    // don't support asset://
    // videoViewWrapper.prepareAssets(
    //   videoPath = "Wakeup/melody_video_happy_ukulele.mp4",
    //   playWhenReady = false,
    //   onVideoPrepared = {mp->
    //     mp.isLooping = true
    //     mp.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING)
    //   }
    // )

    binding.btn1.setOnClickListener {
    }

    binding.btn2.setOnClickListener {
      lifeVideoViewHolder.start()
    }

    binding.btn3.setOnClickListener {
      lifeVideoViewHolder.resume()
    }

    binding.btn4.setOnClickListener {
      lifeVideoViewHolder.pause()
    }

    binding.btn5.setOnClickListener {
      lifeVideoViewHolder.stop()
    }
  }

}