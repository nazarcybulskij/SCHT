package ua.te.hackathon.smartcity2015.ui.main.events.browse;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ua.te.hackathon.smartcity2015.R;
import ua.te.hackathon.smartcity2015.db.model.Event;
import ua.te.hackathon.smartcity2015.sync.events.EventsSyncFinished;
import ua.te.hackathon.smartcity2015.ui.main.events.browse.adapters.EventsAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class BrowseEventsFragment extends Fragment implements BrowseEventsView, SwipeRefreshLayout.OnRefreshListener {

  private static BrowseEventsPresenter presenter;

  @Bind(R.id.recyclerEvents)
  RecyclerView recyclerEvents;

  @Bind(R.id.swipeContainer)
  SwipeRefreshLayout swipeToRefresh;

  private EventsAdapter adapter;

  public static BrowseEventsFragment newInstance() {
    BrowseEventsFragment fragment = new BrowseEventsFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (presenter == null) {
      presenter = new BrowseEventsPresenter(getActivity().getApplicationContext());
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View rootView = inflater.inflate(R.layout.fragment_browse_events, container, false);
    ButterKnife.bind(this, rootView);

    EventBus.getDefault().register(this);

    return rootView;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    recyclerEvents.setLayoutManager(new LinearLayoutManager(getActivity()));
    recyclerEvents.setHasFixedSize(true);
    adapter = new EventsAdapter();
    recyclerEvents.setAdapter(adapter);

    swipeToRefresh.setOnRefreshListener(this);
    swipeToRefresh.setNestedScrollingEnabled(false);
  }

  @Override
  public void showLoadingView() {
    swipeToRefresh.setRefreshing(true);
  }

  @Override
  public void hideLoadingView() {
    swipeToRefresh.setRefreshing(false);
  }

  @Override
  public void deliverEventList(@NonNull List<Event> list) {
    adapter.setItemList(list);
    adapter.notifyDataSetChanged();
  }

  @Override
  public void deliverLoadingError(String error) {
    Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void onEventsSyncFinished(EventsSyncFinished event) {
    if (event.isSuccess()) {
      presenter.loadEvents();
    } else {
      deliverLoadingError(event.getError().getMessage());
    }
  }

  @Override
  public void onStart() {
    super.onStart();
    presenter.attachView(this);
    presenter.onRefresh();
  }

  @Override
  public void onStop() {
    super.onStop();
    presenter.detachView();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    EventBus.getDefault().unregister(this);
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

  @Override
  public void onRefresh() {
    if (presenter != null && !swipeToRefresh.isRefreshing()) {
      presenter.onRefresh();
    }
  }
}
