package ua.te.hackathon.smartcity2015.ui.main.edit

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlacePicker
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import io.realm.Realm
import kotlinx.android.synthetic.main.content_event_creation.*
import org.greenrobot.eventbus.EventBus
import org.joda.time.MutableDateTime
import ua.te.hackathon.smartcity2015.R
import ua.te.hackathon.smartcity2015.api.model.DateTime
import ua.te.hackathon.smartcity2015.db.DBUtil
import ua.te.hackathon.smartcity2015.db.model.Event
import ua.te.hackathon.smartcity2015.sync.events.EventsSyncFinished
import ua.te.hackathon.smartcity2015.ui.BaseActivity
import ua.te.hackathon.smartcity2015.utils.TimeUtils
import ua.te.hackathon.smartcity2015.utils.Utils.isEmpty
import ua.te.hackathon.smartcity2015.utils.Utils.text
import java.util.*

class EventCreationActivity : BaseActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

  private var dateTime: org.joda.time.MutableDateTime? = null
  private var place: Place? = null

  override fun onCreateAuthenticated(savedInstanceState: Bundle?) {
    setContentView(R.layout.activity_event_creation)
    val toolbar = findViewById(R.id.toolbar) as Toolbar
    setSupportActionBar(toolbar)
    supportActionBar!!.setTitle(R.string.create_event)
    supportActionBar!!.setDefaultDisplayHomeAsUpEnabled(true)

    etInputTime.text = currentTime

    etInputTime.setOnClickListener({ v -> chooseDateTime(v) })
    etInputDate.setOnClickListener({ v -> chooseDateTime(v) })
    etEventPlace.setOnClickListener({ v -> selectPlace() })
    btnDone.setOnClickListener({ v -> saveEvent(v) })
  }

  private val currentTime: String
    get() = DateTime.TIME_FORMATTER.print(org.joda.time.DateTime())

  fun chooseDateTime(button: View) {
    val now = Calendar.getInstance()
    when (button.id) {
      R.id.etInputDate -> {
        val dpd = DatePickerDialog.newInstance(
            this,
            now.get(Calendar.YEAR),
            now.get(Calendar.MONTH),
            now.get(Calendar.DAY_OF_MONTH))

        val minDate = Calendar.getInstance()
        minDate.add(Calendar.HOUR, 1)
        val maxDate = Calendar.getInstance()
        maxDate.timeInMillis = minDate.timeInMillis
        maxDate.add(Calendar.DATE, 7)
        dpd.minDate = minDate
        dpd.maxDate = maxDate
        dpd.show(fragmentManager, "Datepickerdialog")
      }
      R.id.etInputTime -> {
        val tpd = TimePickerDialog.newInstance(
            this,
            now.get(Calendar.HOUR),
            now.get(Calendar.MINUTE),
            true)
        tpd.show(fragmentManager, "Timepickerdialog")
      }
    }
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> finish()
    }
    return super.onOptionsItemSelected(item)
  }

  fun selectPlace() {
    val builder = PlacePicker.IntentBuilder()
    try {
      startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST)
    } catch (e: GooglePlayServicesRepairableException) {
      e.printStackTrace()
    } catch (e: GooglePlayServicesNotAvailableException) {
      e.printStackTrace()
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
    super.onActivityResult(requestCode, resultCode, data)

    if (requestCode == PLACE_PICKER_REQUEST) {
      if (resultCode == Activity.RESULT_OK) {
        place = PlacePicker.getPlace(this, data)
        var address = place!!.address.toString()
        if (address.isEmpty()) {
          val loc = place!!.latLng
          address = String.format("%.2f %.2f", loc.latitude, loc.longitude)
        }
        etEventPlace.text = address
      }
    }
  }

  fun saveEvent(view: View) {
    if (place != null && !isEmpty(etInputTime?.text.toString())) {
      if (dateTime == null) {
        dateTime = MutableDateTime()
      }
      val event = Event()
      event.id = DBUtil.getNextId(Event::class.java)
      event.name = text(etName)
      event.date = dateTime!!.millis
      event.description = text(etDescription)
      event.place = place!!.latLng.toString()

      val realm = Realm.getInstance(applicationContext)
      realm.beginTransaction()
      realm.copyToRealm(event)
      realm.commitTransaction()

      EventBus.getDefault().post(EventsSyncFinished())

      finish()
    } else {
      Toast.makeText(this@EventCreationActivity, "Place and date cannot be empty", Toast.LENGTH_LONG).show()
    }
  }

  override fun onDateSet(view: DatePickerDialog, year: Int, monthOfYear: Int, dayOfMonth: Int) {
    if (dateTime == null) {
      dateTime = org.joda.time.MutableDateTime()
    }
    dateTime!!.dayOfMonth = dayOfMonth
    dateTime!!.monthOfYear = monthOfYear
    dateTime!!.year = year
    etInputDate.text = TimeUtils.getDayPresentation(this, dateTime!!.millis)
  }

  override fun onTimeSet(view: RadialPickerLayout, hourOfDay: Int, minute: Int, second: Int) {
    if (dateTime == null) {
      dateTime = org.joda.time.MutableDateTime()
      dateTime!!.millis = System.currentTimeMillis()
    }
    dateTime!!.hourOfDay = hourOfDay
    dateTime!!.minuteOfHour = minute
    dateTime!!.secondOfMinute = second
    etInputTime.text = String.format("at %s", DateTime.TIME_FORMATTER.print(dateTime))
  }

  companion object {

    private val PLACE_PICKER_REQUEST = 1

    fun startActivity(applicationContext: Context): Intent {
      val intent = Intent(applicationContext, EventCreationActivity::class.java)
      intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
      return intent
    }
  }
}
