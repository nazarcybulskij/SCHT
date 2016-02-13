package ua.te.hackathon.smartcity2015.ui.main.events.create;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import butterknife.Bind;
import butterknife.ButterKnife;
import ua.te.hackathon.smartcity2015.R;

public class CreateEventFragment extends Fragment implements CreateEventView {

  @Bind(R.id.categorySpinner)
  Spinner categorySpinner;

  @Bind(R.id.daySpinner)
  Spinner daySpinner;

  @Bind(R.id.timeSpinner)
  Spinner timeSpinner;

  @Bind(R.id.locationSpinner)
  Spinner locationSpinner;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View rootView = inflater.inflate(R.layout.fragment_create_event, container, false);
    ButterKnife.bind(this, rootView);

    ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(
        getContext(),
        R.layout.spinner_item,
        new String[]{"грати", "дивитись", "обговорити"}
    );
    categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    categorySpinner.setAdapter(categoryAdapter);

    ArrayAdapter<String> dayAdapter = new ArrayAdapter<>(
        getContext(),
        R.layout.spinner_item,
        new String[]{"сьогодні", "завтра", "інший"}
    );
    dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    daySpinner.setAdapter(dayAdapter);

    ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(
        getContext(),
        R.layout.spinner_item,
        new String[]{"зранку (9:00)", "в обід (13:00)", "вечером (20:00)"}
    );
    timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    timeSpinner.setAdapter(timeAdapter);

    ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(
        getContext(),
        R.layout.spinner_item,
        new String[]{"на міському стадіоні", "біля Нової", "на Болоті", "на Цизі", "десь інше"}
    );
    timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    locationSpinner.setAdapter(locationAdapter);

    return rootView;
  }
}
