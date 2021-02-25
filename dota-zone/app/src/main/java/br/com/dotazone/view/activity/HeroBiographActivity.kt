package br.com.dotazone.view.activity

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import br.com.dotazone.DotaZoneApplication
import br.com.dotazone.DotaZoneBrain.hero
import br.com.dotazone.DotaZoneBrain.isPremium
import br.com.dotazone.DotazoneMenu
import br.com.dotazone.R
import br.com.dotazone.model.entity.AdMobBanner
import com.google.android.gms.ads.AdView
import com.google.android.gms.analytics.GoogleAnalytics
import java.io.IOException


class HeroBiographActivity : BaseActivity() {
	private var mHeroName: TextView? = null
	private var mHeroBio: TextView? = null
	private var media: MediaPlayer? = null
	private var adView: AdView? = null
	private var mDrawerLayout: DrawerLayout? = null
	private var mDrawerList: RelativeLayout? = null
	private var mMenu: DotazoneMenu? = null
	override fun onCreate(arg0: Bundle?) {
		super.onCreate(arg0)
		setContentView(R.layout.hero_bio_view)
		initComponents()
		(application as DotaZoneApplication).getTracker(DotaZoneApplication.TrackerName.APP_TRACKER)
	}

	public override fun onPause() {
		adView!!.pause()
		media!!.pause()
		super.onPause()
	}

	public override fun onDestroy() {
		adView!!.destroy()
		super.onDestroy()
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
		adView!!.resume()
		try {
			val afd = assets.openFd("bio_theme.mp3")
			media = MediaPlayer()
			media!!.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
			media!!.prepare()
			media!!.isLooping = true
			media!!.setOnPreparedListener { media!!.start() }
		} catch (e: IOException) {
			// TODO Auto-generated catch block
			e.printStackTrace()
		}
	}

	override fun initComponents() {
		mHeroName = findViewById<View>(R.id.hero_text_name_bio) as TextView
		mHeroBio = findViewById<View>(R.id.hero_text_bio) as TextView
		val nameHero = hero!!.name?.replace("_", " ")
		mHeroName!!.text = nameHero
		mHeroBio!!.text = hero!!.bio
		adView = findViewById<View>(R.id.hero_bio_admob) as AdView
		AdMobBanner().createBanner(this, adView!!, isPremium)
		mDrawerLayout = findViewById<View>(R.id.drawer_layout) as DrawerLayout
		mDrawerList = findViewById<View>(R.id.listSliderMenu) as RelativeLayout
		mMenu = DotazoneMenu(this, mDrawerLayout, mDrawerList)
		mMenu!!.checkHeroMenu()
	}

	override fun setActionErrorOk() {
		// TODO Auto-generated method stub
	}

	override fun setActionErrorCancel() {
		// TODO Auto-generated method stub
	}
}
