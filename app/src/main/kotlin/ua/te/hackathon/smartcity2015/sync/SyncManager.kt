package ua.te.hackathon.smartcity2015.sync

import android.content.Context
import common.Logger
import org.greenrobot.eventbus.EventBus
import rx.schedulers.Schedulers
import ua.te.hackathon.smartcity2015.Injector
import ua.te.hackathon.smartcity2015.api.SmartCityService
import ua.te.hackathon.smartcity2015.db.EventsManager
import ua.te.hackathon.smartcity2015.sync.events.EventsSyncFinished
import javax.inject.Inject

/**
 * @author victor
 *
 * @since 2016-02-14
 */
class SyncManager {
  companion object Factory {
    private val LOG_TAG = Logger.getLogTag(SyncManager::class.java)
  }

  @Inject
  lateinit var apiService: SmartCityService

  @Inject
  lateinit var context: Context

  @Inject
  constructor() {
  }

  fun syncUpcomingEvents() {
    Logger.d(SyncManager.LOG_TAG, "Upcoming events sync is triggered")

    apiService.upcomingEvents()
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe({ eventList ->
          Logger.d(SyncManager.LOG_TAG, "Upcoming events are loaded from server")
          EventsManager.updateEvents(context, eventList)
          EventBus.getDefault().post(EventsSyncFinished())
        },
            { error ->
              Logger.e(SyncManager.LOG_TAG, "Error occurred during loading of upcoming events")
              EventBus.getDefault().post(EventsSyncFinished(error))
            })
  }

}
