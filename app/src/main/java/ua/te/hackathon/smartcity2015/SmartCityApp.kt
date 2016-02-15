package ua.te.hackathon.smartcity2015

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import net.danlew.android.joda.JodaTimeAndroid
import ua.te.hackathon.smartcity2015.api.SmartCityService
import ua.te.hackathon.smartcity2015.api.stub.SmartCityServiceStub
import ua.te.hackathon.smartcity2015.utils.Logger

/**
 * @author victor
 * *
 * @since 2016-02-13
 */
class SmartCityApp : Application() {

  var apiService: SmartCityService? = null
    private set

  override fun onCreate() {
    super.onCreate()
    app = this

    Logger.logLevel = Logger.LogLevel.ALL
    JodaTimeAndroid.init(this)
    initializeApi()
  }

  private fun initializeApi() {
    //    Retrofit retrofit = new Retrofit.Builder()
    //        .baseUrl("https://www.smartcity.hackathon.te.ua/api/v1/")
    //        .client(new OkHttpClient())
    //        .addConverterFactory(GsonConverterFactory.create())
    //        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
    //        .build();
    //
    //    apiService = retrofit.create(SmartCityService.class);

    //STUB
    apiService = SmartCityServiceStub(this)
  }

  override fun attachBaseContext(base: Context) {
    super.attachBaseContext(base)
    MultiDex.install(this)
  }

  companion object {
    var app: SmartCityApp? = null
      private set
  }
}
