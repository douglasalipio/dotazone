package com.br.dotazone.view.activity

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.drawerlayout.widget.DrawerLayout
import com.br.dotazone.DotaZoneApplication
import com.br.dotazone.DotaZoneBrain.hero
import com.br.dotazone.DotaZoneBrain.isPremium
import com.br.dotazone.R
import com.br.dotazone.model.entity.AdMobBanner
import com.br.dotazone.model.util.UrlUtils.Companion.convertDpToPixel
import com.br.dotazone.view.components.FrameLayoutSkillBoard
import com.br.dotazone.view.fragment.HeroSkillFragment.Companion.newInstance
import com.com.dotazone.DotazoneMenu
import com.google.android.gms.ads.AdView
import com.google.android.gms.analytics.GoogleAnalytics


class HeroProfileActivity : BaseActivity(), View.OnClickListener {
	private var mTextInt: TextView? = null
	private var mTextAgi: TextView? = null
	private var mTextStr: TextView? = null
	private var mTextAtk: TextView? = null
	private var mTextMove: TextView? = null
	private var mTextArmor: TextView? = null
	private var mIconHero: ImageView? = null
	private var mTextHeroName: TextView? = null
	private var mTextRoles: TextView? = null
	private val mHero = hero
	private var mLinearSkill: LinearLayout? = null
	private var mAnimator: ObjectAnimator? = null
	private var mIconHeroBio: ImageView? = null
	private var mLinearBg: RelativeLayout? = null
	private var mDrawerLayout: DrawerLayout? = null
	private var mDrawerList: RelativeLayout? = null
	private var mMenu: DotazoneMenu? = null
	private var adView: AdView? = null
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.hero_profile_view)
		initComponents()
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

	override fun initComponents() {
		mTextAgi = findViewById<View>(R.id.heroTextAttributeAgi) as TextView
		mTextStr = findViewById<View>(R.id.heroTextAttributeStr) as TextView
		mTextInt = findViewById<View>(R.id.heroTextAttributeInt) as TextView
		mTextAtk = findViewById<View>(R.id.heroTextAttributeAtk) as TextView
		mTextMove = findViewById<View>(R.id.heroTextAttributeMove) as TextView
		mTextArmor = findViewById<View>(R.id.heroTextAttributeArmor) as TextView
		mIconHero = findViewById<View>(R.id.hero_icon_avatar) as ImageView
		mTextHeroName = findViewById<View>(R.id.heroTextName) as TextView
		mTextRoles = findViewById<View>(R.id.hero_text_roles) as TextView
		mIconHeroBio = findViewById<View>(R.id.hero_icon_bio) as ImageView
		mIconHeroBio!!.setOnClickListener(this)
		mLinearBg = findViewById<View>(R.id.hero_linear_bg) as RelativeLayout
		val idImageBackground = resources.getIdentifier(mHero!!.idString + "_bg", "drawable", packageName)
		mLinearBg!!.setBackgroundResource(idImageBackground)
		val nameHero = mHero.name?.replace("_", " ")
		mTextHeroName!!.text = nameHero
		mTextRoles!!.text = mHero.abilites?.droles
		val font = Typeface.createFromAsset(assets, "Roboto-Thin.ttf")
		mTextHeroName!!.setTypeface(font)
		mTextAgi!!.text = mHero.abilites?.agi?.get(0) ?: ""
		mTextInt!!.text = mHero.abilites?.inte?.get(0) ?: ""
		mTextStr!!.text = mHero.abilites?.str?.get(0) ?: ""
		mTextStr!!.text = mHero.abilites?.str?.get(0) ?: ""
		mTextAtk!!.text = mHero.abilites?.dmg?.get(0) ?: ""
		mTextMove!!.text = mHero.abilites?.ms
		mTextArmor!!.text = mHero.abilites?.armor
		val idImage = resources.getIdentifier(mHero.idString + "_avatar", "drawable", packageName)
		if (idImage != 0) {
			mIconHero!!.setImageResource(idImage)
		}
		mLinearSkill = findViewById<View>(R.id.hero_skill_layout) as LinearLayout
		loandingSkillsHero()
		mDrawerLayout = findViewById<View>(R.id.drawer_layout) as DrawerLayout
		mDrawerList = findViewById<View>(R.id.listSliderMenu) as RelativeLayout
		mMenu = DotazoneMenu(this, mDrawerLayout, mDrawerList)
		mMenu!!.checkHeroMenu()
		adView = findViewById<View>(R.id.hero_skill_admob) as AdView
		AdMobBanner().createBanner(this, adView!!, isPremium)
	}

	public override fun onPause() {
		adView!!.pause()
		super.onPause()
	}

	public override fun onResume() {
		super.onResume()
		adView!!.resume()
	}

	public override fun onDestroy() {
		super.onDestroy()
		adView!!.destroy()
	}

	private fun createFrameLayoutBrightness(mainFrame: FrameLayout, idFrame: Int): FrameLayoutSkillBoard {
		val frameBrightness = FrameLayoutSkillBoard(this)
		frameBrightness.id = idFrame
		mainFrame.addView(frameBrightness)
		val paramBright = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
				FrameLayout.LayoutParams.WRAP_CONTENT)
		paramBright.height = convertDpToPixel(87f, resources)
		paramBright.width = convertDpToPixel(87f, resources)
		frameBrightness.layoutParams = paramBright
		return frameBrightness
	}

	private fun createMainFrameSkill(): FrameLayout {
		val mainFrame = FrameLayout(this)
		val paramMainFrame = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
				FrameLayout.LayoutParams.WRAP_CONTENT)
		paramMainFrame.height = convertDpToPixel(87f, resources)
		paramMainFrame.width = convertDpToPixel(87f, resources)
		paramMainFrame.gravity = Gravity.CENTER_HORIZONTAL
		mainFrame.layoutParams = paramMainFrame
		return mainFrame
	}

	private fun createSkillIcon(mainFrame: FrameLayout, idImage: Int): ImageView {
		val skillImage = ImageView(this)
		skillImage.setImageResource(idImage)
		mainFrame.addView(skillImage)
		val teste = skillImage.layoutParams as FrameLayout.LayoutParams
		teste.height = convertDpToPixel(64f, resources)
		teste.width = convertDpToPixel(64f, resources)
		teste.leftMargin = convertDpToPixel(11f, resources)
		teste.topMargin = convertDpToPixel(11f, resources)
		skillImage.layoutParams = teste
		return skillImage
	}

	private fun loandingSkillsHero() {
		val total = mHero!!.skills.size
		for (i in 0 until total) {
			val skill = mHero.skills[i]
			val mainFrame = createMainFrameSkill()
			val frameBrightness = createFrameLayoutBrightness(mainFrame, i)
			val idImage = resources.getIdentifier(skill.idString, "drawable", packageName)
			createSkillIcon(mainFrame, idImage)
			frameBrightness.setOnClickListener {
				frameBrightness.changeBackgroud()
				animationFadeIn(frameBrightness, false)
				for (i in 0 until total) {
					val lastFramLayout = findViewById<View>(i) as FrameLayoutSkillBoard
					if (frameBrightness.id != lastFramLayout.id) lastFramLayout.removeBackground()
				}
				val fragment1 = newInstance(skill)
				supportFragmentManager.beginTransaction().replace(R.id.hero_description_skill_layout, fragment1).commit()
			}

			// default skill in zero position.
			if (i == 0) {
				frameBrightness.changeBackgroud()
				animationFadeIn(frameBrightness, false)
				val fragment1 = newInstance(skill)
				supportFragmentManager.beginTransaction().replace(R.id.hero_description_skill_layout, fragment1).commit()
			}
			mLinearSkill!!.addView(mainFrame)
		}
	}

	private fun animationFadeIn(frameBrightness: FrameLayoutSkillBoard, cancel: Boolean) {
		frameBrightness.alpha = 0f
		val propValueAlpha = PropertyValuesHolder.ofFloat("alpha", 0.5.toFloat(), 1f)
		mAnimator = ObjectAnimator.ofPropertyValuesHolder(frameBrightness, propValueAlpha)
		mAnimator!!.duration = 500
		mAnimator!!.repeatCount = ValueAnimator.INFINITE
		mAnimator!!.repeatMode = ValueAnimator.REVERSE
		mAnimator!!.start()
		mAnimator!!.addListener(object : Animator.AnimatorListener {
			override fun onAnimationStart(animation: Animator) {}
			override fun onAnimationRepeat(animation: Animator) {
				if (cancel) mAnimator!!.cancel()
			}

			override fun onAnimationEnd(animation: Animator) {}
			override fun onAnimationCancel(animation: Animator) {}
		})
	}

	override fun onClick(arg0: View) {
		startActivity(Intent(this, HeroBiographActivity::class.java))
	}

	override fun setActionErrorOk() {
		finish()
	}

	override fun setActionErrorCancel() {
		finish()
	}
}

