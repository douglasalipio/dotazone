package com.br.dotazone.data.model

import com.br.dotazone.domain.Mapper
import com.br.dotazone.domain.heroes.entity.Abaddon
import com.br.dotazone.domain.heroes.entity.Heroes
import com.br.dotazone.domain.heroes.entity.HeroesData

class HeroesDataModelToHeroesDataMapper : Mapper<HeroesDataModel, HeroesData> {

	override fun map(from: HeroesDataModel) = HeroesData(from.heroesData)
}
