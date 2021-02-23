package br.com.dotazone.view.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;
import com.prof.rssparser.Channel;
import com.prof.rssparser.OnTaskCompleted;
import com.prof.rssparser.Parser;

import java.nio.charset.Charset;
import java.util.List;

import br.com.dotazone.DotaZoneBrain;
import br.com.dotazone.R;
import br.com.dotazone.model.entity.AdMobBanner;
import br.com.dotazone.model.entity.Hero;
import br.com.dotazone.model.entity.Item;
import br.com.dotazone.model.service.AdapterAction;
import br.com.dotazone.model.service.FeedNewsAsync;
import br.com.dotazone.model.util.UtilActivity;
import br.com.dotazone.view.activity.MainActivity;
import br.com.dotazone.view.adapter.FeedNewsListAdapter;

public class FeedNewsFragment extends ListFragment implements AdapterAction {

	private View mView;
	private AdView adView;

	public static final FeedNewsFragment newInstance() {
		FeedNewsFragment f = new FeedNewsFragment();
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		mView = inflater.inflate(R.layout.feed_news, container, false);
		initialize();

		return mView;
	}

	@Override
	public void onPause() {
		adView.pause();
		super.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
		adView.resume();
	}

	@Override
	public void onDestroy() {
		adView.destroy();
		super.onDestroy();
	}

	private void initialize() {

		adView = (AdView) mView.findViewById(R.id.MyAdView);

		new AdMobBanner().createBanner(getActivity(), adView, DotaZoneBrain.isPremium);

		TextView titleNews = (TextView) mView.findViewById(R.id.textNews);
		Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-Thin.ttf");
		titleNews.setTypeface(font);


		Parser parser = new Parser.Builder()
				.charset(Charset.forName("UTF-8"))
				.build();
		parser.execute("http://blog.dota2.com/feed/");

		parser.onFinish(new OnTaskCompleted() {
			@Override
			public void onTaskCompleted(Channel channel) {
				DotaZoneBrain.rssItems = channel.getArticles();
				getActivity().runOnUiThread(new Runnable() {
					@Override
					public void run() {
						setListAdapter(new FeedNewsListAdapter(getActivity()));
					}
				});
			}

			@Override
			public void onError(Exception e) {
			}
		});
	}

	@Override
	public void onStop() {
		super.onStop();

	}

	@Override
	public void initList() {

	}

	@Override
	public void initListItem(List<Item> items) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initListIHero(List<Hero> heroes) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initRating() {
		UtilActivity.ratingDotaZoneDialog(getActivity());

	}

}
