package com.br.dotazone.domain.heroes.entity

data class Heroes(
		val antimage: Antimage,
		val axe: Axe,
		val bane: Bane,
		val bloodseeker: Bloodseeker,
		val crystal_maiden: Crystal_maiden,
		val drow_ranger: Drow_ranger,
		val earthshaker: Earthshaker,
		val juggernaut: Juggernaut,
		val mirana: Mirana,
		val nevermore: Nevermore,
		val morphling: Morphling,
		val phantom_lancer: Phantom_lancer,
		val puck: Puck,
		val pudge: Pudge,
		val razor: Razor,
		val sand_king: Sand_king,
		val storm_spirit: Storm_spirit,
		val sven: Sven,
		val tiny: Tiny,
		val vengefulspirit: Vengefulspirit,
		val windrunner: Windrunner,
		val zuus: Zuus,
		val kunkka: Kunkka,
		val lina: Lina,
		val lich: Lich,
		val lion: Lion,
		val shadow_shaman: Shadow_shaman,
		val slardar: Slardar,
		val tidehunter: Tidehunter,
		val witch_doctor: Witch_doctor,
		val riki: Riki?,
		val enigma: Enigma,
		val tinker: Tinker,
		val sniper: Sniper,
		val necrolyte: Necrolyte,
		val warlock: Warlock,
		val beastmaster: Beastmaster,
		val queenofpain: Queenofpain,
		val venomancer: Venomancer,
		val faceless_void: Faceless_void,
		val skeleton_king: Skeleton_king,
		val death_prophet: Death_prophet,
		val phantom_assassin: Phantom_assassin,
		val pugna: Pugna,
		val templar_assassin: Templar_assassin,
		val viper: Viper,
		val luna: Luna,
		val dragon_knight: Dragon_knight,
		val dazzle: Dazzle,
		val rattletrap: Rattletrap,
		val leshrac: Leshrac,
		val furion: Furion,
		val life_stealer: Life_stealer,
		val dark_seer: Dark_seer,
		val clinkz: Clinkz,
		val omniknight: Omniknight,
		val enchantress: Enchantress,
		val huskar: Huskar,
		val night_stalker: Night_stalker,
		val broodmother: Broodmother,
		val bounty_hunter: Bounty_hunter,
		val weaver: Weaver,
		val jakiro: Jakiro,
		val batrider: Batrider,
		val chen: Chen,
		val spectre: Spectre,
		val doom_bringer: Doom_bringer,
		val ancient_apparition: Ancient_apparition,
		val ursa: Ursa,
		val spirit_breaker: Spirit_breaker,
		val gyrocopter: Gyrocopter,
		val alchemist: Alchemist,
		val invoker: Invoker,
		val silencer: Silencer,
		val obsidian_destroyer: Obsidian_destroyer,
		val lycan: Lycan,
		val brewmaster: Brewmaster,
		val shadow_demon: Shadow_demon,
		val lone_druid: Lone_druid,
		val chaos_knight: Chaos_knight,
		val meepo: Meepo,
		val treant: Treant,
		val ogre_magi: Ogre_magi,
		val undying: Undying,
		val rubick: Rubick,
		val disruptor: Disruptor,
		val nyx_assassin: Nyx_assassin,
		val naga_siren: Naga_siren,
		val keeper_of_the_light: Keeper_of_the_light,
		val wisp: Wisp,
		val visage: Visage,
		val slark: Slark,
		val medusa: Medusa,
		val troll_warlord: Troll_warlord,
		val centaur: Centaur,
		val magnataur: Magnataur,
		val shredder: Shredder,
		val bristleback: Bristleback,
		val tusk: Tusk,
		val skywrath_mage: Skywrath_mage,
		val abaddon: Abaddon,
		val elder_titan: Elder_titan,
		val legion_commander: Legion_commander,
		val ember_spirit: Ember_spirit,
		val earth_spirit: Earth_spirit,
		val terrorblade: Terrorblade,
		val phoenix: Phoenix,
		val oracle: Oracle,
		val techies: Techies,
		val winter_wyvern: Winter_wyvern,
		val arc_warden: Arc_warden,
		val abyssal_underlord: Abyssal_underlord,
		val monkey_king: Monkey_king,
		val pangolier: Pangolier,
		val dark_willow: Dark_willow,
		val grimstroke: Grimstroke,
		val mars: Mars,
		val void_spirit: Void_spirit,
		val snapfire: Snapfire,
		val hoodwink: Hoodwink

)

data class Hoodwink(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Snapfire(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Void_spirit(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Mars(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Grimstroke(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Dark_willow(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Pangolier(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Monkey_king(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Abyssal_underlord(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Arc_warden(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Winter_wyvern(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Techies(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Oracle(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Phoenix(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Terrorblade(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Earth_spirit(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Ember_spirit(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Legion_commander(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Elder_titan(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Abaddon(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Skywrath_mage(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Tusk(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Bristleback(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Shredder(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Magnataur(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Centaur(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Troll_warlord(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Medusa(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Slark(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Visage(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Wisp(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Keeper_of_the_light(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Naga_siren(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Nyx_assassin(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Disruptor(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Rubick(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Undying(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Ogre_magi(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Treant(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Meepo(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Chaos_knight(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Lone_druid(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Shadow_demon(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Brewmaster(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Lycan(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Obsidian_destroyer(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Silencer(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Invoker(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Alchemist(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Gyrocopter(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Spirit_breaker(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Ursa(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Ancient_apparition(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Doom_bringer(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Spectre(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Chen(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Batrider(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Jakiro(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Weaver(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Bounty_hunter(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Broodmother(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Night_stalker(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Huskar(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Enchantress(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Omniknight(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Clinkz(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Dark_seer(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Life_stealer(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Furion(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Leshrac(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Rattletrap(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Dazzle(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Dragon_knight(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Luna(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Viper(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Templar_assassin(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Pugna(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Phantom_assassin(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Death_prophet(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Skeleton_king(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Faceless_void(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Venomancer(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Queenofpain(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Beastmaster(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Warlock(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Necrolyte(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Sniper(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Tinker(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Enigma(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Riki(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Witch_doctor(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Tidehunter(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Slardar(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Shadow_shaman(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Lion(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Lich(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Lina(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Kunkka(
		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>
)

data class Zuus(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Windrunner(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Vengefulspirit(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Tiny(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Sven(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Storm_spirit(

		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Sand_king(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Razor(


		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Pudge(
		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Puck(
		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>
)

data class Phantom_lancer(
		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>
)

data class Morphling(
		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>,
)

data class Nevermore(
		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>

)

data class Mirana(
		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>
)

data class Juggernaut(val name: String,
					  val bio: String,
					  val atk: String,
					  val atk_l: String,
					  val roles: List<String>,
					  val roles_l: List<String>
)

data class Earthshaker(
		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>
)

data class Drow_ranger(
		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>
)

data class Crystal_maiden(
		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>
)

data class Bloodseeker(
		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>
)

data class Bane(
		val name: String,
		val bio: String,
		val atk: String,
		val atk_l: String,
		val roles: List<String>,
		val roles_l: List<String>)

data class Axe(val name: String,
			   val bio: String,
			   val atk: String,
			   val atk_l: String,
			   val roles: List<String>,
			   val roles_l: List<String>)

data class Antimage(val name: String,
					val bio: String,
					val atk: String,
					val atk_l: String,
					val roles: List<String>,
					val roles_l: List<String>)
