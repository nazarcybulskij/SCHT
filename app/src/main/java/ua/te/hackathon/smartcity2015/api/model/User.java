package ua.te.hackathon.smartcity2015.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nk91 on 13.02.16.
 */
public class User {
  @SerializedName("nickname")
  private String nickname;
  @SerializedName("name")
  private String name;
  @SerializedName("surname")
  private String surname;
  @SerializedName("avatarUrl")
  private String avatarUrl;
  @SerializedName("phone")
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

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }
}
