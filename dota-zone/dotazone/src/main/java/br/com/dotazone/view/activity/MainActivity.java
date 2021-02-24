package br.com.dotazone.view.activity;

import android.os.Bundle;
import androidx.drawerlayout.widget.DrawerLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.gms.analytics.GoogleAnalytics;

import br.com.dotazone.DotaZoneApplication;
import br.com.dotazone.DotazoneMenu;
import br.com.dotazone.R;
import br.com.dotazone.view.fragment.FeedNewsFragment;

public class MainActivity extends BaseActivity implements OnClickListener {

    public DotazoneMenu mMenu;
    private DrawerLayout mDrawerLayout;
    private RelativeLayout mDrawerList;
    private ImageView mImageLogo;
    private ImageView mImageMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((DotaZoneApplication) getApplication()).getTracker(DotaZoneApplication.TrackerName.APP_TRACKER);

        initComponents();
        FeedNewsFragment fragment1 = FeedNewsFragment.Companion.newInstance();
        if (getIntent() != null && getIntent().getExtras() != null
                && getIntent().getExtras().getInt(MainActivity.class.getName()) == DotazoneMenu.MENU_NEWS) {

            getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_content, fragment1).commit();

        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_content, fragment1).commit();
            mMenu.checkNewsMenu();
        }
        // Get a Tracker (should auto-report)
        ((DotaZoneApplication) getApplication()).getTracker(DotaZoneApplication.TrackerName.APP_TRACKER);


        if (getIntent() != null && getIntent().getExtras() != null
                && getIntent().getExtras().getInt(MainActivity.class.getName()) == 100) {


        }


    }

    @Override
    protected void onResume() {
        super.onResume();

        if (getIntent() != null && getIntent().getExtras() != null
                && getIntent().getExtras().getInt(MainActivity.class.getName()) == DotazoneMenu.MENU_NEWS) {

            mMenu.checkNewsMenu();
        }
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
    public void initComponents() {

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (RelativeLayout) findViewById(R.id.list_slidermenu);
        mMenu = new DotazoneMenu(this, mDrawerLayout, mDrawerList);
        mImageLogo = (ImageView) findViewById(R.id.header_imageLogo);
        mImageMenu = (ImageView) findViewById(R.id.header_imageMenu);
        mImageLogo.setOnClickListener(this);
        mImageMenu.setOnClickListener(this);
    }

    @Override
    public void setActionErrorOk() {
        //finish();
    }

    @Override
    public void setActionErrorCancel() {
        // TODO Auto-generated method stub

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
