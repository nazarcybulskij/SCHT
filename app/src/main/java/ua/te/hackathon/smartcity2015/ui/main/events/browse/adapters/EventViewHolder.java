package ua.te.hackathon.smartcity2015.ui.main.events.browse.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * @author victor
 * @since 2016-02-14
 */
public class EventViewHolder extends RecyclerView.ViewHolder {
  public EventViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }
}
