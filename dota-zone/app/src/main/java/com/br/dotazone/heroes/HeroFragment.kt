package com.br.dotazone.heroes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.br.dotazone.DotaZoneBrain
import com.br.dotazone.R
import com.br.dotazone.domain.heroes.prov.Ability.AbilityElementy
import com.br.dotazone.domain.heroes.prov.Hero
import com.br.dotazone.domain.heroes.prov.Item
import com.br.dotazone.model.service.AdapterAction
import com.br.dotazone.view.activity.BuildHeroActivity
import com.br.dotazone.view.activity.HeroProfileActivity
import com.br.dotazone.view.activity.TabActivity
import com.br.dotazone.view.adapter.HeroGridAdapter
import kotlinx.android.synthetic.main.tab_grid_hero_view.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class HeroFragment : Fragment(), AdapterAction, OnItemClickListener {

	private var mContent: String? = "???"
	private val heroesViewModel: HeroesViewModel by viewModel()
	private val observer = Observer<HeroesState> { handleResponse(it) }

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		if (savedInstanceState != null && savedInstanceState.containsKey(KEY_CONTENT)) {
			mContent = savedInstanceState.getString(KEY_CONTENT)
		}
		val view = inflater.inflate(R.layout.tab_grid_hero_view, container, false)
		view.gridHeroView.onItemClickListener = this
		//if (DotaZoneBrain.heroes.isEmpty()) HeroAsync(this).execute() else initList(view)
		heroesViewModel.heroesStream.observe(viewLifecycleOwner, observer)
		heroesViewModel.requestHeroesData()
		return view
	}

	override fun initList(view: View?) {
		val itemsForTab: MutableList<com.br.dotazone.domain.heroes.entity.Hero> = ArrayList()
		when (mContent) {
			getString(R.string.hero_agility) -> {
				for (hero in DotaZoneBrain.heroes) {
					if (hero.heroAbility != null && hero.heroAbility!!.pa == AbilityElementy.AGI.value) {
						itemsForTab.add(hero)
					}
				}
			}
			getString(R.string.hero_strength) -> {
				for (hero in DotaZoneBrain.heroes) {
					if (hero.heroAbility != null && hero.heroAbility!!.pa == AbilityElementy.STR.value) {
						itemsForTab.add(hero)
					}
				}
			}
			else -> for (hero in DotaZoneBrain.heroes) {
				if (hero.heroAbility != null && hero.heroAbility!!.pa == AbilityElementy.INT.value) {
					itemsForTab.add(hero)
				}
			}
		}
		view?.gridHeroView?.adapter = HeroGridAdapter(requireActivity(), itemsForTab)
	}

	override fun onSaveInstanceState(outState: Bundle) {
		super.onSaveInstanceState(outState)
		outState.putString(KEY_CONTENT, mContent)
	}

	override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
		DotaZoneBrain.hero = parent.adapter.getItem(position) as Hero
		if (TabActivity.isBuild) {
			startActivity(Intent(activity, BuildHeroActivity::class.java))
		} else {
			startActivity(Intent(activity, HeroProfileActivity::class.java))
		}
	}

	private fun handleResponse(heroesState: HeroesState) {
		when (heroesState) {
			is HeroesState.HeroDataLoaded -> if (DotaZoneBrain.heroes.isEmpty())DotaZoneBrain.heroes.addAll(heroesState.heroList)
			is HeroesState.Error -> Toast.makeText(requireContext(), heroesState.error, Toast.LENGTH_LONG).show()
		}
	}

	override fun initListItem(items: List<Item>) {}
	override fun initListIHero(heroes: List<Hero>) {}
	override fun initRating() {}

	companion object {
		private const val KEY_CONTENT = "TestFragment:Content"
		fun newInstance(content: String?): HeroFragment {
			val f = HeroFragment()
			f.mContent = content
			return f
		}
	}
}

