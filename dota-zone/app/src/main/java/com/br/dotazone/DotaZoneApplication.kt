package com.br.dotazone

import android.app.Application
import com.br.dotazone.di.*
import com.couchbase.lite.CouchbaseLite
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import java.util.*


class DotaZoneApplication : Application() {

	override fun onCreate() {
		super.onCreate()

		initKoin()
		initCouchbaseDb()
	}

	private fun initKoin() {
		startKoin {
			androidLogger(Level.ERROR)
			androidContext(this@DotaZoneApplication)
			modules(mappersModule, interactorsModule, serviceModules, appModule, repositoryModule)
		}
	}

	private fun initCouchbaseDb() {
		CouchbaseLite.init(this);
	}
}

