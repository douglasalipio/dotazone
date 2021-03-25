//
object TestDependencies {
	val junit = "junit:junit:${Versions.junit}"
	val mockito = "org.mockito:mockito-core:${Versions.mockito}"
	val hamcrest = "org.hamcrest:hamcrest:${Versions.hamcrest}"
	val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"
	val mockitokotlin2 = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitokotlin2}"
}

object Modules {
	val domain = ":domain"
	val data = ":data"
}

object PluginDependencies {
	val gradle = "com.android.tools.build:gradle:${Versions.gradlePlugin}"
	val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
	val koin = "org.koin:koin-gradle-plugin:${Versions.koin}"
}

object ProjectDependencies {
	val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
	val volley = "com.mcxiaoke.volley:library-aar:${Versions.volley}"
	val rssParser = "com.prof18.rssparser:rssparser:${Versions.rssParser}"
	val circleImage = "de.hdodenhof:circleimageview:${Versions.circleImage}"
	val couchbase = "com.couchbase.lite:couchbase-lite-android:${Versions.couchbase}"
	val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
	val interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
	val timber = "com.jakewharton.timber:timber:${Versions.timber}"
	val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"

}

object Retrofit {
	val core = "com.squareup.retrofit2:retrofit:${Versions.retrofitCore}"
	val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitConverterGson}"
	val scalars = "com.squareup.retrofit2:converter-scalars:${Versions.retrofitConverterScalars}"
}

object Google {
	val gson = "com.google.code.gson:gson:${Versions.gson}"
	val playService = "com.google.android.gms:play-services:${Versions.playService}"
	val material = "com.google.android.material:material:${Versions.material}"
}

object Koin {
	val android = "org.koin:koin-android:${Versions.koin}"
	val scope = "org.koin:koin-androidx-scope:${Versions.koin}"
	val viewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
	val fragment = "org.koin:koin-androidx-fragment:${Versions.koin}"
	val ext = "org.koin:koin-androidx-ext:${Versions.koin}"
}

object Coroutines {
	val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
	val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
}

object Androidx {
	val appCompact = "androidx.appcompat:appcompat:${Versions.appCompact}"
	val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.legacySupport}"
	val mediaRouter = "androidx.mediarouter:mediarouter:${Versions.mediaRouter}"
	val viewPager = "androidx.viewpager:viewpager:${Versions.viewPager}"
	val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
	val androidxCoreKtx = "androidx.core:core-ktx:${Versions.androidxCore}"
}
