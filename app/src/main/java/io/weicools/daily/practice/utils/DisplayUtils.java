package io.weicools.daily.practice.utils;

import android.content.res.Resources;

/**
 * @author weicools
 * @date 2020.08.19
 */
public class DisplayUtils {
  public static int getDisplayWidth() {
    return Resources.getSystem().getDisplayMetrics().widthPixels;
  }

  public static int getDisplayHeight() {
    return Resources.getSystem().getDisplayMetrics().heightPixels;
  }
}
