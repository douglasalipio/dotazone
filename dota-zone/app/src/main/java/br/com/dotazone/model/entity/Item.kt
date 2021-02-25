package br.com.dotazone.model.entity

import android.widget.ImageView

class Item {
	var id: Int? = null
	var imageName: String = ""
	var name: String = ""
	var qual: String = ""
	var cost: Number? = null
	var description: String = ""
	var notes: String = ""
	var attribute: String = ""
	var mc: String = ""
	var cloudown: String = ""
	var lore: String = ""
	var components: MutableList<String> = mutableListOf()
	var isCreated = false
	var imageItem: ImageView? = null
	var itemAttrib: ItemAtrrib? = null
		private set

	constructor() {}
	constructor(id: Int?, imageName: String, name: String, qual: String, cost: Number, description: String, notes: String,
				attribute: String, mc: String, cloudown: String, lore: String, components: MutableList <String>, created: Boolean,
				imageItem: ImageView?, itemAttrib: ItemAtrrib?) {
		this.id = id
		this.imageName = imageName
		this.name = name
		this.qual = qual
		this.cost = cost
		this.description = description
		this.notes = notes
		this.attribute = attribute
		this.mc = mc
		this.cloudown = cloudown
		this.lore = lore
		this.components = components
		isCreated = created
		this.imageItem = imageItem
		this.itemAttrib = itemAttrib
	}

	fun setmItemAttrib(mItemAttrib: ItemAtrrib?) {
		itemAttrib = mItemAttrib
	}

	enum class ItemType {
		BASIC_SHOP, SECRET_SHOP
	}

	enum class ItemElementy {
		ID, DESC, MC, CREATED, DNAME, IMG, COMPONENTS, QUAL, NOTES, ATTRIB, COST, LORE, CD
	}

