package com.br.dotazone.domain.heroes

import com.br.dotazone.domain.heroes.entity.Hero
import kotlinx.coroutines.flow.Flow

interface HeroRepository {

	suspend fun getHeroes(language: String): Flow<List<Hero>>
}
