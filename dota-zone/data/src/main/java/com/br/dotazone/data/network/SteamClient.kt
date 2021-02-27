package com.br.dotazone.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class SteamClient {

	companion object{
		private const val BASE_URL = "http://www.dota2.com/"
	}
	fun createOkHttpClient(): OkHttpClient {
		val httpLoggingInterceptor = HttpLoggingInterceptor()
		val clientBuilder = OkHttpClient.Builder()

		httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
		httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

		return clientBuilder.build()
	}

	fun provideHeroesDataService(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addConverterFactory(ScalarsConverterFactory.create())
			.client(okHttpClient)
			.build()
}
