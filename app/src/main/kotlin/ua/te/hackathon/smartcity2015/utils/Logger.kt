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

  var logLevel = ua.te.hackathon.Logger.LogLevel.INFO

  private fun isLogEnabled(level: ua.te.hackathon.Logger.LogLevel): Boolean {
    return ua.te.hackathon.Logger.logLevel.ordinal <= level.ordinal
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
    if (ua.te.hackathon.Logger.isLogEnabled(LogLevel.VERBOSE)) {
      Log.v(tag, message)
    }
  }

  fun v(tag: String, e: Exception) {
    if (e.message == null) {
      if (ua.te.hackathon.Logger.isLogEnabled(LogLevel.VERBOSE)) {
        e.printStackTrace()
      }
    } else {
      ua.te.hackathon.Logger.v(tag, e.message as String)
    }
  }

  fun v(tag: String, vararg message: Any) {
    if (ua.te.hackathon.Logger.isLogEnabled(LogLevel.VERBOSE)) {
      Log.v(tag, ua.te.hackathon.Logger.arrayToString(*message))
    }
  }

  fun d(tag: String, message: String) {
    if (ua.te.hackathon.Logger.isLogEnabled(LogLevel.DEBUG) && message != null) {
      Log.d(tag, message)
    }
  }

  fun d(tag: String, e: Exception) {
    if (e.message == null) {
      if (ua.te.hackathon.Logger.isLogEnabled(LogLevel.DEBUG)) {
        e.printStackTrace()
      }
    } else {
      ua.te.hackathon.Logger.d(tag, e.message as String)
    }
  }

  fun d(tag: String, vararg messages: Any) {
    if (ua.te.hackathon.Logger.isLogEnabled(LogLevel.DEBUG)) {
      Log.d(tag, ua.te.hackathon.Logger.arrayToString(*messages))
    }
  }

  fun i(tag: String, message: String) {
    if (ua.te.hackathon.Logger.isLogEnabled(LogLevel.INFO)) {
      Log.i(tag, message)
    }
  }

  fun i(tag: String, e: Exception) {
    if (e.message == null) {
      if (ua.te.hackathon.Logger.isLogEnabled(LogLevel.INFO)) {
        e.printStackTrace()
      }
    } else {
      ua.te.hackathon.Logger.i(tag, e.message as String)
    }
  }

  fun i(tag: String, vararg message: Any) {
    if (ua.te.hackathon.Logger.isLogEnabled(LogLevel.INFO)) {
      Log.i(tag, ua.te.hackathon.Logger.arrayToString(*message))
    }
  }

  fun w(tag: String, message: String) {
    if (ua.te.hackathon.Logger.isLogEnabled(LogLevel.WARN)) {
      Log.w(tag, message)
    }
  }

  fun w(tag: String, e: Exception) {
    if (e.message == null) {
      if (ua.te.hackathon.Logger.isLogEnabled(LogLevel.WARN)) {
        e.printStackTrace()
      }
    } else {
      ua.te.hackathon.Logger.w(tag, e.message as String)
    }
  }

  fun w(tag: String, vararg message: Any) {
    if (ua.te.hackathon.Logger.isLogEnabled(LogLevel.WARN)) {
      Log.w(tag, ua.te.hackathon.Logger.arrayToString(*message))
    }
  }

  fun e(tag: String, message: String) {
    if (ua.te.hackathon.Logger.isLogEnabled(LogLevel.ERR)) {
      Log.e(tag, message)
    }
  }

  fun e(tag: String, e: Exception) {
    if (e.message == null) {
      if (ua.te.hackathon.Logger.isLogEnabled(LogLevel.ERR)) {
        e.printStackTrace()
      }
    } else {
      ua.te.hackathon.Logger.e(tag, e.message as String)
    }
  }

  fun e(tag: String, vararg message: Any) {
    if (ua.te.hackathon.Logger.isLogEnabled(LogLevel.ERR)) {
      Log.e(tag, ua.te.hackathon.Logger.arrayToString(*message))
    }
  }
}
