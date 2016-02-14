package ua.te.hackathon.smartcity2015.ui.main.events.browse

import android.content.Context
import android.location.Location
import io.realm.Realm
import pl.charmas.android.reactivelocation.ReactiveLocationProvider
import ua.te.hackathon.smartcity2015.db.model.Event
import ua.te.hackathon.smartcity2015.sync.SyncManager
import ua.te.hackathon.smartcity2015.ui.base.mvp.Presenter
import ua.te.hackathon.smartcity2015.utils.Logger

/**
 * @author victor
 * *
 * @since 2016-02-13
 */
class BrowseEventsPresenter(private val appContext: Context) : Presenter<BrowseEventsView> {

  companion object Factory {
    val LOG_TAG = Logger.getLogTag(BrowseEventsPresenter::class.java)
  }

  private var view: BrowseEventsView? = null

  private var eventList: List<Event>? = null

  private fun loadLastKnownLocation() {
    val locationProvider = ReactiveLocationProvider(appContext)
    locationProvider.lastKnownLocation.subscribe(
        { location -> findEventsNearBy(location) },
        { error -> onLocationLoadFailed(error) })
  }

  private fun findEventsNearBy(location: Location) {
    Logger.d(LOG_TAG, "Location %f %f".format(location.longitude, location.latitude));

    val realm = Realm.getInstance(appContext)
    val results = realm.allObjects(Event::class.java)

    view?.deliverEventList(results)
    view?.hideLoadingView()
  }

  fun onRefresh() {
    SyncManager.syncUpcomingEvents(appContext)
  }

  private fun onLocationLoadFailed(error: Throwable) {
    Logger.e(LOG_TAG, error)

    view?.deliverLoadingError(error.message)
  }

  fun loadEvents() {
    view?.showLoadingView()

    loadLastKnownLocation()
  }

  override fun attachView(view: BrowseEventsView) {
    this.view = view

    if (eventList == null) {
      loadEvents()
    } else {
      view.deliverEventList(eventList!!)
    }
  }

  override fun detachView() {
    this.view = null
  }

  override fun onDestroy() {
    detachView()
  }
}
