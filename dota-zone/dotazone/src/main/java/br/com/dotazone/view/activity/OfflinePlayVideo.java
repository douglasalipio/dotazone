package br.com.dotazone.view.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.android.gms.cast.CastMediaControlIntent;

import br.com.dotazone.R;

/**
 * Created by Douglas on 15/09/2014.
 */
public class OfflinePlayVideo extends BaseActivity {

    public static final java.lang.String PATH_VIDEO = "path_video";
    private MediaRouter mediaRouter;
    private MediaRouteSelector mediaRouteSelector;

    /**
     * Called when the activity is first created.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offline_play_video);
        showVideo();

        mediaRouter = MediaRouter.getInstance(getApplicationContext());
        mediaRouteSelector = new MediaRouteSelector.Builder().addControlCategory(CastMediaControlIntent.categoryForCast(getString(R.string.APP_ID))).build();
    }


    private void showVideo() {
        String pathVideo = getIntent().getExtras().getString(PATH_VIDEO);
        VideoView vd = (VideoView) findViewById(R.id.videoview);
        Uri uri = Uri.parse(pathVideo);
        MediaController mc = new MediaController(this);
        vd.setMediaController(mc);
        vd.setVideoURI(uri);
        vd.start();
    }


    @Override
    public void setActionErrorOk() {

    }

    @Override
    public void setActionErrorCancel() {

    }

    @Override
    public void initComponents() {

    }
}
