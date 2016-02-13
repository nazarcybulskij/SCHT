package ua.te.hackathon.smartcity2015.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ua.te.hackathon.smartcity2015.R;
import ua.te.hackathon.smartcity2015.ui.BaseActivity;
import ua.te.hackathon.smartcity2015.ui.BrowseEventsFragment;
import ua.te.hackathon.smartcity2015.ui.CreateEventFragment;

public class MainActivity extends BaseActivity implements MainView {

  private static MainPresenter presenter;

  @Bind(R.id.toolbar)
  Toolbar toolbar;

  @Bind(R.id.viewpager)
  ViewPager viewPager;

  @Bind(R.id.tabs)
  TabLayout tabLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    setSupportActionBar(toolbar);

    setupViewPager();

    tabLayout.setupWithViewPager(viewPager);
  }

  @Override
  protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);

    if (presenter == null) {
      presenter = new MainPresenter(getApplicationContext());
    }

    presenter.attachView(this);
  }

  public void setupViewPager() {
    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
    adapter.addFragment(new CreateEventFragment(), "Create Event");
    adapter.addFragment(new BrowseEventsFragment(), "Browse Events");
    viewPager.setAdapter(adapter);
  }

  class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
      super(manager);
    }

    @Override
    public Fragment getItem(int position) {
      return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
      return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
      mFragmentList.add(fragment);
      mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
      return mFragmentTitleList.get(position);
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    ButterKnife.unbind(this);

    if (presenter != null) {
      presenter.detachView();

      if (isFinishing()) {
        presenter.onDestroy();
      }
    }
  }
}
