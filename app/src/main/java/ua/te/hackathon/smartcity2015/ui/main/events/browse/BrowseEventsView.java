package ua.te.hackathon.smartcity2015.ui.main.events.browse;

import android.support.annotation.NonNull;

import java.util.List;

import ua.te.hackathon.smartcity2015.db.model.Event;
import ua.te.hackathon.smartcity2015.ui.base.mvp.PresenterView;

/**
 * @author victor
 * @since 2016-02-13
 */
public interface BrowseEventsView extends PresenterView {

  void showLoadingView();

  void hideLoadingView();

  void deliverEventList(@NonNull List<Event> list);

  void deliverLoadingError(String error);

}
