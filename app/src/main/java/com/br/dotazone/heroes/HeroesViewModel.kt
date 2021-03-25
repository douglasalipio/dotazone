package com.br.dotazone.heroes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.dotazone.domain.heroes.GetHeroDataInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class HeroesViewModel(private val getHeroInteractor: GetHeroDataInteractor) : ViewModel() {

	var heroesStream: MutableLiveData<HeroesState> = MutableLiveData()

	fun requestHeroesData() {
		viewModelScope.launch {
			getHeroInteractor
					.execute(GetHeroDataInteractor.Params("en"))
					.flowOn(Dispatchers.Main)
					.catch { heroesStream.value = HeroesState.Error(it.message ?: "") }
					.collect { heroesStream.value = HeroesState.HeroDataLoaded(it) }

		}
	}
}
