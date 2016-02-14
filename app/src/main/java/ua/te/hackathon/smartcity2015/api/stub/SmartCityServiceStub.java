package ua.te.hackathon.smartcity2015.api.stub;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import ua.te.hackathon.smartcity2015.SmartCityApp;
import ua.te.hackathon.smartcity2015.api.SmartCityService;
import ua.te.hackathon.smartcity2015.api.model.Event;

/**
 * @author victor
 * @since 2016-02-14
 */
public class SmartCityServiceStub implements SmartCityService {

  private Context appContext;

  public SmartCityServiceStub(Context appContext) {
    this.appContext = appContext;
  }

  @Override
  public Observable<List<Event>> getUpcomingEvents() {
    return Observable.create(new Observable.OnSubscribe<List<Event>>() {
      @Override
      public void call(Subscriber<? super List<Event>> subscriber) {
        Gson gson = new Gson();
        try {
          String text = getAssetFileContent(appContext, "stubs/upcoming.json");

          Event[] data = gson.fromJson(text, Event[].class);
          if (!subscriber.isUnsubscribed()) {
            subscriber.onNext(Arrays.asList(data));
            subscriber.onCompleted();
          }
        } catch (IOException e) {
          if (!subscriber.isUnsubscribed()) {
            subscriber.onError(e);
            subscriber.onCompleted();
          }
        }
      }
    });
  }

  private static String getAssetFileContent(@NonNull Context context, @NonNull String file) throws IOException {
    StringBuilder buf = new StringBuilder();
    InputStream json = context.getAssets().open(file);
    BufferedReader in =
        new BufferedReader(new InputStreamReader(json, "UTF-8"));
    String str;

    while ((str = in.readLine()) != null) {
      buf.append(str);
    }

    in.close();
    return buf.toString();
  }
}
