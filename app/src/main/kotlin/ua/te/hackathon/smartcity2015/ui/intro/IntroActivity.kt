package ua.te.hackathon.smartcity2015.ui.intro

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_intro.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import ua.te.hackathon.smartcity2015.R
import ua.te.hackathon.smartcity2015.google.GoogleApiHelper
import ua.te.hackathon.smartcity2015.ui.main.MainActivity
import ua.te.hackathon.smartcity2015.utils.LoggedUser
import java.util.*

class IntroActivity : FragmentActivity(), IntroView {

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
    ua.te.hackathon.IntroActivity.Companion.presenter!!.handleOnActivityResult(requestCode, resultCode, data)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    requestWindowFeature(Window.FEATURE_NO_TITLE)
    window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_intro)
    initViewPager()

    btnLogin.setOnClickListener({v -> onLoginClick(v)})

    EventBus.getDefault().register(this)
  }

  private fun initViewPager() {
    val adapter = ViewPagerAdapter(supportFragmentManager)

    val slogans = resources.getStringArray(R.array.intro_slogans)

    for (i in slogans.indices) {
      adapter.addFragment(IntroFragment.newInstance(i))
    }

    viewPager.adapter = adapter
    viewPagerIndicator.setViewPager(viewPager)
  }

  override fun onPostCreate(savedInstanceState: Bundle?) {
    super.onPostCreate(savedInstanceState)

    if (ua.te.hackathon.IntroActivity.Companion.presenter == null) {
      ua.te.hackathon.IntroActivity.Companion.presenter = IntroPresenter(applicationContext)
    }

    ua.te.hackathon.IntroActivity.Companion.presenter!!.attachView(this)
  }

  fun onLoginClick(v: View) {
    ua.te.hackathon.IntroActivity.Companion.presenter!!.login(this)
  }

  private fun startMainActivity() {
    val intent = Intent(this, MainActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    startActivity(intent)
  }

  override fun showLoadingView() {
    Toast.makeText(applicationContext, "show", Toast.LENGTH_SHORT).show()
  }

  override fun hideLoadingView() {
    Toast.makeText(applicationContext, "hide", Toast.LENGTH_SHORT).show()
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  fun handleGoogleLoginResult(result: GoogleApiHelper.GoogleAuthResult) {
    if (result.isSuccess) {
      val profile = GoogleApiHelper.GoogleProfile(result.signInAccount!!)

      LoggedUser.firstName = profile.firstName
      LoggedUser.lastName = profile.lastName
      LoggedUser.photoUrl = profile.imageUrl
      LoggedUser.email = profile.email

      startMainActivity()
      finish()
    } else {
      GoogleApiHelper.logOut()
    }
  }

  override fun onDestroy() {
    super.onDestroy()

    if (ua.te.hackathon.IntroActivity.Companion.presenter != null) {
      ua.te.hackathon.IntroActivity.Companion.presenter!!.detachView()

      if (isFinishing) {
        ua.te.hackathon.IntroActivity.Companion.presenter!!.onDestroy()
        ua.te.hackathon.IntroActivity.Companion.presenter = null
      }
    }
  }

  internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
    private val fragmentList = ArrayList<Fragment>()

    override fun getItem(position: Int): Fragment {
      return fragmentList[position]
    }

    override fun getCount(): Int {
      return fragmentList.size
    }

    fun addFragment(fragment: Fragment) {
      fragmentList.add(fragment)
    }
  }

  companion object {

    fun startRegistration(context: Context): Intent {
      val intent = Intent(context, IntroActivity::class.java)
      return intent
    }

    private var presenter: IntroPresenter? = null
  }

}
