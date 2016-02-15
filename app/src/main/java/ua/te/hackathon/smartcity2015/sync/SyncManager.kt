package ua.te.hackathon.smartcity2015.sync

import android.content.Context
import org.greenrobot.eventbus.EventBus
import rx.schedulers.Schedulers
import ua.te.hackathon.smartcity2015.SmartCityApp
import ua.te.hackathon.smartcity2015.db.EventsManager
import ua.te.hackathon.smartcity2015.sync.events.EventsSyncFinished
import ua.te.hackathon.smartcity2015.utils.Logger

/**
 * @author victor
 * *
 * @since 2016-02-14
 */
object SyncManager {
  private val LOG_TAG = Logger.getLogTag(SyncManager::class.java)

  fun syncUpcomingEvents(appContext: Context) {
    Logger.d(LOG_TAG, "Upcoming events sync is triggered")
    val service = SmartCityApp.app!!.apiService

    service!!.upcomingEvents()
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe({ eventList ->
          Logger.d(LOG_TAG, "Upcoming events are loaded from server")
          EventsManager.updateEvents(appContext, eventList)
          EventBus.getDefault().post(EventsSyncFinished())
        },
            { error ->
              Logger.e(LOG_TAG, "Error occurred during loading of upcoming events")
              EventBus.getDefault().post(EventsSyncFinished(error))
            })
  }

}
