package br.com.dotazone.di.component

import android.app.Application
import br.com.dotazone.UmbrellaApplication
import br.com.dotazone.di.builder.ActivityBuilder
import br.com.dotazone.di.module.AppModule
import br.com.dotazone.di.module.NetworkModudule
import br.com.dotazone.di.module.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class),
    (AppModule::class),
    (RepositoryModule::class),
    (NetworkModudule::class),
    (ActivityBuilder::class)])

interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: UmbrellaApplication)

}
