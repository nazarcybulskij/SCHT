package ua.te.hackathon.smartcity2015.api.model

import org.joda.time.format.DateTimeFormat

/**
 * Created by nk91 on 14.02.16.
 */
class DateTime {
  private var years: Int = 0
  private var months: Int = 0
  private var days: Int = 0
  private var hours: Int = 0
  private var minutes: Int = 0
  private var seconds: Int = 0

  fun setYears(years: Int) {
    this.years = years
  }

  fun setMonths(months: Int) {
    this.months = months
  }

  fun setDays(days: Int) {
    this.days = days
  }

  fun setHours(hours: Int) {
    this.hours = hours
  }

  fun setMinutes(minutes: Int) {
    this.minutes = minutes
  }

  fun setSeconds(seconds: Int) {
    this.seconds = seconds
  }

  fun get(): String {
    val dateTime = org.joda.time.DateTime(years, months, days, hours, minutes, seconds)
    return DATETIME_FORMATTER.print(dateTime)
  }

  val time: String
    get() {
      val dateTime = org.joda.time.DateTime(years, months, days, hours, minutes, seconds)
      return TIME_FORMATTER.print(dateTime)
    }

  val date: String
    get() {
      val dateTime = org.joda.time.DateTime(years, months, days, hours, minutes, seconds)
      return DATE_FORMATTER.print(dateTime)
    }

  val millis: Long
    get() {
      val dateTime = org.joda.time.DateTime(years, months, days, hours, minutes, seconds)
      return dateTime.millis
    }

  companion object {
    private val FORMAT = "HH:mm DD-MM-YY"
    val DATETIME_FORMATTER = DateTimeFormat.forPattern(FORMAT)
    private val DATE_FORMAT = "DD-MM-YY"
    val DATE_FORMATTER = DateTimeFormat.forPattern(DATE_FORMAT)
    private val TIME_FORMAT = "HH:mm"
    val TIME_FORMATTER = DateTimeFormat.forPattern(TIME_FORMAT)
  }
}
