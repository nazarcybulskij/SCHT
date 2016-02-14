package ua.te.hackathon.smartcity2015.ui.intro;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.viewpagerindicator.PageIndicator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ua.te.hackathon.smartcity2015.R;
import ua.te.hackathon.smartcity2015.google.GoogleApiHelper;
import ua.te.hackathon.smartcity2015.ui.main.MainActivity;
import ua.te.hackathon.smartcity2015.utils.LoggedUser;

public class IntroActivity extends FragmentActivity implements IntroView {

  @Bind(R.id.intro_view_pager)
  ViewPager viewPagerInto;
  @Bind(R.id.indicator)
  PageIndicator dotIndicators;

  public static Intent startRegistration(@NonNull Context context) {
    Intent intent = new Intent(context, IntroActivity.class);
    return intent;
  }

  private static IntroPresenter presenter;

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    presenter.handleOnActivityResult(requestCode, resultCode, data);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_intro);
    ButterKnife.bind(this);
    initViewPager();

    EventBus.getDefault().register(this);
  }

  private void initViewPager() {
    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

    String[] slogans = getResources().getStringArray(R.array.intro_slogans);

    for (int i = 0; i < slogans.length; i++) {
      adapter.addFragment(IntroFragment.newInstance(i));
    }

    viewPagerInto.setAdapter(adapter);
    dotIndicators.setViewPager(viewPagerInto);
  }

  @Override
  protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);

    if (presenter == null) {
      presenter = new IntroPresenter(getApplicationContext());
    }

    presenter.attachView(this);
  }

  @OnClick(R.id.login)
  public void onLoginClick(View v) {
    presenter.login(this);
  }

  private void startMainActivity() {
    Intent intent = new Intent(this, MainActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    startActivity(intent);
  }

  @Override
  public void showLoadingView() {
    Toast.makeText(getApplicationContext(), "show", Toast.LENGTH_SHORT).show();
  }

  @Override
  public void hideLoadingView() {
    Toast.makeText(getApplicationContext(), "hide", Toast.LENGTH_SHORT).show();
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void handleGoogleLoginResult(GoogleApiHelper.GoogleAuthResult result) {
    if (result.isSuccess()) {
      GoogleApiHelper.GoogleProfile profile = new GoogleApiHelper.GoogleProfile(result.getSignInAccount());

      LoggedUser.setFirstName(profile.getFirstName());
      LoggedUser.setLastName(profile.getLastName());
      LoggedUser.setPhotoUrl(profile.getImageUrl());
      LoggedUser.setEmail(profile.getEmail());

      startMainActivity();
      finish();
    } else {
      GoogleApiHelper.logOut();
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();

    if (presenter != null) {
      presenter.detachView();

      if (isFinishing()) {
        presenter.onDestroy();
        presenter = null;
      }
    }
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
