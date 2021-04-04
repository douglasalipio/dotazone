package com.br.dotazone.view.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.br.dotazone.DotaZoneBrain.isPremium
import com.br.dotazone.R
import com.br.dotazone.databinding.TabViewBinding
import com.br.dotazone.model.entity.AdMobBanner
import com.br.dotazone.view.adapter.HeroAdapter
import com.br.dotazone.view.adapter.ItemAdapter
import com.com.dotazone.DotazoneMenu
import com.google.android.gms.ads.AdView
import com.google.android.gms.analytics.GoogleAnalytics
import com.google.android.material.tabs.TabLayout


class TabActivity : BaseActivity(), OnPageChangeListener, View.OnClickListener {
    var mMenu: DotazoneMenu? = null
    private lateinit var binding: TabViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TabViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
        binding.tabViewAdmob.pause()
        super.onPause()
    }

    public override fun onResume() {
        super.onResume()
        binding.tabViewAdmob.resume()
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
        binding.tabViewAdmob.destroy()
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
        binding.headerView.headerImageLogo.setOnClickListener(this)
        binding.headerView.headerImageMenu.setOnClickListener(this)
        mMenu = DotazoneMenu(this, binding.drawerLayout, binding.listSliderMenu.root)
        val pager = findViewById<View>(R.id.tab_view_pager) as ViewPager
        setAdapterPage(pager)
        val indicator = findViewById<View>(R.id.indicator) as TabLayout
        indicator.setupWithViewPager(pager)
        //indicator.setOnPageChangeListener(this);
        AdMobBanner().createBanner(this, binding.tabViewAdmob, isPremium)
    }

    override fun setActionErrorOk() {
        finish()
    }

    override fun setActionErrorCancel() {
        finish()
    }

    override fun onClick(v: View) {
        binding.drawerLayout.openDrawer(binding.listSliderMenu.root)
    }

    override fun finish() {
        super.finish()
        mMenu!!.defaultMenu()
    }

    companion object {
        var isBuild = false
    }
}
