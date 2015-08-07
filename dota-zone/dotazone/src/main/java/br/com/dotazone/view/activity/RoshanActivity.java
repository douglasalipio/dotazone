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

/**
 * Created by Douglas on 26/07/2015.
 */
public class RoshanActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;
    private RelativeLayout mDrawerList;
    private DotazoneMenu mMenu;
    private LinearLayout mButtonMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.roshan_view);
       // initComponents();
       // mMenu = new DotazoneMenu(this, mDrawerLayout, mDrawerList);
       // mMenu.checkRoshanlMenu();
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

        //mMenu.checkRoshanlMenu();
    }

    @Override
    public void setActionErrorOk() {

    }

    @Override
    public void setActionErrorCancel() {

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
}
