package ua.te.hackathon.smartcity2015.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * @author victor
 * @since 2016-02-14
 */

public class Event {

  @SerializedName("id")
  private int id;
  @SerializedName("name")
  private String name;
  @SerializedName("date")
  private long date;
  @SerializedName("description")
  private String description;
  @SerializedName("backgroundUrl")
  private String backgroundUrl;
  @SerializedName("place")
  private String place;
  @SerializedName("joinedUsers")
  private List<User> joinedUsers;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public List<User> getJoinedUsers() {
    return joinedUsers;
  }

  public void setJoinedUsers(List<User> joinedUsers) {
    this.joinedUsers = joinedUsers;
  }
}
