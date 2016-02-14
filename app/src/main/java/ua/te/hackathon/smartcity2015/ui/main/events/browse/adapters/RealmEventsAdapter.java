package ua.te.hackathon.smartcity2015.ui.main.events.browse.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;
import ua.te.hackathon.smartcity2015.R;
import ua.te.hackathon.smartcity2015.db.model.Event;

/**
 * Created by nazarko on 2/14/16.
 */
public class RealmEventsAdapter  extends RealmBaseAdapter<Event> implements ListAdapter {


  public RealmEventsAdapter(Context context, int resId, RealmResults<Event> realmResults, boolean automaticUpdate) {
    super(context, realmResults, automaticUpdate);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    Holder viewHolder;
    if (convertView == null) {
      convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
      viewHolder = new Holder(convertView);
      convertView.setTag(viewHolder);
    } else {
      viewHolder = (Holder) convertView.getTag();
    }

    Event item = realmResults.get(position);
    viewHolder.date.setText(String.valueOf(item.getDate()));
    viewHolder.name.setText(item.getName());
    viewHolder.plase.setText(item.getName());

    return convertView;
  }


  public static class Holder{
    @Bind(R.id.place)
    TextView  plase;
    @Bind(R.id.name)
    TextView  name;
    @Bind(R.id.date)
    TextView  date;


    public Holder(View v) {
      ButterKnife.bind(v);
    }
  }






}
