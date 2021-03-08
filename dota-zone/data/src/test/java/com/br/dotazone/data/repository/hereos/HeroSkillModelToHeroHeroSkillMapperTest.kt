package com.br.dotazone.data.repository.hereos

import com.br.dotazone.data.model.HeroSkillModelToHeroSkillMapper
import com.br.dotazone.data.repository.fakeSkillModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HeroSkillModelToHeroHeroSkillMapperTest {

	private lateinit var mapperHero: HeroSkillModelToHeroSkillMapper

	@Before
	fun setUp() {
		mapperHero = HeroSkillModelToHeroSkillMapper()
	}

	@Test
	fun `map from skills model to skills`() {
		val expectedContent = mapperHero.map(fakeSkillModel)
		assertEquals(expectedContent.affects, fakeSkillModel.affects)
		assertEquals(expectedContent.attrib, fakeSkillModel.attrib)
		assertEquals(expectedContent.cmb, fakeSkillModel.cmb)
		assertEquals(expectedContent.dName, fakeSkillModel.dname)
		assertEquals(expectedContent.desc, fakeSkillModel.desc)
		assertEquals(expectedContent.dmg, fakeSkillModel.dmg)
		assertEquals(expectedContent.hurl, fakeSkillModel.hurl)
		assertEquals(expectedContent.lore, fakeSkillModel.lore)
		assertEquals(expectedContent.notes, fakeSkillModel.notes)
	}
}
