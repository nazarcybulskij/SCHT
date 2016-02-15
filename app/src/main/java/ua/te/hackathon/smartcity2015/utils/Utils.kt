package ua.te.hackathon.smartcity2015.utils

import android.widget.EditText
import java.text.DateFormat
import java.text.ParseException
import java.util.*

/**
 * Created by nazarko on 2/14/16.
 */
object Utils {

  fun text(etName: EditText?): String? {
    if (etName != null) {
      return etName.text.toString()
    }
    return null
  }

  fun date(d: String): Long {
    var date: Date? = null
    try {
      date = DateFormat.getDateInstance().parse(d)
    } catch (e: ParseException) {
      throw RuntimeException(e)
    }

    return date!!.time
  }

  fun isEmpty(text: String?): Boolean {
    return text == null || "" == text
  }

}
