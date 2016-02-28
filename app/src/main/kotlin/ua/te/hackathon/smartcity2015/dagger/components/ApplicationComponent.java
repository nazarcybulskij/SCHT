package ua.te.hackathon.smartcity2015.dagger.components;

import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import ua.te.hackathon.smartcity2015.api.SmartCityService;
import ua.te.hackathon.smartcity2015.dagger.modules.ApiModule;
import ua.te.hackathon.smartcity2015.dagger.modules.ApplicationModule;
import ua.te.hackathon.smartcity2015.dagger.modules.NetworkModule;
import ua.te.hackathon.smartcity2015.sync.SyncManager;

@Singleton
@Component(
    modules = {
        ApplicationModule.class,
        NetworkModule.class,
        ApiModule.class
    }
)
public interface ApplicationComponent {
  Context getContext();

  @Named(NetworkModule.NAME_CACHED)
  OkHttpClient getCachedOkHttpClient();

  @Named(NetworkModule.NAME_NON_CACHED)
  OkHttpClient getOkHttpClient();

  Gson getGson();

  SmartCityService getSmartCityService();

  SyncManager getSyncManager();

  void inject(SyncManager manager);
}