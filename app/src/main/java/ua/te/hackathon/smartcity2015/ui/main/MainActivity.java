package ua.te.hackathon.smartcity2015.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ua.te.hackathon.smartcity2015.R;
import ua.te.hackathon.smartcity2015.ui.BaseActivity;
import ua.te.hackathon.smartcity2015.ui.intro.IntroActivity;
import ua.te.hackathon.smartcity2015.ui.main.edit.EventCreationActivity;
import ua.te.hackathon.smartcity2015.ui.main.events.browse.BrowseEventsFragment;
import ua.te.hackathon.smartcity2015.utils.LoggedUser;

public class MainActivity extends BaseActivity implements MainView {

    private static MainPresenter presenter;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void onCreateAuthenticated(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(R.string.upcoming_games);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, BrowseEventsFragment.newInstance())
                .commit();
    }

    @OnClick(R.id.fab)
    public void onCreateNewEvent() {
        startActivity(EventCreationActivity.startActivity(getApplication()));
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        if (presenter == null) {
            presenter = new MainPresenter(getApplicationContext());
        }

        presenter.attachView(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionLogout:
                LoggedUser.logOut();
                Intent intent = IntroActivity.startRegistration(this);
                startActivity(intent);
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);

        EventBus.getDefault().unregister(this);

        if (presenter != null) {
            presenter.detachView();

            if (isFinishing()) {
                presenter.onDestroy();
                presenter = null;
            }
        }
    }
}
