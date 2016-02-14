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

import ua.te.hackathon.smartcity2015.google.GoogleApiHelper;
import ua.te.hackathon.smartcity2015.ui.base.mvp.Presenter;
import ua.te.hackathon.smartcity2015.ui.main.events.browse.BrowseEventsView;

/**
 * Created by nazarko on 2/13/16.
 */
public class IntroPresenter implements Presenter<IntroView> {

  @Nullable
  private IntroView view;

  private Context appContext;

  private GoogleApiHelper googleApiHelper;


  public IntroPresenter(Context appContext) {
    this.appContext = appContext;
    this.googleApiHelper = new GoogleApiHelper(appContext);
  }

  public void login(FragmentActivity activity){
    googleApiHelper.startGoogleRegistration(activity);
  }

  public void handleOnActivityResult(int requestCode, int resultCode, Intent data) {
    googleApiHelper.onActivityResult(requestCode, resultCode, data);
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
    googleApiHelper = null;
  }

}
