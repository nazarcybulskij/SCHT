package ua.te.hackathon.smartcity2015.utils;

import com.google.android.gms.common.api.GoogleApiClient;

import ua.te.hackathon.smartcity2015.SmartCityApp;
import ua.te.hackathon.smartcity2015.google.GoogleApiHelper;

/**
 * @author victor
 * @since 2016-02-14
 */
public class LoggedUser {

  private static final String FIRST_NAME = "pref:first_name";
  private static final String LAST_NAME = "pref:first_name";
  private static final String PHOTO_URL = "pref:photo_url";
  private static final String EMAIL = "pref:email";

  public static void logOut() {
    GoogleApiHelper.logOut();
    PrefsUtil.clear(SmartCityApp.getApp());
  }

  public static boolean isLoggedIn() {
    return getEmail() != null;
  }

  public static String getFirstName() {
    return PrefsUtil.getString(SmartCityApp.getApp(), FIRST_NAME, null);
  }

  public static void setFirstName(String firstName) {
    PrefsUtil.putString(SmartCityApp.getApp(), FIRST_NAME, firstName);
  }

  public static String getLastName() {
    return PrefsUtil.getString(SmartCityApp.getApp(), LAST_NAME, null);
  }

  public static void setLastName(String lastName) {
    PrefsUtil.putString(SmartCityApp.getApp(), LAST_NAME, lastName);
  }

  public static String getPhotoUrl() {
    return PrefsUtil.getString(SmartCityApp.getApp(), PHOTO_URL, null);
  }

  public static void setPhotoUrl(String photoUrl) {
    PrefsUtil.putString(SmartCityApp.getApp(), PHOTO_URL, photoUrl);
  }

  public static String getEmail() {
    return PrefsUtil.getString(SmartCityApp.getApp(), EMAIL, null);
  }

  public static void setEmail(String email) {
    PrefsUtil.putString(SmartCityApp.getApp(), EMAIL, email);
  }
}
