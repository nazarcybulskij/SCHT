package ua.te.hackathon.smartcity2015.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.te.hackathon.smartcity2015.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BrowseEventsFragment extends Fragment {


  public BrowseEventsFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_browse_events, container, false);
  }

}
