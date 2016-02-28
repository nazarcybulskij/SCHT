package ua.te.hackathon.smartcity2015

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import common.Logger
import net.danlew.android.joda.JodaTimeAndroid
import ua.te.hackathon.smartcity2015.dagger.components.DaggerApplicationComponent
import ua.te.hackathon.smartcity2015.dagger.modules.ApiModule
import ua.te.hackathon.smartcity2015.dagger.modules.ApplicationModule
import ua.te.hackathon.smartcity2015.dagger.modules.NetworkModule

/**
 * @author victor
 * *
 * @since 2016-02-13
 */
class SmartCityApp : Application() {

  override fun onCreate() {
    super.onCreate()

    Logger.setLogLevel(Logger.LogLevel.ALL)
    JodaTimeAndroid.init(this)
    initializeDI()
  }

  private fun initializeDI() {
    Injector.context = this
    Injector.applicationComponent = DaggerApplicationComponent.builder()
        .applicationModule(ApplicationModule(this))
        .networkModule(NetworkModule())
        .apiModule(ApiModule())
        .build();
  }

  override fun attachBaseContext(base: Context) {
    super.attachBaseContext(base)
    MultiDex.install(this)
  }
}
