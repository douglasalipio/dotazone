package br.com.dotazone.model.service

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import org.apache.http.HttpResponse
import org.apache.http.client.ClientProtocolException
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.message.BasicHeader
import org.apache.http.params.HttpConnectionParams
import org.apache.http.util.EntityUtils
import java.io.IOException
import java.net.URL


class RequestREST {
	@Throws(ClientProtocolException::class, IOException::class)
	fun getHttpRequest(url: String?): String {
		val httpClient = DefaultHttpClient()
		val httpGet = HttpGet(url)
		val httpResponse = httpClient.execute(httpGet)
		val httpEntity = httpResponse.entity
		return EntityUtils.toString(httpEntity)
	}

	@Throws(IOException::class)
	fun getImageInUrl(urlParam: String?): Bitmap {
		val url = URL(urlParam)
		return BitmapFactory.decodeStream(url.openConnection().getInputStream())
	}

	/**
	 * Envia uma requisição post para o servidor...
	 *
	 * @param stringBody
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Throws(ClientProtocolException::class, IOException::class)
	fun postWS(stringBody: String?, url: String?): HttpResponse {
		val client: HttpClient = DefaultHttpClient()
		HttpConnectionParams.setConnectionTimeout(client.params, 10000)
		val response: HttpResponse
		val post = HttpPost(url)
		post.setHeader("Accept", "application/json")
		post.setHeader("Content-type", "application/json")
		val entitty = StringEntity(stringBody)
		entitty.contentType = BasicHeader("Content-type", "application/json")
		post.entity = entitty
		response = client.execute(post)
		return response
	}
}
