package com.br.dotazone.domain.heroes

import com.br.dotazone.domain.heroes.entity.Hero
import com.br.dotazone.domain.heroes.entity.HeroesData
import kotlinx.coroutines.flow.Flow

interface HeroesRepository {

	suspend fun getHeroesData(language : String): Flow<HeroesData>

	suspend fun parseHeroesData(heroesDataString : String)  : Flow<List<Hero>>
}
