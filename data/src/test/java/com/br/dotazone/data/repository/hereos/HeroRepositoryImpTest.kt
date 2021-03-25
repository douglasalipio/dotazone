package com.br.dotazone.data.repository.hereos

import com.br.dotazone.data.model.HeroAbilityModelToHeroAbility
import com.br.dotazone.data.model.HeroModelToHeroMapper
import com.br.dotazone.data.model.HeroSkillModelToHeroSkillMapper
import com.br.dotazone.data.repository.*
import com.br.dotazone.data.service.HeroesService
import com.br.dotazone.data.service.ParseHeroService
import com.br.dotazone.domain.heroes.HeroRepository
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class HeroRepositoryImpTest {

	@Mock
	lateinit var heroesServiceMock: HeroesService

	@Mock
	lateinit var parseHeroServiceMock: ParseHeroService
	private val heroModelMapper = HeroModelToHeroMapper()
	private val heroSkillModelMapper = HeroSkillModelToHeroSkillMapper()
	private val heroAbilityModelMapper = HeroAbilityModelToHeroAbility()
	private lateinit var heroRepository: HeroRepository

	@Before
	fun setUp() {
		MockitoAnnotations.initMocks(this)
		heroRepository = HeroRepositoryImp(heroesServiceMock, parseHeroServiceMock, heroModelMapper,
				heroSkillModelMapper, heroAbilityModelMapper)
	}

	@Test
	fun `should load heroes successfully`() {
		runBlocking {
			//Given
			val heroDataAsString = ""
			val heroSkillDataAsString = ""
			val heroAbilityDataAsString = ""
			val language = "pt-br"
			given(parseHeroServiceMock.parseHeroesData(heroDataAsString)).willReturn(fakeHeroModelList)
			given(parseHeroServiceMock.parseHeroesSkillData(heroSkillDataAsString)).willReturn(fakeHeroSkillModelList)
			given(parseHeroServiceMock.parseHeroesAbilitiesData(heroAbilityDataAsString)).willReturn(fakeHeroAbilityModelList)
			given(heroesServiceMock.getHeroesData(language)).willReturn(heroDataAsString)
			given(heroesServiceMock.getHeroAbilitiesData(language)).willReturn(heroAbilityDataAsString)
			given(heroesServiceMock.getHeroesSkillsData(language)).willReturn(heroSkillDataAsString)
			//When
			val heroesDataModelActual = heroRepository.getHeroes(language).single()
			val hero = heroModelMapper.map(fakeHeroModel)
			//Then
			assertEquals(fakeHeroList, heroesDataModelActual)
			verify(heroesServiceMock).getHeroesData(language)
		}
	}
}
