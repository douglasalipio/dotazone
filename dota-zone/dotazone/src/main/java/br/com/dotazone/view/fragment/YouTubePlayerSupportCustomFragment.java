package br.com.dotazone.view.fragment;

import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

import br.com.dotazone.BuildConfig;
import br.com.dotazone.R;

/**
 * Created by Douglas on 25/08/2014.
 */
public class YouTubePlayerSupportCustomFragment extends com.google.android.youtube.player.YouTubePlayerSupportFragment {


    public YouTubePlayerSupportCustomFragment() {

        initialize(BuildConfig.DOTA_ZONE_YOUTUBE_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {


                youTubePlayer.cueVideo(getArguments().getString("VIDEO_ID"));
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(getActivity(), getResources().getString(R.string.error_player), Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
