package ua.te.hackathon.smartcity2015.utils;

<<<<<<< HEAD
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by nazarko on 2/14/16.
 */
public class Utils {

  private static final String IS_AUTH="extra:is_auth";

  Context mContext;
  SharedPreferences prefs;

  public Utils(Context mContext) {
    this.mContext = mContext;
    prefs = mContext.getSharedPreferences(
        "ua.te.hackathon.smartcity2015", Context.MODE_PRIVATE);
  }

  public boolean isUserAuthenticated(){
    return  prefs.getBoolean(IS_AUTH,false);
  }

  public void  setUserAuthenticated(boolean state){
    SharedPreferences.Editor editor = prefs.edit();
    editor.putBoolean(IS_AUTH,state);
    editor.commit();
  }
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
