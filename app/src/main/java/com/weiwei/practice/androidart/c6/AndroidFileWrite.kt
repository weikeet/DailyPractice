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

package com.weiwei.practice.androidart.c6

import android.content.Context
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/**
 * @author weiwei
 * @date 2023.03.12
 *
 * 数据存储方案持久化 - 文件存储
 */
class AndroidFileWrite {

  /**
   * save to /data/data/package_name/files/data.txt
   */
  fun writeFile(context: Context) {
    val data = "Hello World"

    // 1
    context.openFileOutput("data.txt", Context.MODE_PRIVATE).use {
      it.write(data.toByteArray())
    }

    var fos: FileOutputStream? = null
    var bw: BufferedWriter? = null
    try {
      val stream = context.openFileOutput("data", Context.MODE_PRIVATE)
      fos = stream
      val writer = BufferedWriter(OutputStreamWriter(stream))
      bw = writer
      writer.write(data)
    } catch (e: Exception) {
      e.printStackTrace()
    } finally {
      bw?.close()
      fos?.close()
    }
  }

  fun load(context: Context) {
    // 1
    // context.openFileInput("data.txt").use {
    //   val available = it.available()
    //   val buffer = ByteArray(available)
    //   it.read(buffer)
    //   val data = String(buffer)
    // }

    val builder: StringBuilder = StringBuilder()
    var fis: FileInputStream? = null
    var br: BufferedReader? = null
    try {
      val stream = context.openFileInput("data.txt")
      fis = stream
      val reader = BufferedReader(InputStreamReader(stream))
      br = reader
      // val data = reader.readLine()
      var line: String?
      while (reader.readLine().also { line = it } != null) {
        builder.append(line)
      }
    } catch (e: Exception) {
      e.printStackTrace()
    } finally {
      br?.close()
      fis?.close()
    }
  }

}