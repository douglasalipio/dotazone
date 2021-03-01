package com.br.dotazone.data.repository

import com.br.dotazone.data.model.HeroesDataModelToHeroesDataMapper
import com.br.dotazone.data.service.HeroesService
import com.br.dotazone.domain.heroes.HeroesRepository
import com.br.dotazone.domain.heroes.entity.Heroes
import com.br.dotazone.domain.heroes.entity.HeroesData
import com.br.dotazone.domain.heroes.prov.Hero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class HeroesRepositoryImp(private val heroesService: HeroesService,
						  private val mapper: HeroesDataModelToHeroesDataMapper)
	: HeroesRepository {

	override suspend fun getHeroesData(language: String): Flow<HeroesData> {
		return flow {
			val heroesDataModel = heroesService.getHeroesData(language)
			val heroesData = mapper.map(heroesDataModel)
			emit(heroesData)
		}.flowOn(Dispatchers.IO)
	}

	override suspend fun parseHeroesData(heroesDataString: String): Flow<Hero?> {
		return flow {
			val heroes = heroesService.parseHeroesData(heroesDataString)
			emit(heroes)
		}.flowOn(Dispatchers.IO)
	}
}
