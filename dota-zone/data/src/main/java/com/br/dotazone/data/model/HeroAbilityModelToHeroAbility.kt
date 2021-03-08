package com.br.dotazone.data.model

import com.br.dotazone.domain.Mapper
import com.br.dotazone.domain.heroes.entity.HeroAbility

class HeroAbilityModelToHeroAbility : Mapper<HeroAbilityModel, HeroAbility> {

	override fun map(from: HeroAbilityModel): HeroAbility =
			HeroAbility(
					dname = from.dname,
					u = from.u,
					pa = from.pa,
					dac = from.dac,
					droles = from.droles
			)
}
