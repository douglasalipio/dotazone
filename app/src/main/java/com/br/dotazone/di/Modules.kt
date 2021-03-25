package com.br.dotazone.di

import com.br.dotazone.data.model.HeroAbilityModelToHeroAbility
import com.br.dotazone.data.model.HeroModelToHeroMapper
import com.br.dotazone.data.model.HeroSkillModelToHeroSkillMapper
import com.br.dotazone.data.network.createOkHttpClient
import com.br.dotazone.data.network.provideHeroesDataService
import com.br.dotazone.data.repository.HeroRepositoryImp
import com.br.dotazone.data.service.ParseHeroService
import com.br.dotazone.data.service.ParseHeroServiceImp
import com.br.dotazone.domain.heroes.GetHeroDataInteractor
import com.br.dotazone.domain.heroes.HeroRepository
import com.br.dotazone.heroes.HeroesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mappersModule = module {
	single { HeroModelToHeroMapper() }
	single { HeroSkillModelToHeroSkillMapper() }
	single { HeroAbilityModelToHeroAbility() }
}

val appModule = module {
	viewModel { HeroesViewModel(get()) }
}

val interactorsModule = module {
	factory { GetHeroDataInteractor(get()) }
}

val repositoryModule = module {
	single<HeroRepository> { HeroRepositoryImp(get(), get(), get(),get(),get()) }
}

val serviceModules = module {
	single { provideHeroesDataService(createOkHttpClient()) }
	single<ParseHeroService> { ParseHeroServiceImp() }
}
