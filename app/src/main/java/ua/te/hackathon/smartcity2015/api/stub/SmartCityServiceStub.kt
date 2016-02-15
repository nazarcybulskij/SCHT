package ua.te.hackathon.smartcity2015.api.stub

import android.content.Context
import com.google.gson.Gson
import rx.Observable
import ua.te.hackathon.smartcity2015.api.SmartCityService
import ua.te.hackathon.smartcity2015.api.model.Event
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*

/**
 * @author victor
 * *
 * @since 2016-02-14
 */
class SmartCityServiceStub(private val appContext: Context) : SmartCityService {

  override fun upcomingEvents(): Observable<List<Event>> {
    return Observable.create { subscriber ->
      val gson = Gson()
      try {
        val text = getAssetFileContent(appContext, "stubs/upcoming.json")

        val data = gson.fromJson(text, Array<Event>::class.java)
        if (!subscriber.isUnsubscribed) {
          subscriber.onNext(Arrays.asList(*data))
          subscriber.onCompleted()
        }
      } catch (e: IOException) {
        if (!subscriber.isUnsubscribed) {
          subscriber.onError(e)
          subscriber.onCompleted()
        }
      }
    }
  }

  @Throws(IOException::class)
  private fun getAssetFileContent(context: Context, file: String): String {
    val buf = StringBuilder()
    val json = context.assets.open(file)
    val inp = BufferedReader(InputStreamReader(json, "UTF-8"))

    var str: String? = inp.readLine()
    while (str != null) {
      buf.append(str)
      str = inp.readLine()
    }

    inp.close()
    return buf.toString()
  }
}
