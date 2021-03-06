package com.br.dotazone.data.network

import com.br.dotazone.data.service.HeroesService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val BASE_URL = "http://www.dota2.com/"

fun createOkHttpClient(): OkHttpClient {
	val httpLoggingInterceptor = HttpLoggingInterceptor()
	val clientBuilder = OkHttpClient.Builder()
	httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
	//httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
	clientBuilder.addInterceptor(httpLoggingInterceptor)
	return clientBuilder.build()
}

fun provideHeroesDataService(okHttpClient: OkHttpClient): HeroesService = Retrofit.Builder()
		.baseUrl(BASE_URL)
		.addConverterFactory(ScalarsConverterFactory.create())
		.client(okHttpClient)
		.build()
		.create(HeroesService::class.java)
