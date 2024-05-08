package usrService;


import java.io.DataOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ValidatePhoneService {
	private static String makeSignature(String method, String url, String timestamp, String accessKey, String secretKey)
			throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
		String space = " "; // one space
		String newLine = "\n"; // new line

		String message = new StringBuilder().append(method).append(space).append(url).append(newLine).append(timestamp)
				.append(newLine).append(accessKey).toString();

		SecretKeySpec signingKey;
		String encodeBase64String;
		try {
			signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(signingKey);
			byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
			encodeBase64String = Base64.getEncoder().encodeToString(rawHmac);
		} catch (Exception e) {
			encodeBase64String = e.toString();
		}

		return encodeBase64String;
	}

	@SuppressWarnings("unchecked")
	public static int sendSms(String dstPhoneNumber) {
		System.out.println("sendSms() 호출");

		String method = "POST"; // method
		String smsUrl = "https://sens.apigw.ntruss.com"; // url (include query string)
		String requestUrl = "/sms/v2/services/";
		String requestUrlType = "/messages";
		String accessKey = "C9w2lMo9VZfhwWWa86zo"; // access key id (from portal or Sub Account)
		String secretKey = "QRb2Zkg06XskzfD1WrZvyZ3gZ42G7wo89kL2iUXF";
		String serviceId = "ncp:sms:kr:309212737894:rankmarket";
		String timestamp = Long.toString(System.currentTimeMillis()); // current timestamp (epoch)

		requestUrl += serviceId + requestUrlType;
		String apiUrl = smsUrl + requestUrl;

		JSONObject bodyJson = new JSONObject();
		JSONObject toJson = new JSONObject();
		JSONArray toArr = new JSONArray();

		toJson.put("to", dstPhoneNumber);
		toArr.add(toJson);

		bodyJson.put("type", "SMS");
		bodyJson.put("contentType", "COMM");
		bodyJson.put("countryCode", "82");
		bodyJson.put("from", "01064698562");
		bodyJson.put("subject", "쇼핑몰");

		// 난수 생성
		int min = 1000;
		int max = 9999;
		int validateNum = (int) (Math.random() * (max - min + 1)) + min;
		bodyJson.put("content", "쇼핑몰 인증번호 : " + Integer.toString(validateNum));

		bodyJson.put("messages", toArr);

		String body = bodyJson.toString();


		try {
			URL url = new URL(apiUrl);

			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setUseCaches(false);
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("x-ncp-apigw-timestamp", timestamp);
			con.setRequestProperty("x-ncp-iam-access-key", accessKey);
			con.setRequestProperty("x-ncp-apigw-signature-v2",
					makeSignature(method, requestUrl, timestamp, accessKey, secretKey));
			con.setRequestMethod(method);
			con.setDoOutput(true);
			DataOutputStream dos = new DataOutputStream(con.getOutputStream());
			

			dos.write(body.getBytes());
			dos.flush();		
			dos.close();
			System.out.println("responseCode : " + con.getResponseCode());


		} catch (Exception e) {
			System.out.println();
			return 0;
			
		}

		return validateNum;
	}
}
