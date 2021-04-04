package com.br.dotazone.view.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.drawerlayout.widget.DrawerLayout
import com.br.dotazone.DotaZoneApplication
import com.br.dotazone.R
import com.br.dotazone.databinding.ActivityMainBinding
import com.br.dotazone.databinding.SplashScreenViewBinding
import com.br.dotazone.view.fragment.FeedNewsFragment.Companion.newInstance
import com.com.dotazone.DotazoneMenu
import com.google.android.gms.analytics.GoogleAnalytics


class MainActivity : BaseActivity(), View.OnClickListener {
    var mMenu: DotazoneMenu? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponents()
        val fragment1 = newInstance()
        if (intent != null && intent.extras != null && intent.extras!!.getInt(MainActivity::class.java.name) == DotazoneMenu.MENU_NEWS) {
            supportFragmentManager.beginTransaction().replace(R.id.main_activity_content, fragment1).commit()
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.main_activity_content, fragment1).commit()
            mMenu!!.checkNewsMenu()
        }
        // Get a Tracker (should auto-report)

        if (intent != null && intent.extras != null && intent.extras!!.getInt(MainActivity::class.java.name) == 100) {
        }
    }

    override fun onResume() {
        super.onResume()
        if (intent != null && intent.extras != null && intent.extras!!.getInt(MainActivity::class.java.name) == DotazoneMenu.MENU_NEWS) {
            mMenu!!.checkNewsMenu()
        }
    }

    override fun onStart() {
        super.onStart()
        //GoogleAnalytics.getInstance(this).reportActivityStart(this)
    }

    override fun onStop() {
        super.onStop()

        // Stop the analytics tracking
        //GoogleAnalytics.getInstance(this).reportActivityStop(this)
    }

    override fun initComponents() {
        mMenu = DotazoneMenu(this, binding.drawerLayout, binding.listSliderMenu.root)
        binding.headerView.headerImageLogo.setOnClickListener(this)
        binding.headerView.headerImageMenu.setOnClickListener(this)
    }

    override fun setActionErrorOk() {
        //finish();
    }

    override fun setActionErrorCancel() {
        // TODO Auto-generated method stub
    }

    override fun onClick(v: View) {
        binding.drawerLayout.openDrawer(binding.listSliderMenu.root)
    }

    override fun finish() {
        super.finish()
        mMenu!!.defaultMenu()
    }
}
