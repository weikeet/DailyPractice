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

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author weiwei
 * @date 2023.03.07
 */
public final class Intrinsics {
  private Intrinsics() {
  }

  public static <T extends Throwable> T sanitizeStackTrace(T throwable, Class<?> clazz) {
    return sanitizeStackTrace(throwable, clazz.getName());
  }

  public static <T extends Throwable> T sanitizeStackTrace(T throwable, String classNameToDrop) {
    final StackTraceElement[] stackTrace = throwable.getStackTrace();
    final ArrayList<StackTraceElement> newStackTrace = new ArrayList<>(stackTrace.length);
    //noinspection ManualArrayToCollectionCopy
    for (final StackTraceElement element : stackTrace) {
      //noinspection UseBulkOperation
      newStackTrace.add(element);
    }

    final Iterator<StackTraceElement> iterator = newStackTrace.iterator();
    //noinspection Java8CollectionRemoveIf
    while (iterator.hasNext()) {
      final StackTraceElement ste = iterator.next();
      if (classNameToDrop.equals(ste.getClassName())) {
        iterator.remove();
      }
    }

    if (stackTrace.length != newStackTrace.size()) {
      throwable.setStackTrace(newStackTrace.toArray(new StackTraceElement[0]));
    }

    return throwable;
  }
}
