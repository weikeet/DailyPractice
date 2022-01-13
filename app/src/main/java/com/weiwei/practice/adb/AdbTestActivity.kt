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

package com.weiwei.practice.adb

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import com.weiwei.core.app.BaseActivity
import com.weiwei.fluent.widget.button
import com.weiwei.fluent.widget.editText
import com.weiwei.fluent.widget.linearLayout
import com.weiwei.fluent.widget.extensions.dp
import com.weiwei.fluent.widget.extensions.gravity_centerHorizontal
import com.weiwei.fluent.widget.params.linearParams
import dadb.Dadb

/**
 * @author weiwei
 * @date 2021.12.20
 */
class AdbTestActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val v = linearLayout {
            orientation = LinearLayout.VERTICAL
            gravity = gravity_centerHorizontal

            val editText = editText {
                layoutParams = linearParams {
                    topMargin = 16.dp
                    leftMargin = 8.dp
                    rightMargin = 8.dp
                }
            }

            button {
                layoutParams = linearParams {
                    topMargin = 16.dp
                    leftMargin = 8.dp
                    rightMargin = 8.dp
                }

                setOnClickListener {
                    val dadb = Dadb.discover("localhost")
                    if (dadb == null) {
                        Dadb.create("localhost", 5555).use { dadb ->
                            Toast.makeText(applicationContext, "create localhost 5555 success", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(applicationContext, "discover localhost adb", Toast.LENGTH_SHORT).show()
                    }
                }

                text = "Check adb server"
            }

            button {
                layoutParams = linearParams {
                    topMargin = 16.dp
                    leftMargin = 8.dp
                    rightMargin = 8.dp
                }

                setOnClickListener {
                    val pkg = editText.editableText.toString()
                    Dadb.create("localhost", 5555).use { dadb ->
                        Toast.makeText(applicationContext, "create localhost 5555 success", Toast.LENGTH_SHORT).show()
                        dadb.uninstall(pkg)
                    }

                    // val dadb = Dadb.discover("localhost")
                    // if (dadb != null) {
                    //     dadb.uninstall(pkg)
                    // } else {
                    //     Toast.makeText(applicationContext, "Please create adb", Toast.LENGTH_SHORT).show()
                    // }
                }

                text = "Uninstall app"
            }
        }

        setContentView(v)
    }
}