package com.br.dotazone.data.model

import com.br.dotazone.domain.Mapper
import com.br.dotazone.domain.heroes.entity.HeroSkill

class HeroSkillModelToHeroSkillMapper : Mapper<HeroSkillModel, HeroSkill> {

	override fun map(from: HeroSkillModel): HeroSkill =
			HeroSkill(
					dName = from.dname,
					affects = from.affects,
					desc = from.desc,
					notes = from.notes,
					dmg = from.dmg,
					attrib = from.attrib,
					cmb = from.cmb,
					lore = from.lore,
					hurl = from.hurl
			)
}
