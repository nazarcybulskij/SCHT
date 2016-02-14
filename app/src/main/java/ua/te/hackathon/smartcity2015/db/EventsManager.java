package ua.te.hackathon.smartcity2015.db;

import android.content.Context;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import ua.te.hackathon.smartcity2015.api.model.Event;
import ua.te.hackathon.smartcity2015.db.model.User;

/**
 * @author victor
 * @since 2016-02-14
 */
public class EventsManager {

  public static void updateEvents(Context context, List<Event> eventList) {
    Realm realm = Realm.getInstance(context);
    realm.beginTransaction();
    realm.clear(ua.te.hackathon.smartcity2015.db.model.Event.class);
    realm.commitTransaction();

    realm.beginTransaction();
    for (Event event : eventList) {
      ua.te.hackathon.smartcity2015.db.model.Event dbEvent = realm.createObject(ua.te.hackathon.smartcity2015.db.model.Event.class);
      dbEvent.setId(event.getId());
      dbEvent.setName(event.getName());
      dbEvent.setDate(event.getDate());
      dbEvent.setDescription(event.getDescription());
      dbEvent.setBackgroundUrl(event.getBackgroundUrl());

      RealmList<User> joinedUsers = new RealmList<>();
      for (ua.te.hackathon.smartcity2015.api.model.User user : event.getJoinedUsers()) {
        User dbUser = realm.createObject(User.class);
        dbUser.setNickname(user.getNickname());
        dbUser.setName(user.getName());
        dbUser.setSurname(user.getSurname());
        dbUser.setAvatarResourcesUrl(user.getAvatarUrl());
        dbUser.setPhone(user.getPhone());
      }
      dbEvent.setJoinedUsers(joinedUsers);
    }
    realm.commitTransaction();
  }

}
