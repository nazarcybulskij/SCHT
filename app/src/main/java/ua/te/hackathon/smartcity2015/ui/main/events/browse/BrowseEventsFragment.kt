package ua.te.hackathon.smartcity2015.ui.main.events.browse


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import ua.te.hackathon.smartcity2015.R
import ua.te.hackathon.smartcity2015.sync.events.EventsSyncFinished
import ua.te.hackathon.smartcity2015.ui.base.adapters.OnItemClickListener
import ua.te.hackathon.smartcity2015.ui.main.events.browse.adapters.EventsAdapter
import ua.te.hackathon.smartcity2015.utils.Logger

import kotlinx.android.synthetic.main.fragment_browse_events.*
import ua.te.hackathon.smartcity2015.db.model.Event

/**
 * A simple [Fragment] subclass.
 */
class BrowseEventsFragment : Fragment(), BrowseEventsView, SwipeRefreshLayout.OnRefreshListener {

  private var adapter: EventsAdapter? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    if (presenter == null) {
      presenter = BrowseEventsPresenter(activity.applicationContext)
    }
  }

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
    val rootView = inflater!!.inflate(R.layout.fragment_browse_events, container, false)

    EventBus.getDefault().register(this)

    return rootView
  }

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    recyclerEvents.layoutManager = LinearLayoutManager(activity)
    recyclerEvents.setHasFixedSize(true)
    adapter = EventsAdapter()
    adapter!!.itemClickListener = OnEventClickListener()
    recyclerEvents.adapter = adapter

    swipeToRefresh.setOnRefreshListener(this)
    swipeToRefresh.isNestedScrollingEnabled = false
  }

  private inner class OnEventClickListener : OnItemClickListener {
    override fun onItemClicked(pos: Int) {
      val event = adapter!!.getItem(pos)

      // open new activity, where user can join
    }
  }

  override fun showLoadingView() {
    Logger.d(LOG_TAG, "showLoadingView")
    swipeToRefresh.post { swipeToRefresh.isRefreshing = true }
  }

  override fun hideLoadingView() {
    Logger.d(LOG_TAG, "hideLoadingView")
    swipeToRefresh.post { swipeToRefresh.isRefreshing = false }
  }

  override fun deliverEventList(list: List<Event>) {
    Logger.d(LOG_TAG, "deliverEventList")
    adapter!!.setItemList(list)
    adapter!!.notifyDataSetChanged()
  }

  override fun deliverLoadingError(error: String) {
    Logger.e(LOG_TAG, error)
    Toast.makeText(activity, error, Toast.LENGTH_LONG).show()
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  fun onEventsSyncFinished(event: EventsSyncFinished) {
    if (event.isSuccess) {
      presenter!!.loadEvents()
    } else {
      deliverLoadingError(event.error!!.message!!)
      hideLoadingView()
    }
  }

  override fun onStart() {
    super.onStart()
    presenter!!.attachView(this)
  }

  override fun onStop() {
    super.onStop()
    presenter!!.detachView()
  }

  override fun onDestroyView() {
    super.onDestroyView()
    EventBus.getDefault().unregister(this)
  }

  override fun onDestroy() {
    super.onDestroy()

    if (presenter != null) {
      presenter!!.detachView()
      presenter!!.onDestroy()
    }
  }

  override fun onRefresh() {
    Logger.d(LOG_TAG, "Upcoming events refresh is triggered by user")
    if (presenter != null) {
      presenter!!.onRefresh()
    }
  }

  companion object {
    val LOG_TAG = Logger.getLogTag(BrowseEventsFragment::class.java)

    private var presenter: BrowseEventsPresenter? = null

    fun newInstance(): BrowseEventsFragment {
      val fragment = BrowseEventsFragment()
      val args = Bundle()
      fragment.arguments = args
      return fragment
    }
  }
}
