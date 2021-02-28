package com.br.dotazone.domain.heroes

import com.br.dotazone.domain.UseCaseWithParam
import com.br.dotazone.domain.heroes.entity.HeroesData
import kotlinx.coroutines.flow.Flow

class GetHeroesDataInteractor(private val heroesRepository: HeroesRepository) : UseCaseWithParam<Flow<HeroesData>, GetHeroesDataInteractor.Params> {

	override suspend fun execute(param: Params): Flow<HeroesData> = heroesRepository.getHeroesData(param.language)

	data class Params(val language: String)
}
