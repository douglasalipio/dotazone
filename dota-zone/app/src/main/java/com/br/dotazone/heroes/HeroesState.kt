package com.br.dotazone.heroes

sealed class HeroesState {
	data class DataLoaded(val heroesDataString : String) : HeroesState()
	object HeroLoaded : HeroesState()
	data class Error (val error : String): HeroesState()
}
