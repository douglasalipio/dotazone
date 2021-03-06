package com.br.dotazone.di

import com.br.dotazone.data.model.HeroModelToHeroMapper
import com.br.dotazone.data.model.HeroesDataModelToHeroesDataMapper
import com.br.dotazone.data.network.createOkHttpClient
import com.br.dotazone.data.network.provideHeroesDataService
import com.br.dotazone.data.repository.HeroesRepositoryImp
import com.br.dotazone.data.service.HeroesService
import com.br.dotazone.data.service.ParseHeroesService
import com.br.dotazone.data.service.ParseHeroesServiceImp
import com.br.dotazone.domain.heroes.GetHeroesDataInteractor
import com.br.dotazone.domain.heroes.HeroesRepository
import com.br.dotazone.domain.heroes.ParseHeroesDataInteractor
import com.br.dotazone.heroes.HeroesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mappersModule = module {
	single { HeroesDataModelToHeroesDataMapper() }
	single { HeroModelToHeroMapper() }
}

val appModule = module {
	viewModel { HeroesViewModel(get(), get()) }
}

val interactorsModule = module {
	factory { GetHeroesDataInteractor(get()) }
	factory { ParseHeroesDataInteractor(get()) }
}

val repositoryModule = module {
	single<HeroesRepository> { HeroesRepositoryImp(get(), get(), get(), get()) }
}
val serviceModules = module {
	single { provideHeroesDataService(createOkHttpClient()) }
	single<ParseHeroesService> { ParseHeroesServiceImp() }
}
