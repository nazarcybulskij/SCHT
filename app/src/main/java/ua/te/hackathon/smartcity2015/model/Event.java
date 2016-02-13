package ua.te.hackathon.smartcity2015.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

/**
 * Created by nk91 on 13.02.16.
 */
public class Event extends RealmObject {

    @PrimaryKey
    private String name;
    @Index
    private long date;
    private String description;
    private String backgroundUrl;
    @Index
    private String place;
    private RealmList<User> joinedUsers;

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", backgroundUrl='" + backgroundUrl + '\'' +
                ", place='" + place + '\'' +
                ", joinedUsers=" + joinedUsers +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public RealmList<User> getJoinedUsers() {
        return joinedUsers;
    }

    public void setJoinedUsers(RealmList<User> joinedUsers) {
        this.joinedUsers = joinedUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (date != event.date) return false;
        if (!name.equals(event.name)) return false;
        if (!description.equals(event.description)) return false;
        if (!backgroundUrl.equals(event.backgroundUrl)) return false;
        if (!place.equals(event.place)) return false;
        return joinedUsers.equals(event.joinedUsers);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (int) (date ^ (date >>> 32));
        result = 31 * result + description.hashCode();
        result = 31 * result + backgroundUrl.hashCode();
        result = 31 * result + place.hashCode();
        result = 31 * result + joinedUsers.hashCode();
        return result;
    }
}
