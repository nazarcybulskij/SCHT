package ua.te.hackathon.smartcity2015.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by nk91 on 13.02.16.
 */
public class User extends RealmObject {
  @PrimaryKey
  private String nickname;
  private String name;
  private String surname;
  private String avatarResourcesUrl;
  private String phone;

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getAvatarResourcesUrl() {
    return avatarResourcesUrl;
  }

  public void setAvatarResourcesUrl(String avatarResourcesUrl) {
    this.avatarResourcesUrl = avatarResourcesUrl;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }
}
