package ua.te.hackathon.smartcity2015

import android.content.Context
import ua.te.hackathon.smartcity2015.dagger.components.ApplicationComponent

/**
 * @author victor
 * @since 2016-02-21
 */
object Injector {
  @JvmStatic
  lateinit var applicationComponent: ApplicationComponent

  @JvmStatic
  lateinit var context: Context
}