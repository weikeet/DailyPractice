package io.weicools.daily.practice.room.data;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by weicools on 2017/12/30.
 * <p>
 * Type converters. Converts a list of {@link String} to a {@link String} and back.
 */

public class StringTypeConverter {
  @TypeConverter
  public static String stringListToString(List<String> stringList) {
    return new Gson().toJson(stringList);
  }

  @TypeConverter
  public static String stringToStringList(String string) {
    Type listType = new TypeToken<ArrayList<String>>() {
    }.getType();
    return new Gson().fromJson(string, listType);
  }
}
