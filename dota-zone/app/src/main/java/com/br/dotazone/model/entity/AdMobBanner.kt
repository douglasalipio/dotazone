package com.br.dotazone.model.entity

import android.content.Context
import android.telephony.TelephonyManager
import android.view.View
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import javax.swing.text.View


class AdMobBanner {
	fun createBanner(context: Context, adView: AdView, isPremium: Boolean): AdView {
		return createBannerProduction(context, adView, isPremium)
	}

	private fun createBannerProduction(context: Context, adView: AdView, isPremium: Boolean): AdView {
		if (isPremium) {
			adView.visibility = View.GONE
		} else {
			val adRequest = AdRequest.Builder().build()
			adView.loadAd(adRequest)
		}
		return adView
	}

	private fun createBannerTests(context: Context, adView: AdView, isPremium: Boolean): AdView {
		if (isPremium) {
			adView.visibility = View.GONE
		} else {
			val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
			val adRequest = AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).addTestDevice(telephonyManager.deviceId)
					.build()
			adView.loadAd(adRequest)
		}
		return adView
	}

	companion object {
		private const val MY_AD_UNIT_ID = "5296490904174793"
	}
}
