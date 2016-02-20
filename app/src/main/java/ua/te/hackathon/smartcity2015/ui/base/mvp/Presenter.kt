package ua.te.hackathon.smartcity2015.ui.base.mvp

/**
 * @author victor
 * *
 * @since 2016-02-13
 */
interface Presenter<T : PresenterView> {

  fun attachView(view: T)

  fun detachView()

  fun onDestroy()

}