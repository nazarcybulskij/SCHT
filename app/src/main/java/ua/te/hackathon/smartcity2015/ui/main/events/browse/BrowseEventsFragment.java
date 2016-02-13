package ua.te.hackathon.smartcity2015.ui.main.events.browse;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ua.te.hackathon.smartcity2015.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BrowseEventsFragment extends Fragment implements BrowseEventsView {

  private static BrowseEventsPresenter presenter;

  @Bind(R.id.loadingView)
  View loadingView;

  @Bind(R.id.recyclerEvents)
  RecyclerView recyclerEvents;

  public static BrowseEventsFragment newInstance() {
    BrowseEventsFragment fragment = new BrowseEventsFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View rootView = inflater.inflate(R.layout.fragment_browse_events, container, false);
    ButterKnife.bind(this, rootView);

    if (presenter == null) {
      presenter = new BrowseEventsPresenter(getActivity().getApplicationContext());
    }

    return rootView;
  }

  @Override
  public void showLoadingView() {
    loadingView.setVisibility(View.VISIBLE);
    recyclerEvents.setVisibility(View.GONE);
  }

  @Override
  public void hideLoadingView() {
    loadingView.setVisibility(View.GONE);
    recyclerEvents.setVisibility(View.VISIBLE);
  }

  @Override
  public void deliverEventList(List list) {

  }

  @Override
  public void deliverLoadingError(String error) {
    Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
  }

  @Override
  public void onStart() {
    super.onStart();
    presenter.attachView(this);
  }

  @Override
  public void onStop() {
    super.onStop();
    presenter.detachView();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();

    if (presenter != null) {
      presenter.detachView();
      presenter.onDestroy();
    }
  }
}
