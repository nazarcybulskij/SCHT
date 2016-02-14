package ua.te.hackathon.smartcity2015.ui.main.edit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import org.joda.time.MutableDateTime;

import java.util.Calendar;
import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import ua.te.hackathon.smartcity2015.R;
import ua.te.hackathon.smartcity2015.api.model.DateTime;
import ua.te.hackathon.smartcity2015.db.model.Event;
import ua.te.hackathon.smartcity2015.ui.BaseActivity;

import static ua.te.hackathon.smartcity2015.utils.Utils.isEmpty;
import static ua.te.hackathon.smartcity2015.utils.Utils.text;

public class EventCreationActivity extends BaseActivity
        implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    @Bind(R.id.etInputDate)
    EditText etInputDate;

    @Bind(R.id.etInputTime)
    EditText etInputTime;

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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showSnackbar(view);
            }
        });

        etInputTime.setText(getCurrentTime());
    }

    private String getCurrentTime() {
        return DateTime.TIME_FORMATTER.print(new org.joda.time.DateTime());
    }

    @OnClick({R.id.etInputTime, R.id.etInputDate, R.id.input_date, R.id.input_time})
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

    private void showSnackbar(View view) {
        Snackbar.make(view, "Click to create", Snackbar.LENGTH_LONG)
                .setAction("Save", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: 14.02.16 add background image url
                        if(!isEmpty(text(etPlace)) && !isEmpty(text(etInputDate))) {
                            if(dateTime == null) {
                                dateTime= new MutableDateTime();
                            }
                            Event event = new Event();
                            event.setName(text(etName));
                            event.setDate(dateTime.getMillis());
                            event.setDescription(text(etDescription));
                            event.setPlace(text(etPlace));

                            Realm realm = Realm.getInstance(getApplicationContext());
                            realm.beginTransaction();
                            // increatement index
                            int nextID = (int) (realm.where(Event.class).max("id").intValue() + 1);
                            event.setId(nextID);
                            realm.copyToRealm(event);
                            realm.commitTransaction();

                            finish();
                        } else {
                            Toast.makeText(EventCreationActivity.this, "Place and date can't be empty", Toast.LENGTH_LONG);
                        }
                    }
                }).show();
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        if (dateTime == null) {
            dateTime = new org.joda.time.MutableDateTime();
        }
        dateTime.setDayOfMonth(dayOfMonth);
        dateTime.setMonthOfYear(monthOfYear);
        dateTime.setYear(year);
        etInputDate.setText(DateTime.DATE_FORMATTER.print(dateTime));
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
        if (dateTime == null) {
            dateTime = new org.joda.time.MutableDateTime();
        }
        dateTime.setHourOfDay(hourOfDay);
        dateTime.setMinuteOfDay(minute);
        dateTime.setSecondOfMinute(second);
        etInputTime.setText(DateTime.TIME_FORMATTER.print(dateTime));
    }
}
