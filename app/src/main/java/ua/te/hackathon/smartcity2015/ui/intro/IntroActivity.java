package ua.te.hackathon.smartcity2015.ui.intro;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;


import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ua.te.hackathon.smartcity2015.R;
import ua.te.hackathon.smartcity2015.ui.BaseActivity;
import ua.te.hackathon.smartcity2015.ui.main.MainActivity;
import ua.te.hackathon.smartcity2015.ui.main.MainPresenter;
import ua.te.hackathon.smartcity2015.utils.Utils;

public class IntroActivity extends FragmentActivity implements  IntroView {


  @Bind(R.id.intro_view_pager)
  ViewPager viewPagerInto;
  @Bind(R.id.indicator)
  PageIndicator indicatordots;

  public static Intent startRegistration(@NonNull Context context) {
    Intent intent = new Intent(context, IntroActivity.class);
    return intent;
  }

  @Bind(R.id.login)
  SignInButton  signInButton;

  private static IntroPresenter presenter;


  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == IntroPresenter.REQUEST_SIGNUP) {
      if (resultCode == RESULT_OK) {

        // TODO: Implement successful signup logic here
        // By default we just finish the Activity and log them in automatically
        this.finish();
        return;
      }
    }

    if (requestCode == IntroPresenter.RC_SIGN_IN) {
      GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
      presenter.handleSignInResult(result);

    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_intro);
    ButterKnife.bind(this);
    initViewPager();
    initSiginButton();

  }

  private void initSiginButton() {
    // [START customize_button]
    // Customize sign-in button. The sign-in button can be displayed in
    // multiple sizes and color schemes. It can also be contextually
    // rendered based on the requested scopes. For example. a red button may
    // be displayed when Google+ scopes are requested, but a white button
    // may be displayed when only basic profile is requested. Try adding the
    // Scopes.PLUS_LOGIN scope to the GoogleSignInOptions to see the

    // [START configure_signin]
    // Configure sign-in to request the user's ID, email address, and basic
    // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build();
    // difference.

    signInButton.setSize(SignInButton.SIZE_ICON_ONLY);
    signInButton.setScopes(gso.getScopeArray());

  }

  private void initViewPager() {
    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

    String[] slogans = getResources().getStringArray(R.array.intro_slogans);

    for (int i = 0; i < slogans.length; i++) {
      adapter.addFragment(IntroFragment.newInstance(i));
    }

    viewPagerInto.setAdapter(adapter);
    indicatordots.setViewPager(viewPagerInto);
  }

  @Override
  protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);

    if (presenter == null) {
      presenter = new IntroPresenter(getApplicationContext());
    }

    presenter.attachView(this);
    presenter.attachActivity(this);
  }

  @OnClick(R.id.login)
  public  void onLoginClick(View v){
    presenter.login(this);
  }

  private  void  startMainActivity(){
    Intent intent = new Intent(this, MainActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    startActivity(intent);
  }

  @Override
  public void showLoadingView() {
    Toast.makeText(getApplicationContext(),"show",Toast.LENGTH_SHORT).show();
  }

  @Override
  public void hideLoadingView() {
    Toast.makeText(getApplicationContext(),"hide",Toast.LENGTH_SHORT).show();

  }



  @Override
  public void success() {
    new Utils(this).setUserAuthenticated(true);
    startMainActivity();
  }

  @Override
  public void error() {
    new Utils(this).setUserAuthenticated(false);


  }


  class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragmentList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
      super(manager);
    }

    @Override
    public Fragment getItem(int position) {
      return fragmentList.get(position);
    }

    @Override
    public int getCount() {
      return fragmentList.size();
    }

    public void addFragment(Fragment fragment) {
      fragmentList.add(fragment);
    }
  }

}
