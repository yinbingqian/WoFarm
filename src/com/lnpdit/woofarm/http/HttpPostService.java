package com.lnpdit.woofarm.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONObject;

import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.util.Log;

public class HttpPostService {

    /**
     * 
     * @param method
     *            SOAP_UTILS
     * @param property_nm
     * @param property_va
     * @return
     */
    public static Object data(String method, String[] property_nm,
            Object[] property_va) {
        try {
            String url = SOAP_UTILS.URL + method;

            // HttpClient httpClient = HttpsClient.getNewHttpClient();
            HttpClient httpClient = new DefaultHttpClient();
            HttpContext localContext = new BasicHttpContext();
            HttpPost httpPost = new HttpPost(url);

            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("User-Agent", "imgfornote");

            JSONObject jsonObject = new JSONObject();
            for (int i = 0; i < property_va.length; i++) {
                jsonObject.put(property_nm[i], property_va[i]);
            }
            httpPost.setEntity(
                    new StringEntity(jsonObject.toString(), "UTF-8"));

            HttpResponse response = httpClient.execute(httpPost, localContext);
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent(), "UTF-8"));
            String sResponse = reader.readLine();

            return sResponse;
        } catch (Exception e) {
            Log.v("ImgPostService", "Some error came up");
            return null;

        }
    }
}
