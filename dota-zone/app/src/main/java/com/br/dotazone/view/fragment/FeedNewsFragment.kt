package com.br.dotazone.view.fragment

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.ListFragment
import com.br.dotazone.DotaZoneBrain
import com.br.dotazone.R
import com.br.dotazone.domain.heroes.HeroesRepository
import com.br.dotazone.heroes.HeroesViewModel
import com.br.dotazone.model.entity.AdMobBanner
import com.br.dotazone.model.entity.Hero
import com.br.dotazone.model.entity.Item
import com.br.dotazone.model.service.AdapterAction
import com.br.dotazone.model.util.UtilActivity
import com.br.dotazone.view.adapter.FeedNewsListAdapter
import com.prof.rssparser.Channel
import com.prof.rssparser.OnTaskCompleted
import com.prof.rssparser.Parser
import kotlinx.android.synthetic.main.feed_news.*
import kotlinx.android.synthetic.main.header_view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.nio.charset.Charset


class FeedNewsFragment : ListFragment(), AdapterAction {
	private var mView: View? = null

	private val heroesViewModel: HeroesViewModel by viewModel()

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		mView = inflater.inflate(R.layout.feed_news, container, false)
		return mView
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		initialize()
		heroesViewModel.getHeroesData()
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

		AdMobBanner().createBanner(requireActivity(), myAdView, DotaZoneBrain.isPremium)
		val font = Typeface.createFromAsset(requireActivity().assets, "Roboto-Thin.ttf")
		titleTextNews.typeface = font
		val parser: Parser = Parser.Builder()
				.charset(Charset.forName("UTF-8"))
				.build()
		parser.execute("http://blog.dota2.com/feed/")
		parser.onFinish(object : OnTaskCompleted {
			override fun onTaskCompleted(channel: Channel) {
				DotaZoneBrain.rssItems = channel.articles
				requireActivity().runOnUiThread { listAdapter = FeedNewsListAdapter(requireActivity()) }
			}

			override fun onError(e: Exception) {}
		})
	}

	override fun initList(view: View?) {}
	override fun initListIHero(heroes: List<Hero>) {}
	override fun initListItem(items: List<Item>) {}
	override fun initRating() {
		UtilActivity.ratingDotaZoneDialog(requireActivity())
	}

	companion object {
		fun newInstance(): FeedNewsFragment {
			return FeedNewsFragment()
		}
	}
}

