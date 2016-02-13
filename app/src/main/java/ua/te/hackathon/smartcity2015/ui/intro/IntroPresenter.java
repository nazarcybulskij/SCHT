package ua.te.hackathon.smartcity2015.ui.intro;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

import ua.te.hackathon.smartcity2015.ui.Presenter;
import ua.te.hackathon.smartcity2015.ui.PresenterView;
import ua.te.hackathon.smartcity2015.ui.main.events.browse.BrowseEventsView;

/**
 * Created by nazarko on 2/13/16.
 */
public class IntroPresenter implements Presenter<IntroView> {

  @Nullable
  private IntroView view;

  private Context appContext;
  private Activity activity;

  private GoogleApiClient mGoogleApiClient;

  private static final int RC_SIGN_IN = 9001;


  public IntroPresenter(Context appContext) {
    this.appContext = appContext;



  }

  public void login(){
    if (view != null) {
      view.showLoadingView();
    }


    // login
    //view.success();
    //view.error();

    Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
    activity.startActivityForResult(signInIntent, RC_SIGN_IN);


    // loading done
    if (view != null) {
      view.hideLoadingView();
    }

  }



  public void attachActivity(FragmentActivity  activity) {
    this.activity = activity;

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
    mGoogleApiClient = new GoogleApiClient.Builder(this.appContext)
        .enableAutoManage(activity/* FragmentActivity */, (GoogleApiClient.OnConnectionFailedListener) activity /* OnConnectionFailedListener */)
        .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
        .build();

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
}
