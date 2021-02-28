package com.br.dotazone.data.repository.hereos

import com.br.dotazone.data.model.HeroesDataModelToHeroesDataMapper
import com.br.dotazone.domain.heroes.entity.HeroesData
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HeroesDataModelToHeroesDataMapperTest {

	private lateinit var mapper: HeroesDataModelToHeroesDataMapper

	@Before
	fun setUp() {
		mapper = HeroesDataModelToHeroesDataMapper()
	}

	@Test
	fun `map from heroes data model to heroes data`() {
		//given
		val heroesDataAsString = " "
		val heroesDataMock = HeroesData(heroesDataAsString)
		//when
		val expectedContent = mapper.map(heroesDataAsString)
		//then
		assertEquals(expectedContent.heroesStringData, heroesDataMock.heroesStringData)

	}
}
