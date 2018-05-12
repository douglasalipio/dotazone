package br.com.dotazone.di.builder

import br.com.dotazone.ui.main.MainActivity
import br.com.dotazone.ui.standard.StandardProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(StandardProvider::class)])
    abstract fun bindFeedActivity(): MainActivity
}

