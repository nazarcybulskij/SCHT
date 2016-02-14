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
import ua.te.hackathon.smartcity2015.ui.base.adapters.OnItemClickListener;
import ua.te.hackathon.smartcity2015.ui.main.events.browse.adapters.EventsAdapter;
import ua.te.hackathon.smartcity2015.utils.Logger;

/**
 * A simple {@link Fragment} subclass.
 */
public class BrowseEventsFragment extends Fragment implements BrowseEventsView, SwipeRefreshLayout.OnRefreshListener {
  public static final String LOG_TAG = Logger.getLogTag(BrowseEventsFragment.class);

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
    adapter.setItemClickListener(new OnEventClickListener());
    recyclerEvents.setAdapter(adapter);

    swipeToRefresh.setOnRefreshListener(this);
    swipeToRefresh.setNestedScrollingEnabled(false);
  }

  private class OnEventClickListener implements OnItemClickListener {
    @Override
    public void onItemClicked(int pos) {
      Event event = adapter.getItem(pos);

      // open new activity, where user can join
    }
  }

  @Override
  public void showLoadingView() {
    Logger.d(LOG_TAG, "showLoadingView");
    swipeToRefresh.post(new Runnable() {
      @Override public void run() {
        swipeToRefresh.setRefreshing(true);
      }
    });
  }

  @Override
  public void hideLoadingView() {
    Logger.d(LOG_TAG, "hideLoadingView");
    swipeToRefresh.post(new Runnable() {
      @Override public void run() {
        swipeToRefresh.setRefreshing(false);
      }
    });
  }

  @Override
  public void deliverEventList(@NonNull List<Event> list) {
    Logger.d(LOG_TAG, "deliverEventList");
    adapter.setItemList(list);
    adapter.notifyDataSetChanged();
  }

  @Override
  public void deliverLoadingError(String error) {
    Logger.e(LOG_TAG, error);
    Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void onEventsSyncFinished(EventsSyncFinished event) {
    if (event.isSuccess()) {
      presenter.loadEvents();
    } else {
      deliverLoadingError(event.getError().getMessage());
      hideLoadingView();
    }
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
    Logger.d(LOG_TAG, "Upcoming events refresh is triggered by user");
    if (presenter != null) {
      presenter.onRefresh();
    }
  }
}