	enum class ItemIdentify(val itemIdentify: String) {
		BLINK("blink"), BLADES_OF_ATTACK("blades_of_attack"), BROADSWORD("broadsword"), CHAINMAIL("chainmail"), CLAYMORE(
				"claymore"),
		HELM_OF_IRON_WILL("helm_of_iron_will"), JAVELIN("javelin"), MITHRIL_HAMMER("mithril_hammer"), PLATEMAIL(
				"platemail"),
		QUARTERSTAFF("quarterstaff"), QUELLING_BLADE("quelling_blade"), RING_OF_PROTECTION(
				"ring_of_protection"),
		STOUT_SHIELD("stout_shield"), GAUNTLETS("gauntlets"), SLIPPERS("slippers"), MANTLE(
				"mantle"),
		BRANCHES("branches"), BELT_OF_STRENGTH("belt_of_strength"), BOOTS_OF_ELVES("boots_of_elves"), ROBE(
				"robe"),
		CIRCLET("circlet"), OGRE_AXE("ogre_axe"), BLADE_OF_ALACRITY("blade_of_alacrity"), STAFF_OF_WIZARDRY(
				"staff_of_wizardry"),
		ULTIMATE_ORB("ultimate_orb"), GLOVES("gloves"), LIFESTEAL("lifesteal"), RING_OF_REGEN(
				"ring_of_regen"),
		SOBI_MASK("sobi_mask"), POWER_TREADS("power_treads"), HAND_OF_MIDAS("hand_of_midas"), OBLIVION_STAFF(
				"oblivion_staff"),
		PERS("pers"), POOR_MANS_SHIELD("poor_mans_shield"), BRACER("bracer"), BOOTS("boots"), WRAITH_BAND(
				"wraith_band"),
		NULL_TALISMAN("null_talisman"), MEKANSM("mekansm"), VLADMIR("vladmir"), BUCKLER("buckler"), FLYING_COURIER(
				"flying_courier"),
		GEM("gem"), RING_OF_BASILIUS("ring_of_basilius"), PIPE("pipe"), URN_OF_SHADOWS(
				"urn_of_shadows"),
		HEADDRESS("headdress"), ORCHID("orchid"), SHEEPSTICK("sheepstick"), CLOAK("cloak"), DAGON_4(
				"dagon_4"),
		DAGON_3("dagon_3"), DAGON_2("dagon_2"), DAGON("dagon"), FORCE_STAFF("force_staff"), CYCLONE("cyclone"), TALISMAN_OF_EVASION(
				"talisman_of_evasion"),
		DAGON_5("dagon_5"), NECRONOMICON("necronomicon"), NECRONOMICON_2("necronomicon_2"), NECRONOMICON_3(
				"necronomicon_3"),
		REFRESHER("refresher"), ULTIMATE_SCEPTER("ultimate_scepter"), CHEESE("cheese"), ASSAULT(
				"assault"),
		HEART("heart"), BLACK_KING_BAR("black_king_bar"), AEGIS("aegis"), SHIVAS_GUARD("shivas_guard"), BLOODSTONE(
				"bloodstone"),
		MAGIC_STICK("magic_stick"), RAPIER("rapier"), HOOD_OF_DEFIANCE("hood_of_defiance"), SOUL_BOOSTER(
				"soul_booster"),
		BLADE_MAIL("blade_mail"), VANGUARD("vanguard"), SPHERE("sphere"), MAGIC_WAND("magic_wand"), BFURY(
				"bfury"),
		BASHER("basher"), GREATER_CRIT("greater_crit"), BUTTERFLY("butterfly"), RADIANCE("radiance"), MONKEY_KING_BAR(
				"monkey_king_bar"),
		GHOST("ghost"), SATANIC("satanic"), SANGE_AND_YASHA("sange_and_yasha"), INVIS_SWORD(
				"invis_sword"),
		ARMLET("armlet"), LESSER_CRIT("lesser_crit"), MANTA("manta"), CLARITY("clarity"), DESOLATOR(
				"desolator"),
		MAELSTROM("maelstrom"), HELM_OF_THE_DOMINATOR("helm_of_the_dominator"), SANGE("sange"), SKADI(
				"skadi"),
		MJOLLNIR("mjollnir"), FLASK("flask"), SOUL_RING("soul_ring"), ETHEREAL_BLADE("ethereal_blade"), DIFFUSAL_BLADE_2(
				"diffusal_blade_2"),
		DIFFUSAL_BLADE("diffusal_blade"), MASK_OF_MADNESS("mask_of_madness"), YASHA("yasha"), DUST(
				"dust"),
		VEIL_OF_DISCORD("veil_of_discord"), SMOKE_OF_DECEIT("smoke_of_deceit"), MEDALLION_OF_COURAGE(
				"medallion_of_courage"),
		ANCIENT_JANGGO("ancient_janggo"), ORB_OF_VENOM("orb_of_venom"), ARCANE_BOOTS(
				"arcane_boots"),
		BOTTLE("bottle"), SHADOW_AMULET("shadow_amulet"), TRANQUIL_BOOTS("tranquil_boots"), RING_OF_AQUILA(
				"ring_of_aquila"),
		HEAVENS_HALBERD("heavens_halberd"), ABYSSAL_BLADE("abyssal_blade"), ROD_OF_ATOS("rod_of_atos"), WARD_OBSERVER(
				"ward_observer"),
		MYSTERY_VACUUM("mystery_vacuum"), MYSTERY_TOSS("mystery_toss"), MYSTERY_MISSILE(
				"mystery_missile"),
		MYSTERY_ARROW("mystery_arrow"), MYSTERY_HOOK("mystery_hook"), HALLOWEEN_CANDY_CORN(
				"halloween_candy_corn"),
		WARD_SENTRY("ward_sentry"), HALLOWEEN_RAPIER("halloween_rapier"), GREEVIL_WHISTLE(
				"greevil_whistle"),
		GREEVIL_WHISTLE_TOGGLE("greevil_whistle_toggle"), WINTER_SKATES("winter_skates"), WINTER_STOCKING(
				"winter_stocking"),
		PRESENT("present"), TANGO("tango"), WINTER_MUSHROOM("winter_mushroom"), WINTER_KRINGLE(
				"winter_kringle"),
		WINTER_HAM("winter_ham"), WINTER_COCO("winter_coco"), WINTER_COOKIE("winter_cookie"), WINTER_CAKE(
				"winter_cake"),
		TANGO_SINGLE("tango_single"), WINTER_GREEVIL_TREAT("winter_greevil_treat"), WINTER_GREEVIL_CHEWY(
				"winter_greevil_chewy"),
		WINTER_GREEVIL_GARBAGE("winter_greevil_garbage"), COURIER("courier"), TPSCROLL(
				"tpscroll"),
		TRAVEL_BOOTS("travel_boots"), PHASE_BOOTS("phase_boots"), DEMON_EDGE("demon_edge"), EAGLE("eagle"), REAVER(
				"reaver"),
		RELIC("relic"), HYPERSTONE("hyperstone"), RING_OF_HEALTH("ring_of_health"), VOID_STONE("void_stone"), MYSTIC_STAFF(
				"mystic_staff"),
		ENERGY_BOOSTER("energy_booster"), POINT_BOOSTER("point_booster"), VITALITY_BOOSTER(
				"vitality_booster");

	}
}
