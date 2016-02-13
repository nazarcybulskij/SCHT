package ua.te.hackathon.smartcity2015.ui.main.events.browse;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;
import ua.te.hackathon.smartcity2015.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BrowseEventsFragment extends Fragment implements BrowseEventsView {

  private static BrowseEventsPresenter presenter;

  public BrowseEventsFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View rootView = inflater.inflate(R.layout.fragment_browse_events, container, false);
    ButterKnife.bind(this, rootView);



    return rootView;
  }

  @Override
  public void showLoadingView() {

  }

  @Override
  public void hideLoadingView() {

  }

  @Override
  public void deliverEventList(List list) {

  }

  @Override
  public void deliverLoadingError() {

  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }
}
