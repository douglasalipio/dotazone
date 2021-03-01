package com.br.dotazone.model.service

import android.app.ProgressDialog
import android.os.AsyncTask
import android.util.Log
import androidx.fragment.app.Fragment
import com.br.dotazone.DotaZoneBrain.TAG
import com.br.dotazone.DotaZoneBrain.heroes
import com.br.dotazone.R
import com.br.dotazone.domain.heroes.prov.Ability
import com.br.dotazone.domain.heroes.prov.Ability.AbilityElementy
import com.br.dotazone.domain.heroes.prov.Hero
import com.br.dotazone.domain.heroes.prov.Hero.HeroElementy
import com.br.dotazone.domain.heroes.prov.Skill
import com.br.dotazone.domain.heroes.prov.Skill.SkillElementy
import com.br.dotazone.model.util.UrlUtils.Companion.getUrlAbility
import com.br.dotazone.model.util.UrlUtils.Companion.getUrlHero
import com.br.dotazone.model.util.UrlUtils.Companion.getUrlSkill
import com.br.dotazone.view.activity.BaseActivity
import com.br.dotazone.view.fragment.DialogError.Companion.newFragmentDialog
import com.br.dotazone.view.fragment.DialogError.TypeError
import com.br.dotazone.heroes.HeroFragment
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import java.util.regex.Pattern


class HeroAsync(private val mFragment: Fragment) : AsyncTask<Void?, Void?, String?>() {
	private var mProgressDialog: ProgressDialog
	private val mRequest: RequestREST
	private val mAdapterAction: AdapterAction
	private val mHeroes: MutableList<Hero>
	private val mSkills: MutableList<Skill>
	private val mAbilities: MutableList<Ability>


	override fun doInBackground(vararg p0: Void?): String? {
		var resultHero: String? = null
		var resultSkill: String? = null
		var resultAbility: String? = null
		try {
			resultHero = mRequest.getHttpRequest(getUrlHero(mFragment.activity!!))
			resultSkill = mRequest.getHttpRequest(getUrlSkill(mFragment.activity!!))
			resultAbility = mRequest.getHttpRequest(getUrlAbility(mFragment.activity!!))
			if (heroes.isEmpty()) {
				createHero(resultHero)
				createSkill(resultSkill)
				createAbility(resultAbility)
				combineHeroToSkillsAndAttributes()
			}
		} catch (e: Exception) {
			Log.e(TAG, "Erro ao tentar consultar o arquivo de Hero.--------")
			e.printStackTrace()
			val fragmentError = newFragmentDialog(mFragment.activity!!.resources
					.getString(R.string.error_hero_connection), (mFragment.activity as BaseActivity?)!!, TypeError.ONE_OPTIONS)
			// DotaZoneBrain.mHeroes = new ArrayList<Hero>();
			fragmentError.show(mFragment.activity!!.supportFragmentManager, "Hero")
		}
		return null
	}

	private fun combineHeroToSkillsAndAttributes() {
		if (!(mHeroes.isEmpty() && mSkills.isEmpty())) {
			for (i in mHeroes.indices) {
				for (j in mSkills.indices) {
					val heroName = mHeroes[i].name
					val skillHeroName = mSkills[j].hurl
					if (heroName.equals(skillHeroName, ignoreCase = true)) {
						mHeroes[i].skills.add(mSkills[j])
						Log.i("teste", "yes______" + mHeroes[i].name + "-----" + mSkills[j].hurl)
					}
				}
				for (y in mAbilities.indices) {
					val heroName = mHeroes[i].idString.replace("[A-Z/a-z/_/-]".toRegex(), "")
					val skillHeroName = mAbilities[y].u.replace("[A-Z/a-z/_/-]".toRegex(), "")
					if (mHeroes[i].idString == mAbilities[y].idString) {
						mHeroes[i].abilites = mAbilities[y]
						break
					}
					if (mHeroes[i].name == "Anti-Mage" && mAbilities[y].u == "Anti-Mage") {
						mHeroes[i].abilites = mAbilities[y]
					}
					if (mHeroes[i].name == "Nature's_Prophet" && mAbilities[y].u == "Natures_Prophet") {
						mHeroes[i].abilites = mAbilities[y]
					}
				}
			}
			heroes = mHeroes
		}
	}

