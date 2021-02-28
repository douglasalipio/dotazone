package com.br.dotazone.data.repository.hereos

import com.br.dotazone.data.model.HeroesDataModelToHeroesDataMapper
import com.br.dotazone.data.repository.HeroesDataRepositoryImp
import com.br.dotazone.data.service.HeroesDataService
import com.br.dotazone.domain.heroes.HeroesRepository
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class HeroesDataRepositoryImpTest {

	@Mock
	lateinit var heroesDataServiceMock: HeroesDataService

	private val mapper = HeroesDataModelToHeroesDataMapper()

	private lateinit var heroesRepository: HeroesRepository

	@Before
	fun setUp() {
		MockitoAnnotations.initMocks(this)
		heroesRepository = HeroesDataRepositoryImp(heroesDataServiceMock, mapper)
	}

	@Test
	fun `should load heroes successfully`() {
		runBlocking {
			//Given
			val heroesDataAsString = " "
			val language = "pt-br"
			given(heroesDataServiceMock.getHeroesData(language)).willReturn(heroesDataAsString)
			//When
			val heroesDataModelActual = heroesRepository.getHeroesData(language).single()
			val heroesData = mapper.map(heroesDataAsString)
			//Then
			assertEquals(heroesDataAsString, heroesDataModelActual.heroesStringData)
			verify(heroesDataServiceMock).getHeroesData(language)
		}
	}
}
