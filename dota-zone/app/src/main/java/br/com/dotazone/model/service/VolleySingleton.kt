package br.com.dotazone.model.service

import android.content.Context
import android.graphics.Bitmap
import androidx.collection.LruCache
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.Volley

class VolleySingleton private constructor(context: Context) {
	// Fila de execução
	val requestQueue: RequestQueue

	// Image Loader
	val imageLoader: ImageLoader

	companion object {
		private var mInstance: VolleySingleton? = null
		fun getInstance(
				context: Context): VolleySingleton? {
			if (mInstance == null) {
				mInstance = VolleySingleton(context)
			}
			return mInstance
		}
	}

	init {
		requestQueue = Volley.newRequestQueue(context)
		imageLoader = ImageLoader(requestQueue, object : ImageLoader.ImageCache {
			private val mCache = LruCache<String, Bitmap>(10)
			override fun putBitmap(url: String, bitmap: Bitmap) {
				mCache.put(url, bitmap)
			}

			override fun getBitmap(url: String): Bitmap {
				return mCache[url]!!
			}
		})
	}
}
