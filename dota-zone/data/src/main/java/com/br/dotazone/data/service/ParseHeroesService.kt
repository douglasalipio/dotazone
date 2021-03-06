package com.br.dotazone.data.service

import com.br.dotazone.data.model.HeroModel

interface ParseHeroesService {

	suspend fun parseHeroesData(heroDataString: String): List<HeroModel>
}
