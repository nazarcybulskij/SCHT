package ua.te.hackathon.smartcity2015.ui.intro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import ua.te.hackathon.smartcity2015.R;
import ua.te.hackathon.smartcity2015.ui.main.MainActivity;

/**
 * Created by nazarko on 2/13/16.
 */
public class IntroFinishFragment extends Fragment {

  public static final String COLOR = "extra:color_intro";
  int colorres;


  public IntroFinishFragment() {

  }


  public static IntroFinishFragment newInstance(int color) {
    IntroFinishFragment fragment = new IntroFinishFragment();
    Bundle args = new Bundle();
    args.putInt(COLOR, color);
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

    View view = inflater.inflate(R.layout.fragment_intro_finish, container, false);
    ButterKnife.bind(this, view);
    return view;


  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    view.setBackgroundResource(colorres);
  }

  @OnClick(R.id.login)
  public void onLoginClick(View v) {
    startMainActivity();
  }

  private void startMainActivity() {
    Intent intent = new Intent(getActivity(), MainActivity.class);
    startActivity(intent);
  }
}
