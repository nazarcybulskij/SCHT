package ua.te.hackathon.smartcity2015.api

import rx.Observable
import ua.te.hackathon.smartcity2015.api.model.Event

/**
 * @author victor
 * *
 * @since 2016-02-13
 */
interface SmartCityService {

  fun upcomingEvents(): Observable<List<Event>>

}
