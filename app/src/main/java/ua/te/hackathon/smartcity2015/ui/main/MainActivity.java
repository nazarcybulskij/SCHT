package ua.te.hackathon.smartcity2015.ui.main;

import android.os.Bundle;

import ua.te.hackathon.smartcity2015.R;
import ua.te.hackathon.smartcity2015.ui.BaseActivity;

public class MainActivity extends BaseActivity implements MainView {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }
}
