package br.com.dotazone.view.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.widget.DrawerLayout;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.analytics.GoogleAnalytics;

import br.com.dotazone.DotaZoneApplication;
import br.com.dotazone.DotazoneMenu;
import br.com.dotazone.R;

/**
 * Created by Douglas on 26/07/2015.
 */
public class RoshanActivity extends BaseActivity {


    private TextView timerRoshan;
    private TextView textRoshan;
    private ImageView btnSilence;
    private ImageView btnPlay;
    private ImageView btnVibration;
    private boolean silencePressed;
    private boolean playPressed;
    private boolean vibrationPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.roshan_view);
        initComponents();
        ((DotaZoneApplication) getApplication()).getTracker(DotaZoneApplication.TrackerName.APP_TRACKER);
    }

    public void startRoshan(){

        new CountDownTimer(100000, 1000) {

            public void onTick(long millisUntilFinished) {
                String v = String.format("%02d", millisUntilFinished/60000);
                int va = (int)( (millisUntilFinished%60000)/1000);
                timerRoshan.setText(v+":"+String.format("%02d",va));
            }

            public void onFinish() {
                textRoshan.setText(getResources().getText(R.string.roshan_go_to_roshan));
            }
        }.start();

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
        startRoshan();
    }

    @Override
    public void setActionErrorOk() {

    }

    @Override
    public void setActionErrorCancel() {

    }

    @Override
    public void initComponents() {
        timerRoshan = (TextView) findViewById(R.id.roshan_view_countdown);
        textRoshan = (TextView) findViewById(R.id.roshan_view_text);
        btnPlay = (ImageView) findViewById(R.id.roshan_view_btn_play);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!playPressed) {
                    btnPlay.setBackground(getResources().getDrawable(R.drawable.btn_stop));
                    playPressed = true;
                }else{
                    btnPlay.setBackground(getResources().getDrawable(R.drawable.btn_play));
                    playPressed = false;
                }
            }
        });

        btnSilence =  (ImageView)findViewById(R.id.roshan_view_btn_silence);
        btnSilence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!silencePressed) {
                    btnSilence.setBackground(getResources().getDrawable(R.drawable.ic_bell_off));
                    silencePressed = true;
                }else{
                    btnSilence.setBackground(getResources().getDrawable(R.drawable.ic_bell));
                    silencePressed = false;
                }
            }
        });
        btnVibration = (ImageView) findViewById(R.id.roshan_view_btn_vibration);
        btnVibration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!vibrationPressed) {
                    btnVibration.setBackground(getResources().getDrawable(R.drawable.ic_vibrate_off));
                    vibrationPressed = true;
                }else{
                    btnVibration.setBackground(getResources().getDrawable(R.drawable.ic_vibrate));
                    vibrationPressed = false;
                }
            }
        });
    }
}
