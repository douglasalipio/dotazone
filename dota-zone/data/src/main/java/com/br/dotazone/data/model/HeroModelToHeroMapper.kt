package com.br.dotazone.data.model

import com.br.dotazone.domain.Mapper
import com.br.dotazone.domain.heroes.entity.Hero
import com.br.dotazone.domain.heroes.entity.HeroesData

class HeroModelToHeroMapper : Mapper<HeroModel, Hero> {

	override fun map(from: HeroModel): Hero =
			Hero(name = from.name,
					idString = from.idString,
					bio = from.bio,
					atk = from.atk,
					roles = from.roles)
}
