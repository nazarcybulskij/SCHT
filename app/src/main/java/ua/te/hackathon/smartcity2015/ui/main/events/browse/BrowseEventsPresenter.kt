package ua.te.hackathon.smartcity2015.ui.main.events.browse

import android.content.Context
import android.location.Location
import pl.charmas.android.reactivelocation.ReactiveLocationProvider
import ua.te.hackathon.smartcity2015.ui.Presenter

/**
 * @author victor
 * *
 * @since 2016-02-13
 */
class BrowseEventsPresenter(private val appContext: Context) : Presenter<BrowseEventsView> {

  private var view: BrowseEventsView? = null

  private fun loadLastKnownLocation() {
    val locationProvider = ReactiveLocationProvider(appContext)
    locationProvider.lastKnownLocation.subscribe(
        { location -> onLocationLoaded(location) },
        { error -> onLocationLoadFailed(error)})
  }

  private fun onLocationLoaded(location: Location) {

  }

  private fun findEventsNearBy(location: Location) {

  }

  private fun onLocationLoadFailed(error: Throwable) {
    view?.deliverLoadingError(error.message)
  }

  fun loadEvents() {
    view?.showLoadingView()

    loadLastKnownLocation()

    // loading

    // loading done
    view?.deliverEventList(null)
    view?.hideLoadingView()
  }

  override fun attachView(view: BrowseEventsView) {
    this.view = view
  }

  override fun detachView() {
    this.view = null
  }

  override fun onDestroy() {
    detachView()
  }
}
