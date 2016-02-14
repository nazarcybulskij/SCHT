package ua.te.hackathon.smartcity2015.ui.base.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * @author victor
 * @since 2016-02-14
 */
public abstract class BaseRecyclerAdapter<T, V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V> {

  private List<T> itemList = null;

  public void setItemList(@NonNull List<T> itemList) {
    this.itemList = itemList;
  }

  public T getItem(int pos) {
    return itemList == null ? null : itemList.get(pos);
  }

  @Override
  public int getItemCount() {
    return itemList == null ? 0 : itemList.size();
  }
}
