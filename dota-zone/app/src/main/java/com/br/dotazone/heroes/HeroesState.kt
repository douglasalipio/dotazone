package com.br.dotazone.heroes

sealed class HeroesState {
	object DataLoaded : HeroesState()
	object HeroLoadded : HeroesState()
	object Error : HeroesState()
}
