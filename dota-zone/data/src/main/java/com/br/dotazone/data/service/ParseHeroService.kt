package com.br.dotazone.data.service

import com.br.dotazone.data.model.HeroAbilityModel
import com.br.dotazone.data.model.HeroModel
import com.br.dotazone.data.model.HeroSkillModel

interface ParseHeroService {

	suspend fun parseHeroesData(heroDataString: String): List<HeroModel>

	suspend fun parseHeroesSkillData(heroSkillsDataString : String): List<HeroSkillModel>

	suspend fun parseHeroesAbilitiesData(heroAbilitiesDataString : String): List<HeroAbilityModel>
}
