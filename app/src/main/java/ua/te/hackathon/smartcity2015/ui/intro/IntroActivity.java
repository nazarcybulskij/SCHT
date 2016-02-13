package ua.te.hackathon.smartcity2015.ui.intro;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.viewpagerindicator.CirclePageIndicator;
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
    adapter.addFragment(IntroFragment.newInstance(R.color.blue));
    adapter.addFragment(IntroFragment.newInstance(R.color.red));
    adapter.addFragment(IntroFinishFragment.newInstance(R.color.green));
    viewPagerInto.setAdapter(adapter);
    indicatordots.setViewPager(viewPagerInto);


  }


  class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();

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

    public void addFragment(Fragment fragment) {
      mFragmentList.add(fragment);
    }


  }

}
