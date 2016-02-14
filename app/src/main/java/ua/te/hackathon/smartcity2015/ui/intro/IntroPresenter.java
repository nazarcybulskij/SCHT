package ua.te.hackathon.smartcity2015.ui.intro;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;

import ua.te.hackathon.smartcity2015.ui.base.mvp.Presenter;
import ua.te.hackathon.smartcity2015.ui.main.events.browse.BrowseEventsView;

/**
 * Created by nazarko on 2/13/16.
 */
public class IntroPresenter implements Presenter<IntroView>, GoogleApiClient.OnConnectionFailedListener {

  @Nullable
  private IntroView view;

  private Context appContext;

  private GoogleApiClient mGoogleApiClient;

  public static final int RC_SIGN_IN = 9001;
  public static final int REQUEST_SIGNUP = 0;


  public IntroPresenter(Context appContext) {
    this.appContext = appContext;



  }

  public void login(FragmentActivity  activity){
    if (view != null) {
      view.showLoadingView();
    }



    Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
    activity.startActivityForResult(signInIntent, RC_SIGN_IN);
    OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
    if (opr.isDone()) {
      // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
      // and the GoogleSignInResult will be available instantly.
      GoogleSignInResult result = opr.get();
      handleSignInResult(result);
    } else {
      // If the user has not previously signed in on this device or the sign-in has expired,
      // this asynchronous branch will attempt to sign in the user silently.  Cross-device
      // single sign-on will occur in this branch.

      opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
        @Override
        public void onResult(GoogleSignInResult googleSignInResult) {;
          handleSignInResult(googleSignInResult);
        }
      });
    }


    // loading done
    if (view != null) {
      view.hideLoadingView();
    }

  }

  // [START handleSignInResult]
  public void handleSignInResult(GoogleSignInResult result) {
    if (result.isSuccess()) {
      // Signed in successfully, show authenticated UI.
      GoogleSignInAccount acct = result.getSignInAccount();
      view.success();
    } else {
      view.error();

    }
  }



  public void attachActivity(FragmentActivity  activity) {
    // [START configure_signin]
    // Configure sign-in to request the user's ID, email address, and basic
    // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build();
    // [END configure_signin]


    // [START build_client]
    // Build a GoogleApiClient with access to the Google Sign-In API and the
    // options specified by gso.

    synchronized(activity){
      mGoogleApiClient = new GoogleApiClient.Builder(this.appContext)
          .enableAutoManage(activity/* FragmentActivity */, this /* OnConnectionFailedListener */)
          .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
          .build();

    }


  }








  @Override
  public void attachView(IntroView view) {
      this.view = view;
  }

  @Override
  public void detachView() {
    this.view = null;
  }

  @Override
  public void onDestroy() {
    detachView();
  }

  @Override
  public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

  }
}
