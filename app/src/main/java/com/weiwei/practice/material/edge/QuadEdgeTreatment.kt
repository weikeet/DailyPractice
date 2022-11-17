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

package com.weiwei.practice.material.edge

import com.google.android.material.shape.EdgeTreatment
import com.google.android.material.shape.ShapePath

/**
 * @author weiwei
 * @date 2022.11.17
 */
class QuadEdgeTreatment(val size: Float) : EdgeTreatment() {
  override fun getEdgePath(length: Float, center: Float, f: Float, shapePath: ShapePath) {
    shapePath.quadToPoint(center, size * f, length, 0f)
  }
}