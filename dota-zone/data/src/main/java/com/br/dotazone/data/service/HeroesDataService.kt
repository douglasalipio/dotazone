package com.br.dotazone.data.service

import com.br.dotazone.domain.heroes.entity.Heroes
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroesDataService {

	@GET("jsfeed/heropickerdata/{l}")
	suspend fun getHeroesData(@Path("l") language: String): String
}
