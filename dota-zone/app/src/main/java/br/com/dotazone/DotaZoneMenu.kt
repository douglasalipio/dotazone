package br.com.dotazone

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentActivity
import br.com.dotazone.model.listeners.Controllable
import br.com.dotazone.view.activity.AboutActivity
import br.com.dotazone.view.activity.LanguageActivity
import br.com.dotazone.view.activity.MainActivity
import br.com.dotazone.view.activity.TabActivity


class DotazoneMenu(private val mActivity: FragmentActivity, drawerLayout: DrawerLayout?, drawerList: RelativeLayout?) : Controllable {
	var drawerLayout: DrawerLayout? = null
	var drawerList: RelativeLayout? = null
	private var mTextMenuHero: TextView? = null
	private var mTextMenuItems: TextView? = null
	private var mTextMenuNews: TextView? = null
	private var mTextMenuInvite: TextView? = null
	private var mTextMenuLanguage: TextView? = null
	private var mTextMenuBuild: TextView? = null
	private var mTextMenuChannel: TextView? = null
	private var mtextMenuAbout: TextView? = null
	private var mPaymentButton: Button? = null
	private var mFrameAdPro: FrameLayout? = null

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 */
	private fun displayView(position: Int) {
		val intent = Intent(mActivity, TabActivity::class.java)
		when (position) {
			0 -> {
				val intentNews = Intent(mActivity, MainActivity::class.java)
				intentNews.putExtra(MainActivity::class.java.name, MENU_NEWS)
				mActivity.startActivity(intentNews)
			}
			1 -> {
				intent.putExtra(MainActivity::class.java.name, MENU_HERO)
				mActivity.startActivity(intent)
				TabActivity.isBuild = false
			}
			2 -> {
				intent.putExtra(MainActivity::class.java.name, MENU_ITEM)
				mActivity.startActivity(intent)
			}
			5 -> mActivity.startActivity(Intent(mActivity, LanguageActivity::class.java))
			6 -> {
				intent.putExtra(MainActivity::class.java.name, MENU_BUILD)
				mActivity.startActivity(intent)
				TabActivity.isBuild = true
			}
			7 -> mActivity.startActivity(Intent(mActivity, AboutActivity::class.java))
			else -> {
			}
		}
		drawerLayout!!.closeDrawer(drawerList!!)
	}

	fun defaultItemSelected() {
		mTextMenuNews!!.setTextColor(Color.RED)
	}

	fun checkHeroMenu() {
		mTextMenuItems!!.setTextColor(Color.WHITE)
		mTextMenuHero!!.setTextColor(Color.RED)
		mTextMenuNews!!.setTextColor(Color.WHITE)
		mTextMenuLanguage!!.setTextColor(Color.WHITE)
		mTextMenuInvite!!.setTextColor(Color.WHITE)
		mtextMenuAbout!!.setTextColor(Color.WHITE)
	}

	fun checkItemMenu() {
		mTextMenuItems!!.setTextColor(Color.RED)
		mTextMenuHero!!.setTextColor(Color.WHITE)
		mTextMenuNews!!.setTextColor(Color.WHITE)
		mTextMenuLanguage!!.setTextColor(Color.WHITE)
		mTextMenuInvite!!.setTextColor(Color.WHITE)
		mtextMenuAbout!!.setTextColor(Color.WHITE)
	}

	fun checkLanguageMenu() {
		mTextMenuItems!!.setTextColor(Color.WHITE)
		mTextMenuHero!!.setTextColor(Color.WHITE)
		mTextMenuNews!!.setTextColor(Color.WHITE)
		mTextMenuInvite!!.setTextColor(Color.WHITE)
		mTextMenuLanguage!!.setTextColor(Color.RED)
		mtextMenuAbout!!.setTextColor(Color.WHITE)
	}

	fun checkInviteMenu() {
		mTextMenuItems!!.setTextColor(Color.WHITE)
		mTextMenuHero!!.setTextColor(Color.WHITE)
		mTextMenuNews!!.setTextColor(Color.WHITE)
		mTextMenuLanguage!!.setTextColor(Color.WHITE)
		mTextMenuInvite!!.setTextColor(Color.RED)
		mtextMenuAbout!!.setTextColor(Color.WHITE)
	}

	fun checkNewsMenu() {
		mTextMenuItems!!.setTextColor(Color.WHITE)
		mTextMenuHero!!.setTextColor(Color.WHITE)
		mTextMenuLanguage!!.setTextColor(Color.WHITE)
		mTextMenuNews!!.setTextColor(Color.RED)
		mTextMenuInvite!!.setTextColor(Color.WHITE)
		mtextMenuAbout!!.setTextColor(Color.WHITE)
	}

	fun checkBuildMenu() {
		mTextMenuItems!!.setTextColor(Color.WHITE)
		mTextMenuHero!!.setTextColor(Color.WHITE)
		mTextMenuNews!!.setTextColor(Color.WHITE)
		mTextMenuInvite!!.setTextColor(Color.WHITE)
		mTextMenuLanguage!!.setTextColor(Color.WHITE)
		mTextMenuBuild!!.setTextColor(Color.RED)
		mtextMenuAbout!!.setTextColor(Color.WHITE)
	}

