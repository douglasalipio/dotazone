package com.br.dotazone.di

import com.br.dotazone.data.model.HeroesDataModelToHeroesDataMapper
import com.br.dotazone.data.network.createOkHttpClient
import com.br.dotazone.data.network.provideHeroesDataService
import com.br.dotazone.data.repository.HeroesRepositoryImp
import com.br.dotazone.domain.heroes.GetHeroesDataInteractor
import com.br.dotazone.domain.heroes.HeroesRepository
import com.br.dotazone.heroes.HeroesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mappersModule = module {
	single { HeroesDataModelToHeroesDataMapper() }
}

val appModule = module {
	viewModel { HeroesViewModel(get()) }
}

val interactorsModule = module {
	factory { GetHeroesDataInteractor(get()) }
}

val repositoryModule = module {
	single<HeroesRepository> { HeroesRepositoryImp(get(), get()) }
}
val serviceModules = module {
	single { provideHeroesDataService(createOkHttpClient()) }
}
