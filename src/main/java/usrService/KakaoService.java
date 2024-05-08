package usrService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import config.Const;
import usrBean.UsrBean;

public class KakaoService {

	public static String getUserToken(String code) {

		String access_Token = "";
		String refresh_Token = "";
		String user_id = "";
		UsrBean usr = null;
		String reqURL = "https://kauth.kakao.com/oauth/token";

		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=" + Const.K_REST_KEY); // TODO REST_API_KEY 입력
			sb.append("&redirect_uri=http://localhost:9123/usr/kakaoLogin"); // TODO 인가코드 받은 redirect_uri 입력
			sb.append("&code=" + code);
			bw.write(sb.toString());
			bw.flush();

			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();

			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = br.readLine();

			System.out.println(line);

			// Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
			JsonObject usrinfo = (JsonObject) JsonParser.parseString(line);
			access_Token = usrinfo.getAsJsonObject().get("access_token").getAsString();

			br.close();
			bw.close();


		} catch (IOException e) {
			e.printStackTrace();
		}

		return access_Token;
	}

	public static UsrBean getUserInfo(String token) {
		UsrBean usr = new UsrBean();

		String reqURL = "https://kapi.kakao.com/v2/user/me";

		// access_token을 이용하여 사용자 정보 조회
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setRequestProperty("Authorization", "Bearer " + token); // 전송할 header 작성, access_token전송

			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = br.readLine();

			System.out.println(line);

			// Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
			JsonObject usrinfo = (JsonObject) JsonParser.parseString(line);

			String email = usrinfo.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
			String name = usrinfo.getAsJsonObject().get("properties").getAsJsonObject().get("nickname").getAsString();
			usr.setEmail(email);
			usr.setName(name);
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return usr;
	}

	public static void kakaoLogout(String token) {
		System.out.println(token);
		String reqURL = "https://kapi.kakao.com/v1/user/logout";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Authorization", "Bearer " + token);

			int responseCode = conn.getResponseCode();
			System.out.println(responseCode);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
