package com.br.dotazone.heroes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.dotazone.domain.heroes.GetHeroesDataInteractor
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class HeroesViewModel(private val getHeroesInteractor: GetHeroesDataInteractor) : ViewModel() {

	fun getHeroesData() {
		viewModelScope.launch {
			val test = getHeroesInteractor.execute(GetHeroesDataInteractor.Params("en")).collect {
				Log.d("", it.toString())
			}

		}
	}
}
