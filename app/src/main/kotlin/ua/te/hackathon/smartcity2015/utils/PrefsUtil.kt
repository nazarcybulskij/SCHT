package ua.te.hackathon.smartcity2015.utils

import android.content.Context

/**
 * @author Paul Zin
 */
object PrefsUtil {
  private val PREFS_KEY = "ua.te.hackathon.smartcity2015.PREFS"
  private val PREFS_MODE = Context.MODE_PRIVATE

  private val strDefault: String? = null
  private val intDefault = 0
  private val longDefault = 0L
  private val boolDefault = false

  @JvmOverloads fun getString(context: Context, key: String, defaultValue: String? = strDefault): String? {
    return context.getSharedPreferences(PREFS_KEY, PREFS_MODE).getString(key, defaultValue)
  }

  @JvmOverloads fun getInt(context: Context, key: String, defaultValue: Int = intDefault): Int {
    return context.getSharedPreferences(PREFS_KEY, PREFS_MODE).getInt(key, defaultValue)
  }

  @JvmOverloads fun getLong(context: Context, key: String, defaultValue: Long = longDefault): Long {
    return context.getSharedPreferences(PREFS_KEY, PREFS_MODE).getLong(key, defaultValue)
  }

  @JvmOverloads fun getBoolean(context: Context, key: String, defaultValue: Boolean = boolDefault): Boolean {
    return context.getSharedPreferences(PREFS_KEY, PREFS_MODE).getBoolean(key, defaultValue)
  }

  fun putString(context: Context, key: String, value: String?) {
    context.getSharedPreferences(PREFS_KEY, PREFS_MODE).edit().putString(key, value).apply()
  }

  fun putInt(context: Context, key: String, value: Int) {
    context.getSharedPreferences(PREFS_KEY, PREFS_MODE).edit().putInt(key, value).apply()
  }

  fun putLong(context: Context, key: String, value: Long) {
    context.getSharedPreferences(PREFS_KEY, PREFS_MODE).edit().putLong(key, value).apply()
  }

  fun putBoolean(context: Context, key: String, value: Boolean) {
    context.getSharedPreferences(PREFS_KEY, PREFS_MODE).edit().putBoolean(key, value).apply()
  }

  fun remove(context: Context, key: String) {
    context.getSharedPreferences(PREFS_KEY, PREFS_MODE).edit().remove(key).apply()
  }

  fun clear(context: Context) {
    context.getSharedPreferences(PREFS_KEY, PREFS_MODE).edit().clear().commit()
  }
}
