package com.br.dotazone.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.ListFragment
import com.br.dotazone.R
import com.br.dotazone.databinding.FeedNewsBinding
import com.br.dotazone.domain.heroes.prov.Hero
import com.br.dotazone.domain.heroes.prov.Item
import com.br.dotazone.model.service.AdapterAction
import com.br.dotazone.model.util.UtilActivity


class FeedNewsFragment : ListFragment(), AdapterAction {

	private var binding: FeedNewsBinding? = null


	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.feed_news, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val binding = FeedNewsBinding.bind(view)
		this.binding = binding
		//initialize()
	}

	override fun onPause() {
		binding?.feedNewsAdView?.pause()
		super.onPause()
	}

	override fun onResume() {
		super.onResume()
		binding?.feedNewsAdView?.resume()

	}

	override fun onDestroy() {
		binding?.feedNewsAdView?.destroy()
		super.onDestroy()
	}

	private fun initialize() {

//		AdMobBanner().createBanner(requireActivity(), binding?.feedNewsAdView?, DotaZoneBrain.isPremium)
//		val font = Typeface.createFromAsset(requireActivity().assets, "Roboto-Thin.ttf")
//		titleTextNews.typeface = font
//		val parser: Parser = Parser.Builder()
//				.charset(Charset.forName("UTF-8"))
//				.build()
//		parser.execute("Http://blog.dota2.com/feed")
//		parser.onFinish(object : OnTaskCompleted {
//			override fun onTaskCompleted(channel: Channel) {
//				DotaZoneBrain.rssItems = channel.articles
//				requireActivity().runOnUiThread { listAdapter = FeedNewsListAdapter(requireActivity()) }
//			}
//
//			override fun onError(e: Exception) {}
//		})
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

