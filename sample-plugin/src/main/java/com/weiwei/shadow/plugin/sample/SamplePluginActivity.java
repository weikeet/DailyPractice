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

package com.weiwei.shadow.plugin.sample;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.weiwei.shadow.ShadowActivity;

/**
 * @author weiwei
 * @date 2022.07.01
 */
public class SamplePluginActivity extends ShadowActivity {
  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_sample_plugin);

    Button nextButton = findViewById(R.id.toNext);
    nextButton.setOnClickListener(v -> {
      Toast.makeText(hostActivity, "click to next", Toast.LENGTH_SHORT).show();
    });
  }
}
