package br.com.dotazone.model.service;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.ListFragment;
import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.dotazone.DotaZoneBrain;
import br.com.dotazone.R;
import br.com.dotazone.model.entity.VideosOffline;
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

        DotaZoneBrain.rssItems = new ArrayList<RssItem>();
        DotaZoneBrain.rssItems = result;
        mListFragment.setListAdapter(new FeedNewsListAdapter(mListFragment.getActivity()));
        mProgressDialog.cancel();
        mAdapterAction.initRating();
    }


}