	override fun onPreExecute() {
		mProgressDialog = ProgressDialog(mFragment.activity)
		mProgressDialog.setMessage(mFragment.activity!!.getString(R.string.loading))
		mProgressDialog.show()
		mProgressDialog.setCancelable(false)
	}

	override fun onPostExecute(result: String?) {
		super.onPostExecute(result)
		mProgressDialog.dismiss()
		mAdapterAction.initList(null)
	}

	private fun createAbility(result: String?) {
		try {
			val jsonResponse = JSONObject(result)
			val mainNode = jsonResponse["herodata"] as JSONObject
			val keys: Iterator<*> = mainNode.keys()
			while (keys.hasNext()) {
				val key = keys.next() as String
				val ability = Ability()
				if (mainNode[key] is JSONObject) {
					val jsonChild = mainNode.getJSONObject(key)
					try {
						ability.idString = key
						ability.name = jsonChild.getString(AbilityElementy.DNAME.value)
						ability.u = jsonChild.getString(AbilityElementy.U.value)
						ability.pa = jsonChild.getString(AbilityElementy.PA.value)
						ability.droles = jsonChild.getString(AbilityElementy.DROLES.value)
						ability.dac = jsonChild.getString(AbilityElementy.DAC.value)
						val jsonAttribs = jsonChild.getJSONObject(AbilityElementy.ATTRIBS.value)
						val childAttribsInt = jsonAttribs.getJSONObject(AbilityElementy.INT.value)
						val intAbilityA = childAttribsInt.getString(AbilityElementy.B.value)
						val intAbilityB = childAttribsInt.getString(AbilityElementy.G.value)
						ability.inte = arrayOf(intAbilityA, intAbilityB)
						val childAttribsAgi = jsonAttribs.getJSONObject(AbilityElementy.AGI.value)
						val agiAbilityA = childAttribsAgi.getString(AbilityElementy.B.value)
						val agiAbilityB = childAttribsAgi.getString(AbilityElementy.G.value)
						ability.agi = arrayOf(agiAbilityA, agiAbilityB)
						val childAttribsStr = jsonAttribs.getJSONObject(AbilityElementy.STR.value)
						val strAbilityA = childAttribsStr.getString(AbilityElementy.B.value)
						val strAbilityB = childAttribsStr.getString(AbilityElementy.G.value)
						ability.str = arrayOf(strAbilityA, strAbilityB)
						val childAttribsDmg = jsonAttribs.getJSONObject(AbilityElementy.DMG.value)
						val dmgAbilityA = childAttribsDmg.getString(AbilityElementy.MIN.value)
						val dmgAbilityB = childAttribsDmg.getString(AbilityElementy.MAX.value)
						ability.dmg = arrayOf(dmgAbilityA, dmgAbilityB)
						ability.ms = jsonAttribs.getString(AbilityElementy.MS.value)
						ability.armor = jsonAttribs.getString(AbilityElementy.ARMOR.value)
						mAbilities.add(ability)
					} catch (e: JSONException) {
						Log.e(TAG, "No ability [" + ability.name + "  -]" + e)
						errorMessengerJSON()
					}
				}
			}
		} catch (e: JSONException) {
			// TODO Auto-generated catch block
			e.printStackTrace()
			errorMessengerJSON()
		}
	}

	private fun errorMessengerJSON() {
		val fragmentError = newFragmentDialog(mFragment.activity!!.resources.getString(R.string.error_json_hero),
				(mFragment.activity as BaseActivity?)!!, TypeError.ONE_OPTIONS)
		// DotaZoneBrain.mHeroes = new ArrayList<Hero>();
		fragmentError.show(mFragment.activity!!.supportFragmentManager, "Hero")
	}

