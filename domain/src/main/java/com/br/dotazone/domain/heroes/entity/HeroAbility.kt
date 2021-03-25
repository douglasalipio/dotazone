package com.br.dotazone.domain.heroes.entity

import java.util.*

data class HeroAbility(
		val dname: String,
		val u: String,
		val pa: String,
		val dac: String,
		val droles: String
) {

	var attribs: Attribs = Attribs(Str("", ""), Int("", ""), Agi("", ""), 0, Dmg(0, 0), 0.0)
}

data class Dmg(var min: kotlin.Int, var max: kotlin.Int)
data class Str(var b: String, var g: String)
data class Int(var b: String, var g: String)
data class Agi(var b: String, var g: String)

data class Attribs(
		var str: Str,
		var int: Int,
		var agi: Agi,
		var ms: kotlin.Int,
		var dmg: Dmg,
		var armor: Double
)
