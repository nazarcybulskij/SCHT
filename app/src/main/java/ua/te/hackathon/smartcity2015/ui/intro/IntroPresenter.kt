package ua.te.hackathon.smartcity2015.ui.intro

import android.content.Context
import android.content.Intent
import android.support.v4.app.FragmentActivity
import ua.te.hackathon.smartcity2015.google.GoogleApiHelper
import ua.te.hackathon.smartcity2015.ui.base.mvp.Presenter

/**
 * Created by nazarko on 2/13/16.
 */
class IntroPresenter(private val appContext: Context) : Presenter<IntroView> {

  private var view: IntroView? = null

  private var googleApiHelper: GoogleApiHelper? = null


  init {
    this.googleApiHelper = GoogleApiHelper(appContext)
  }

  fun login(activity: FragmentActivity) {
    googleApiHelper!!.startGoogleRegistration(activity)
  }

  fun handleOnActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
    googleApiHelper!!.onActivityResult(requestCode, resultCode, data)
  }

  override fun attachView(view: IntroView) {
    this.view = view
  }

  override fun detachView() {
    this.view = null
  }

  override fun onDestroy() {
    detachView()
    googleApiHelper = null
  }

}