	fun createSkill(result: String?) {
		try {
			val jsonResponse = JSONObject(result)
			val mainNode = jsonResponse["abilitydata"] as JSONObject
			val keys: Iterator<*> = mainNode.keys()
			while (keys.hasNext()) {
				val key = keys.next() as String
				val skill = Skill()
				if (mainNode[key] is JSONObject) {
					val jsonChild = mainNode.getJSONObject(key)
					try {
						skill.idString = key
						skill.affectes = jsonChild.getString(SkillElementy.AFFECTS.toString().toLowerCase())
						skill.attrib = jsonChild.getString(SkillElementy.ATTRIB.toString().toLowerCase())
						skill.cmb = jsonChild.getString(SkillElementy.CMB.toString().toLowerCase())
						skill.desc = jsonChild.getString(SkillElementy.DESC.toString().toLowerCase())
						skill.dmg = jsonChild.getString(SkillElementy.DMG.toString().toLowerCase())
						skill.setdName(jsonChild.getString(SkillElementy.DNAME.toString().toLowerCase()))
						skill.hurl = jsonChild.getString(SkillElementy.HURL.toString().toLowerCase())
						skill.lore = jsonChild.getString(SkillElementy.LORE.toString().toLowerCase())
						skill.notes = jsonChild.getString(SkillElementy.NOTES.toString().toLowerCase())
						mSkills.add(skill)
					} catch (e: JSONException) {
						errorMessengerJSON()
						Log.e(TAG, "No ability [" + skill.getdName() + "  -]" + e)
					}
				}
			}
		} catch (e: JSONException) {
			// TODO Auto-generated catch block
			e.printStackTrace()
		}
	}

	fun createHero(result: String?) {
		try {
			val jsonResponse = JSONObject(result)
			val keys: Iterator<*> = jsonResponse.keys()
			while (keys.hasNext()) {
				val hero = Hero()
				val key = keys.next() as String
				if (jsonResponse[key] is JSONObject) {
					val jsonChild = jsonResponse.getJSONObject(key)
					var rolesList: JSONArray? = null
					var rolesLList: JSONArray? = null
					try {
						hero.idString = key
						hero.atk = jsonChild.getString(HeroElementy.ATK.toString().toLowerCase())
						hero.bio = jsonChild.getString(HeroElementy.BIO.toString().toLowerCase())
						hero.name = jsonChild.getString(HeroElementy.NAME.toString().toLowerCase())
						val padrao = "\\s"
						val padrao1 = "[- /.]"
						val regPat = Pattern.compile(padrao)
						val regPat1 = Pattern.compile(padrao1)
						val matcher = regPat.matcher(hero.name)
						var nameWithUnderline = matcher.replaceAll("_")
						val matcherTrace = regPat1.matcher(nameWithUnderline)
						nameWithUnderline = matcherTrace.replaceAll("-")
						hero.name = nameWithUnderline.replace("'", "")
						try {
							rolesList = jsonChild.getJSONArray(HeroElementy.ROLES.toString().toLowerCase())
							rolesLList = jsonChild.getJSONArray(HeroElementy.ROLES_L.toString().toLowerCase())
						} catch (e: Exception) {
							// Trata os nos que não tem um componente.
							Log.e(TAG, "no ability hero[" + hero.name + "  -]")
						}
						if (rolesList != null) {
							for (i in 0 until rolesList.length()) {
								hero.roles.add(rolesList.getString(i))
								Log.i(TAG, "ability hero roles add[" + hero.name + "  -]")
							}
						}
						if (rolesLList != null) {
							for (i in 0 until rolesList!!.length()) {
								hero.rolesL.add(rolesLList.getString(i))
								Log.i(TAG, "ability hero roles_l add[" + hero.name + "  -]")
							}
						}
						mHeroes.add(hero)
					} catch (e: JSONException) {
						errorMessengerJSON()
						Log.e(TAG, "No hero [" + hero.name + "  -]" + e)
					}
				}
			}
		} catch (e: JSONException) {
			errorMessengerJSON()
			e.printStackTrace()
		}
	}

	init {
		mRequest = RequestREST()
		mProgressDialog = ProgressDialog(mFragment.activity)
		mAdapterAction = mFragment as HeroFragment
		mSkills = ArrayList()
		mHeroes = ArrayList()
		mAbilities = ArrayList()
	}
}
