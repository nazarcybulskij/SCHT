package ua.te.hackathon.smartcity2015.dagger.modules;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import common.Logger;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import ua.te.hackathon.smartcity2015.dagger.scopes.PerApplication;

/**
 * @author victor
 * @since 2015-12-28
 */
@Module
public class NetworkModule {
  public static final String LOG_TAG = Logger.getLogTag(NetworkModule.class);

  public static final String NAME_CACHED = "cached";
  public static final String NAME_NON_CACHED = "non-cached";

  @Provides
  @Singleton
  @Named(NAME_CACHED)
  public OkHttpClient provideCachedOkHttpClient(Context context) {
    OkHttpClient.Builder builder = getOkHttpClientBuilder();
    try {
      Cache responseCache = new Cache(context.getCacheDir(), 4 * 1024 * 1024);
      builder.cache(responseCache);
    } catch (Exception e) {
      Logger.e(LOG_TAG, "Unable to set http cache", e);
    }
    return builder.build();
  }

  @Provides
  @Singleton
  @Named(NAME_NON_CACHED)
  public OkHttpClient provideNonCachedOkHttpClient() {
    return getOkHttpClientBuilder().build();
  }

  private OkHttpClient.Builder getOkHttpClientBuilder() {
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    builder.readTimeout(30, TimeUnit.SECONDS);
    builder.connectTimeout(15, TimeUnit.SECONDS);
    return builder;
  }

}
