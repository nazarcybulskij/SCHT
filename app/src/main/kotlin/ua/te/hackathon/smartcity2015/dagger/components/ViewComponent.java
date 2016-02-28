package ua.te.hackathon.smartcity2015.dagger.components;

import dagger.Component;
import ua.te.hackathon.smartcity2015.dagger.scopes.PerActivity;
import ua.te.hackathon.smartcity2015.ui.main.MainActivity;
import ua.te.hackathon.smartcity2015.ui.main.edit.EventCreationActivity;

/**
 * @author victor
 * @since 2015-12-28
 */
@PerActivity
@Component(
    dependencies = {
        ApplicationComponent.class
    }
)
public interface ViewComponent {
  void inject(MainActivity activity);

  void inject(EventCreationActivity activity);
}
