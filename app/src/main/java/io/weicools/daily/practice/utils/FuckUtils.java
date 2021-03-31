package io.weicools.daily.practice.utils;

import android.animation.ValueAnimator;
import java.util.List;

/**
 * @author weicools
 * @date 2021.01.28
 */
public class FuckUtils {
  public static Object[] convert(List<ValueAnimator> animators) {
    return animators.toArray();
  }
}
