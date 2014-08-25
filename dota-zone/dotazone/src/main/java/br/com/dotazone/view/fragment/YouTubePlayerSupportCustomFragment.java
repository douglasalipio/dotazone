package br.com.dotazone.view.fragment;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

import br.com.dotazone.model.util.DeveloperKey;

/**
 * Created by Douglas on 25/08/2014.
 */
public class YouTubePlayerSupportCustomFragment extends com.google.android.youtube.player.YouTubePlayerSupportFragment {


    public YouTubePlayerSupportCustomFragment() {

        initialize(DeveloperKey.DEVELOPER_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo("aQTE3qG9W20");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }

}
