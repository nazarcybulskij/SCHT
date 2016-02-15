package ua.te.hackathon.smartcity2015.utils

import ua.te.hackathon.smartcity2015.SmartCityApp
import ua.te.hackathon.smartcity2015.google.GoogleApiHelper

/**
 * @author victor
 * *
 * @since 2016-02-14
 */
object LoggedUser {

  private val FIRST_NAME = "pref:first_name"
  private val LAST_NAME = "pref:first_name"
  private val PHOTO_URL = "pref:photo_url"
  private val EMAIL = "pref:email"

  fun logOut() {
    GoogleApiHelper.logOut()
    PrefsUtil.clear(SmartCityApp.app!!)
  }

  val isLoggedIn: Boolean
    get() = email != null

  var firstName: String?
    get() = PrefsUtil.getString(SmartCityApp.app!!, FIRST_NAME)
    set(firstName) = PrefsUtil.putString(SmartCityApp.app!!, FIRST_NAME, firstName)

  var lastName: String?
    get() = PrefsUtil.getString(SmartCityApp.app!!, LAST_NAME)
    set(lastName) = PrefsUtil.putString(SmartCityApp.app!!, LAST_NAME, lastName)

  var photoUrl: String?
    get() = PrefsUtil.getString(SmartCityApp.app!!, PHOTO_URL)
    set(photoUrl) = PrefsUtil.putString(SmartCityApp.app!!, PHOTO_URL, photoUrl)

  var email: String?
    get() = PrefsUtil.getString(SmartCityApp.app!!, EMAIL)
    set(email) = PrefsUtil.putString(SmartCityApp.app!!, EMAIL, email)
}
