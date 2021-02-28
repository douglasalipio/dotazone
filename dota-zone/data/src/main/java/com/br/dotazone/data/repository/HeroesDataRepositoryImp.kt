package com.br.dotazone.data.repository

import com.br.dotazone.data.model.HeroesDataModelToHeroesDataMapper
import com.br.dotazone.data.service.HeroesDataService
import com.br.dotazone.domain.heroes.HeroesRepository
import com.br.dotazone.domain.heroes.entity.HeroesData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class HeroesDataRepositoryImp(private val heroesDataService: HeroesDataService,
							  private val mapper: HeroesDataModelToHeroesDataMapper)
	: HeroesRepository {

	override suspend fun getHeroesData(language: String): Flow<HeroesData> {
		return flow {
			val heroesDataModel = heroesDataService.getHeroesData(language)
			val heroesData = mapper.map(heroesDataModel)
			emit(heroesData)
		}.flowOn(Dispatchers.IO)
	}
}
