package ua.te.hackathon.smartcity2015.api;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;
import ua.te.hackathon.smartcity2015.api.model.Event;

/**
 * @author victor
 * @since 2016-02-13
 */
public interface SmartCityService {

  @GET("events/upcoming")
  Observable<List<Event>> getUpcomingEvents();

}
