package br.com.dotazone.view.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.viewpagerindicator.TabPageIndicator;

import br.com.dotazone.DotaZoneApplication;
import br.com.dotazone.DotaZoneBrain;
import br.com.dotazone.DotazoneMenu;
import br.com.dotazone.R;
import br.com.dotazone.model.entity.AdMobBanner;
import br.com.dotazone.model.listeners.OnFragmentInteractionListener;
import br.com.dotazone.view.adapter.ChannelAdapter;
import br.com.dotazone.view.adapter.HeroAdapter;
import br.com.dotazone.view.adapter.ItemAdapter;

/**
 * Created by Douglas on 21/08/2014.
 */
public class TabChannel extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener, OnFragmentInteractionListener {

    private DotazoneMenu mMenu;
    private AdView adView;
    private DrawerLayout mDrawerLayout;
    private RelativeLayout mDrawerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_channel_view);

        ((DotaZoneApplication) getApplication()).getTracker(DotaZoneApplication.TrackerName.APP_TRACKER);

        initComponents();

    }

    @Override
    protected void onStart() {
        super.onStart();

        GoogleAnalytics.getInstance(this).reportActivityStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Stop the analytics tracking
        GoogleAnalytics.getInstance(this).reportActivityStop(this);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPause() {
        adView.pause();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

        adView.resume();
        mMenu.checkChannelMenu();

    }

    @Override
    public void onDestroy() {
        adView.destroy();
        super.onDestroy();
    }

    private void setAdapterPage(ViewPager pager) {

        ChannelAdapter channelAdapter = new ChannelAdapter(getSupportFragmentManager(),this);
        pager.setAdapter(channelAdapter);
        mMenu.checkChannelMenu();
    }

    @Override
    public void initComponents() {

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (RelativeLayout) findViewById(R.id.list_slidermenu);
        ImageView mImageLogo = (ImageView) findViewById(R.id.header_imageLogo);
        ImageView mImageMenu = (ImageView) findViewById(R.id.header_imageMenu);
        mImageLogo.setOnClickListener(this);
        mImageMenu.setOnClickListener(this);
        mMenu = new DotazoneMenu(this, mDrawerLayout, mDrawerList);

        ViewPager pager = (ViewPager) findViewById(R.id.tab_channel_pager);
        adView = (AdView) findViewById(R.id.tab_view_admob);
        setAdapterPage(pager);
        TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.tab_channel_indicator);
        indicator.setViewPager(pager);
        indicator.setOnPageChangeListener(this);
        new AdMobBanner().createBanner(this, adView, DotaZoneBrain.isPremium);

    }

    @Override
    public void setActionErrorOk() {
        finish();
    }

    @Override
    public void setActionErrorCancel() {
        finish();

    }

    @Override
    public void onClick(View v) {

        mDrawerLayout.openDrawer(mDrawerList);

    }

    @Override
    public void finish() {
        super.finish();
        mMenu.defaultMenu();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
