package com.br.dotazone.domain.heroes

import com.br.dotazone.domain.heroes.entity.Heroes
import com.br.dotazone.domain.heroes.entity.HeroesData
import kotlinx.coroutines.flow.Flow

interface HeroesRepository {

	suspend fun getHeroesData(language : String): Flow<HeroesData>

}
