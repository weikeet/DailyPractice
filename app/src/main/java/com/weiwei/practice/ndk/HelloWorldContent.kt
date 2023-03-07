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

package com.weiwei.practice.ndk

import com.weiwei.main.ui.data.ModuleContent
import com.weiwei.main.ui.data.ModuleFunction

class HelloWorldContent : ModuleContent() {
  override fun setupFunction(function: ModuleFunction) {
    function.apply {
      title = "NDK HelloWorld"
      description = "HelloWorld ndk JNI"
      clickAction = {
        val helloWorld = HelloWorld()

        helloWorld.sayHi()

        // 访问静态字段和实例字段
        helloWorld.accessField()
        helloWorld.printName()

        // 访问静态方法和实例方法
        helloWorld.accessMethod()
      }
    }
  }
}