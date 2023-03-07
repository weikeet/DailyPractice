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

package com.weiwei.core.androidx.toast;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author weiwei
 * @date 2023.03.07
 *
 * 1.Wrapper Context {@link Context#getApplicationContext()}
 * 2.Wrapper ApplicationContext {@link Context#getSystemService(String)}
 */
public class ToastContextCompat extends ContextWrapper {

  @NonNull
  private final Toast toast;

  @Nullable
  private ToastCompat.BadTokenListener badTokenListener = null;

  public ToastContextCompat(Context base, Toast toast) {
    super(base);
    this.toast = toast;
  }

  public void setBadTokenListener(ToastCompat.BadTokenListener listener) {
    this.badTokenListener = listener;
  }

  @Override
  public Context getApplicationContext() {
    return new ApplicationContextWrapper(getBaseContext().getApplicationContext());
  }

  /**
   * Wrapper {@link Context#getSystemService(String)}
   */
  private final class ApplicationContextWrapper extends ContextWrapper {

    public ApplicationContextWrapper(Context base) {
      super(base);
    }

    @Override
    public Object getSystemService(String name) {
      if (Context.WINDOW_SERVICE.equals(name)) {
        // noinspection ConstantConditions
        return new WindowManagerWrapper((WindowManager) getBaseContext().getSystemService(name));
      }
      return super.getSystemService(name);
    }
  }

  private final class WindowManagerWrapper implements WindowManager {
    private static final String TAG = "WindowManagerWrapper";

    @NonNull
    private final WindowManager base;

    public WindowManagerWrapper(@NonNull WindowManager base) {
      this.base = base;
    }

    @Override
    public Display getDefaultDisplay() {
      return base.getDefaultDisplay();
    }

    @Override
    public void removeViewImmediate(View view) {
      base.removeViewImmediate(view);
    }

    @Override
    public void addView(View view, ViewGroup.LayoutParams params) {
      try {
        Log.d(TAG, "WindowManager's addView(view, params) has been hooked.");
        base.addView(view, params);
      } catch (BadTokenException e) {
        Log.i(TAG, e.getMessage());
        if (badTokenListener != null) {
          badTokenListener.onBadTokenCaught(toast);
        }
      } catch (Throwable throwable) {
        Log.e(TAG, "addView:", throwable);
      }
    }

    @Override
    public void updateViewLayout(View view, ViewGroup.LayoutParams params) {
      base.updateViewLayout(view, params);
    }

    @Override
    public void removeView(View view) {
      base.removeView(view);
    }
  }
}
