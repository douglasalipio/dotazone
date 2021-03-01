package com.br.dotazone.data.repository.hereos

import com.br.dotazone.data.model.HeroesDataModelToHeroesDataMapper
import com.br.dotazone.data.repository.HeroesRepositoryImp
import com.br.dotazone.data.repository.fakeHeroes
import com.br.dotazone.data.service.HeroesService
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

class HeroesRepositoryImpTest {

	@Mock
	lateinit var heroesServiceMock: HeroesService

	private val mapper = HeroesDataModelToHeroesDataMapper()

	private lateinit var heroesRepository: HeroesRepository

	@Before
	fun setUp() {
		MockitoAnnotations.initMocks(this)
		heroesRepository = HeroesRepositoryImp(heroesServiceMock, mapper)
	}

	@Test
	fun `should load heroes successfully`() {
		runBlocking {
			//Given
			val heroesDataAsString = " "
			val language = "pt-br"
			given(heroesServiceMock.getHeroesData(language)).willReturn(heroesDataAsString)
			//When
			val heroesDataModelActual = heroesRepository.getHeroesData(language).single()
			val heroesData = mapper.map(heroesDataAsString)
			//Then
			assertEquals(heroesDataAsString, heroesDataModelActual.heroesStringData)
			verify(heroesServiceMock).getHeroesData(language)
		}
	}

	@Test
	fun `should parse heroes successfully`(){
		runBlocking {
			//Given
			val heroesDataAsString = " "
			given(heroesServiceMock.parseHeroesData(heroesDataAsString)).willReturn(fakeHeroes)
		}
	}
}
