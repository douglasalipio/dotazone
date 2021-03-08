package com.br.dotazone.domain.heroes


import com.br.dotazone.domain.heroes.entity.Hero
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class GetHeroDataInteractorTest {

	@Mock
	lateinit var heroRepositoryMock: HeroRepository
	private lateinit var getHeroDataInteractor: GetHeroDataInteractor

	@Before
	fun setUp() {
		MockitoAnnotations.initMocks(this)
		getHeroDataInteractor = GetHeroDataInteractor(heroRepositoryMock)
	}

	@Test
	fun `should load heroes data`() {
		runBlocking {
			//given
			val params = GetHeroDataInteractor.Params("pt-br")
			given(heroRepositoryMock.getHeroes("pt-br")).willReturn(flowOf(fakeHeroList))

			//when
			val actual = getHeroDataInteractor.execute(params).single()

			//then
			assertEquals(fakeHeroList, actual)
			verify(heroRepositoryMock).getHeroes(params.language)
		}
	}
}
val fakeHeroList = listOf(Hero("","","","", listOf()))
