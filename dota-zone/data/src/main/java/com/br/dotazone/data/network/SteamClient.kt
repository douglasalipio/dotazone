package com.br.dotazone.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

	fun createRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.client(okHttpClient)
			.build()
}
