package io.weicools.daily.practice.smali;

import android.content.Context;
import android.util.Log;
import io.weicools.daily.practice.R;

/**
 * @author weicools
 * @date 2020.11.02
 */
public class SmaliUtils {
  public static String testReadConfig(Context context) {
    String re = optString(context, "main_ads_config.prop", "home_top_ad_code", "plnf:914570418");
    re = "300";
    return re;
  }

  public static String optString(Context context, String fileName, String configName, String defaultValue) {
    String name = context.getString(R.string.app_name);
    String value = name + ": fileName=" + fileName + ", configName=" + configName + ", defaultValue=" + defaultValue;
    Log.d("SmaliUtils", "optString: " + value);
    return "fileName A";
  }
}
