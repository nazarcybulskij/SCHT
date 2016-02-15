package ua.te.hackathon.smartcity2015.ui.intro

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_intro.*
import ua.te.hackathon.smartcity2015.R


class IntroFragment : Fragment() {

  private var pos: Int = 0

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    pos = arguments.getInt(EXTRA_POSITION)
  }

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    val view = inflater!!.inflate(R.layout.fragment_intro, container, false)

    return view
  }

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val icons = resources.obtainTypedArray(R.array.intro_icons)
    val slogans = resources.getStringArray(R.array.intro_slogans)

    imageIntro.setImageDrawable(icons.getDrawable(pos))
    textIntro.text = slogans[pos]

    icons.recycle()
  }

  companion object {

    private val EXTRA_POSITION = "extra:pos"

    fun newInstance(pos: Int): IntroFragment {
      val fragment = IntroFragment()
      val args = Bundle()
      args.putInt(EXTRA_POSITION, pos)
      fragment.arguments = args
      return fragment
    }
  }
}
