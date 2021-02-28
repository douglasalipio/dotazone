package com.br.dotazone.domain.heroes


import com.br.dotazone.domain.heroes.entity.HeroesData
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetHeroesDataTest {

	@Mock
	lateinit var heroesRepositoryMock: HeroesRepository
	private lateinit var getHeroesData: GetHeroesData

	@Before
	fun setUp() {
		MockitoAnnotations.initMocks(this)
		getHeroesData = GetHeroesData(heroesRepositoryMock)
	}

	@Test
	fun `should load heroes data`() {
		runBlocking {
			//given
			val params = GetHeroesData.Params("pt-br")
			given(heroesRepositoryMock.getHeroesData("pt-br")).willReturn(flowOf(fakeHeroesData))

			//when
			val actual = getHeroesData.execute(params).single()

			//then
			assertEquals(fakeHeroesData.heroesStringData, actual.heroesStringData)
			verify(heroesRepositoryMock).getHeroesData(params.language)
		}
	}
}

val fakeHeroesData = HeroesData("")
