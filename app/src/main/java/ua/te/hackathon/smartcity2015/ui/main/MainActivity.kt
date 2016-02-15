package ua.te.hackathon.smartcity2015.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import ua.te.hackathon.smartcity2015.R
import ua.te.hackathon.smartcity2015.ui.BaseActivity
import ua.te.hackathon.smartcity2015.ui.intro.IntroActivity
import ua.te.hackathon.smartcity2015.ui.main.edit.EventCreationActivity
import ua.te.hackathon.smartcity2015.ui.main.events.browse.BrowseEventsFragment
import ua.te.hackathon.smartcity2015.utils.LoggedUser

class MainActivity : BaseActivity(), MainView {

  public override fun onCreateAuthenticated(savedInstanceState: Bundle?) {
    setContentView(R.layout.activity_main)

    setSupportActionBar(toolbar)

    fab.setOnClickListener({ v -> onCreateNewEvent() })

    supportActionBar!!.setTitle(R.string.upcoming_games)

    supportFragmentManager.beginTransaction().replace(R.id.container, BrowseEventsFragment.newInstance()).commit()
  }

  fun onCreateNewEvent() {
    startActivity(EventCreationActivity.startActivity(application))
  }

  override fun onPostCreate(savedInstanceState: Bundle?) {
    super.onPostCreate(savedInstanceState)

    if (presenter == null) {
      presenter = MainPresenter(applicationContext)
    }

    presenter!!.attachView(this)
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.menu_profile, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.actionLogout -> {
        LoggedUser.logOut()
        val intent = IntroActivity.startRegistration(this)
        startActivity(intent)
        finish()
      }
      else -> return super.onOptionsItemSelected(item)
    }
    return true
  }

  override fun onDestroy() {
    super.onDestroy()

    EventBus.getDefault().unregister(this)

    if (presenter != null) {
      presenter!!.detachView()

      if (isFinishing) {
        presenter!!.onDestroy()
        presenter = null
      }
    }
  }

  companion object {
    private var presenter: MainPresenter? = null
  }
}
