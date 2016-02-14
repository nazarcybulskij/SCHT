package ua.te.hackathon.smartcity2015.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Paul Zin
 */
public class PrefsUtil {
  private static final String PREFS_KEY = "ua.te.hackathon.smartcity2015.PREFS";
  private static final int PREFS_MODE = Context.MODE_PRIVATE;

  private static final String strDefault = null;
  private static final int intDefault = 0;
  private static final long longDefault = 0L;
  private static final boolean boolDefault = false;

  public static String getString(@NonNull Context context, String key, String defaultValue) {
    return context.getSharedPreferences(PREFS_KEY, PREFS_MODE)
        .getString(key, defaultValue);
  }

  public static String getString(@NonNull Context context, String key) {
    return getString(context, key, strDefault);
  }

  public static int getInt(@NonNull Context context, String key, int defaultValue) {
    return context.getSharedPreferences(PREFS_KEY, PREFS_MODE)
        .getInt(key, defaultValue);
  }

  public static int getInt(@NonNull Context context, String key) {
    return getInt(context, key, intDefault);
  }

  public static long getLong(@NonNull Context context, String key, long defaultValue) {
    return context.getSharedPreferences(PREFS_KEY, PREFS_MODE)
        .getLong(key, defaultValue);
  }

  public static long getLong(@NonNull Context context, String key) {
    return getLong(context, key, longDefault);
  }

  public static boolean getBoolean(@NonNull Context context, String key, boolean defaultValue) {
    return context.getSharedPreferences(PREFS_KEY, PREFS_MODE)
        .getBoolean(key, defaultValue);
  }

  public static boolean getBoolean(Context context, String key) {
    return getBoolean(context, key, boolDefault);
  }

  public static void putString(@NonNull Context context, String key, String value) {
    context.getSharedPreferences(PREFS_KEY, PREFS_MODE).edit()
        .putString(key, value)
        .apply();
  }

  public static void putInt(@NonNull Context context, String key, int value) {
    context.getSharedPreferences(PREFS_KEY, PREFS_MODE).edit()
        .putInt(key, value)
        .apply();
  }

  public static void putLong(@NonNull Context context, String key, long value) {
    context.getSharedPreferences(PREFS_KEY, PREFS_MODE).edit()
        .putLong(key, value)
        .apply();
  }

  public static void putBoolean(@NonNull Context context, String key, boolean value) {
    context.getSharedPreferences(PREFS_KEY, PREFS_MODE).edit()
        .putBoolean(key, value)
        .apply();
  }

  public static void putIntegerArrayList(@NonNull Context context, String key, List<Integer> values) {
    if (values == null) {
      return;
    }

    Set<String> strings = new TreeSet<>();

    for (int integer : values) {
      strings.add(Integer.toString(integer));
    }

    Logger.d("PrefsUtil", "putIntegerArrayList() " + Arrays.toString(strings.toArray()));

    context.getSharedPreferences(PREFS_KEY, PREFS_MODE).edit()
        .putStringSet(key, strings);
  }

  public static List<Integer> getIntegerArrayList(@NonNull Context context, String key) {
    List<Integer> integers = new ArrayList<>();
    Set<String> strings = context.getSharedPreferences(PREFS_KEY, PREFS_MODE).getStringSet(key, null);

    if (strings == null) {
      Logger.d("PrefsUtil", "strings are null");
      return null;
    }

    Logger.d("PrefsUtil", "strings: " + Arrays.toString(strings.toArray()));

    for (String string : strings) {
      integers.add(Integer.getInteger(string));
    }

    return integers;
  }

  public static void putStringSet(@NonNull Context context, String key, Set<String> values) {
    Logger.d("PrefsUtil", "putStringSet");
    for (String string : values) {
      Logger.d("PrefsUtil", string);
    }

    context.getSharedPreferences(PREFS_KEY, PREFS_MODE).edit().putStringSet(key, values);
  }

  public static Set<String> getStringSet(@NonNull Context context, String key) {
    Logger.d("PrefsUtil", "getStringSet: " + context.getSharedPreferences(PREFS_KEY, PREFS_MODE).getStringSet(key, null));

    return context.getSharedPreferences(PREFS_KEY, PREFS_MODE).getStringSet(key, null);
  }

  public static void remove(@NonNull Context context, String key) {
    context.getSharedPreferences(PREFS_KEY, PREFS_MODE).edit()
        .remove(key)
        .apply();
  }

  public static void clear(@NonNull Context context) {
    context.getSharedPreferences(PREFS_KEY, PREFS_MODE).edit().clear().commit();
  }
}
