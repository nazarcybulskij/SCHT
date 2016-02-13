package ua.te.hackathon.smartcity2015;

import android.app.Application;

import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import ua.te.hackathon.smartcity2015.api.SmartCityService;

/**
 * @author victor
 * @since 2016-02-13
 */
public class SmartCityApp extends Application {

  private static SmartCityApp INSTANCE = null;

  private SmartCityService apiService;

  public static SmartCityApp getApp() {
    return INSTANCE;
  }

  public SmartCityService getApiService() {
    return apiService;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    INSTANCE = this;

    initializeApi();
  }

  private void initializeApi() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://www.smartcity.hackathon.te.ua/api/v1/")
        .client(new OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();

    apiService = retrofit.create(SmartCityService.class);
  }
}