	fun checkAboutMenu() {
		mTextMenuItems!!.setTextColor(Color.WHITE)
		mTextMenuHero!!.setTextColor(Color.WHITE)
		mTextMenuNews!!.setTextColor(Color.WHITE)
		mTextMenuInvite!!.setTextColor(Color.WHITE)
		mTextMenuLanguage!!.setTextColor(Color.WHITE)
		mTextMenuBuild!!.setTextColor(Color.WHITE)
		mtextMenuAbout!!.setTextColor(Color.RED)
	}

	fun checkChannelMenu() {
		mTextMenuItems!!.setTextColor(Color.WHITE)
		mTextMenuHero!!.setTextColor(Color.WHITE)
		mTextMenuNews!!.setTextColor(Color.WHITE)
		mTextMenuInvite!!.setTextColor(Color.WHITE)
		mTextMenuLanguage!!.setTextColor(Color.WHITE)
		mTextMenuBuild!!.setTextColor(Color.WHITE)
		mtextMenuAbout!!.setTextColor(Color.WHITE)
		mTextMenuChannel!!.setTextColor(Color.RED)
	}

	fun defaultMenu() {
		mTextMenuItems!!.setTextColor(Color.WHITE)
		mTextMenuHero!!.setTextColor(Color.WHITE)
		mTextMenuNews!!.setTextColor(Color.WHITE)
		mTextMenuInvite!!.setTextColor(Color.WHITE)
		mTextMenuLanguage!!.setTextColor(Color.WHITE)
		mTextMenuBuild!!.setTextColor(Color.WHITE)
		mtextMenuAbout!!.setTextColor(Color.WHITE)
		mTextMenuChannel!!.setTextColor(Color.WHITE)
	}

	fun initComponents() {
		val font = Typeface.createFromAsset(mActivity.assets, "Roboto-Thin.ttf")
		drawerLayout = mActivity.findViewById<View>(R.id.drawer_layout) as DrawerLayout
		drawerList = mActivity.findViewById<View>(R.id.listSliderMenu) as RelativeLayout
		mTextMenuHero = mActivity.findViewById<View>(R.id.textMenuHero) as TextView
		mFrameAdPro = mActivity.findViewById<View>(R.id.content_drawer_list_account_premium) as FrameLayout
		mTextMenuHero!!.setOnClickListener {
			checkHeroMenu()
			displayView(1)
		}
		mTextMenuItems = mActivity.findViewById<View>(R.id.textMenuItems) as TextView
		mTextMenuItems!!.setOnClickListener {
			checkItemMenu()
			displayView(2)
		}
		mTextMenuNews = mActivity.findViewById<View>(R.id.textMenuNews) as TextView
		mTextMenuNews!!.setOnClickListener {
			checkNewsMenu()
			displayView(0)
		}
		mTextMenuInvite = mActivity.findViewById<View>(R.id.textMenuInvite) as TextView
		mTextMenuInvite!!.setOnClickListener {
			checkInviteMenu()
			val sharingIntent = Intent(Intent.ACTION_SEND)
			sharingIntent.type = "text/plain"
			val shareBody = mActivity.getString(R.string.share_app_text)
			sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
			mActivity.startActivity(Intent.createChooser(sharingIntent, "Share via"))
		}
		mTextMenuLanguage = mActivity.findViewById<View>(R.id.textMenuLanguage) as TextView
		mTextMenuLanguage!!.setOnClickListener {
			checkLanguageMenu()
			displayView(5)
		}
		mTextMenuBuild = mActivity.findViewById<View>(R.id.textMenuBuild) as TextView
		mTextMenuBuild!!.setOnClickListener {
			checkBuildMenu()
			displayView(6)
		}
		mtextMenuAbout = mActivity.findViewById<View>(R.id.textMenuAbout) as TextView
		mtextMenuAbout!!.setOnClickListener {
			checkAboutMenu()
			displayView(7)
		}
		mTextMenuChannel = mActivity.findViewById<View>(R.id.textMenuChannel) as TextView
		mTextMenuChannel!!.setOnClickListener {
			checkChannelMenu()
			displayView(8)
		}
		mtextMenuAbout!!.typeface = font
		mTextMenuHero!!.typeface = font
		mTextMenuItems!!.typeface = font
		mTextMenuNews!!.typeface = font
		mTextMenuInvite!!.typeface = font
		mTextMenuLanguage!!.typeface = font
		mTextMenuBuild!!.typeface = font
		mTextMenuChannel!!.typeface = font
		mPaymentButton = mActivity.findViewById<View>(R.id.content_drawer_buy_button) as Button
		mPaymentButton!!.setOnClickListener {
			//mActivity.startActivity(new Intent(mActivity, PaymentActivity.class));
		}
		defaultItemSelected()
	}

	override fun menu(isOpen: Boolean) {
		drawerLayout!!.openDrawer(drawerList!!)
	}

	companion object {
		const val MENU_HERO = 0
		const val MENU_ITEM = 1
		const val MENU_LANGUAGE = 2
		const val MENU_BUILD = 3
		const val MENU_NEWS = 4
	}

	init {
		initComponents()
	}
}

