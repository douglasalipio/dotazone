package com.br.dotazone.data.repository

import com.br.dotazone.data.model.HeroModelToHeroMapper
import com.br.dotazone.data.model.HeroesDataModelToHeroesDataMapper
import com.br.dotazone.data.service.HeroesService
import com.br.dotazone.data.service.ParseHeroesService
import com.br.dotazone.domain.heroes.HeroesRepository
import com.br.dotazone.domain.heroes.entity.Hero
import com.br.dotazone.domain.heroes.entity.HeroesData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class HeroesRepositoryImp(
		private val heroesService: HeroesService,
		private val parseHeroesService: ParseHeroesService,
		private val heroDataMapper: HeroesDataModelToHeroesDataMapper,
		private val heroMapper: HeroModelToHeroMapper,
) : HeroesRepository {

	override suspend fun getHeroesData(language: String): Flow<HeroesData> {
		return flow {
			val heroesDataModel = heroesService.getHeroesData(language)
			val heroesData = heroDataMapper.map(heroesDataModel)
			emit(heroesData)
		}.flowOn(Dispatchers.IO)
	}

	override suspend fun parseHeroesData(heroesDataString: String): Flow<List<Hero>> {
		return flow {
			val heroesModel = parseHeroesService.parseHeroesData(heroesDataString)
			val heroes = heroesModel.map { heroMapper.map(it) }
			emit(heroes)
		}.flowOn(Dispatchers.IO)
	}
}
