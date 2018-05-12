package br.com.dotazone.di.module

import android.app.Application
import android.content.Context
import br.com.dotazone.util.SchedulerProvider
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import br.com.dotazone.data.local.standard.StandardDao
import br.com.dotazone.data.local.standard.StandardRepo
import br.com.dotazone.data.local.standard.StandardRepository
import br.com.dotazone.data.network.ApiHelper
import br.com.dotazone.data.network.NetworkEndPoint.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProvider()

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

}

@Module
class RepositoryModule {

    internal val standardDao
        get() = object : StandardDao {}

    @Provides
    @Singleton
    internal fun provideApiStandardRepo(): StandardRepo = StandardRepository(standardDao)

}

@Module
class NetworkModudule {

    @Provides
    @Reusable
    internal fun providePostApi(retrofit: Retrofit): ApiHelper {
        return retrofit.create(ApiHelper::class.java)
    }

    @Provides
    @Reusable
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }

}
