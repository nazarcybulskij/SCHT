package ua.te.hackathon.smartcity2015.db

import io.realm.Realm
import io.realm.RealmObject
import ua.te.hackathon.smartcity2015.SmartCityApp

/**
 * @author victor
 * *
 * @since 2016-02-14
 */
object DBUtil {

  fun getNextId(clazz: Class<out RealmObject>): Int {
    val realm = Realm.getInstance(SmartCityApp.app)
    val number = realm.where(clazz).max("id")
    if (number == null) {
      return 1
    } else {
      return number!!.toInt() + 1
    }
  }

}
