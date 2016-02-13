package ua.te.hackathon.smartcity2015.ui.base.mvp;

/**
 * @author victor
 * @since 2016-02-13
 */
public interface Presenter<T extends PresenterView> {

  void attachView(T view);

  void detachView();

  void onDestroy();

}
