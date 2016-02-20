package ua.te.hackathon.smartcity2015.ui.main

import android.content.Context

import ua.te.hackathon.smartcity2015.ui.base.mvp.Presenter

/**
 * @author victor
 * *
 * @since 2016-02-13
 */
class MainPresenter(private val appContext: Context) : Presenter<MainView> {
  private var view: MainView? = null

  override fun attachView(view: MainView) {
    this.view = view
  }

  override fun detachView() {
    this.view = null
  }

  override fun onDestroy() {
    detachView()
  }
}
