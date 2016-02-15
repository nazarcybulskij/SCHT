package ua.te.hackathon.smartcity2015.utils

import android.util.Log

/**
 * @author victor
 * *
 * @since 2016-02-13
 */
object Logger {
  enum class LogLevel {
    ALL,
    VERBOSE,
    DEBUG,
    INFO,
    WARN,
    ERR,
    NONE
  }

  var logLevel = LogLevel.INFO

  private fun isLogEnabled(level: LogLevel): Boolean {
    return logLevel.ordinal <= level.ordinal
  }

  fun getLogTag(classObj: Class<*>): String {
    return classObj.name
  }

  fun getLogTag(obj: Any?): String {
    return if (obj == null) "null" else obj.javaClass.name
  }

  private fun arrayToString(vararg arr: Any): String {
    val builder = StringBuilder()
    for (obj in arr) {
      builder.append(if (obj == null) "null" else obj.toString())
      builder.append(" ")
    }
    return builder.toString()
  }

  fun v(tag: String, message: String) {
    if (isLogEnabled(LogLevel.VERBOSE)) {
      Log.v(tag, message)
    }
  }

  fun v(tag: String, e: Exception) {
    if (e.message == null) {
      if (isLogEnabled(LogLevel.VERBOSE)) {
        e.printStackTrace()
      }
    } else {
      v(tag, e.message as String)
    }
  }

  fun v(tag: String, vararg message: Any) {
    if (isLogEnabled(LogLevel.VERBOSE)) {
      Log.v(tag, arrayToString(*message))
    }
  }

  fun d(tag: String, message: String) {
    if (isLogEnabled(LogLevel.DEBUG) && message != null) {
      Log.d(tag, message)
    }
  }

  fun d(tag: String, e: Exception) {
    if (e.message == null) {
      if (isLogEnabled(LogLevel.DEBUG)) {
        e.printStackTrace()
      }
    } else {
      d(tag, e.message as String)
    }
  }

  fun d(tag: String, vararg messages: Any) {
    if (isLogEnabled(LogLevel.DEBUG)) {
      Log.d(tag, arrayToString(*messages))
    }
  }

  fun i(tag: String, message: String) {
    if (isLogEnabled(LogLevel.INFO)) {
      Log.i(tag, message)
    }
  }

  fun i(tag: String, e: Exception) {
    if (e.message == null) {
      if (isLogEnabled(LogLevel.INFO)) {
        e.printStackTrace()
      }
    } else {
      i(tag, e.message as String)
    }
  }

  fun i(tag: String, vararg message: Any) {
    if (isLogEnabled(LogLevel.INFO)) {
      Log.i(tag, arrayToString(*message))
    }
  }

  fun w(tag: String, message: String) {
    if (isLogEnabled(LogLevel.WARN)) {
      Log.w(tag, message)
    }
  }

  fun w(tag: String, e: Exception) {
    if (e.message == null) {
      if (isLogEnabled(LogLevel.WARN)) {
        e.printStackTrace()
      }
    } else {
      w(tag, e.message as String)
    }
  }

  fun w(tag: String, vararg message: Any) {
    if (isLogEnabled(LogLevel.WARN)) {
      Log.w(tag, arrayToString(*message))
    }
  }

  fun e(tag: String, message: String) {
    if (isLogEnabled(LogLevel.ERR)) {
      Log.e(tag, message)
    }
  }

  fun e(tag: String, e: Exception) {
    if (e.message == null) {
      if (isLogEnabled(LogLevel.ERR)) {
        e.printStackTrace()
      }
    } else {
      e(tag, e.message as String)
    }
  }

  fun e(tag: String, vararg message: Any) {
    if (isLogEnabled(LogLevel.ERR)) {
      Log.e(tag, arrayToString(*message))
    }
  }
}
