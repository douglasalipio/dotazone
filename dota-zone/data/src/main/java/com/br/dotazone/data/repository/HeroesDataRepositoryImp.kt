package com.br.dotazone.data.repository

import com.br.dotazone.data.service.HeroesDataService
import com.br.dotazone.domain.heroes.HeroesRepository
import com.br.dotazone.domain.heroes.entity.Heroes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class HeroesDataRepositoryImp(private val heroesDataService: HeroesDataService) : HeroesRepository {

	override suspend fun getHeroesData(language: String): Flow<String> =
			flow { emit(heroesDataService.getHeroesData(language)) }.flowOn(Dispatchers.IO)
}
