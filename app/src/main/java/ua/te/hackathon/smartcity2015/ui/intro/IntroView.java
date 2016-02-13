package ua.te.hackathon.smartcity2015.ui.intro;

import java.util.List;

import ua.te.hackathon.smartcity2015.ui.PresenterView;

/**
 * Created by nazarko on 2/13/16.
 */
public interface IntroView  extends PresenterView {


  void showLoadingView();
  void hideLoadingView();
  void success();
  void  error();

}
