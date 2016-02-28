package ua.te.hackathon.smartcity2015.dagger.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ua.te.hackathon.smartcity2015.sync.SyncManager;

@Module
public class ApplicationModule {
  private Application app;

  public ApplicationModule(Application app) {
    this.app = app;
  }

  @Provides
  @Singleton
  public Context provideAppContext() {
    return app;
  }

  @Provides
  @Singleton
  SyncManager provideSyncManager() {
    return new SyncManager();
  }

}