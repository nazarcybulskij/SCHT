package ua.te.hackathon.smartcity2015.ui.main.events.browse.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import ua.te.hackathon.smartcity2015.R
import ua.te.hackathon.smartcity2015.db.model.Event
import ua.te.hackathon.smartcity2015.ui.base.adapters.BaseRecyclerAdapter
import ua.te.hackathon.smartcity2015.ui.base.adapters.OnItemClickListener
import ua.te.hackathon.smartcity2015.utils.TimeUtils
import java.util.*

/**
 * @author victor
 * *
 * @since 2016-02-14
 */
class EventsAdapter : BaseRecyclerAdapter<Event, EventViewHolder>() {

  var itemClickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_event, parent, false)
    return EventViewHolder(view)
  }

  override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
    val event = getItem(position)!!

    holder.itemView.setOnClickListener {
      if (itemClickListener != null) {
        itemClickListener!!.onItemClicked(position)
      }
    }

    holder.textEventPlace?.text = event.place
    val joinedUsersCount = event.joinedUsers!!.size
    if (joinedUsersCount > 0) {
      holder.textEventParticipantsCount?.text = String.format(Locale.US, "%d", joinedUsersCount)
    } else {
      holder.textEventParticipantsCount?.text = String.format(Locale.US, "%d", 1)
      //      holder.textEventParticipantsCount.setVisibility(View.GONE);
    }

    val day = TimeUtils.getDayPresentation(holder.itemView.context, event.date)
    val time = TimeUtils.getTimePresentation(holder.itemView.context, event.date)

    val absoluteTime = String.format(holder.itemView.context.getString(R.string.event_time), day, time)

    holder.textEventTime?.text = absoluteTime
  }

}
