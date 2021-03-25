package com.br.dotazone.model.util

import android.content.Context
import android.content.res.Resources
import com.br.dotazone.view.activity.LanguageActivity
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class UrlUtils {

	companion object {

		const val URL_IMAGE_ITEM = "http://media.steampowered.com/apps/dota2/images/items/"
		const val PREFS_NAME = "DotaZone"

		fun getUrlItem(context: Context) = "http://www.dota2.com/jsfeed/itemdata/?l=" + getLanguage(context)

		fun getUrlHero(context: Context) = "http://www.dota2.com/jsfeed/heropickerdata/?l=" + getLanguage(context)

		fun getUrlSkill(context: Context) = "http://www.dota2.com/jsfeed/abilitydata?l=" + getLanguage(context)

		fun getUrlAbility(context: Context) = "http://www.dota2.com/jsfeed/heropediadata?feeds=herodata&l=" + getLanguage(context)

		fun convertDpToPixel(dp: Float, res: Resources) = (res.displayMetrics.density * dp + 0.5f).toInt()

		fun extractNumber(string: String) = string.replace("\\D+".toRegex(), "")

		fun convertStreamToString(inputStream: InputStream): String {
			val reader = BufferedReader(InputStreamReader(inputStream))
			val sb = StringBuilder()
			var line = ""
			while (reader.readLine().also { line = it } != null) {
				sb.append(line + "\n")
			}

			return sb.toString()
		}

		private fun getLanguage(context: Context): String {
			val settings = context.getSharedPreferences(PREFS_NAME, 0)
			val isLanguageSelected = settings.getString(LanguageActivity.LANGUAGE_KEY, "english")
			return isLanguageSelected?.toLowerCase() ?: ""
		}
	}

}
