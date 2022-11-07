package com.dcu.wfd.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * HTTP Request 관련 유틸
 * @author apple
 * @since 2022.11.04
 * **/
public class HttpUtil {
	
	/**
	 * 파라메터 데이터를 HTTP통신을 위한 문자열로 변환시켜주는 메서드
	 * 
	 * @author 최정우
	 * @since 2020.07.21
	 * @return URL QueryString
	 */

	// 데이터 보낼때 url 뒤에 ?id=test&pw=1234 처럼 get 방식으로 만들어줌  , map 객체에 id: test, pw: 1234
	public String createUrlQuery (Map<String, Object> params, String encoding) {

		if (params == null || params.isEmpty() == true) {

			return "";

		} else {

			StringBuilder query = new StringBuilder();

			for(Map.Entry<String,Object> param : params.entrySet()) {

				try {

					if(query.length() > 0) {
						query.append('&');

					}
					if (encoding == null) {
						
						query.append(param.getKey());
						query.append('=');
						query.append(param.getValue());
						
					} else {
						
						query.append(URLEncoder.encode(param.getKey(), encoding));
						query.append('=');
						query.append(URLEncoder.encode(String.valueOf(param.getValue()), encoding));
						
					}

				} catch (Exception e) {
					
					e.printStackTrace();
					
				}
			}

			return query.toString();
			
		}
		
	}

	public String httpRequest(String requestUrl, Map<String, Object> params) {

		//		String responseText = null;
		StringBuilder responseText = new StringBuilder();

		String queryString = createUrlQuery(params, "UTF-8");

		if(queryString.isEmpty() == false) { // queryString이 비어있지 않으면 
			requestUrl += "?";
			requestUrl += queryString;
		}

		try {
			//URL을 통한 통신 객체 생성 
			URL url = new URL(requestUrl);

			//URL객체를 활용하여 HTTP Request 연결 통신 객체 생성 
			HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();


			httpURLConnection.setRequestMethod("GET");

			// http가 끊어 지도록 하는 요청시간  (10초 대기후 연결종료)
			httpURLConnection.setConnectTimeout(1000*10);

			// 읽어들이는 시간
			httpURLConnection.setReadTimeout(1000*5);

			// HTTP Request 실행 
			httpURLConnection.connect();

			// error처리 404 500 등 
			int responseCode = httpURLConnection.getResponseCode();

			if(responseCode == 200) { // 요청이 정상적으로 왔을 때 
				
				InputStream inputStream = httpURLConnection.getInputStream();

				InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");

				BufferedReader bufferReader = new BufferedReader(inputStreamReader);

				// 버퍼리더를통해 한줄한줄씩 읽고 리턴해준다  readLine이 알아서 +1 씩 읽어준다 
				String readLine = "";

				while((readLine = bufferReader.readLine()) != null) {

					responseText.append(readLine);
					//					System.out.println(readLine);
				}

			} else { // 404, 500과같은 에러일 때 

				System.out.println("requestUrl: " + requestUrl);
				System.out.println("responseCode :" + responseCode + "connection Error");

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return responseText.toString();
	}

}
