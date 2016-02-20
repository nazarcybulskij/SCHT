package ua.te.hackathon.smartcity2015.ui.main.events.browse.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.list_item_event.view.*

/**
 * @author victor
 * *
 * @since 2016-02-14
 */
class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  internal val textEventPlace = itemView.textEventPlace
  internal var textEventTime = itemView.textEventTime
  internal var textEventParticipantsCount = itemView.textEventParticipantsCount

  init {
  }
}
