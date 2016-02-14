package ua.te.hackathon.smartcity2015.utils;

import android.content.Context;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by nazarko on 2/14/16.
 */
public class Utils {

  Context mContext;

  public Utils(Context mContext) {
    this.mContext = mContext;
  }

  public static String text(EditText etName) {
    if (etName != null) {
      return etName.getText().toString();
    }
    return null;
  }

  public static long date(String d) {
    Date date = null;
    try {
      date = DateFormat.getDateInstance().parse(d);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
    return date.getTime();
  }

  public static boolean isEmpty(String text) {
    return text == null || "".equals(text);
  }

}
