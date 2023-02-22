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

package com.weiwei.practice.flow.download

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File

/**
 * @author weiwei
 * @date 2022.12.10
 */
class FlowSampleDownloader {
  fun download(fileUrl: String): Flow<DownloadStatus> {
    return flow {
      val file = File("")
      val request = Request.Builder().url(fileUrl).get().build()
      val response = OkHttpClient.Builder().build().newCall(request).execute()
      if (response.isSuccessful) {
        response.body!!.let { body ->
          val total = body.contentLength()
          //文件读写
          file.outputStream().use { output ->
            val input = body.byteStream()
            var emittedProgress = 0L
            //使用对应的扩展函数 ，因为该函数最后参为内联函数，因此需要在后面实现对应业务逻辑
            // input.copyTo(output) { bytesCopied ->
            //   //获取下载进度百分比
            //   val progress = bytesCopied * 100 / total
            //   //每下载进度比上次大于5时，通知UI线程
            //   if (progress - emittedProgress > 5) {
            //     delay(100)
            //     //使用Flow对应的emit 发送对应下载进度通知
            //     emit(DownloadStatus.Progress(progress.toInt()))
            //     //记录当前下载进度
            //     emittedProgress = progress
            //   }
            // }
          }

        }
      } else {
        //
      }
    }
  }
}