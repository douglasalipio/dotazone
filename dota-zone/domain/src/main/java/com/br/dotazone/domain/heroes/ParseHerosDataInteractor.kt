package com.br.dotazone.domain.heroes

import com.br.dotazone.domain.UseCaseWithParam
import com.br.dotazone.domain.heroes.entity.Hero
import kotlinx.coroutines.flow.Flow

class ParseHeroesDataInteractor(private val heroesRepository: HeroesRepository)
	: UseCaseWithParam<Flow<List<Hero>>, ParseHeroesDataInteractor.Params> {

	override suspend fun execute(param: Params): Flow<List<Hero>> = heroesRepository.parseHeroesData(param.heroesDataString)

	data class Params(val heroesDataString: String)
}
