package com.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.io.IOException;
import java.security.MessageDigest;

/**
 * Created by yzy on 2018/8/2.
 * email: mia5121@163.com
 */
public class SignUtil {
	private static Logger logger = Logger.getLogger(SignUtil.class);


	public static String sha256(byte[] data) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			return bytesToHex(md.digest(data));
		} catch (Exception ex) {
			logger.info("Never happen.", ex);
			return null;
		}
	}

	/**
	 * 将byte数组转换成16进制字符串
	 *
	 * @param bytes
	 * @return 16进制字符串
	 */
	public static String bytesToHex(byte[] bytes) {
		String hexArray = "0123456789abcdef";
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		for (byte b : bytes) {
			int bi = b & 0xff;
			sb.append(hexArray.charAt(bi >> 4));
			sb.append(hexArray.charAt(bi & 0xf));
		}
		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		long date = System.currentTimeMillis();
		System.out.println(date);
		String dat = String.valueOf(date);
		String waitForSign = "appId=a348810e79ac415e9ab34a566a035a87&nonceStr=3Dij68448r97T249&secret=355b0297bcc147eab9fbdfc2e54ccecc&timestamp="+ dat;
		String s1 =          "appId=a348810e79ac415e9ab34a566a035a87&nonceStr=3Dij68448r97T249&secret=355b0297bcc147eab9fbdfc2e54ccecc&timestamp=1533212804698";
//		System.out.println(sha256(waitForSign.getBytes()));
		String sign = sha256(waitForSign.getBytes());

		 HttpPost httpPost = new HttpPost("https://open.95516.com/open/access/1.0/frontToken");
	     CloseableHttpClient client = HttpClients.createDefault();
	     String respContent = null;

		//json方式
	     JSONObject jsonParam = new JSONObject();
		 jsonParam.put("appId","a348810e79ac415e9ab34a566a035a87");
//		                        fd6505537cfd4efd84414c38e8072ef2
		 jsonParam.put("secret","355b0297bcc147eab9fbdfc2e54ccecc");
//		                         a8dacc80f5de4fbd91d58e7a0eb0bb8e
		 jsonParam.put("nonceStr","3Dij68448r97T249");
		 jsonParam.put("timestamp",dat);
		 jsonParam.put("signature",sign);
	     StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");//解决中文乱码问题
	     entity.setContentEncoding("UTF-8");
	     entity.setContentType("application/json");
	     httpPost.setEntity(entity);

//	     System.out.println(entity);

		HttpResponse resp = client.execute(httpPost);
		if(resp.getStatusLine().getStatusCode() == 200) {
			HttpEntity he = resp.getEntity();
			respContent = EntityUtils.toString(he,"UTF-8");
		}
//		return respContent;
//		String post = client4.post("https://open.95516.com/open/access/1.0/frontToken", );
		System.out.println(respContent);

		//a25ccdaa93e79b6095931f5fd9330075b91a2c5a889b63f164f9e88f873ae3f7
		//a25ccdaa93e79b6095931f5fd9330075b91a2c5a889b63f164f9e88f873ae3f7

	}

}
