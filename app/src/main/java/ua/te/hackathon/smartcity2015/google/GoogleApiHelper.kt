package ua.te.hackathon.smartcity2015.google

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity

import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.Scope

import org.greenrobot.eventbus.EventBus

import ua.te.hackathon.smartcity2015.R
import ua.te.hackathon.smartcity2015.SmartCityApp
import ua.te.hackathon.smartcity2015.utils.Logger

/**
 * @author Victor Kifer
 * *
 * * Helps with Google user registration process
 * *
 * * Note:
 * * this helper sends sticky event GoogleAuthResult which should be handled by your activity
 * * and IT SHOULD BE REMOVED using [EventBus.removeStickyEvent]
 */
class GoogleApiHelper(context: Context) : GoogleApiClient.OnConnectionFailedListener {

  private var mGoogleApiClient: GoogleApiClient? = null
  private val context: Context

  init {
    this.context = context.applicationContext
  }

  private fun initGoogleApiIfNeeded(activity: FragmentActivity) {
    if (mGoogleApiClient != null) {
      return
    }

    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestScopes(Scope(Scopes.PLUS_LOGIN))
        //        .requestIdToken(context.getString(R.string.server_client_id))
        .requestEmail()
        .build()

    mGoogleApiClient = GoogleApiClient.Builder(context)
        .enableAutoManage(activity, this)
        .addApi<GoogleSignInOptions>(Auth.GOOGLE_SIGN_IN_API, gso)
        .build()
  }

  fun startGoogleRegistration(activity: FragmentActivity) {
    initGoogleApiIfNeeded(activity)

    val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
    activity.startActivityForResult(signInIntent, GoogleApiHelper.GOOGLE_SIGN_IN_CODE)
  }

  /**
   * This code should be called on activity, which calls [.startGoogleRegistration]
   */
  fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
    if (requestCode == GoogleApiHelper.GOOGLE_SIGN_IN_CODE) {
      val result = com.google.android.gms.auth.api.Auth.GoogleSignInApi.getSignInResultFromIntent(data)
      handleGoogleSignInResult(result)
    }
  }

  private fun handleGoogleSignInResult(result: GoogleSignInResult?) {
    if (result == null) {
      val googleAuthResult = GoogleAuthResult(
          0,
          context.getString(R.string.error_registration_google))
      EventBus.getDefault().postSticky(googleAuthResult)
      return
    }

    if (result.isSuccess) {
      val googleAuthResult = GoogleAuthResult(result.signInAccount!!)
      EventBus.getDefault().postSticky(googleAuthResult)
    } else {
      val googleAuthResult = GoogleAuthResult(
          result.status.statusCode,
          result.status.statusMessage)
      EventBus.getDefault().postSticky(googleAuthResult)
    }
  }

  override fun onConnectionFailed(connectionResult: ConnectionResult) {
    Logger.w(LOG_TAG, "Cannot connect to Google account", connectionResult)
    val googleAuthResult = GoogleAuthResult(0, context.getString(R.string.error_registration_google))
    EventBus.getDefault().postSticky(googleAuthResult)
  }

  class GoogleAuthResult {
    var isSuccess: Boolean = false
      internal set
    internal var errorCode: Int = 0
    var error: String? = null
      internal set
    var signInAccount: GoogleSignInAccount? = null
      internal set
    var token: String? = null
      internal set(token) {
        this.token = token
      }

    internal constructor(errorCode: Int, error: String) {
      isSuccess = false
      this.errorCode = errorCode
      this.error = error
    }

    internal constructor(signInAccount: GoogleSignInAccount) {
      isSuccess = true
      this.signInAccount = signInAccount
    }
  }

  class GoogleProfile(account: GoogleSignInAccount) {

    var nickname: String
    var firstName: String? = null
    var lastName: String? = null
    var email: String? = null
    var imageUrl: String? = null


    init {
      this.nickname = account.displayName ?: ""
      this.email = account.email
      this.imageUrl = if (account.photoUrl != null) account.photoUrl!!.toString() + IMAGE_SIZE else null
      val result = this.nickname.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
      this.nickname = this.nickname.replace(" ", "")
      if (result.size >= 2) {
        this.firstName = result[0]
        this.lastName = result[1]
      }
    }
  }

  companion object {
    private val LOG_TAG = Logger.getLogTag(GoogleApiHelper::class.java)

    private val IMAGE_SIZE = "?sz=500"
    val GOOGLE_SIGN_IN_CODE = 85

    fun logOut() {
      // TODO: I think we should consider reusing class field instead of initializing new client
      // but as long as it works, I'm not going to change it now
      val googleApiClient = GoogleApiClient.Builder(SmartCityApp.app!!).addApi(Auth.GOOGLE_SIGN_IN_API).build()

      googleApiClient.registerConnectionCallbacks(object : GoogleApiClient.ConnectionCallbacks {
        override fun onConnected(bundle: Bundle?) {
          Auth.GoogleSignInApi.signOut(googleApiClient)
        }

        override fun onConnectionSuspended(i: Int) {
          Logger.w(LOG_TAG, "Google Api Connection suspended")
        }
      })

      googleApiClient.connect()
    }
  }
}
