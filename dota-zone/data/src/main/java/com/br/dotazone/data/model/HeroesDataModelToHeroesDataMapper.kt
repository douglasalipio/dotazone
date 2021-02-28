package com.br.dotazone.data.model

import com.br.dotazone.domain.Mapper
import com.br.dotazone.domain.heroes.entity.Abaddon
import com.br.dotazone.domain.heroes.entity.Heroes
import com.br.dotazone.domain.heroes.entity.HeroesData

class HeroesDataModelToHeroesDataMapper : Mapper<String, HeroesData> {

	override fun map(from: String) = HeroesData(from)
}
