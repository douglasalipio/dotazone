package com.br.dotazone.domain.heroes

import com.br.dotazone.domain.heroes.entity.HeroesData
import com.br.dotazone.domain.heroes.prov.Hero
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ParseHeroesDataInteractorTest {

	@Mock
	lateinit var heroesRepositoryMock: HeroesRepository
	private lateinit var getHeroesDataInteractor: ParseHeroesDataInteractor

	@Before
	fun setUp() {
		MockitoAnnotations.initMocks(this)
		getHeroesDataInteractor = ParseHeroesDataInteractor(heroesRepositoryMock)
	}

	@Test
	fun `should parse heroes data`() {
		runBlocking {
			//given
			val heroesDataString= "dataString"
			val params = ParseHeroesDataInteractor.Params(heroesDataString)
			given(heroesRepositoryMock.parseHeroesData(heroesDataString)).willReturn(flowOf(fakeHeroes))

			//when
			val actual = getHeroesDataInteractor.execute(params).single()

			//then
			verify(heroesRepositoryMock).parseHeroesData(heroesDataString)
		}
	}
}

val fakeHeroes = Hero()
