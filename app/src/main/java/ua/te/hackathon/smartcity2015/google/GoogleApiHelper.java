package ua.te.hackathon.smartcity2015.google;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;

import org.greenrobot.eventbus.EventBus;

import ua.te.hackathon.smartcity2015.R;
import ua.te.hackathon.smartcity2015.SmartCityApp;
import ua.te.hackathon.smartcity2015.utils.Logger;

/**
 * @author Victor Kifer
 *
 * Helps with Google user registration process
 *
 * Note:
 * this helper sends sticky event GoogleAuthResult which should be handled by your activity
 * and IT SHOULD BE REMOVED using {@link EventBus#removeStickyEvent(Object)}
 */
public class GoogleApiHelper implements GoogleApiClient.OnConnectionFailedListener {
  private static final String LOG_TAG = Logger.getLogTag(GoogleApiHelper.class);

  private static final String IMAGE_SIZE = "?sz=500";
  public static final int GOOGLE_SIGN_IN_CODE = 85;

  private GoogleApiClient mGoogleApiClient;
  private Context context;

  public GoogleApiHelper(Context context) {
    this.context = context.getApplicationContext();
  }

  private void initGoogleApiIfNeeded(FragmentActivity activity) {
    if (mGoogleApiClient != null) {
      return;
    }

    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//        .requestIdToken(context.getString(R.string.server_client_id))
        .requestScopes(new Scope(Scopes.PLUS_LOGIN))
        .requestEmail()
        .build();

    mGoogleApiClient = new GoogleApiClient.Builder(context)
        .enableAutoManage(activity, this)
        .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
        .build();
  }

  public void startGoogleRegistration(FragmentActivity activity) {
    initGoogleApiIfNeeded(activity);

    Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
    activity.startActivityForResult(signInIntent, GoogleApiHelper.GOOGLE_SIGN_IN_CODE);
  }

  /**
   * This code should be called on activity, which calls {@link #startGoogleRegistration}
   */
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == GoogleApiHelper.GOOGLE_SIGN_IN_CODE) {
      GoogleSignInResult result = com.google.android.gms.auth.api.Auth.GoogleSignInApi.getSignInResultFromIntent(data);
      handleGoogleSignInResult(result);
    }
  }

  private void handleGoogleSignInResult(GoogleSignInResult result) {
    if (result == null) {
      GoogleAuthResult googleAuthResult = new GoogleAuthResult(
          0,
          context.getString(R.string.error_google_registration)
      );
      EventBus.getDefault().postSticky(googleAuthResult);
      return;
    }

    if (result.isSuccess()) {
      GoogleAuthResult googleAuthResult = new GoogleAuthResult(result.getSignInAccount());
      EventBus.getDefault().postSticky(googleAuthResult);
    } else {
      GoogleAuthResult googleAuthResult = new GoogleAuthResult(
          result.getStatus().getStatusCode(),
          result.getStatus().getStatusMessage()
      );
      EventBus.getDefault().postSticky(googleAuthResult);
    }
  }

  public static void logOut() {
    // TODO: I think we should consider reusing class field instead of initializing new client
    // but as long as it works, I'm not going to change it now
    final GoogleApiClient googleApiClient = new GoogleApiClient.Builder(SmartCityApp.getApp())
        .addApi(Auth.GOOGLE_SIGN_IN_API)
        .build();

    googleApiClient.registerConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
      @Override
      public void onConnected(Bundle bundle) {
        Auth.GoogleSignInApi.signOut(googleApiClient);
      }

      @Override
      public void onConnectionSuspended(int i) {
        Logger.w(LOG_TAG, "Google Api Connection suspended");
      }
    });

    googleApiClient.connect();
  }

  @Override
  public void onConnectionFailed(ConnectionResult connectionResult) {
    Logger.w(LOG_TAG, "Cannot connect to Google account", connectionResult);
    GoogleAuthResult googleAuthResult = new GoogleAuthResult(0, context.getString(R.string.error_google_registration));
    EventBus.getDefault().postSticky(googleAuthResult);
  }

  public static class GoogleAuthResult {
    boolean success;
    int errorCode;
    String error;
    GoogleSignInAccount signInAccount;
    String token;

    GoogleAuthResult(int errorCode, String error) {
      success = false;
      this.errorCode = errorCode;
      this.error = error;
    }

    GoogleAuthResult(GoogleSignInAccount signInAccount) {
      success = true;
      this.signInAccount = signInAccount;
    }

    void setToken(String token) {
      this.token = token;
    }

    public boolean isSuccess() {
      return success;
    }

    public String getError() {
      return error;
    }

    public GoogleSignInAccount getSignInAccount() {
      return signInAccount;
    }

    public String getToken() {
      return token;
    }
  }

  public static class GoogleProfile {

    public String nickname;
    public String firstName;
    public String lastName;
    public String email;
    public String imageUrl;


    public GoogleProfile(GoogleSignInAccount account) {
      this.nickname = account.getDisplayName() != null ? account.getDisplayName() : "";
      this.email = account.getEmail();
      this.imageUrl = account.getPhotoUrl() != null ? account.getPhotoUrl().toString() + IMAGE_SIZE : null;
      String[] result = this.nickname.split(" ");
      this.nickname = this.nickname.replace(" ", "");
      if (result.length >= 2) {
        this.firstName = result[0];
        this.lastName = result[1];
      }
    }

    public String getNickname() {
      return nickname;
    }

    public String getFirstName() {
      return firstName;
    }

    public String getLastName() {
      return lastName;
    }

    public String getEmail() {
      return email;
    }

    public String getImageUrl() {
      return imageUrl;
    }
  }
}
