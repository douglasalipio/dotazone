package br.com.dotazone.view.activity;

import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
import androidx.drawerlayout.widget.DrawerLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.material.tabs.TabLayout;

import br.com.dotazone.DotaZoneApplication;
import br.com.dotazone.DotaZoneBrain;
import br.com.dotazone.DotazoneMenu;
import br.com.dotazone.R;
import br.com.dotazone.model.entity.AdMobBanner;
import br.com.dotazone.view.adapter.HeroAdapter;
import br.com.dotazone.view.adapter.ItemAdapter;

public class TabActivity extends BaseActivity implements OnPageChangeListener, OnClickListener {

    public static final int TAB_ITEM = 1;
    public static final int TAB_HERO = 0;
    public static boolean isBuild;
    public DotazoneMenu mMenu;
    private AdView adView;
    private DrawerLayout mDrawerLayout;
    private RelativeLayout mDrawerList;
    private ImageView mImageLogo;
    private ImageView mImageMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_view);

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

        int id = getIntent().getExtras().getInt(MainActivity.class.getName());
        if (getIntent() != null && id == DotazoneMenu.MENU_HERO) {
            mMenu.checkHeroMenu();

        } else if (id == DotazoneMenu.MENU_ITEM) {
            mMenu.checkItemMenu();
        } else {

            mMenu.checkBuildMenu();
        }
    }

    @Override
    public void onDestroy() {
        adView.destroy();
        super.onDestroy();
    }

    private void setAdapterPage(ViewPager pager) {

        ItemAdapter itemAdapter = new ItemAdapter(getSupportFragmentManager(), getApplicationContext());
        HeroAdapter heroAdapter = new HeroAdapter(getSupportFragmentManager(), this);

        int id = getIntent().getExtras().getInt(MainActivity.class.getName());

        if (getIntent() != null && id == DotazoneMenu.MENU_HERO) {
            pager.setAdapter(heroAdapter);
            mMenu.checkHeroMenu();

        } else if (id == DotazoneMenu.MENU_ITEM) {
            pager.setAdapter(itemAdapter);
            mMenu.checkItemMenu();
        } else {
            pager.setAdapter(heroAdapter);
            mMenu.checkBuildMenu();
        }
    }

    @Override
    public void initComponents() {

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (RelativeLayout) findViewById(R.id.list_slidermenu);
        mImageLogo = (ImageView) findViewById(R.id.header_imageLogo);
        mImageMenu = (ImageView) findViewById(R.id.header_imageMenu);
        mImageLogo.setOnClickListener(this);
        mImageMenu.setOnClickListener(this);
        mMenu = new DotazoneMenu(this, mDrawerLayout, mDrawerList);

        ViewPager pager = (ViewPager) findViewById(R.id.tab_view_pager);
        adView = (AdView) findViewById(R.id.tab_view_admob);
        setAdapterPage(pager);
        TabLayout indicator = (TabLayout) findViewById(R.id.indicator);
        indicator.setupWithViewPager(pager);
        //indicator.setOnPageChangeListener(this);
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
}
