package com.br.dotazone.heroes

import com.br.dotazone.domain.heroes.entity.Hero

sealed class HeroesState {
	data class HeroDataLoaded(val heroList : List<Hero>) : HeroesState()
	data class Error (val error : String): HeroesState()
}
