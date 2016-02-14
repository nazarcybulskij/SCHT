package ua.te.hackathon.smartcity2015.utils;

import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by nk91 on 14.02.16.
 */
public class Utils {
    public static String text(EditText etName) {
        if (etName != null) {
            return etName.getText().toString();
        }
        return null;
    }

    public static long date(String d){
        Date date = null;
        try {
            date = DateFormat.getDateInstance().parse(d);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date.getTime();
    }

}
