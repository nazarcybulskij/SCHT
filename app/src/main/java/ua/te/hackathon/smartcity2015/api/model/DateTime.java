package ua.te.hackathon.smartcity2015.api.model;

import java.util.Calendar;
import java.util.Formatter;

/**
 * Created by nk91 on 14.02.16.
 */
public class DateTime {
    private static final String FORMAT = "HH:mm DD-MM-YY";
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
        Calendar calendar = Calendar.getInstance();
        calendar.set(years, months, days, hours, minutes, seconds);
        return calendar.getTime().toString();
    }

    public long getTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(years, months, days, hours, minutes, seconds);
        return calendar.getTimeInMillis();
    }
}
