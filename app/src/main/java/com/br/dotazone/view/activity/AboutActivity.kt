package com.br.dotazone.view.activity

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.drawerlayout.widget.DrawerLayout
import com.br.dotazone.DotaZoneApplication
import com.br.dotazone.R
import com.br.dotazone.databinding.AboutViewBinding
import com.br.dotazone.databinding.BuildHeroViewBinding
import com.com.dotazone.DotazoneMenu
import com.google.android.gms.analytics.GoogleAnalytics


class AboutActivity : BaseActivity() {
    private var mMenu: DotazoneMenu? = null
    private var mButtonMenu: LinearLayout? = null

    private lateinit var binding: AboutViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about_view)
        mMenu = DotazoneMenu(this, binding.drawerLayout, binding.listSliderMenu.root)
        mMenu!!.checkAboutMenu()
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

    override fun onResume() {
        super.onResume()
        mMenu!!.checkAboutMenu()
    }

    override fun initComponents() {
        mButtonMenu = findViewById<View>(R.id.about_menu) as LinearLayout
        mButtonMenu?.setOnClickListener { binding.drawerLayout.openDrawer(binding.drawerLayout) }
    }

    override fun setActionErrorOk() {
        TODO("Not yet implemented")
    }

    override fun setActionErrorCancel() {
        TODO("Not yet implemented")
    }


}
