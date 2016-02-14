package ua.te.hackathon.smartcity2015.ui.main.events.browse.adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import ua.te.hackathon.smartcity2015.R;

/**
 * @author victor
 * @since 2016-02-14
 */
public class EventViewHolder extends RecyclerView.ViewHolder {

  @Nullable
  @Bind(R.id.textEventPlace)
  TextView textEventPlace;

  @Nullable
  @Bind(R.id.textEventTime)
  TextView textEventTime;

  @Nullable
  @Bind(R.id.textEventParticipantsCount)
  TextView textEventParticipantsCount;

  public EventViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }
}
