package ua.te.hackathon.smartcity2015.db;

import io.realm.Realm;
import io.realm.RealmObject;
import ua.te.hackathon.smartcity2015.SmartCityApp;

/**
 * @author victor
 * @since 2016-02-14
 */
public class DBUtil {

  public static int getNextId(Class<? extends RealmObject> clazz) {
    Realm realm = Realm.getInstance(SmartCityApp.getApp());
    return realm.where(clazz).max("id").intValue() + 1;
  }

}
