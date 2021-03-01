package com.br.dotazone.data.service

import com.br.dotazone.domain.heroes.entity.Heroes
import com.br.dotazone.domain.heroes.prov.Hero
import org.json.JSONObject
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroesService {

	@GET("jsfeed/heropickerdata/{l}")
	suspend fun getHeroesData(@Path("l") language: String): String

	suspend fun parseHeroesData(heroDataString: String): Hero?{

		val jsonResponse = JSONObject(heroDataString)
//		val mainNode = jsonResponse["herodata"] as JSONObject
//		val keys: Iterator<*> = mainNode.keys()
//		while (keys.hasNext()) {
//			val key = keys.next() as String
//			val ability = Ability()
//			if (mainNode[key] is JSONObject) {
//				val jsonChild = mainNode.getJSONObject(key)
//				ability.idString = key
//				ability.name = jsonChild.getString(AbilityElementy.DNAME.value)
//			}
//		}
		return null
	}
}
