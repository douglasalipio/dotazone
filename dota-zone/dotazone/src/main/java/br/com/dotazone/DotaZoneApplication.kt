package br.com.dotazone

import android.app.Application
import com.google.android.gms.analytics.GoogleAnalytics
import com.google.android.gms.analytics.Tracker
import java.util.*


class DotaZoneApplication : Application() {
	var mTrackers = HashMap<TrackerName, Tracker>()

	fun getTracker(trackerId: TrackerName): Tracker? {
		if (!mTrackers.containsKey(trackerId)) {
			val analytics = GoogleAnalytics.getInstance(this)
			val t = when (trackerId) {
				TrackerName.APP_TRACKER -> analytics.newTracker(R.xml.app_tracker)
				TrackerName.GLOBAL_TRACKER -> analytics.newTracker(PROPERTY_ID)
				else -> analytics
						.newTracker(R.xml.ecommerce_tracker)
			}
			mTrackers[trackerId] = t
			println()
		}
		return mTrackers[trackerId]
	}

	enum class TrackerName {
		APP_TRACKER,  // Tracker used only in this app.
		GLOBAL_TRACKER,  // Tracker used by all the apps from a company. eg:

		// roll-up tracking.
		ECOMMERCE_TRACKER
		// Tracker used by all ecommerce transactions from a
		// company.
	}

	companion object {
		// The following line should be changed to include the correct property id.
		private const val PROPERTY_ID = "UA-52567344-1"

		// Logging TAG
		//private static final String TAG = DotaZoneBrain.TAG;
		var GENERAL_TRACKER = 0
	}
}

