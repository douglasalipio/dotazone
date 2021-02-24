package br.com.dotazone.view.fragment

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.ListFragment
import br.com.dotazone.DotaZoneBrain
import br.com.dotazone.R
import br.com.dotazone.model.entity.AdMobBanner
import br.com.dotazone.model.entity.Hero
import br.com.dotazone.model.entity.Item
import br.com.dotazone.model.service.AdapterAction
import br.com.dotazone.model.util.UtilActivity
import br.com.dotazone.view.adapter.FeedNewsListAdapter
import com.prof.rssparser.Channel
import com.prof.rssparser.OnTaskCompleted
import com.prof.rssparser.Parser
import kotlinx.android.synthetic.main.feed_news.*
import kotlinx.android.synthetic.main.header_view.*
import java.nio.charset.Charset


class FeedNewsFragment : ListFragment(), AdapterAction {
	private var mView: View? = null
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		mView = inflater.inflate(R.layout.feed_news, container, false)
		return mView
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		initialize()
	}

	override fun onPause() {
		myAdView?.pause()
		super.onPause()
	}

	override fun onResume() {
		super.onResume()
		myAdView?.resume()
	}

	override fun onDestroy() {
		myAdView?.destroy()
		super.onDestroy()
	}

	private fun initialize() {

		AdMobBanner().createBanner(activity, myAdView, DotaZoneBrain.isPremium)
		val font = Typeface.createFromAsset(activity!!.assets, "Roboto-Thin.ttf")
		titleTextNews.typeface = font
		val parser: Parser = Parser.Builder()
				.charset(Charset.forName("UTF-8"))
				.build()
		parser.execute("http://blog.dota2.com/feed/")
		parser.onFinish(object : OnTaskCompleted {
			override fun onTaskCompleted(channel: Channel) {
				DotaZoneBrain.rssItems = channel.articles
				activity!!.runOnUiThread { listAdapter = FeedNewsListAdapter(activity) }
			}

			override fun onError(e: Exception) {}
		})
	}

	override fun initList() {}
	override fun initListIHero(heroes: List<Hero>) {}
	override fun initListItem(items: List<Item>) {}
	override fun initRating() {
		UtilActivity.ratingDotaZoneDialog(activity)
	}

	companion object {
		fun newInstance(): FeedNewsFragment {
			return FeedNewsFragment()
		}
	}
}

