package ua.te.hackathon.smartcity2015.ui.main.edit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import org.greenrobot.eventbus.EventBus;
import org.joda.time.MutableDateTime;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import ua.te.hackathon.smartcity2015.R;
import ua.te.hackathon.smartcity2015.api.model.DateTime;
import ua.te.hackathon.smartcity2015.db.DBUtil;
import ua.te.hackathon.smartcity2015.db.model.Event;
import ua.te.hackathon.smartcity2015.sync.events.EventsSyncFinished;
import ua.te.hackathon.smartcity2015.ui.BaseActivity;
import ua.te.hackathon.smartcity2015.utils.TimeUtils;

import static ua.te.hackathon.smartcity2015.utils.Utils.isEmpty;
import static ua.te.hackathon.smartcity2015.utils.Utils.text;

public class EventCreationActivity extends BaseActivity
    implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

  @Bind(R.id.etInputDate)
  Button etInputDate;

  @Bind(R.id.etInputTime)
  Button etInputTime;

  @Bind(R.id.etPlace)
  EditText etPlace;

  @Bind(R.id.etName)
  EditText etName;

  @Bind(R.id.etDescription)
  EditText etDescription;

  private org.joda.time.MutableDateTime dateTime;

  public static Intent startActivity(Context applicationContext) {
    Intent intent = new Intent(applicationContext, EventCreationActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    return intent;
  }

  @Override
  protected void onCreateAuthenticated(Bundle savedInstanceState) {
    setContentView(R.layout.activity_event_creation);
    ButterKnife.bind(this);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle(R.string.create_event);
    getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

    etInputTime.setText(getCurrentTime());
  }

  private String getCurrentTime() {
    return DateTime.TIME_FORMATTER.print(new org.joda.time.DateTime());
  }

  @OnClick({R.id.etInputTime, R.id.etInputDate})
  public void chooseDateTime(View button) {
    Calendar now = Calendar.getInstance();
    switch (button.getId()) {
      case R.id.etInputDate:
        DatePickerDialog dpd = DatePickerDialog.newInstance(
            this,
            now.get(Calendar.YEAR),
            now.get(Calendar.MONTH),
            now.get(Calendar.DAY_OF_MONTH)
        );

        Calendar minDate = Calendar.getInstance();
        minDate.add(Calendar.HOUR, 1);
        Calendar maxDate = Calendar.getInstance();
        maxDate.setTimeInMillis(minDate.getTimeInMillis());
        maxDate.add(Calendar.DATE, 7);
        dpd.setMinDate(minDate);
        dpd.setMaxDate(maxDate);
        dpd.show(getFragmentManager(), "Datepickerdialog");
        break;
      case R.id.etInputTime:
        TimePickerDialog tpd = TimePickerDialog.newInstance(
            this,
            now.get(Calendar.HOUR),
            now.get(Calendar.MINUTE),
            true
        );
        tpd.show(getFragmentManager(), "Timepickerdialog");
        break;
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  @OnClick(R.id.btnDone)
  public void saveEvent(View view) {
    if (!isEmpty(text(etPlace)) && !isEmpty(etInputTime.getText().toString())) {
      if (dateTime == null) {
        dateTime = new MutableDateTime();
      }
      Event event = new Event();
      event.setId(DBUtil.getNextId(Event.class));

      event.setName(text(etName));
      event.setDate(dateTime.getMillis());
      event.setDescription(text(etDescription));
      event.setPlace(text(etPlace));

      Realm realm = Realm.getInstance(getApplicationContext());
      realm.beginTransaction();
      realm.copyToRealm(event);
      realm.commitTransaction();

      EventBus.getDefault().post(new EventsSyncFinished());

      finish();
    } else {
      Toast.makeText(EventCreationActivity.this, "Place and date cannot be empty", Toast.LENGTH_LONG).show();
    }
  }

  @Override
  public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
    if (dateTime == null) {
      dateTime = new org.joda.time.MutableDateTime();
    }
    dateTime.setDayOfMonth(dayOfMonth);
    dateTime.setMonthOfYear(monthOfYear);
    dateTime.setYear(year);
    etInputDate.setText(TimeUtils.getDayPresentation(this, dateTime.getMillis()));
  }

  @Override
  public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
    if (dateTime == null) {
      dateTime = new org.joda.time.MutableDateTime();
      dateTime.setMillis(System.currentTimeMillis());
    }
    dateTime.setHourOfDay(hourOfDay);
    dateTime.setMinuteOfHour(minute);
    dateTime.setSecondOfMinute(second);
    etInputTime.setText(String.format("at %1$s", DateTime.TIME_FORMATTER.print(dateTime)));
  }
}
