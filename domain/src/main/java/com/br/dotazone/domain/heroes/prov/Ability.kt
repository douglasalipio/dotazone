package com.br.dotazone.domain.heroes.prov

class Ability {
	/**
	 * Hero's display name *
	 */
	var name: String = ""

	/**
	 * Hero token *
	 */
	var u: String = ""

	/**
	 * primery attribute *
	 */
	var pa: String = ""

	/**
	 * attributs strange | first param to list, b and second g *
	 */
	var str: Array<String> = arrayOf()

	/**
	 * attributs inteligent | first param to list, b and second g *
	 */
	var inte: Array<String> = arrayOf()

	/**
	 * attributs agility | first param to list, b and second g *
	 */
	var agi: Array<String> = arrayOf()

	/**
	 * Move speed. *
	 */
	var ms: String = ""

	/**
	 * dageme | first param, mim and seconde param, max *
	 */
	var dmg: Array<String> = arrayOf()

	/**
	 * Starting armour. *
	 */
	var armor: String = ""

	/**
	 * Localized string for attack type, Melee or Ranged. *
	 */
	var dac: String = ""

	/**
	 * Display string for roles. *
	 */
	var droles: String = ""

	/**
	 * id principal node in ability json *
	 */
	var idString: String = ""

	enum class AbilityElementy(val value: String) {
		DNAME("dname"), U("u"), PA("pa"), ATTRIBS("attribs"), STR("str"), B("b"), G("g"), INT("int"), AGI("agi"), MS("ms"), DMG(
				"dmg"),
		MIN("min"), MAX("max"), ARMOR("armor"), DAC("dac"), DROLES("droles");

	}
}
