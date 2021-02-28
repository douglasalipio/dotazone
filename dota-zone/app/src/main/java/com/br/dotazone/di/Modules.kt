package com.br.dotazone.di

import com.br.dotazone.data.model.HeroesDataModelToHeroesDataMapper
import com.br.dotazone.data.network.createOkHttpClient
import com.br.dotazone.data.network.provideHeroesDataService
import com.br.dotazone.data.repository.HeroesDataRepositoryImp
import org.koin.dsl.module

val mappersModule = module {
	single { HeroesDataModelToHeroesDataMapper() }
}
val interactorsModule = module {
	factory { HeroesDataRepositoryImp(get(), get()) }
}
val serviceModules = module {
	single { provideHeroesDataService(createOkHttpClient()) }
}
