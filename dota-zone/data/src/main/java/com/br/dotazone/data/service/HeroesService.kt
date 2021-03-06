package com.br.dotazone.data.service

import retrofit2.http.GET
import retrofit2.http.Path

interface HeroesService {

	@GET("jsfeed/heropickerdata/{l}")
	suspend fun getHeroesData(@Path("l") language: String): String
}
