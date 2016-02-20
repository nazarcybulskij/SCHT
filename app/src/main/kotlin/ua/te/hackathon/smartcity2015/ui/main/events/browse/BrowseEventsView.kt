package ua.te.hackathon.smartcity2015.ui.main.events.browse

import ua.te.hackathon.smartcity2015.db.model.Event
import ua.te.hackathon.smartcity2015.ui.base.mvp.PresenterView

/**
 * @author victor
 * *
 * @since 2016-02-13
 */
interface BrowseEventsView : PresenterView {

  fun showLoadingView()

  fun hideLoadingView()

  fun deliverEventList(list: List<Event>)

  fun deliverLoadingError(error: String)

}
