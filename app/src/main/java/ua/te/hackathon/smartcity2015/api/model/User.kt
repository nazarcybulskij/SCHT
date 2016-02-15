package ua.te.hackathon.smartcity2015.api.model

import com.google.gson.annotations.SerializedName

/**
 * Created by nk91 on 13.02.16.
 */
class User {
  @SerializedName("nickname")
  var nickname: String? = null
  @SerializedName("name")
  var name: String? = null
  @SerializedName("surname")
  var surname: String? = null
  @SerializedName("avatarUrl")
  var avatarUrl: String? = null
  @SerializedName("phone")
  var phone: String? = null
}
