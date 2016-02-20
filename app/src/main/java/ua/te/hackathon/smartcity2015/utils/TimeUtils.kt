package ua.te.hackathon.smartcity2015.utils

import android.content.Context
import org.joda.time.DateTime
import org.joda.time.Days
import org.joda.time.Period
import org.joda.time.format.DateTimeFormat
import ua.te.hackathon.smartcity2015.R
import java.util.concurrent.TimeUnit

/**
 * @author victor
 * *
 * @since 2016-02-14
 */
object TimeUtils {

  fun getDayPresentation(context: Context, time: Long): String {
    val currentDate = DateTime.now().withTimeAtStartOfDay()
    val eventDate = DateTime(time)
    val resources = context.resources

    val days = Days.daysBetween(currentDate, eventDate).days

    when (days) {
      0 -> return resources.getString(R.string.date_today)
      1 -> return resources.getString(R.string.date_tomorrow)
      else -> {
        val formatter = DateTimeFormat.forPattern("dd MMMM")
        return formatter.print(eventDate)
      }
    }
  }

  fun getTimePresentation(context: Context, time: Long): String {
    val eventDate = DateTime(time)

    val formatter = DateTimeFormat.forPattern("HH:mm")
    return formatter.print(eventDate)
  }

}
