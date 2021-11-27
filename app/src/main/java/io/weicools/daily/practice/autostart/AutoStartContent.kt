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

package io.weicools.daily.practice.autostart

import io.weicools.daily.practice.ui.main.data.ModuleContent
import io.weicools.daily.practice.ui.main.data.ModuleFunction

/**
 * @author weiwei
 * @date 2021.11.27
 *
 * Android正确的保活方案，不要掉进保活需求死循环陷进
 * https://juejin.cn/post/7003992225575075876
 * https://gist.github.com/TheMelody/5044dd1b697707b18e94b89f97f55db6
 */
class AutoStartContent : ModuleContent() {
  override fun setupFunction(function: ModuleFunction) {
    function.apply {
      title = "Open auto start"
      description = "打开自动页面"
      clickAction = {
        openWhiteListDialog(it)
      }
    }
  }
}