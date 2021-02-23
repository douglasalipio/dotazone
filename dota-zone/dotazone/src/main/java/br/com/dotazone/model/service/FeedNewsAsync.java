package br.com.dotazone.model.service;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.ListFragment;
import android.util.Log;

import com.google.gson.Gson;
import com.parse.Parse;
import com.prof.rssparser.Article;
import com.prof.rssparser.Channel;
import com.prof.rssparser.OnTaskCompleted;
import com.prof.rssparser.Parser;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import br.com.dotazone.DotaZoneBrain;
import br.com.dotazone.R;
import br.com.dotazone.model.entity.VideoOffline;
import br.com.dotazone.view.activity.BaseActivity;
import br.com.dotazone.view.adapter.FeedNewsListAdapter;
import br.com.dotazone.view.fragment.DialogError;
import br.com.dotazone.view.fragment.DialogError.TypeError;
import br.com.dotazone.view.fragment.FeedNewsFragment;
import nl.matshofman.saxrssreader.RssFeed;
import nl.matshofman.saxrssreader.RssItem;
import nl.matshofman.saxrssreader.RssReader;

public class FeedNewsAsync extends AsyncTask<Void, Void, List<RssItem>> {

    private ArrayList<RssItem> mRssItems;
    private ProgressDialog mProgressDialog;
    private ListFragment mListFragment;
    private BaseActivity mActivity;
    private AdapterAction mAdapterAction;

    public FeedNewsAsync(ListFragment listFragment, BaseActivity activity) {
        this.mListFragment = listFragment;
        this.mActivity = activity;
        this.mAdapterAction = (FeedNewsFragment) mListFragment;
    }

    public static InputStream getInputStreamFromUrl(String url) {

        InputStream content = null;
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(new HttpGet(url));
            content = response.getEntity().getContent();
        } catch (Exception e) {
            Log.i("[GET REQUEST]", "Network exception", e);
        }
        return content;
    }

    @Override
    protected List<RssItem> doInBackground(Void... params) {

        URL url;
        try {
            url = new URL("http://blog.dota2.com/feed/");

            RssFeed feed = RssReader.read(url);
            mRssItems = feed.getRssItems();

        } catch (Exception e) {
            Log.i(DotaZoneBrain.TAG, "Erro ao carregar o feed---" + e.getMessage());
            DialogError fragmentError = DialogError.newFragmentDialog(mActivity.getResources().getString(R.string.error_feed_news), mActivity,
                    TypeError.ONE_OPTIONS);
            mRssItems = new ArrayList<RssItem>();
            fragmentError.show(mActivity.getSupportFragmentManager(), "Feed");

        }
        return mRssItems;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        mProgressDialog = new ProgressDialog(mListFragment.getActivity());
        mProgressDialog.setMessage(mActivity.getResources().getString(R.string.loading_dialog));
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    @Override
    protected void onPostExecute(List<RssItem> result) {
        super.onPostExecute(result);

//        DotaZoneBrain.rssItems = new ArrayList<RssItem>();
//        DotaZoneBrain.rssItems = result;
        mListFragment.setListAdapter(new FeedNewsListAdapter(mListFragment.getActivity()));
        mProgressDialog.cancel();
        mAdapterAction.initRating();
    }

    private void test() {

        Gson gson = new Gson();
        VideoOffline videosOffline = new VideoOffline();
        for (int i = 0; i <= 2; i++) {


            VideoOffline.Championship championship = new VideoOffline().new Championship();
            VideoOffline.Channel channel = new VideoOffline().new Channel();

            List<VideoOffline.Championship> championships = new ArrayList<VideoOffline.Championship>();
            championships.add(championship);
            videosOffline.channels.add(channel);

            channel.mChannel = "NomadTV Dota2" + i;
            championship.mChampionship = "TI2014" + i;
            championship.mChampionShipImg = "url_img" + i;
            channel.championship = championships;

            for (int j = 0; i < 10; j++) {

                VideoOffline.Video video = new VideoOffline().new Video();
                video.mDateVideo = "20-10-2014";
                video.mTitleVideo = "Navi vc Newbee";
                video.mUrlVideo = "url_video";
                championship.mVideos.add(video);
            }
        }


        String json = gson.toJson(videosOffline);
        System.out.println();
    }


}
