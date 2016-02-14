package ua.te.hackathon.smartcity2015.ui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.OptionalPendingResult;

import java.util.Locale;

import ua.te.hackathon.smartcity2015.ui.intro.IntroActivity;

public abstract class BaseActivity extends AppCompatActivity {

  @Override
  public final void onCreate(Bundle savedInstanceState) {
    onPreCreate(savedInstanceState);
    super.onCreate(savedInstanceState);

    if (isUserAuthenticated()) {
      onCreateAuthenticated(savedInstanceState);
    } else {
      onCreateUnauthenticated(savedInstanceState);
    }
  }

  protected abstract void onCreateAuthenticated(Bundle savedInstanceState);

  protected void onPreCreate(Bundle savedInstanceState) {
    if (shouldLockOrientation()) {
      setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    setUkrainianLocale();
  }

  protected boolean shouldLockOrientation() {
    return true;
  }

  private boolean isUserAuthenticated() {
      return  true;

  }

  private void setUkrainianLocale() {
    Locale locale = new Locale("uk");
    Locale.setDefault(locale);
    Configuration config = new Configuration();
    config.locale = locale;
    getBaseContext().getResources().updateConfiguration(config,
        getBaseContext().getResources().getDisplayMetrics());
  }

  public void onCreateUnauthenticated(Bundle savedInstanceState) {
    Intent intent = IntroActivity.startRegistration(this);
    startActivity(intent);
    finish();
  }
}
