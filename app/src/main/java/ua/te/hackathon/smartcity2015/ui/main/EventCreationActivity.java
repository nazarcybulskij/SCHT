package ua.te.hackathon.smartcity2015.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import ua.te.hackathon.smartcity2015.R;
import ua.te.hackathon.smartcity2015.model.Event;
import ua.te.hackathon.smartcity2015.utils.Utils;

import static ua.te.hackathon.smartcity2015.utils.Utils.date;
import static ua.te.hackathon.smartcity2015.utils.Utils.text;

public class EventCreationActivity extends AppCompatActivity {

    @Bind(R.id.etInputDate)
    EditText etInputDate;

    @Bind(R.id.etPlace)
    EditText etPlace;

    @Bind(R.id.etName)
    EditText etName;

    @Bind(R.id.etDescription)
    EditText etDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_event_creation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Click to create", Snackbar.LENGTH_LONG)
                        .setAction("Save", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // TODO: 14.02.16 add background image url
                                Event event = new Event();
                                event.setName(text(etName));
                                event.setDate(date(text(etInputDate)));
                                event.setDescription(text(etDescription));
                                event.setPlace(text(etPlace));

                                Realm realm = Realm.getInstance(getApplicationContext());
                                realm.beginTransaction();
                                realm.copyToRealm(event);
                                realm.commitTransaction();

                                finish();
                            }
                        }).show();
            }
        });
    }

    public static Intent startActivity(Context applicationContext) {
        Intent intent = new Intent(applicationContext, EventCreationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }
}
