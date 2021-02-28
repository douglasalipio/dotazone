package com.br.dotazone.view.activity

import android.app.Dialog
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.br.dotazone.DotaZoneApplication
import com.br.dotazone.DotaZoneBrain.hero
import com.br.dotazone.DotaZoneBrain.isPremium
import com.br.dotazone.R
import com.br.dotazone.model.entity.Item
import com.br.dotazone.model.entity.ItemAtrrib
import com.br.dotazone.model.listeners.BuildHeroAction
import com.br.dotazone.model.util.UrlUtils
import com.br.dotazone.model.util.UtilActivity
import com.br.dotazone.view.adapter.ItemAdapter
import com.com.dotazone.DotazoneMenu
import com.google.android.gms.analytics.GoogleAnalytics
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.build_hero_view.*
import kotlinx.android.synthetic.main.hero_attributes_build.*
import kotlinx.android.synthetic.main.hero_profile_view.*
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
	private var mDrawerLayout: DrawerLayout? = null
	private var mDrawerList: RelativeLayout? = null
	private var mMainBoot: Item? = null
	private var mIsBoot = false

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.build_hero_view)
		initComponents()
		heroTextAttributeAgi.text = mHero?.abilites?.agi?.get(0) ?:""
		heroTextAttributeInt.text = mHero?.abilites?.inte?.get(0) ?: ""
		heroTextAttributeStr.text = mHero?.abilites?.str?.get(0) ?: ""
		heroTextAttributeAtk.text = mHero?.abilites?.dmg?.get(0) ?: ""
		heroTextAttributeMove.text = mHero?.abilites?.ms ?: ""
		heroTextAttributeArmor.text = mHero?.abilites?.armor ?:""
		val idImage = resources.getIdentifier((mHero?.idString ?: "") + "_avatar", "drawable", packageName)
		buildHeroCircleImage.setImageResource(idImage)
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
			editor.commit()
		}
	}

	override fun initComponents() {

		buildHeroSlot1.setOnClickListener {
			buildHeroSlot1.setBackgroundResource(R.drawable.empty)
			mBusySlot1 = false
			if (mItem1 != null) decrementValuesInAttribute(mItem1!!.itemAttrib)
			mItem1 = null
		}
		buildHeroSlot2.setOnClickListener {
			buildHeroSlot2.setBackgroundResource(R.drawable.empty)
			mBusySlot2 = false
			if (mItem2 != null) decrementValuesInAttribute(mItem2!!.itemAttrib)
			mItem2 = null
		}
		buildHeroSlot3.setOnClickListener {
			buildHeroSlot3.setBackgroundResource(R.drawable.empty)
			mBusySlot3 = false
			if (mItem3 != null) decrementValuesInAttribute(mItem3!!.itemAttrib)
			mItem3 = null
		}
		buildHeroSlot4.setOnClickListener {
			buildHeroSlot4.setBackgroundResource(R.drawable.empty)
			mBusySlot4 = false
			if (mItem4 != null) decrementValuesInAttribute(mItem4!!.itemAttrib)
			mItem4 = null
		}
		buildHeroSlot5.setOnClickListener {
			buildHeroSlot5.setBackgroundResource(R.drawable.empty)
			mBusySlot5 = false
			if (mItem5 != null) decrementValuesInAttribute(mItem5!!.itemAttrib)
			mItem5 = null
		}
		buildHeroSlot6.setOnClickListener {
			buildHeroSlot6.setBackgroundResource(R.drawable.empty)
			mBusySlot6 = false
			if (mItem6 != null) decrementValuesInAttribute(mItem6!!.itemAttrib)
			mItem6 = null
		}
		buildHeroHelp.setOnClickListener { UtilActivity.showHelpBuildHero(this@BuildHeroActivity) }
		val viewPager = findViewById<View>(R.id.buildItemPager) as ViewPager
		val indicator = findViewById<View>(R.id.indicatorBuildHero) as TabLayout
		val itemAdapter = ItemAdapter(supportFragmentManager, this)
		viewPager.adapter = itemAdapter
		indicator.setupWithViewPager(viewPager)
		val nameHero = mHero!!.name?.replace("_", " ")
		heroTextNameView.text = nameHero
		val font = Typeface.createFromAsset(assets, "Roboto-Thin.ttf")
		heroTextAttributeAddStr.typeface = font
		mDrawerLayout = findViewById<View>(R.id.drawer_layout) as DrawerLayout
		mDrawerList = findViewById<View>(R.id.listSliderMenu) as RelativeLayout
		mMenu = DotazoneMenu(this, mDrawerLayout, mDrawerList)
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
				buildHeroSlot1.setBackgroundResource(idImage)
				incrementValuesInAttribute(item)
				mItem1 = item
				mBusySlot1 = true
				buildHeroSlot1.setOnLongClickListener {
					if (mItem1 != null) UtilActivity.showDescriptionItem(mItem1, this@BuildHeroActivity)
					false
				}
			} else if (!mBusySlot2 && item != null) {
				buildHeroSlot2.setBackgroundResource(idImage)
				mBusySlot2 = true
				mItem2 = item
				incrementValuesInAttribute(item)
				buildHeroSlot2.setOnLongClickListener {
					if (mItem2 != null) UtilActivity.showDescriptionItem(mItem2, this@BuildHeroActivity)
					false
				}
			} else if (!mBusySlot3 && item != null) {
				buildHeroSlot3.setBackgroundResource(idImage)
				mBusySlot3 = true
				mItem3 = item
				incrementValuesInAttribute(item)
				buildHeroSlot3.setOnLongClickListener {
					if (mItem3 != null) UtilActivity.showDescriptionItem(mItem3, this@BuildHeroActivity)
					false
				}
			} else if (!mBusySlot4 && item != null) {
				buildHeroSlot4.setBackgroundResource(idImage)
				mBusySlot4 = true
				mItem4 = item
				incrementValuesInAttribute(item)
				buildHeroSlot4.setOnLongClickListener {
					if (mItem4 != null) UtilActivity.showDescriptionItem(mItem4, this@BuildHeroActivity)
					false
				}
			} else if (!mBusySlot5 && item != null) {
				buildHeroSlot5.setBackgroundResource(idImage)
				mBusySlot5 = true
				mItem5 = item
				incrementValuesInAttribute(item)
				buildHeroSlot5.setOnLongClickListener {
					if (mItem5 != null) UtilActivity.showDescriptionItem(mItem5, this@BuildHeroActivity)
					false
				}
			} else if (!mBusySlot6 && item != null) {
				buildHeroSlot6.setBackgroundResource(idImage)
				mBusySlot6 = true
				mItem6 = item
				incrementValuesInAttribute(item)
				buildHeroSlot6.setOnLongClickListener {
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
			val valueInt = if (heroTextAttributeAddInt.text == "") 0 else Integer.valueOf(UrlUtils.extractNumber(heroTextAttributeAddInt.text.toString()))
			val valueAgi = if (heroTextAttributeAddAgi.text == "") 0 else Integer.valueOf(UrlUtils.extractNumber(heroTextAttributeAddAgi.text.toString()))
			val valueStr = if (heroTextAttributeAddInt.text == "") 0 else Integer.valueOf(UrlUtils.extractNumber(heroTextAttributeAddInt.text.toString()))
			val valueAtk = if (heroTextAttributeAddAtk.text == "") 0 else Integer.valueOf(UrlUtils.extractNumber(heroTextAttributeAddAtk.text.toString()))
			val valueMs = if (heroTextAttributeAddMove.text == "") 0 else Integer.valueOf(UrlUtils.extractNumber(heroTextAttributeAddMove.text.toString()))
			val valueArmor = if (heroTextAttributeAddArmor.text == "") 0 else Integer.valueOf(UrlUtils.extractNumber(heroTextAttributeAddArmor.text.toString()))
			val additionalInt = item.inteligence
			val additionalAgi = item.agility
			val additionalStr = item.strength
			val additionalAtk = item.damage
			val additionalMs = Integer.valueOf(item.ms)
			val additionalArmor = item.armor
			heroTextAttributeAddAgi.text = if (Integer.valueOf(abs(valueAgi - additionalAgi)) == 0) "" else " + "+Integer.valueOf(abs(valueAgi - additionalAgi)).toString()
			heroTextAttributeAddInt.text = if (Integer.valueOf(abs(valueInt - additionalInt)) == 0) "" else " + "+Integer.valueOf(abs(valueInt - additionalInt)).toString()
			heroTextAttributeAddInt.text = if (Integer.valueOf(abs(valueStr - additionalStr)) == 0) "" else " + "+Integer.valueOf(abs(valueStr - additionalStr)).toString()
			if (mMainBoot!!.itemAttrib?.id == item.id && additionalMs != 0 && mIsBoot) {
				heroTextAttributeAddMove.text = if (Integer.valueOf(abs(valueMs - additionalMs)) == 0) "" else " + "+Integer.valueOf(abs(valueMs - additionalMs)).toString()
				mIsBoot = false
			}
			heroTextAttributeAddArmor.text = if (Integer.valueOf(abs(valueArmor - additionalArmor)) == 0) "" else " + "+Integer.valueOf(abs(valueArmor - additionalArmor)).toString()
			heroTextAttributeAddAtk.text = if (Integer.valueOf(abs(additionalAtk - valueAtk)) == 0) "" else " + "+Integer.valueOf(abs(valueAtk - additionalAtk)).toString()
		}
	}

	private fun incrementValuesInAttribute(item: Item?) {
		if (item != null && item.itemAttrib != null) {
			val valueInt = if (heroTextAttributeAddInt.text == "") 0 else Integer.valueOf(UrlUtils.extractNumber(heroTextAttributeAddInt.text.toString()))
			val valueAgi = if (heroTextAttributeAddAgi.text == "") 0 else Integer.valueOf(UrlUtils.extractNumber(heroTextAttributeAddAgi.text.toString()))
			val valueStr = if (heroTextAttributeAddStr.text == "") 0 else Integer.valueOf(UrlUtils.extractNumber(heroTextAttributeAddStr.text.toString()))
			val valueAtk = if (heroTextAttributeAddAtk.text == "") 0 else Integer.valueOf(UrlUtils.extractNumber(heroTextAttributeAddAtk.text.toString()))
			val valueMs = if (heroTextAttributeAddMove.text == "") 0 else Integer.valueOf(UrlUtils.extractNumber(heroTextAttributeAddMove.text.toString()))
			val valueArmor = if (heroTextAttributeAddArmor.text == "") 0 else Integer.valueOf(UrlUtils.extractNumber(heroTextAttributeAddArmor.text.toString()))
			val additionalInt = item.itemAttrib?.inteligence
			val additionalAgi = item.itemAttrib?.agility
			val additionalStr = item.itemAttrib?.strength
			val additionalAtk = item.itemAttrib?.damage
			val additionalMs = Integer.valueOf(item.itemAttrib?.ms?:"0")
			val additionalArmor = item.itemAttrib?.armor
			heroTextAttributeAddAgi.text = if (Integer.valueOf(valueAgi + additionalAgi!!) == 0) "" else " + "+Integer.valueOf(valueAgi + additionalAgi).toString()
			heroTextAttributeAddInt.text = if (Integer.valueOf(valueInt + additionalInt!!) == 0) "" else " + "+Integer.valueOf(valueInt + additionalInt).toString()
			heroTextAttributeAddStr.text = if (Integer.valueOf(valueStr + additionalStr!!) == 0) "" else " + "+Integer.valueOf(valueStr + additionalStr).toString()
			heroTextAttributeAddAtk.text = if (Integer.valueOf(valueAtk + additionalAtk!!) == 0) "" else " + "+Integer.valueOf(valueAtk + additionalAtk).toString()
			if (valueMs == 0) {
				heroTextAttributeAddMove.text = if (Integer.valueOf(valueMs + additionalMs) == 0) "" else " + " + Integer.valueOf(valueMs + additionalMs).toString()
				mMainBoot = item
				mIsBoot = true
			}
			heroTextAttributeAddArmor.text = if (Integer.valueOf(valueArmor + additionalArmor!!) == 0) "" else " + "+Integer.valueOf(valueArmor + additionalArmor).toString()
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

