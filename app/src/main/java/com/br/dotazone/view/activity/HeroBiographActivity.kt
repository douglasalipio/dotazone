package com.br.dotazone.view.activity

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import com.br.dotazone.DotaZoneBrain.hero
import com.br.dotazone.DotaZoneBrain.isPremium
import com.br.dotazone.R
import com.br.dotazone.databinding.HeroBioViewBinding
import com.br.dotazone.databinding.HeroProfileViewBinding
import com.br.dotazone.model.entity.AdMobBanner
import com.com.dotazone.DotazoneMenu
import com.google.android.gms.ads.AdView
import com.google.android.gms.analytics.GoogleAnalytics
import java.io.IOException


class HeroBiographActivity : BaseActivity() {

	private val media: MediaPlayer = MediaPlayer()
	private var mMenu: DotazoneMenu? = null
	private lateinit var binding: HeroBioViewBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = HeroBioViewBinding.inflate(layoutInflater)
		setContentView(binding.root)
		initComponents()
	}

	public override fun onPause() {
		binding.heroBioAdmob.pause()
		media.pause()
		super.onPause()
	}

	public override fun onDestroy() {
		binding.heroBioAdmob.destroy()
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
		binding.heroBioAdmob.resume()
		try {
			val afd = assets.openFd("bio_theme.mp3")
			media.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
			media.prepare()
			media.isLooping = true
			media.setOnPreparedListener { media.start() }
		} catch (e: IOException) {
			// TODO Auto-generated catch block
			e.printStackTrace()
		}
	}

	override fun initComponents() {
		val nameHero = hero!!.name?.replace("_", " ")
		binding.heroTextNameBio.text = nameHero
		binding.heroTextBio.text = hero!!.bio
		AdMobBanner().createBanner(this, binding.heroBioAdmob, isPremium)
		mMenu = DotazoneMenu(this, binding.drawerLayout, binding.listSliderMenu.root)
		mMenu!!.checkHeroMenu()
	}

	override fun setActionErrorOk() {
		// TODO Auto-generated method stub
	}

	override fun setActionErrorCancel() {
		// TODO Auto-generated method stub
	}
}
