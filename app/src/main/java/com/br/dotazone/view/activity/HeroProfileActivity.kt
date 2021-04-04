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
import com.br.dotazone.DotaZoneBrain.hero
import com.br.dotazone.DotaZoneBrain.isPremium
import com.br.dotazone.R
import com.br.dotazone.databinding.HeroProfileViewBinding
import com.br.dotazone.model.entity.AdMobBanner
import com.br.dotazone.model.util.UrlUtils.Companion.convertDpToPixel
import com.br.dotazone.view.components.FrameLayoutSkillBoard
import com.br.dotazone.view.fragment.HeroSkillFragment.Companion.newInstance
import com.com.dotazone.DotazoneMenu
import com.google.android.gms.analytics.GoogleAnalytics


class HeroProfileActivity : BaseActivity(), View.OnClickListener {

	private val mHero = hero
	private lateinit var animator: ObjectAnimator
	private var mMenu: DotazoneMenu? = null
	private lateinit var binding: HeroProfileViewBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = HeroProfileViewBinding.inflate(layoutInflater)
		setContentView(binding.root)
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
		binding.heroIconBio.setOnClickListener(this)
		val idImageBackground = resources.getIdentifier(mHero!!.idString + "_bg", "drawable", packageName)
		binding.heroLinearBg.setBackgroundResource(idImageBackground)
		val nameHero = mHero.name?.replace("_", " ")
		binding.heroTextName.text = nameHero
		binding.heroTextRoles.text = mHero.abilites?.droles
		val font = Typeface.createFromAsset(assets, "Roboto-Thin.ttf")
		binding.heroTextName.typeface = font
		binding.heroAttributeView.heroTextAttributeAgi.text = mHero.abilites?.agi?.get(0) ?: ""
		binding.heroAttributeView.heroTextAttributeInt.text = mHero.abilites?.inte?.get(0) ?: ""
		binding.heroAttributeView.heroTextAttributeStr.text = mHero.abilites?.str?.get(0) ?: ""
		binding.heroAttributeView.heroTextAttributeAtk.text = mHero.abilites?.dmg?.get(0) ?: ""
		binding.heroAttributeView.heroTextAttributeMove.text = mHero.abilites?.ms
		binding.heroAttributeView.heroTextAttributeAddArmor.text = mHero.abilites?.armor
		val idImage = resources.getIdentifier(mHero.idString + "_avatar", "drawable", packageName)
		if (idImage != 0) {
			binding.heroIconAvatar.setImageResource(idImage)
		}
		loandingSkillsHero()
		mMenu = DotazoneMenu(this, binding.drawerLayout, binding.listSliderMenu.root)
		mMenu!!.checkHeroMenu()
		AdMobBanner().createBanner(this, binding.heroSkillAdmob, isPremium)
	}

	public override fun onPause() {
		binding.heroSkillAdmob.pause()
		super.onPause()
	}

	public override fun onResume() {
		super.onResume()
		binding.heroSkillAdmob.resume()
	}

	public override fun onDestroy() {
		super.onDestroy()
		binding.heroSkillAdmob.destroy()
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
			binding.heroAttributeView.heroSkillLayout.addView(mainFrame)
		}
	}

	private fun animationFadeIn(frameBrightness: FrameLayoutSkillBoard, cancel: Boolean) {
		frameBrightness.alpha = 0f
		val propValueAlpha = PropertyValuesHolder.ofFloat("alpha", 0.5.toFloat(), 1f)
		animator = ObjectAnimator.ofPropertyValuesHolder(frameBrightness, propValueAlpha)
		animator.duration = 500
		animator.repeatCount = ValueAnimator.INFINITE
		animator.repeatMode = ValueAnimator.REVERSE
		animator.start()
		animator.addListener(object : Animator.AnimatorListener {
			override fun onAnimationStart(animation: Animator) {}
			override fun onAnimationRepeat(animation: Animator) {
				if (cancel) animator.cancel()
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

