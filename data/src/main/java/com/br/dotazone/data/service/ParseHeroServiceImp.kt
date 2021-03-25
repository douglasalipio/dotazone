package com.br.dotazone.data.service

import com.br.dotazone.data.model.HeroAbilityModel
import com.br.dotazone.data.model.HeroModel
import com.br.dotazone.data.model.HeroSkillModel
import com.br.dotazone.domain.heroes.prov.Ability
import org.json.JSONArray
import org.json.JSONObject
import timber.log.Timber

class ParseHeroServiceImp : ParseHeroService {
	override suspend fun parseHeroesData(heroDataString: String): List<HeroModel> {

		val jsonResponse = JSONObject(heroDataString)
		val heroes = mutableListOf<HeroModel>()
		val keys: Iterator<*> = jsonResponse.keys()

		while (keys.hasNext()) {
			val key = keys.next() as String
			val jsonChild = jsonResponse.getJSONObject(key)
			val heroModel = HeroModel(idString = key,
					name = jsonChild.getString(HeroModel.Elements.Name.toString()),
					atk = jsonChild.getString(HeroModel.Elements.Atk.toString()),
					bio = jsonChild.getString(HeroModel.Elements.Bio.toString()),
					roles = jsonChild.getJSONArray(HeroModel.Elements.Roles.toString()).toList()
			)
			heroes.add(heroModel)
			Timber.d("parse hero -  $heroModel")
		}
		return heroes
	}

	override suspend fun parseHeroesSkillData(heroSkillsDataString: String): List<HeroSkillModel> {

		val jsonResponse = JSONObject(heroSkillsDataString)
		val mainNode = jsonResponse["abilitydata"] as JSONObject
		val keys: Iterator<*> = mainNode.keys()
		val heroesSkillsModel = mutableListOf<HeroSkillModel>()

		while (keys.hasNext()) {
			val key = keys.next() as String
			if (mainNode[key] is JSONObject) {
				val jsonChild = mainNode.getJSONObject(key)
				val heroSkillModel = HeroSkillModel(
						dname = jsonChild.getString(HeroSkillModel.Elements.Dname.toString()),
						affects = jsonChild.getString(HeroSkillModel.Elements.Affects.toString()),
						desc = jsonChild.getString(HeroSkillModel.Elements.Desc.toString()),
						notes = jsonChild.getString(HeroSkillModel.Elements.Notes.toString()),
						dmg = jsonChild.getString(HeroSkillModel.Elements.Dmg.toString()),
						attrib = jsonChild.getString(HeroSkillModel.Elements.Attrib.toString()),
						cmb = jsonChild.getString(HeroSkillModel.Elements.Cmb.toString()),
						lore = jsonChild.getString(HeroSkillModel.Elements.Lore.toString()),
						hurl = jsonChild.getString(HeroSkillModel.Elements.Hurl.toString()),
				)
				heroesSkillsModel.add(heroSkillModel)
			}
		}
		heroesSkillsModel.sortWith(compareBy{it.hurl})
		return heroesSkillsModel
	}

	override suspend fun parseHeroesAbilitiesData(heroAbilitiesDataString: String): List<HeroAbilityModel> {

		val jsonResponse = JSONObject(heroAbilitiesDataString)
		val mainNode = jsonResponse["herodata"] as JSONObject
		val keys: Iterator<*> = mainNode.keys()
		val heroesAbilitiesModel = mutableListOf<HeroAbilityModel>()

		while (keys.hasNext()) {
			val key = keys.next() as String
			if (mainNode[key] is JSONObject) {
				val jsonChild = mainNode.getJSONObject(key)
				val heroAbilityModel = HeroAbilityModel(
						dname = jsonChild.getString(HeroAbilityModel.Elements.Dname.toString()),
						u = jsonChild.getString(HeroAbilityModel.Elements.U.toString()),
						pa = jsonChild.getString(HeroAbilityModel.Elements.Pa.toString()),
						dac = jsonChild.getString(HeroAbilityModel.Elements.Dac.toString()),
						droles = jsonChild.getString(HeroAbilityModel.Elements.Droles.toString())
				)
				val jsonAttribs = jsonChild.getJSONObject(HeroAbilityModel.Elements.Attribs.toString())
				val childAttribsInt = jsonAttribs.getJSONObject(HeroAbilityModel.Elements.Int.toString())
				heroAbilityModel.attribs.int.b = childAttribsInt.getString(HeroAbilityModel.Elements.B.toString())
				heroAbilityModel.attribs.int.g = childAttribsInt.getString(HeroAbilityModel.Elements.G.toString())

				val childAttribsAgi = jsonAttribs.getJSONObject(HeroAbilityModel.Elements.Agi.toString())
				heroAbilityModel.attribs.agi.b = childAttribsAgi.getString(HeroAbilityModel.Elements.B.toString())
				heroAbilityModel.attribs.agi.g = childAttribsAgi.getString(HeroAbilityModel.Elements.G.toString())

				val childAttribsStr = jsonAttribs.getJSONObject(HeroAbilityModel.Elements.Str.toString())
				heroAbilityModel.attribs.str.b = childAttribsStr.getString(HeroAbilityModel.Elements.B.toString())
				heroAbilityModel.attribs.str.g = childAttribsStr.getString(HeroAbilityModel.Elements.G.toString())

				val childAttribsDmg = jsonAttribs.getJSONObject(HeroAbilityModel.Elements.Dmg.toString())
				heroAbilityModel.attribs.dmg.min = childAttribsDmg.getInt(HeroAbilityModel.Elements.Min.toString())
				heroAbilityModel.attribs.dmg.max = childAttribsDmg.getInt(HeroAbilityModel.Elements.Max.toString())

				heroAbilityModel.attribs.ms = jsonAttribs.getInt(HeroAbilityModel.Elements.Ms.toString())
				heroAbilityModel.attribs.armor = jsonAttribs.getDouble(HeroAbilityModel.Elements.Armor.toString())
				heroesAbilitiesModel.add(heroAbilityModel)
			}
		}
		return heroesAbilitiesModel
	}

	private fun JSONArray.toList(): List<String> {
		val roles = mutableListOf<String>()
		for (i in 0 until this.length()) {
			roles.add(this.getString(i))
		}
		return roles
	}
}
