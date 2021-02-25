package br.com.dotazone.view.activity

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.drawerlayout.widget.DrawerLayout
import br.com.dotazone.DotaZoneApplication
import br.com.dotazone.DotazoneMenu
import br.com.dotazone.R
import com.google.android.gms.analytics.GoogleAnalytics


class AboutActivity : BaseActivity() {
	private var mDrawerLayout: DrawerLayout? = null
	private var mDrawerList: RelativeLayout? = null
	private var mMenu: DotazoneMenu? = null
	private var mButtonMenu: LinearLayout? = null
	override fun onCreate(arg0: Bundle?) {
		super.onCreate(arg0)
		setContentView(R.layout.about_view)
		mMenu = DotazoneMenu(this, mDrawerLayout, mDrawerList)
		mMenu!!.checkAboutMenu()
		initComponents()
		(application as DotaZoneApplication).getTracker(DotaZoneApplication.TrackerName.APP_TRACKER)
	}

	override fun onStart() {
		super.onStart()
		GoogleAnalytics.getInstance(this).reportActivityStart(this)
	}

	override fun onStop() {
		super.onStop()

		// Stop the analytics tracking
		GoogleAnalytics.getInstance(this).reportActivityStop(this)
	}

	override fun onResume() {
		super.onResume()
		mMenu!!.checkAboutMenu()
	}

	override fun initComponents() {
		mDrawerLayout = findViewById<View>(R.id.drawer_layout) as DrawerLayout
		mDrawerList = findViewById<View>(R.id.listSliderMenu) as RelativeLayout
		mButtonMenu = findViewById<View>(R.id.about_menu) as LinearLayout
		mButtonMenu!!.setOnClickListener { mDrawerLayout!!.openDrawer(mDrawerList!!) }
	}

	override fun setActionErrorOk() {
		TODO("Not yet implemented")
	}

	override fun setActionErrorCancel() {
		TODO("Not yet implemented")
	}


}
