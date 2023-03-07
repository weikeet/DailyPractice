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

package com.weiwei.core.androidx;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author weiwei
 * @date 2023.03.07
 */
public final class ThrowableHandler {

  public static boolean hasStackTraceElement(final Throwable t, final String... traces) {
    return hasStackTraceElement(t, new HashSet<>(Arrays.asList(traces)));
  }

  public static boolean hasStackTraceElement(final Throwable t, final Set<String> traces) {
    if (null == t || null == traces || traces.isEmpty()) {
      return false;
    }

    for (final StackTraceElement element : t.getStackTrace()) {
      if (traces.contains(element.getClassName() + "." + element.getMethodName())) {
        return true;
      }
    }

    return hasStackTraceElement(t.getCause(), traces);
  }

  @SafeVarargs
  public static boolean isCausedBy(final Throwable t, final Class<? extends Throwable>... causes) {
    return isCausedBy(t, new HashSet<>(Arrays.asList(causes)));
  }

  public static boolean isCausedBy(final Throwable t, final Set<Class<? extends Throwable>> causes) {
    if (null == t) {
      return false;
    }

    if (causes.contains(t.getClass())) {
      return true;
    }

    return isCausedBy(t.getCause(), causes);
  }

}
