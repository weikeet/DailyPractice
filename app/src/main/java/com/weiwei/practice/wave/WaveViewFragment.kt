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

package com.weiwei.practice.wave

import android.media.MediaRecorder
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.weiwei.extensions.colorOf
import com.weiwei.practice.R
import com.weiwei.practice.binding.viewBinding
import com.weiwei.practice.databinding.WaveFragmentViewBinding
import com.weiwei.task.scheduler.SchedulerTask
import com.weiwei.task.scheduler.TaskScheduler
import java.io.File
import kotlin.math.log10


/**
 * @author weiwei
 * @date 2022.01.09
 */
class WaveViewFragment : Fragment() {
  companion object {
    fun newInstance(): WaveViewFragment {
      val args = Bundle()

      val fragment = WaveViewFragment()
      fragment.arguments = args
      return fragment
    }
  }

  private val binding: WaveFragmentViewBinding by viewBinding(WaveFragmentViewBinding::bind)

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.wave_fragment_view, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    // FIXME: 2022/1/10 voice
    // binding.voiceLine.start()

    binding.startButton.setOnClickListener {
      binding.apply {
        customWaveLine.setAboveWaveColor(colorOf(R.color.colorAccent11))
        customWaveLine.setBlowWaveColor(colorOf(R.color.colorPrimary))
        customWaveLine.visibility = View.VISIBLE
        customWaveLine.height = 0
        customWaveLine.layoutParams.height = 0
        customWaveLine.showView(true)

        if (height != 0) {
          mHandler.removeCallbacks(stopRunnable)
        }

        height = 0
        mHandler.postDelayed(startRunnable, 5)
      }
    }

    binding.stopButton.setOnClickListener {
      mHandler.removeCallbacks(startRunnable)
      mHandler.postDelayed(stopRunnable, 5)
    }

    startRecord()
  }

  override fun onDestroyView() {
    super.onDestroyView()

    stopRecord()
  }

  private lateinit var mMediaRecorder: MediaRecorder

  private fun startRecord() {
    mMediaRecorder = MediaRecorder()

    // 2.setAudioSource/setVideoSource
    mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC) // 设置麦克风

    // 2.设置输出文件的格式：THREE_GPP/MPEG-4/RAW_AMR/Default
    // THREE_GPP(3gp格式，H263视频/ARM音频编码)、MPEG-4、RAW_AMR(只支持音频且音频编码要求为AMR_NB)
    mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB)
    // 2.设置音频文件的编码：AAC/AMR_NB/AMR_MB/Default 声音的（波形）的采样
    mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT)

    // 3.准备
    try {
      val file = File(requireContext().getExternalFilesDir("AudioMedia"), "hello.log")
      if (!file.exists()) {
        file.createNewFile()
      }
      mMediaRecorder.setOutputFile(file.absolutePath)
    } catch (e: Exception) {
      e.printStackTrace()
    }

    try {
      mMediaRecorder.setMaxDuration(1000 * 60 * 10)
      mMediaRecorder.prepare()
    } catch (e: Exception) {
      e.printStackTrace()
    }

    // 4.开始
    try {
      mMediaRecorder.start()
    } catch (e: Exception) {
      e.printStackTrace()
    }

    // FIXME: 2022/1/10 voice
    // TaskScheduler.scheduleTask(task)
  }

  private fun stopRecord() {
    TaskScheduler.stopScheduleTask(task)

    try {
      mMediaRecorder.stop()
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }

  private val task = object : SchedulerTask(100L, true) {
    override fun onSchedule() {
      val ratio = mMediaRecorder.maxAmplitude.toDouble() / 100
      var db = 0.0 // 分贝
      if (ratio > 1) {
        db = 30 * log10(ratio)
      }
      binding.voiceLine.setVolume(db.toInt())
    }
  }

  private var height = 0

  private val mHandler = Handler(Looper.getMainLooper())

  private val startRunnable = object : Runnable {
    override fun run() {
      // if (height < 50) {
      //   height += 4;
      // }
      if (height >= 0) {
        height -= 1
      }

      val ratio = mMediaRecorder.maxAmplitude.toDouble() / 100
      var db = 25.0 // 分贝
      if (ratio > 1) {
        db = 30 * log10(ratio)
      }
      height = db.toInt()

      binding.customWaveLine.layoutParams.height = height * 2
      binding.customWaveLine.height = height

      mHandler.postDelayed(this, 100)
    }
  }

  private val stopRunnable = object : Runnable {
    override fun run() {
      if (height >= 0) {
        height -= 5
      }

      binding.customWaveLine.layoutParams.height = height * 2
      binding.customWaveLine.height = height
      mHandler.postDelayed(this, 100)

      if (height == 0) {
        binding.customWaveLine.height = 0
        binding.customWaveLine.layoutParams.height = 0
        binding.customWaveLine.showView(false)
      }
    }
  }

}