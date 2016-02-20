package ua.te.hackathon.smartcity2015.api.model

import com.google.gson.annotations.SerializedName


/**
 * @author victor
 * *
 * @since 2016-02-14
 */

class Event {

  @SerializedName("id")
  var id: Int = 0
  @SerializedName("name")
  var name: String? = null
  @SerializedName("date")
  var date: Long = 0
  @SerializedName("description")
  var description: String? = null
  @SerializedName("backgroundUrl")
  var backgroundUrl: String? = null
  @SerializedName("place")
  var place: String? = null
  @SerializedName("joinedUsers")
  var joinedUsers: List<User>? = null
}
