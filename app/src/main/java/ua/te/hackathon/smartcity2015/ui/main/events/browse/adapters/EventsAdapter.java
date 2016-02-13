package ua.te.hackathon.smartcity2015.ui.main.events.browse.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.te.hackathon.smartcity2015.R;
import ua.te.hackathon.smartcity2015.model.Event;
import ua.te.hackathon.smartcity2015.ui.base.adapters.BaseRecyclerAdapter;

/**
 * @author victor
 * @since 2016-02-14
 */
public class EventsAdapter extends BaseRecyclerAdapter<Event, EventViewHolder> {

  @Override
  public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_event, parent, false);
    return new EventViewHolder(view);
  }

  @Override
  public void onBindViewHolder(EventViewHolder holder, int position) {

  }

}
