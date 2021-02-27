package com.br.dotazone.data.repository

import com.br.dotazone.data.model.HeroesDataModel
import com.br.dotazone.data.service.HeroesDataService
import com.br.dotazone.domain.heroes.HeroesRepository
import com.br.dotazone.domain.heroes.entity.HeroesData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class HeroesDataRepositoryImpTest {

	@Mock
	lateinit var heroesDataServiceMock: HeroesDataService
	private lateinit var heroesRepository: HeroesRepository

	@Before
	fun setUp() {
		MockitoAnnotations.initMocks(this)
		heroesRepository = HeroesDataRepositoryImp(heroesDataServiceMock)
	}

	@Test
	fun `should load heroes successfully`() {
		runBlocking {
			//Given
			val heroesDataAsString = anyString()
			val language = "pt-br"
			given(heroesDataServiceMock.getHeroesData(language)).willReturn(heroesDataAsString)
			//When
			val actual = heroesRepository.getHeroesData(language).single()
			//Then
			assertEquals(heroesDataAsString, actual)
			verify(heroesDataServiceMock).getHeroesData(language)
		}
	}
}

val fakeHeroesDataModel = HeroesDataModel("")
val fakeHeroesData = HeroesData("")
