package com.br.dotazone.view.activity

import android.app.Dialog
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.viewpager.widget.ViewPager
import com.br.dotazone.DotaZoneBrain.hero
import com.br.dotazone.DotaZoneBrain.isPremium
import com.br.dotazone.R
import com.br.dotazone.databinding.BuildHeroViewBinding
import com.br.dotazone.domain.heroes.prov.Item
import com.br.dotazone.domain.heroes.prov.ItemAtrrib
import com.br.dotazone.model.listeners.BuildHeroAction
import com.br.dotazone.model.util.UrlUtils
import com.br.dotazone.model.util.UtilActivity
import com.br.dotazone.view.adapter.ItemAdapter
import com.com.dotazone.DotazoneMenu
import com.google.android.gms.analytics.GoogleAnalytics
import com.google.android.material.tabs.TabLayout
import kotlin.math.abs


class BuildHeroActivity : BaseActivity(), BuildHeroAction {

	var mMenu: DotazoneMenu? = null
	private var mBusySlot1 = false
	private var mBusySlot2 = false
	private var mBusySlot3 = false
	private var mBusySlot4 = false
	private var mBusySlot5 = false
	private var mBusySlot6 = false
	private var mItem1: Item? = null
	private var mItem2: Item? = null
	private var mItem3: Item? = null
	private var mItem4: Item? = null
	private var mItem5: Item? = null
	private var mItem6: Item? = null
	private val mHero = hero

	private var mMainBoot: Item? = null
	private var mIsBoot = false

