package com.br.dotazone.data.repository

import com.br.dotazone.data.model.HeroAbilityModel
import com.br.dotazone.data.model.HeroModel
import com.br.dotazone.data.model.HeroSkillModel
import com.br.dotazone.domain.heroes.entity.Hero
import com.br.dotazone.domain.heroes.entity.HeroSkill


val fakeHeroModel = HeroModel("", "", "", "", listOf())

val fakeHeroModelList = listOf(HeroModel("", "", "", "", listOf()))

val fakeHeroSkillModelList = listOf( HeroSkillModel("", "", "", "", "", "", "", "", ""))

val fakeHeroAbilityModelList = listOf(HeroAbilityModel("","","","",""))

val fakeHeroList = listOf(Hero("","","","", listOf()))

val fakeSkillModel = HeroSkillModel("", "", "", "", "", "", "", "", "")
