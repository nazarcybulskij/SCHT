package ua.te.hackathon.smartcity2015.dagger.modules;

import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import ua.te.hackathon.smartcity2015.api.SmartCityService;
import ua.te.hackathon.smartcity2015.api.stub.SmartCityServiceStub;
import ua.te.hackathon.smartcity2015.dagger.scopes.PerApplication;

/**
 * @author victor
 * @since 2015-12-28
 */
@Module
public class ApiModule {

  @Provides
  @Singleton
  Gson provideGson() {
    return new Gson();
  }

  @Provides
  @Singleton
  public SmartCityService provideISSService(Context context) {
    return new SmartCityServiceStub(context);
  }

//  @Provides
//  @Singleton
//  public SmartCityService provideISSService(
//      @Named(NetworkModule.NAME_CACHED) OkHttpClient okHttpClient,
//      Gson gson) {
//    Retrofit retrofit = new Retrofit.Builder()
//        .baseUrl("baseUrl")
//        .client(okHttpClient)
//        .addConverterFactory(GsonConverterFactory.create(gson))
//        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//        .build();
//
//    return retrofit.create(SmartCityService.class);
//  }

}
