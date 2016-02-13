package ua.te.hackathon.smartcity2015.ui.main;

import android.content.Context;
import android.support.annotation.Nullable;

import ua.te.hackathon.smartcity2015.ui.base.mvp.Presenter;

/**
 * @author victor
 * @since 2016-02-13
 */
public class MainPresenter implements Presenter<MainView> {
  @Nullable
  private MainView view;

  private Context appContext;

  public MainPresenter(Context appContext) {
    this.appContext = appContext;
  }

  @Override
  public void attachView(MainView view) {
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
