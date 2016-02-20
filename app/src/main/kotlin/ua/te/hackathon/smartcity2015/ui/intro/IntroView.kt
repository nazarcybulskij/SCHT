package ua.te.hackathon.smartcity2015.ui.intro

import ua.te.hackathon.smartcity2015.ui.base.mvp.PresenterView

/**
 * Created by nazarko on 2/13/16.
 */
interface IntroView : PresenterView {

  fun showLoadingView()

  fun hideLoadingView()

}
