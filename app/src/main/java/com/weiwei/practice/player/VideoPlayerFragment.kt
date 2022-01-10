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
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.weiwei.practice.R
import com.weiwei.practice.binding.viewBinding
import com.weiwei.practice.databinding.PlayerFragmentVideoBinding


/**
 * @author weiwei
 * @date 2022.01.09
 */
class VideoPlayerFragment : Fragment() {
  companion object {
    fun newInstance(): VideoPlayerFragment {
      val args = Bundle()

      val fragment = VideoPlayerFragment()
      fragment.arguments = args
      return fragment
    }
  }

  private val binding: PlayerFragmentVideoBinding by viewBinding(PlayerFragmentVideoBinding::bind)

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.player_fragment_video, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.apply {
      btn1.setOnClickListener {
        Log.d("weiweix", "onViewCreated: setVideoURI")
        val videoResId = R.raw.melody_video_ocean_waves
        val localUrl = "android.resource://" + requireContext().packageName.toString() + "/" + videoResId
        videoView.setVideoURI(Uri.parse(localUrl))
        videoView.setOnPreparedListener { mp ->
          mp.isLooping = true
          mp.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING)
          videoView.setOnInfoListener { player, what, extra ->
            if (what === MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
              Log.d("weiweix", "onViewCreated: onInfo start")
              // coverView.setVisibility(View.GONE)
            }
            false
          }
          Log.d("weiweix", "onViewCreated: Prepared")
        }
      }

      btn2.setOnClickListener {
        Log.d("weiweix", "onViewCreated: start")
        videoView.start()
      }

      btn3.setOnClickListener {
        Log.d("weiweix", "onViewCreated: resume")
        videoView.resume()
      }

      btn4.setOnClickListener {
        Log.d("weiweix", "onViewCreated: pause")
        videoView.pause()
      }

      btn5.setOnClickListener {
        Log.d("weiweix", "onViewCreated: stopPlayback")
        videoView.stopPlayback()
      }
    }
  }
}