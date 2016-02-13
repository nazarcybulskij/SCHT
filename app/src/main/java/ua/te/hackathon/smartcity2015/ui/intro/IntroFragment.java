package ua.te.hackathon.smartcity2015.ui.intro;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.te.hackathon.smartcity2015.R;



public class IntroFragment extends Fragment {

  public static final String COLOR = "extra:color_intro";
  int colorres ;


  public IntroFragment() {

  }


  public static IntroFragment newInstance(int color) {
    IntroFragment fragment = new IntroFragment();
    Bundle args = new Bundle();
    args.putInt(COLOR,color);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      colorres = getArguments().getInt(COLOR);

    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_intro, container, false);

  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    view.setBackgroundResource(colorres);
  }


}
