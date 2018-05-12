package br.com.dotazone

import android.app.Activity
import android.app.Application
import br.com.dotazone.data.local.AppDatabase
import br.com.dotazone.data.local.SQLCipherHelperImpl
import br.com.dotazone.di.component.DaggerAppComponent
import com.raizlabs.android.dbflow.config.DatabaseConfig
import com.raizlabs.android.dbflow.config.FlowConfig
import com.raizlabs.android.dbflow.config.FlowLog
import com.raizlabs.android.dbflow.config.FlowManager
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import net.sqlcipher.database.SQLiteDatabase
import javax.inject.Inject


class UmbrellaApplication : Application(), HasActivityInjector {

    @Inject
    internal lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>


    override fun activityInjector() = activityDispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()

        initDaggerComponent()
        initDatabase()
    }

    private fun initDaggerComponent() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }

    private fun initDatabase() {
        SQLiteDatabase.loadLibs(this)
        val dbConfig = FlowConfig.Builder(this)
                .addDatabaseConfig(DatabaseConfig
                        .Builder(AppDatabase::class.java)
                        .databaseName(AppDatabase.NAME)
                        .openHelper { databaseDefinition, helperListener ->
                            SQLCipherHelperImpl(databaseDefinition, helperListener)
                        }
                        .build())
                .build()
        FlowManager.init(dbConfig)
        FlowLog.setMinimumLoggingLevel(FlowLog.Level.V)
    }

    override fun onTerminate() {
        super.onTerminate()
        FlowManager.destroy()
    }
}
