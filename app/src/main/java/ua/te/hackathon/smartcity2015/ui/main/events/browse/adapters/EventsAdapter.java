package ua.te.hackathon.smartcity2015.ui.main.events.browse.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Locale;

import ua.te.hackathon.smartcity2015.R;
import ua.te.hackathon.smartcity2015.db.model.Event;
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
    Event event = getItem(position);

    holder.textEventPlace.setText(event.getPlace());
    int joinedUsersCount = event.getJoinedUsers().size();
    if (joinedUsersCount > 0) {
      holder.textEventParticipantsCount.setText(String.format(Locale.US, "%d", joinedUsersCount));
    } else {
      holder.textEventParticipantsCount.setVisibility(View.GONE);
    }
    holder.textEventTime.setText(String.format(Locale.US, "%d", event.getDate()));
  }

}
