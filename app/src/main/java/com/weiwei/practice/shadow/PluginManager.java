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

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author weiwei
 * @date 2022.07.01
 */
public class PluginManager {
  private static final PluginManager INSTANCE = new PluginManager();

  public static PluginManager get() {
    return INSTANCE;
  }

  private PluginManager() {
  }

  private Context context;

  private Resources resources;
  private DexClassLoader dexClassLoader;

  public void setContext(Context context) {
    this.context = context.getApplicationContext();
  }

  public Resources getResources() {
    return resources;
  }

  public DexClassLoader getDexClassLoader() {
    return dexClassLoader;
  }

  public void loadPlugin(String apkName) {
    File dexFile = context.getExternalFilesDir("dex");
    if (!dexFile.exists()) {
      dexFile.mkdirs();
    }

    String pluginPath = dexFile.getAbsolutePath() + File.separator + apkName;
    dexClassLoader = new DexClassLoader(pluginPath, context.getDir("dex", Context.MODE_PRIVATE).getAbsolutePath(), null, context.getClassLoader());

    try {
      AssetManager assetManager = AssetManager.class.newInstance();
      // assetManager.getClass().getDeclaredMethod("addAssetPath", String.class).invoke(assetManager, pluginPath);
      Method addAssetPathMethod = AssetManager.class.getMethod("addAssetPath", String.class);
      addAssetPathMethod.invoke(assetManager, pluginPath);
      resources = new Resources(assetManager, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
