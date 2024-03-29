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

package com.weiwei.shadow;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;

/**
 * @author weiwei
 * @date 2022.07.01
 */
public interface ShadowLifecycle {
  void onCreate(@Nullable Bundle savedInstanceState);
  void onStart();
  void onResume();
  void onPause();
  void onStop();
  void onDestroy();
  void onAttachedToWindow();
  void onWindowFocusChanged(boolean hasFocus);
  void attachHostActivity(Activity activity);
}
