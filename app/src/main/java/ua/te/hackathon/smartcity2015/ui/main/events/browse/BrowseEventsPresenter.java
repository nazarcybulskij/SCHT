package ua.te.hackathon.smartcity2015.ui.main.events.browse;

import android.content.Context;
import android.support.annotation.Nullable;

import ua.te.hackathon.smartcity2015.ui.Presenter;

/**
 * @author victor
 * @since 2016-02-13
 */
public class BrowseEventsPresenter implements Presenter<BrowseEventsView> {
  @Nullable
  private BrowseEventsView view;

  private Context appContext;

  public BrowseEventsPresenter(Context appContext) {
    this.appContext = appContext;
  }

  public void loadEvents() {
    if (view != null) {
      view.showLoadingView();
    }

    // loading

    // loading done
    if (view != null) {
      view.deliverEventList(null);
      view.hideLoadingView();
    }
  }

  @Override
  public void attachView(BrowseEventsView view) {
    this.view = view;
  }

  @Override
  public void detachView() {
    this.view = null;
  }

  @Override
  public void onDestroy() {
    detachView();
  }
}
