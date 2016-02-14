package ua.te.hackathon.smartcity2015.ui.intro;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.viewpagerindicator.PageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ua.te.hackathon.smartcity2015.R;

public class IntroActivity extends AppCompatActivity {


  @Bind(R.id.intro_view_pager)
  ViewPager viewPagerInto;
  @Bind(R.id.indicator)
  PageIndicator indicatordots;

  public static Intent startRegistration(@NonNull Context context) {
    Intent intent = new Intent(context, IntroActivity.class);
    return intent;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_intro);
    ButterKnife.bind(this);
    initViewPager();
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
