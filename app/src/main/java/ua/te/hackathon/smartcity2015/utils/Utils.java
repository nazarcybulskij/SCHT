package ua.te.hackathon.smartcity2015.utils;

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

}
