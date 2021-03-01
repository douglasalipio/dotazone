package com.br.dotazone.domain.heroes

import com.br.dotazone.domain.heroes.entity.Heroes
import com.br.dotazone.domain.heroes.entity.HeroesData
import com.br.dotazone.domain.heroes.prov.Hero
import kotlinx.coroutines.flow.Flow

interface HeroesRepository {

	suspend fun getHeroesData(language : String): Flow<HeroesData>

	suspend fun parseHeroesData(heroesDataString : String)  : Flow<Hero?>
}
