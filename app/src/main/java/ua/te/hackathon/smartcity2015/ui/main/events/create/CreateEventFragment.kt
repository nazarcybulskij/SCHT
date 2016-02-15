package ua.te.hackathon.smartcity2015.ui.main.events.create


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_create_event.*
import ua.te.hackathon.smartcity2015.R

class CreateEventFragment : Fragment(), CreateEventView {

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
    val rootView = inflater!!.inflate(R.layout.fragment_create_event, container, false)

    val categoryAdapter = ArrayAdapter(
        context,
        R.layout.spinner_item,
        arrayOf("грати", "дивитись", "обговорити"))
    categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    categorySpinner.adapter = categoryAdapter

    val dayAdapter = ArrayAdapter(
        context,
        R.layout.spinner_item,
        arrayOf("сьогодні", "завтра", "інший"))
    dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    daySpinner.adapter = dayAdapter

    val timeAdapter = ArrayAdapter(
        context,
        R.layout.spinner_item,
        arrayOf("зранку (9:00)", "в обід (13:00)", "вечером (20:00)"))
    timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    timeSpinner.adapter = timeAdapter

    val locationAdapter = ArrayAdapter(
        context,
        R.layout.spinner_item,
        arrayOf("на міському стадіоні", "біля Нової", "на Болоті", "на Цизі", "десь інше"))
    timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    locationSpinner.adapter = locationAdapter

    return rootView
  }
}