	private lateinit var binding: BuildHeroViewBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = BuildHeroViewBinding.inflate(layoutInflater)
		setContentView(binding.root)
		initComponents()
		binding.heroAttributeView.heroTextAttributeAgi.text = mHero?.abilites?.agi?.get(0) ?:""
		binding.heroAttributeView.heroTextAttributeInt.text = mHero?.abilites?.inte?.get(0) ?: ""
		binding.heroAttributeView.heroTextAttributeStr.text = mHero?.abilites?.str?.get(0) ?: ""
		binding.heroAttributeView.heroTextAttributeAtk.text = mHero?.abilites?.dmg?.get(0) ?: ""
		binding.heroAttributeView.heroTextAttributeMove.text = mHero?.abilites?.ms ?: ""
		binding.heroAttributeView.heroTextAttributeArmor.text = mHero?.abilites?.armor ?:""
		val idImage = resources.getIdentifier((mHero?.idString ?: "") + "_avatar", "drawable", packageName)
		binding.buildHeroCircleImage.setImageResource(idImage)
		Toast.makeText(this, getString(R.string.error_build_alert), Toast.LENGTH_LONG).show()
	}

	override fun onStart() {
		super.onStart()
		GoogleAnalytics.getInstance(this).reportActivityStart(this)
	}

	override fun onStop() {
		super.onStop()
		// Stop the analytics tracking
		GoogleAnalytics.getInstance(this).reportActivityStop(this)
	}
	override fun onResume() {
		super.onResume()
		val settings = getSharedPreferences(UrlUtils.PREFS_NAME, 0)
		val isHelp = settings.getBoolean("help_build_hero", true)
		if (isHelp) {
			UtilActivity.showHelpBuildHero(this)
			val editor = settings.edit()
			editor.putBoolean("help_build_hero", false)
			editor.apply()
		}
	}

	override fun initComponents() {

		binding.heroAttributeView.buildHeroSlot1.setOnClickListener {
			binding.heroAttributeView.buildHeroSlot1.setBackgroundResource(R.drawable.empty)
			mBusySlot1 = false
			if (mItem1 != null) decrementValuesInAttribute(mItem1!!.itemAttrib)
			mItem1 = null
		}
		binding.heroAttributeView.buildHeroSlot2.setOnClickListener {
			binding.heroAttributeView.buildHeroSlot2.setBackgroundResource(R.drawable.empty)
			mBusySlot2 = false
			if (mItem2 != null) decrementValuesInAttribute(mItem2!!.itemAttrib)
			mItem2 = null
		}
		binding.heroAttributeView.buildHeroSlot3.setOnClickListener {
			binding.heroAttributeView.buildHeroSlot3.setBackgroundResource(R.drawable.empty)
			mBusySlot3 = false
			if (mItem3 != null) decrementValuesInAttribute(mItem3!!.itemAttrib)
			mItem3 = null
		}
		binding.heroAttributeView.buildHeroSlot4.setOnClickListener {
			binding.heroAttributeView.buildHeroSlot4.setBackgroundResource(R.drawable.empty)
			mBusySlot4 = false
			if (mItem4 != null) decrementValuesInAttribute(mItem4!!.itemAttrib)
			mItem4 = null
		}
		binding.heroAttributeView.buildHeroSlot5.setOnClickListener {
			binding.heroAttributeView.buildHeroSlot5.setBackgroundResource(R.drawable.empty)
			mBusySlot5 = false
			if (mItem5 != null) decrementValuesInAttribute(mItem5!!.itemAttrib)
			mItem5 = null
		}
		binding.heroAttributeView.buildHeroSlot6.setOnClickListener {
			binding.heroAttributeView.buildHeroSlot6.setBackgroundResource(R.drawable.empty)
			mBusySlot6 = false
			if (mItem6 != null) decrementValuesInAttribute(mItem6!!.itemAttrib)
			mItem6 = null
		}
		binding.buildHeroHelp.setOnClickListener { UtilActivity.showHelpBuildHero(this@BuildHeroActivity) }
		val viewPager = findViewById<View>(R.id.buildItemPager) as ViewPager
		val indicator = findViewById<View>(R.id.indicatorBuildHero) as TabLayout
		val itemAdapter = ItemAdapter(supportFragmentManager, this)
		viewPager.adapter = itemAdapter
		indicator.setupWithViewPager(viewPager)
		val nameHero = mHero!!.name?.replace("_", " ")
		binding.heroTextNameView.text = nameHero
		val font = Typeface.createFromAsset(assets, "Roboto-Thin.ttf")
		binding.heroAttributeView.heroTextAttributeAddStr.typeface = font
		mMenu = DotazoneMenu(this, binding.drawerLayout,binding.listSliderMenu.root)
		mMenu!!.checkBuildMenu()
	}

	override fun setActionErrorOk() {
		// TODO Auto-generated method stub
	}

	override fun setActionErrorCancel() {
		// TODO Auto-generated method stub
	}

	override fun addingItemSlot(item: Item?) {
		val pos = item?.imageName?.lastIndexOf('.')?:0
		val idImage = resources.getIdentifier(item?.imageName?.substring(0, pos), "drawable", packageName)
		if (isPremium) {
			if (!mBusySlot1 && item != null) {
				binding.heroAttributeView.buildHeroSlot1.setBackgroundResource(idImage)
				incrementValuesInAttribute(item)
				mItem1 = item
				mBusySlot1 = true
				binding.heroAttributeView.buildHeroSlot1.setOnLongClickListener {
					if (mItem1 != null) UtilActivity.showDescriptionItem(mItem1, this@BuildHeroActivity)
					false
				}
			} else if (!mBusySlot2 && item != null) {
				binding.heroAttributeView.buildHeroSlot2.setBackgroundResource(idImage)
				mBusySlot2 = true
				mItem2 = item
				incrementValuesInAttribute(item)
				binding.heroAttributeView.buildHeroSlot2.setOnLongClickListener {
					if (mItem2 != null) UtilActivity.showDescriptionItem(mItem2, this@BuildHeroActivity)
					false
				}
			} else if (!mBusySlot3 && item != null) {
				binding.heroAttributeView.buildHeroSlot3.setBackgroundResource(idImage)
				mBusySlot3 = true
				mItem3 = item
				incrementValuesInAttribute(item)
				binding.heroAttributeView.buildHeroSlot3.setOnLongClickListener {
					if (mItem3 != null) UtilActivity.showDescriptionItem(mItem3, this@BuildHeroActivity)
					false
				}
			} else if (!mBusySlot4 && item != null) {
				binding.heroAttributeView.buildHeroSlot4.setBackgroundResource(idImage)
				mBusySlot4 = true
				mItem4 = item
				incrementValuesInAttribute(item)
				binding.heroAttributeView.buildHeroSlot4.setOnLongClickListener {
					if (mItem4 != null) UtilActivity.showDescriptionItem(mItem4, this@BuildHeroActivity)
					false
				}
			} else if (!mBusySlot5 && item != null) {
				binding.heroAttributeView.buildHeroSlot5.setBackgroundResource(idImage)
				mBusySlot5 = true
				mItem5 = item
				incrementValuesInAttribute(item)
				binding.heroAttributeView.buildHeroSlot5.setOnLongClickListener {
					if (mItem5 != null) UtilActivity.showDescriptionItem(mItem5, this@BuildHeroActivity)
					false
				}
			} else if (!mBusySlot6 && item != null) {
				binding.heroAttributeView.buildHeroSlot6.setBackgroundResource(idImage)
				mBusySlot6 = true
				mItem6 = item
				incrementValuesInAttribute(item)
				binding.heroAttributeView.buildHeroSlot6.setOnLongClickListener {
					if (mItem6 != null) UtilActivity.showDescriptionItem(mItem6, this@BuildHeroActivity)
					false
				}
			} else {
				Toast.makeText(this, getString(R.string.full_slot), Toast.LENGTH_SHORT).show()
			}
		} else {
			showAlertBuyProVersion()
		}
	}

	override fun verifyPayment() {}
	private fun decrementValuesInAttribute(item: ItemAtrrib?) {
		if (item != null) {
			val valueInt = if (binding.heroAttributeView.heroTextAttributeAddInt.text == "") 0 else Integer.valueOf(UrlUtils.extractNumber(binding.heroAttributeView.heroTextAttributeAddInt.text.toString()))
			val valueAgi = if (binding.heroAttributeView.heroTextAttributeAddAgi.text == "") 0 else Integer.valueOf(UrlUtils.extractNumber(binding.heroAttributeView.heroTextAttributeAddAgi.text.toString()))
			val valueStr = if (binding.heroAttributeView.heroTextAttributeAddInt.text == "") 0 else Integer.valueOf(UrlUtils.extractNumber(binding.heroAttributeView.heroTextAttributeAddInt.text.toString()))
			val valueAtk = if (binding.heroAttributeView.heroTextAttributeAddAtk.text == "") 0 else Integer.valueOf(UrlUtils.extractNumber(binding.heroAttributeView.heroTextAttributeAddAtk.text.toString()))
			val valueMs = if (binding.heroAttributeView.heroTextAttributeAddMove.text == "") 0 else Integer.valueOf(UrlUtils.extractNumber(binding.heroAttributeView.heroTextAttributeAddMove.text.toString()))
			val valueArmor = if (binding.heroAttributeView.heroTextAttributeAddArmor.text == "") 0 else Integer.valueOf(UrlUtils.extractNumber(binding.heroAttributeView.heroTextAttributeAddArmor.text.toString()))
			val additionalInt = item.inteligence
			val additionalAgi = item.agility
			val additionalStr = item.strength
			val additionalAtk = item.damage
			val additionalMs = Integer.valueOf(item.ms)
			val additionalArmor = item.armor
			binding.heroAttributeView.heroTextAttributeAddAgi.text = if (Integer.valueOf(abs(valueAgi - additionalAgi)) == 0) "" else " + "+Integer.valueOf(abs(valueAgi - additionalAgi)).toString()
			binding.heroAttributeView.heroTextAttributeAddInt.text = if (Integer.valueOf(abs(valueInt - additionalInt)) == 0) "" else " + "+Integer.valueOf(abs(valueInt - additionalInt)).toString()
			binding.heroAttributeView.heroTextAttributeAddInt.text = if (Integer.valueOf(abs(valueStr - additionalStr)) == 0) "" else " + "+Integer.valueOf(abs(valueStr - additionalStr)).toString()
			if (mMainBoot!!.itemAttrib?.id == item.id && additionalMs != 0 && mIsBoot) {
				binding.heroAttributeView.heroTextAttributeAddMove.text = if (Integer.valueOf(abs(valueMs - additionalMs)) == 0) "" else " + "+Integer.valueOf(abs(valueMs - additionalMs)).toString()
				mIsBoot = false
			}
			binding.heroAttributeView.heroTextAttributeAddArmor.text = if (Integer.valueOf(abs(valueArmor - additionalArmor)) == 0) "" else " + "+Integer.valueOf(abs(valueArmor - additionalArmor)).toString()
			binding.heroAttributeView.heroTextAttributeAddAtk.text = if (Integer.valueOf(abs(additionalAtk - valueAtk)) == 0) "" else " + "+Integer.valueOf(abs(valueAtk - additionalAtk)).toString()
		}
	}

	private fun incrementValuesInAttribute(item: Item?) {
		if (item?.itemAttrib != null) {
			val valueInt = if (binding.heroAttributeView.heroTextAttributeAddInt.text == "") 0 else Integer.valueOf(UrlUtils.extractNumber(binding.heroAttributeView.heroTextAttributeAddInt.text.toString()))
			val valueAgi = if (binding.heroAttributeView.heroTextAttributeAddAgi.text == "") 0 else Integer.valueOf(UrlUtils.extractNumber(binding.heroAttributeView.heroTextAttributeAddAgi.text.toString()))
			val valueStr = if (binding.heroAttributeView.heroTextAttributeAddStr.text == "") 0 else Integer.valueOf(UrlUtils.extractNumber(binding.heroAttributeView.heroTextAttributeAddStr.text.toString()))
			val valueAtk = if (binding.heroAttributeView.heroTextAttributeAddAtk.text == "") 0 else Integer.valueOf(UrlUtils.extractNumber(binding.heroAttributeView.heroTextAttributeAddAtk.text.toString()))
			val valueMs = if (binding.heroAttributeView.heroTextAttributeAddMove.text == "") 0 else Integer.valueOf(UrlUtils.extractNumber(binding.heroAttributeView.heroTextAttributeAddMove.text.toString()))
			val valueArmor = if (binding.heroAttributeView.heroTextAttributeAddArmor.text == "") 0 else Integer.valueOf(UrlUtils.extractNumber(binding.heroAttributeView.heroTextAttributeAddArmor.text.toString()))
			val additionalInt = item.itemAttrib?.inteligence
			val additionalAgi = item.itemAttrib?.agility
			val additionalStr = item.itemAttrib?.strength
			val additionalAtk = item.itemAttrib?.damage
			val additionalMs = Integer.valueOf(item.itemAttrib?.ms?:"0")
			val additionalArmor = item.itemAttrib?.armor
			binding.heroAttributeView.heroTextAttributeAddAgi.text = if (Integer.valueOf(valueAgi + additionalAgi!!) == 0) "" else " + "+Integer.valueOf(valueAgi + additionalAgi).toString()
			binding.heroAttributeView.heroTextAttributeAddInt.text = if (Integer.valueOf(valueInt + additionalInt!!) == 0) "" else " + "+Integer.valueOf(valueInt + additionalInt).toString()
			binding.heroAttributeView.heroTextAttributeAddStr.text = if (Integer.valueOf(valueStr + additionalStr!!) == 0) "" else " + "+Integer.valueOf(valueStr + additionalStr).toString()
			binding.heroAttributeView.heroTextAttributeAddAtk.text = if (Integer.valueOf(valueAtk + additionalAtk!!) == 0) "" else " + "+Integer.valueOf(valueAtk + additionalAtk).toString()
			if (valueMs == 0) {
				binding.heroAttributeView.heroTextAttributeAddMove.text = if (Integer.valueOf(valueMs + additionalMs) == 0) "" else " + " + Integer.valueOf(valueMs + additionalMs).toString()
				mMainBoot = item
				mIsBoot = true
			}
			binding.heroAttributeView.heroTextAttributeAddArmor.text = if (Integer.valueOf(valueArmor + additionalArmor!!) == 0) "" else " + "+Integer.valueOf(valueArmor + additionalArmor).toString()
		}
	}

	private fun showAlertBuyProVersion() {
		val dialog = Dialog(this, R.style.FullHeightDialog)
		dialog.show()
		dialog.setContentView(R.layout.alert_buy_view)
		dialog.setCancelable(false)
		val buy = dialog.findViewById<View>(R.id.buy_pro_version) as Button
		val close = dialog.findViewById<View>(R.id.buy_close) as Button
		close.setOnClickListener { dialog.dismiss() }
		buy.setOnClickListener { dialog.dismiss() }
	}
}

