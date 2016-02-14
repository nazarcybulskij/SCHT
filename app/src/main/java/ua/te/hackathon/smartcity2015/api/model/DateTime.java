package ua.te.hackathon.smartcity2015.api.model;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;
import java.util.Formatter;

/**
 * Created by nk91 on 14.02.16.
 */
public class DateTime {
    private static final String FORMAT = "HH:mm DD-MM-YY";
    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormat.forPattern(FORMAT);
    private static final String DATE_FORMAT = "DD-MM-YY";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern(DATE_FORMAT);
    private static final String TIME_FORMAT = "HH:mm";
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormat.forPattern(TIME_FORMAT);
    private int years;
    private int months;
    private int days;
    private int hours;
    private int minutes;
    private int seconds;

    public void setYears(int years) {
        this.years = years;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public String get() {
        org.joda.time.DateTime dateTime = new org.joda.time.DateTime(years, months, days, hours, minutes, seconds);
        return DATETIME_FORMATTER.print(dateTime);
    }

    public String getTime() {
        org.joda.time.DateTime dateTime = new org.joda.time.DateTime(years, months, days, hours, minutes, seconds);
        return TIME_FORMATTER.print(dateTime);
    }

    public String getDate() {
        org.joda.time.DateTime dateTime = new org.joda.time.DateTime(years, months, days, hours, minutes, seconds);
        return DATE_FORMATTER.print(dateTime);
    }

    public long getMillis() {
        org.joda.time.DateTime dateTime = new org.joda.time.DateTime(years, months, days, hours, minutes, seconds);
        return dateTime.getMillis();
    }
}
