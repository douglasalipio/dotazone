package com.br.dotazone.data.service

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HeroesService {

	@GET("jsfeed/heropickerdata/{l}")
	suspend fun getHeroesData(@Path("l") language: String): String

	@GET("jsfeed/abilitydata/{l}")
	suspend fun getHeroesSkillsData(@Path("l") language: String): String

	@GET("jsfeed/heropediadata/{l}")
	suspend fun getHeroAbilitiesData(
			@Path("l") language: String,
			@Query("feeds") feeds: String ="herodata"
	): String
}
