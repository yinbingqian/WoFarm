package com.lnpdit.woofarm.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.util.Log;

public class ImgPostService {

	/**
	 * 上传头像
	 * @param method
	 *            SOAP_UTILS
	 * @param property_nm
	 * @param property_va
	 * @return
	 */
	public static Object data(String method, String[] property_nm, Object[] property_va, byte[] data,String memberid) {
		try {
			String url = SOAP_UTILS.IP + SOAP_UTILS.METHOD.UPLOADFILE + "?memberid=" + memberid;
			// for (int i = 0; i < property_va.length; i++) {
			// url = url + "/" + property_va[i];
			// }
			HttpClient httpClient = new DefaultHttpClient();
			HttpContext localContext = new BasicHttpContext();

			HttpPost httpPost = new HttpPost(url);
			ByteArrayEntity arrayEntity = new ByteArrayEntity(data);
			arrayEntity.setContentType("application/octet-stream");
			httpPost.setEntity(arrayEntity);

			HttpResponse response = httpClient.execute(httpPost, localContext);
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
			String sResponse = reader.readLine();

			return sResponse;
		} catch (Exception e) {
			Log.v("ImgPostService", "Some error came up");
			return null;

		}
	}
}
