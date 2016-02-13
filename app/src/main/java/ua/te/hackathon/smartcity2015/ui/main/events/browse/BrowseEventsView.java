package ua.te.hackathon.smartcity2015.ui.main.events.browse;

import java.util.List;

import ua.te.hackathon.smartcity2015.ui.PresenterView;

/**
 * @author victor
 * @since 2016-02-13
 */
public interface BrowseEventsView extends PresenterView {

  void showLoadingView();

  void hideLoadingView();

  void deliverEventList(List list);

  void deliverLoadingError(String error);

}
