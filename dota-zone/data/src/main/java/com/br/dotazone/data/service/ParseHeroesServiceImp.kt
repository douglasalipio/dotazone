package com.br.dotazone.data.service

import android.util.Log
import com.br.dotazone.data.model.HeroModel
import com.br.dotazone.domain.heroes.prov.Hero
import org.json.JSONArray
import org.json.JSONObject

class ParseHeroesServiceImp : ParseHeroesService {
	override suspend fun parseHeroesData(heroDataString: String): List<HeroModel> {
		val jsonResponse = JSONObject(heroDataString)
		val heroes = mutableListOf<HeroModel>()
		while (jsonResponse.keys().hasNext()) {
			val key = jsonResponse.keys().next()
			val jsonChild = jsonResponse.getJSONObject(key)
			val heroModel = HeroModel(idString = key,
					name = jsonChild.getString(HeroModel.Elements.Name.toString()),
					atk = jsonChild.getString(HeroModel.Elements.Atk.toString()),
					bio = jsonChild.getString(HeroModel.Elements.Bio.toString()),
					roles = jsonChild.getJSONArray(HeroModel.Elements.Roles.toString()).toList()
			)
			heroes.add(heroModel)
		}
		return heroes
	}

	private fun JSONArray.toList(): List<String> {
		val roles = mutableListOf<String>()
		for (i in 0 until this.length()) {
			roles.add(this.getString(i))
		}
		return roles
	}
}
