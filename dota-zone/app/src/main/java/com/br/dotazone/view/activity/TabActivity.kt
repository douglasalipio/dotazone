package com.br.dotazone.view.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.br.dotazone.DotaZoneApplication
import com.br.dotazone.DotaZoneBrain.isPremium
import com.br.dotazone.R
import com.br.dotazone.model.entity.AdMobBanner
import com.br.dotazone.view.adapter.HeroAdapter
import com.br.dotazone.view.adapter.ItemAdapter
import com.com.dotazone.DotazoneMenu
import com.google.android.gms.ads.AdView
import com.google.android.gms.analytics.GoogleAnalytics
import com.google.android.material.tabs.TabLayout


class TabActivity : BaseActivity(), OnPageChangeListener, View.OnClickListener {
	var mMenu: DotazoneMenu? = null
	private var adView: AdView? = null
	private var mDrawerLayout: DrawerLayout? = null
	private var mDrawerList: RelativeLayout? = null
	private var mImageLogo: ImageView? = null
	private var mImageMenu: ImageView? = null
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.tab_view)
		(application as DotaZoneApplication).getTracker(DotaZoneApplication.TrackerName.APP_TRACKER)
		initComponents()
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

	override fun onPageScrollStateChanged(state: Int) {}
	override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
	override fun onPageSelected(position: Int) {}
	public override fun onPause() {
		adView!!.pause()
		super.onPause()
	}

	public override fun onResume() {
		super.onResume()
		adView!!.resume()
		val id = intent.extras!!.getInt(MainActivity::class.java.name)
		if (intent != null && id == DotazoneMenu.MENU_HERO) {
			mMenu!!.checkHeroMenu()
		} else if (id == DotazoneMenu.MENU_ITEM) {
			mMenu!!.checkItemMenu()
		} else {
			mMenu!!.checkBuildMenu()
		}
	}

	public override fun onDestroy() {
		adView!!.destroy()
		super.onDestroy()
	}

	private fun setAdapterPage(pager: ViewPager) {
		val itemAdapter = ItemAdapter(supportFragmentManager, applicationContext)
		val heroAdapter = HeroAdapter(supportFragmentManager, this)
		val id = intent.extras!!.getInt(MainActivity::class.java.name)
		if (intent != null && id == DotazoneMenu.MENU_HERO) {
			pager.adapter = heroAdapter
			mMenu!!.checkHeroMenu()
		} else if (id == DotazoneMenu.MENU_ITEM) {
			pager.adapter = itemAdapter
			mMenu!!.checkItemMenu()
		} else {
			pager.adapter = heroAdapter
			mMenu!!.checkBuildMenu()
		}
	}

	override fun initComponents() {
		mDrawerLayout = findViewById<View>(R.id.drawer_layout) as DrawerLayout
		mDrawerList = findViewById<View>(R.id.listSliderMenu) as RelativeLayout
		mImageLogo = findViewById<View>(R.id.header_imageLogo) as ImageView
		mImageMenu = findViewById<View>(R.id.header_imageMenu) as ImageView
		mImageLogo!!.setOnClickListener(this)
		mImageMenu!!.setOnClickListener(this)
		mMenu = DotazoneMenu(this, mDrawerLayout, mDrawerList)
		val pager = findViewById<View>(R.id.tab_view_pager) as ViewPager
		adView = findViewById<View>(R.id.tab_view_admob) as AdView
		setAdapterPage(pager)
		val indicator = findViewById<View>(R.id.indicator) as TabLayout
		indicator.setupWithViewPager(pager)
		//indicator.setOnPageChangeListener(this);
		AdMobBanner().createBanner(this, adView!!, isPremium)
	}

	override fun setActionErrorOk() {
		finish()
	}

	override fun setActionErrorCancel() {
		finish()
	}

	override fun onClick(v: View) {
		mDrawerLayout!!.openDrawer(mDrawerList!!)
	}

	override fun finish() {
		super.finish()
		mMenu!!.defaultMenu()
	}

	companion object {
		const val TAB_ITEM = 1
		const val TAB_HERO = 0
		var isBuild = false
	}
}
