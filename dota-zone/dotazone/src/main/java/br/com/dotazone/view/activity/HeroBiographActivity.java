package br.com.dotazone.view.activity;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.GoogleAnalytics;

import java.io.IOException;

import br.com.dotazone.DotaZoneApplication;
import br.com.dotazone.DotaZoneBrain;
import br.com.dotazone.DotazoneMenu;
import br.com.dotazone.R;
import br.com.dotazone.model.entity.AdMobBanner;

public class HeroBiographActivity extends BaseActivity {

    private TextView mHeroName;
    private TextView mHeroBio;
    private MediaPlayer media;
    private AdView adView;
    private DrawerLayout mDrawerLayout;
    private RelativeLayout mDrawerList;
    private DotazoneMenu mMenu;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);

        setContentView(R.layout.hero_bio_view);
        initComponents();
        ((DotaZoneApplication) getApplication()).getTracker(DotaZoneApplication.TrackerName.APP_TRACKER);

    }

    @Override
    public void onPause() {
        adView.pause();
        media.pause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        adView.destroy();
        super.onDestroy();
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
        adView.resume();
        try {
            AssetFileDescriptor afd = getAssets().openFd("bio_theme.mp3");
            media = new MediaPlayer();
            media.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            media.prepare();
            media.setLooping(true);
            media.setOnPreparedListener(new OnPreparedListener() {

                @Override
                public void onPrepared(MediaPlayer mp) {
                    media.start();

                }
            });

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void initComponents() {

        mHeroName = (TextView) findViewById(R.id.hero_text_name_bio);
        mHeroBio = (TextView) findViewById(R.id.hero_text_bio);
        String nameHero = DotaZoneBrain.hero.getName().replace("_", " ");
        mHeroName.setText(nameHero);
        mHeroBio.setText(DotaZoneBrain.hero.getBio());
        adView = (AdView) findViewById(R.id.hero_bio_admob);
        new AdMobBanner().createBanner(this, adView, DotaZoneBrain.isPremium);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (RelativeLayout) findViewById(R.id.list_slidermenu);
        mMenu = new DotazoneMenu(this, mDrawerLayout, mDrawerList);
        mMenu.checkHeroMenu();
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
