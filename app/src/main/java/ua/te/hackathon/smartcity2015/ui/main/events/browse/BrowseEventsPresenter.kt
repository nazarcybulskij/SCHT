package ua.te.hackathon.smartcity2015.ui.main.events.browse

import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationRequest
import io.realm.Realm
import io.realm.Sort
import org.joda.time.DateTime
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
    // right now this method returns nothing as probably there is no
    // runtime permission

    //    val locationProvider = ReactiveLocationProvider(appContext)
    //    locationProvider.lastKnownLocation
    //        .subscribeOn(Schedulers.io())
    //        .observeOn(AndroidSchedulers.mainThread())
    //        .subscribe(
    //            { location -> findEventsNearBy(location) },
    //            { error -> onLocationLoadFailed(error) }
    //        )

    val location = Location("wifi")
    location.latitude = 30.0
    location.longitude = 40.0
    findEventsNearBy(location)
  }

  private fun findEventsNearBy(location: Location) {
    val realm = Realm.getInstance(appContext)
    val results = realm.where(Event::class.java)
        .greaterThan("date", System.currentTimeMillis())
        .findAllSorted("date", Sort.ASCENDING);

    view?.deliverEventList(results)
    view?.hideLoadingView()
  }

  fun onRefresh() {
    Logger.d(LOG_TAG, "Starting upcoming events loading")
    SyncManager.syncUpcomingEvents(appContext)
  }

  private fun onLocationLoadFailed(error: Throwable) {
    Logger.e(LOG_TAG, error)

    val request = LocationRequest.create() //standard GMS LocationRequest
        .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        .setNumUpdates(1)
        .setInterval(100);

    val locationProvider = ReactiveLocationProvider(appContext);
    val subscription = locationProvider.getUpdatedLocation(request)
        .subscribe({ location ->
          findEventsNearBy(location);
        });

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
      view.hideLoadingView()
    }
  }

  override fun detachView() {
    this.view = null
  }

  override fun onDestroy() {
    detachView()
  }
}
