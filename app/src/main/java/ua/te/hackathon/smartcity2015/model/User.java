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

    @Override
    public String toString() {
        return "User{" +
                "nickname='" + nickname + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", avatarResourcesUrl='" + avatarResourcesUrl + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!nickname.equals(user.nickname)) return false;
        if (!name.equals(user.name)) return false;
        if (!surname.equals(user.surname)) return false;
        if (!avatarResourcesUrl.equals(user.avatarResourcesUrl)) return false;
        return phone.equals(user.phone);

    }

    @Override
    public int hashCode() {
        int result = nickname.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + avatarResourcesUrl.hashCode();
        result = 31 * result + phone.hashCode();
        return result;
    }

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
