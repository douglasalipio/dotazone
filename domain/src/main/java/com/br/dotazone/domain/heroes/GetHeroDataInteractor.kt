package com.br.dotazone.domain.heroes

import com.br.dotazone.domain.UseCaseWithParam
import com.br.dotazone.domain.heroes.entity.Hero
import kotlinx.coroutines.flow.Flow

class GetHeroDataInteractor(private val heroRepository: HeroRepository) : UseCaseWithParam<Flow<List<Hero>>, GetHeroDataInteractor.Params> {

	override suspend fun execute(param: Params): Flow<List<Hero>> = heroRepository.getHeroes(param.language)

	data class Params(val language: String)
}
