package com.br.dotazone.data.repository

import com.br.dotazone.data.model.*
import com.br.dotazone.data.service.HeroesService
import com.br.dotazone.data.service.ParseHeroService
import com.br.dotazone.domain.heroes.HeroRepository
import com.br.dotazone.domain.heroes.entity.Hero
import com.br.dotazone.domain.heroes.entity.HeroAbility
import com.br.dotazone.domain.heroes.entity.HeroSkill
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class HeroRepositoryImp(
		private val heroesService: HeroesService,
		private val parseHeroService: ParseHeroService,
		private val heroMapper: HeroModelToHeroMapper,
		private val heroSkillMapper: HeroSkillModelToHeroSkillMapper,
		private val heroAbilityMapper: HeroAbilityModelToHeroAbility
) : HeroRepository {

	override suspend fun getHeroes(language: String): Flow<List<Hero>> {
		return flow {

			//get heroes data
			val heroesData = heroesService.getHeroesData(language)
			val heroModelList = parseHeroService.parseHeroesData(heroesData)
			val heroList = heroModelList.map { heroMapper.map(it) }

			//get heroes skill data
			val heroesSkillData = heroesService.getHeroesSkillsData(language)
			val heroSkillModelList = parseHeroService.parseHeroesSkillData(heroesSkillData)
			val heroSkillList = heroSkillModelList.map { heroSkillMapper.map(it) }

			//get heroes abilities data
			val heroesAbilitiesData = heroesService.getHeroAbilitiesData(language)
			val heroAbilityModelList = parseHeroService.parseHeroesAbilitiesData(heroesAbilitiesData)
			val heroAbilityList = heroAbilityModelList.map { heroAbilityMapper.map(it) }

			emit(combineHeroAttributes(heroSkillList,heroAbilityList,heroList))
		}.flowOn(Dispatchers.IO)
	}

	private fun combineHeroAttributes(
			heroSkillList: List<HeroSkill>,
			heroAbilityList: List<HeroAbility>,
			heroList: List<Hero>
	): List<Hero> {

		heroList.forEach { heroModel ->
			heroModel.heroSkills = heroSkillList.filter { it.hurl.equals(heroModel.idString, ignoreCase = true) }.toMutableList()
			heroModel.heroAbility = heroAbilityList.last { it.u.equals(heroModel.idString, ignoreCase = true) }
		}
		return heroList
	}
}
