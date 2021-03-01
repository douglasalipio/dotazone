package com.br.dotazone.domain.heroes

import com.br.dotazone.domain.UseCaseWithParam
import com.br.dotazone.domain.heroes.prov.Hero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ParseHeroesDataInteractor(private val heroesRepository: HeroesRepository) : UseCaseWithParam<Flow<Hero?>, ParseHeroesDataInteractor.Params> {

	override suspend fun execute(param: Params): Flow<Hero?> = heroesRepository.parseHeroesData(param.heroesDataString)

	data class Params(val heroesDataString: String)
}
