package ua.te.hackathon.smartcity2015.utils;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;

import net.danlew.android.joda.DateUtils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import ua.te.hackathon.smartcity2015.R;

/**
 * @author victor
 * @since 2016-02-14
 */
public class TimeUtils {

  public static String getDayPresentation(@NonNull Context context, long time) {
    DateTime currentDate = new DateTime().withTimeAtStartOfDay();
    DateTime eventDate = new DateTime(time);
    Resources resources = context.getResources();
    Period period = new Period(eventDate, currentDate);

    Logger.d("TAG",
        period.getYears(),
        period.getMonths(),
        period.toStandardDays().getDays(),
        period.getHours(),
        period.getMinutes(),
        period.getSeconds()
    );

    switch (period.toStandardDays().getDays()) {
      case 0:
        return resources.getString(R.string.date_today);
      case 1:
        return resources.getString(R.string.date_tomorrow);
      default:
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd MMMM");
        return formatter.print(eventDate);
    }
  }

  public static String getTimePresentation(@NonNull Context context, long time) {
    DateTime eventDate = new DateTime(time);

    DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");
    return formatter.print(eventDate);
  }

}
