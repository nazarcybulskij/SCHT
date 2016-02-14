package ua.te.hackathon.smartcity2015.ui.main.events.browse.adapters;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Locale;

import ua.te.hackathon.smartcity2015.R;
import ua.te.hackathon.smartcity2015.db.model.Event;
import ua.te.hackathon.smartcity2015.ui.base.adapters.BaseRecyclerAdapter;
import ua.te.hackathon.smartcity2015.ui.base.adapters.OnItemClickListener;
import ua.te.hackathon.smartcity2015.utils.Logger;
import ua.te.hackathon.smartcity2015.utils.TimeUtils;

/**
 * @author victor
 * @since 2016-02-14
 */
public class EventsAdapter extends BaseRecyclerAdapter<Event, EventViewHolder> {

  @Nullable
  private OnItemClickListener itemClickListener;

  @Nullable
  public OnItemClickListener getItemClickListener() {
    return itemClickListener;
  }

  public void setItemClickListener(@Nullable OnItemClickListener itemClickListener) {
    this.itemClickListener = itemClickListener;
  }

  @Override
  public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_event, parent, false);
    Logger.d("TAG", view);
    return new EventViewHolder(view);
  }

  @Override
  public void onBindViewHolder(EventViewHolder holder, final int position) {
    Event event = getItem(position);

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (itemClickListener != null) {
          itemClickListener.onItemClicked(position);
        }
      }
    });

    holder.textEventPlace.setText(event.getPlace());
    int joinedUsersCount = event.getJoinedUsers().size();
    if (joinedUsersCount > 0) {
      holder.textEventParticipantsCount.setText(String.format(Locale.US, "%d", joinedUsersCount));
    } else {
      holder.textEventParticipantsCount.setText(String.format(Locale.US, "%d", 1));
//      holder.textEventParticipantsCount.setVisibility(View.GONE);
    }

    String day = TimeUtils.getDayPresentation(holder.itemView.getContext(), event.getDate());
    String time = TimeUtils.getTimePresentation(holder.itemView.getContext(), event.getDate());

    String absoluteTime = String.format(holder.itemView.getContext().getString(R.string.event_time), day, time);

    holder.textEventTime.setText(absoluteTime);
  }

}
