package br.com.dotazone.model.service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URL;

public class RequestREST {

    public String getHttpRequest(String url) throws ClientProtocolException, IOException {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);

        HttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity httpEntity = httpResponse.getEntity();
        String output = EntityUtils.toString(httpEntity);

        return output;

    }

    public Bitmap getImageInUrl(String urlParam) throws IOException {

        URL url = new URL(urlParam);
        Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());

        return image;
    }

    /**
     * Envia uma requisição post para o servidor...
     *
     * @param stringBody
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public HttpResponse postWS(String stringBody, String url) throws ClientProtocolException, IOException {

        HttpClient client = new DefaultHttpClient();
        HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000);
        HttpResponse response;

        HttpPost post = new HttpPost(url);

        post.setHeader("Accept", "application/json");
        post.setHeader("Content-type", "application/json");

        StringEntity entitty = new StringEntity(stringBody);
        entitty.setContentType(new BasicHeader("Content-type", "application/json"));

        post.setEntity(entitty);

        response = client.execute(post);

        return response;

    }
}
