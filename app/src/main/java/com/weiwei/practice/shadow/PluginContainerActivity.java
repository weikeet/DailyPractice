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

package com.weiwei.practice.shadow;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.weiwei.shadow.ShadowLifecycle;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author weiwei
 * @date 2022.07.01
 */
public class PluginContainerActivity extends AppCompatActivity {
  private static final String TAG = "PluginContainerActivity";

  private ShadowLifecycle pluginActivity;

  @Override
  public ClassLoader getClassLoader() {
    return PluginManager.get().getDexClassLoader();
  }

  @Override
  public Resources getResources() {
    return PluginManager.get().getResources();
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.d(TAG, "onCreate: ");

    try {
      Class<?> pluginActivityClass = getClassLoader().loadClass("com.weiwei.shadow.plugin.sample.SamplePluginActivity");
      Constructor<?> pluginActivityConstructor = pluginActivityClass.getConstructor();
      Object instance = pluginActivityConstructor.newInstance();
      pluginActivity = (ShadowLifecycle) instance;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }

    pluginActivity.attachHostActivity(this);
    pluginActivity.onCreate(savedInstanceState);
  }

  @Override
  protected void onStart() {
    super.onStart();
    Log.d(TAG, "onStart: ");

    pluginActivity.onStart();
  }

  @Override
  protected void onResume() {
    super.onResume();
    Log.d(TAG, "onResume: ");

    pluginActivity.onResume();
  }

  @Override
  protected void onPause() {
    super.onPause();
    Log.d(TAG, "onPause: ");

    pluginActivity.onPause();
  }

  @Override
  protected void onStop() {
    super.onStop();
    Log.d(TAG, "onStop: ");

    pluginActivity.onStop();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.d(TAG, "onDestroy: ");

    pluginActivity.onDestroy();
  }

  @Override
  public void onAttachedToWindow() {
    super.onAttachedToWindow();

    pluginActivity.onAttachedToWindow();
  }

  @Override
  public void onWindowFocusChanged(boolean hasFocus) {
    super.onWindowFocusChanged(hasFocus);

    pluginActivity.onWindowFocusChanged(hasFocus);
  }
}
