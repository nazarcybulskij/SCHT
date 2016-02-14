package ua.te.hackathon.smartcity2015.sync

import android.content.Context
import org.greenrobot.eventbus.EventBus
import rx.schedulers.Schedulers
import ua.te.hackathon.smartcity2015.SmartCityApp
import ua.te.hackathon.smartcity2015.db.EventsManager
import ua.te.hackathon.smartcity2015.sync.events.EventsSyncFinished

/**
 * @author victor
 * *
 * @since 2016-02-14
 */
object SyncManager {

  fun syncUpcomingEvents(appContext: Context) {
    val service = SmartCityApp.getApp().apiService

    service.upcomingEvents.subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
        .subscribe({ eventList ->
          EventsManager.updateEvents(appContext, eventList)
          EventBus.getDefault().post(EventsSyncFinished())
        },
            { error ->
              EventBus.getDefault().post(EventsSyncFinished(error))
            })
  }

}
