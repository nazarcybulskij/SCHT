package ua.te.hackathon.smartcity2015.db

import android.content.Context
import io.realm.Realm
import io.realm.RealmList
import ua.te.hackathon.smartcity2015.api.model.Event
import ua.te.hackathon.smartcity2015.db.model.User

/**
 * @author victor
 * *
 * @since 2016-02-14
 */
object EventsManager {

  fun updateEvents(context: Context, eventList: List<Event>): List<ua.te.hackathon.smartcity2015.db.model.Event> {
    val realm = Realm.getInstance(context)
    realm.beginTransaction()
    realm.clear(ua.te.hackathon.smartcity2015.db.model.Event::class.java)
    realm.clear(User::class.java)

    val results = RealmList<ua.te.hackathon.smartcity2015.db.model.Event>()

    for (event in eventList) {
      val dbEvent = realm.createObject(ua.te.hackathon.smartcity2015.db.model.Event::class.java)
      dbEvent.id = event.id
      dbEvent.name = event.name
      dbEvent.date = event.date
      dbEvent.place = event.place
      dbEvent.description = event.description
      dbEvent.backgroundUrl = event.backgroundUrl

      val joinedUsers = RealmList<User>()
      for (user in event.joinedUsers!!) {
        val dbUser = realm.createObject(User::class.java)
        dbUser.nickname = user.nickname
        dbUser.name = user.name
        dbUser.surname = user.surname
        dbUser.avatarResourcesUrl = user.avatarUrl
        dbUser.phone = user.phone
        realm.copyToRealm(dbUser)
        joinedUsers.add(dbUser)
      }
      dbEvent.joinedUsers = joinedUsers
      realm.copyToRealm(dbEvent)
      results.add(dbEvent)
    }
    realm.commitTransaction()

    return results
  }

}
