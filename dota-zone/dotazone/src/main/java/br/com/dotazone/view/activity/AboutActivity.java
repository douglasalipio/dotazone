package br.com.dotazone.view.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.gms.analytics.GoogleAnalytics;

import br.com.dotazone.DotaZoneApplication;
import br.com.dotazone.DotazoneMenu;
import br.com.dotazone.R;

public class AboutActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;
    private RelativeLayout mDrawerList;
    private DotazoneMenu mMenu;
    private LinearLayout mButtonMenu;


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.about_view);
        mMenu = new DotazoneMenu(this, mDrawerLayout, mDrawerList);
        mMenu.checkAboutMenu();
        initComponents();
        ((DotaZoneApplication) getApplication()).getTracker(DotaZoneApplication.TrackerName.APP_TRACKER);


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
    protected void onResume() {
        super.onResume();

        mMenu.checkAboutMenu();
    }

    @Override
    public void initComponents() {

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (RelativeLayout) findViewById(R.id.list_slidermenu);
        mButtonMenu = (LinearLayout) findViewById(R.id.about_menu);

        mButtonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(mDrawerList);
            }
        });
    }

    @Override
    public void setActionErrorOk() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setActionErrorCancel() {
        // TODO Auto-generated method stub

    }

}
