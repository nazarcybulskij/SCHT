package ua.te.hackathon.smartcity2015.ui.base.adapters

import android.support.v7.widget.RecyclerView

/**
 * @author victor
 * *
 * @since 2016-02-14
 */
abstract class BaseRecyclerAdapter<T, V : RecyclerView.ViewHolder> : RecyclerView.Adapter<V>() {

  private var itemList: List<T>? = null

  fun setItemList(itemList: List<T>) {
    this.itemList = itemList
  }

  fun getItem(pos: Int): T? {
    return if (itemList == null) null else itemList!![pos]
  }

  override fun getItemCount(): Int {
    return if (itemList == null) 0 else itemList!!.size
  }
}
