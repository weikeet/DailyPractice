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
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author weiwei
 * @date 2022.07.01
 */
public class ShadowActivity extends Activity implements ShadowLifecycle {
  protected Activity hostActivity;

  @Override
  public void attachHostActivity(Activity activity) {
    hostActivity = activity;
  }

  @Override
  public ClassLoader getClassLoader() {
    return hostActivity.getClassLoader();
  }

  @Override
  public Resources getResources() {
    return hostActivity.getResources();
  }

  @Override
  public void setContentView(View view) {
    hostActivity.setContentView(view);
  }

  @Override
  public void setContentView(int layoutResId) {
    hostActivity.setContentView(layoutResId);
  }

  @Override
  public <T extends View> T findViewById(int id) {
    return hostActivity.findViewById(id);
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    // super.onCreate(savedInstanceState);
    Log.d(getClass().getSimpleName(), "onCreate: ");
  }

  @Override
  public void onStart() {
    // super.onStart();
    Log.d(getClass().getSimpleName(), "onStart: ");
  }

  @Override
  public void onResume() {
    // super.onResume();
    Log.d(getClass().getSimpleName(), "onResume: ");
  }

  @Override
  public void onPause() {
    // super.onPause();
    Log.d(getClass().getSimpleName(), "onPause: ");
  }

  @Override
  public void onStop() {
    // super.onStop();
    Log.d(getClass().getSimpleName(), "onStop: ");
  }

  @Override
  public void onDestroy() {
    // super.onDestroy();
    Log.d(getClass().getSimpleName(), "onDestroy: ");
  }

  @Override
  public void onAttachedToWindow() {
    // super.onAttachedToWindow();
    Log.d(getClass().getSimpleName(), "onAttachedToWindow: ");
  }

  @Override
  public void onWindowFocusChanged(boolean hasFocus) {
    // super.onWindowFocusChanged(hasFocus);
    Log.d(getClass().getSimpleName(), "onWindowFocusChanged: hasFocus=" + hasFocus);
  }
}
