package ua.te.hackathon.smartcity2015.ui.intro;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import ua.te.hackathon.smartcity2015.R;


public class IntroFragment extends Fragment {

  private static final String EXTRA_POSITION = "extra:pos";

  @Bind(R.id.imageIntro)
  ImageView imageIntro;

  @Bind(R.id.textIntro)
  TextView textIntro;

  private int pos;

  public static IntroFragment newInstance(int pos) {
    IntroFragment fragment = new IntroFragment();
    Bundle args = new Bundle();
    args.putInt(EXTRA_POSITION, pos);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    pos = getArguments().getInt(EXTRA_POSITION);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_intro, container, false);
    ButterKnife.bind(this, view);

    return view;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    TypedArray icons = getResources().obtainTypedArray(R.array.intro_icons);
    String[] slogans = getResources().getStringArray(R.array.intro_slogans);

    imageIntro.setImageDrawable(icons.getDrawable(pos));
    textIntro.setText(slogans[pos]);

    icons.recycle();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }
}
