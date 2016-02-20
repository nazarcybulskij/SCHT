package ua.te.hackathon.smartcity2015.ui

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ua.te.hackathon.smartcity2015.ui.intro.IntroActivity
import ua.te.hackathon.smartcity2015.utils.LoggedUser
import java.util.*

abstract class BaseActivity : AppCompatActivity() {

  public override fun onCreate(savedInstanceState: Bundle?) {
    onPreCreate(savedInstanceState)
    super.onCreate(savedInstanceState)

    if (isUserAuthenticated) {
      onCreateAuthenticated(savedInstanceState)
    } else {
      onCreateUnauthenticated(savedInstanceState)
    }
  }

  protected abstract fun onCreateAuthenticated(savedInstanceState: Bundle?)

  protected fun onPreCreate(savedInstanceState: Bundle?) {
    if (shouldLockOrientation()) {
      requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    setUkrainianLocale()
  }

  protected fun shouldLockOrientation(): Boolean {
    return true
  }

  private val isUserAuthenticated: Boolean
    get() = LoggedUser.isLoggedIn

  private fun setUkrainianLocale() {
    val locale = Locale("uk")
    Locale.setDefault(locale)
    val config = Configuration()
    config.locale = locale
    baseContext.resources.updateConfiguration(config,
        baseContext.resources.displayMetrics)
  }

  fun onCreateUnauthenticated(savedInstanceState: Bundle?) {
    val intent = IntroActivity.startRegistration(this)
    startActivity(intent)
    finish()
  }
}
