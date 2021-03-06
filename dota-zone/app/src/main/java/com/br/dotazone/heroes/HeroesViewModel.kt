package com.br.dotazone.heroes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.dotazone.domain.heroes.GetHeroesDataInteractor
import com.br.dotazone.domain.heroes.ParseHeroesDataInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class HeroesViewModel(
		private val getHeroesInteractor: GetHeroesDataInteractor,
		private val parseHeroesDataInteractor: ParseHeroesDataInteractor
) : ViewModel() {

	var heroesStream: MutableLiveData<HeroesState> = MutableLiveData()

	fun requestHeroesData() {
		viewModelScope.launch {
			getHeroesInteractor
					.execute(GetHeroesDataInteractor.Params("en"))
					.flowOn(Dispatchers.Main)
					.catch { heroesStream.value = HeroesState.Error(it.message ?: "") }
					.collect { heroesStream.value = HeroesState.DataLoaded(it.heroesStringData) }

		}
	}

	fun requestParseHeroesData(heroesDataString: String) {
		viewModelScope.launch {
			parseHeroesDataInteractor
					.execute(ParseHeroesDataInteractor.Params(heroesDataString))
					.flowOn(Dispatchers.Main)
					.catch { heroesStream.value = HeroesState.Error(it.message ?: "") }
					.collect { heroesStream.value = HeroesState.HeroLoaded }

		}
	}
}
