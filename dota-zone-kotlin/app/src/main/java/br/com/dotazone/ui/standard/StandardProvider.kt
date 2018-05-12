package br.com.dotazone.ui.standard

import br.com.dotazone.ui.standard.view.StandardFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.secfirst.dotazone.ui.standard.StandardModule


@Module
internal abstract class StandardProvider {

    @ContributesAndroidInjector(modules = [StandardModule::class])
    internal abstract fun provideStandardFactory(): StandardFragment
}
