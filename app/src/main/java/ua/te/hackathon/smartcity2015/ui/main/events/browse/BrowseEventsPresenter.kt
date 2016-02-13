package ua.te.hackathon.smartcity2015.ui.main.events.browse

import android.content.Context
import android.location.Location
import pl.charmas.android.reactivelocation.ReactiveLocationProvider
import ua.te.hackathon.smartcity2015.model.Event
import ua.te.hackathon.smartcity2015.ui.base.mvp.Presenter
import java.util.*

/**
 * @author victor
 * *
 * @since 2016-02-13
 */
class BrowseEventsPresenter(private val appContext: Context) : Presenter<BrowseEventsView> {

  private var view: BrowseEventsView? = null

  private var eventList: List<Event>? = null

  private fun loadLastKnownLocation() {
    val locationProvider = ReactiveLocationProvider(appContext)
    locationProvider.lastKnownLocation.subscribe(
        { location -> findEventsNearBy(location) },
        { error -> onLocationLoadFailed(error) })
  }

  private fun findEventsNearBy(location: Location) {
    view?.deliverEventList(ArrayList())
    view?.hideLoadingView()
  }

  private fun onLocationLoadFailed(error: Throwable) {
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
