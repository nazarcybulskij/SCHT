package ua.te.hackathon.smartcity2015.dagger.components;

import dagger.Component;
import ua.te.hackathon.smartcity2015.dagger.modules.PresenterModule;
import ua.te.hackathon.smartcity2015.dagger.scopes.PerActivity;
import ua.te.hackathon.smartcity2015.sync.SyncManager;
import ua.te.hackathon.smartcity2015.ui.main.events.browse.BrowseEventsPresenter;

/**
 * @author victor
 * @since 2015-12-28
 */
@PerActivity
@Component(
    modules = {
        PresenterModule.class
    },
    dependencies = {
        ApplicationComponent.class
    }
)
public interface PresenterComponent {
  void inject(BrowseEventsPresenter presenter);
}
